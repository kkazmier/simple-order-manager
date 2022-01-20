package com.company.restaurant.service;

import com.company.restaurant.domain.Address;
import com.company.restaurant.domain.GeoResponse;
import com.company.restaurant.repository.AddressRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{
    private final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
    private final AddressRepository addressRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Autowired
    private Environment env;

    public Address save(Address address) {
        logger.info("Address saved.");
        return addressRepository.save(address);
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    public void setGeographicalCoords(Long id) throws Exception {
        final String locationIqBasePath = env.getProperty("locationiq.basepath");
        final String locationIqKey = env.getProperty("locationiq.key");
        Address address = findById(id).orElseThrow(Exception::new);
        logger.info(address.toString());
        String url = locationIqBasePath + "search.php?key=" + locationIqKey + "&q="
                + address.getStreet() + "," + address.getBuildingNumber() + "," + address.getTown() + "&format=json";
        logger.info(url);
//        final WebClient webClient = WebClient.create(url);
//        Mono<JSONObject> geoResponse = webClient.get().accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(JSONObject.class);
        String locationResponse = restTemplate.getForObject(url, String.class);
        GeoResponse[] geoResponses = mapper.readValue(locationResponse, GeoResponse[].class);
        logger.info(geoResponses[0].toString());
        address.setLongitude(geoResponses[0].getLon());
        address.setLatitude(geoResponses[0].getLat());
        save(address);
        logger.info(findById(id).orElseThrow(Exception::new).toString());
    }

    @Override
    public Address setGeographicalCoords(Address address) throws JsonProcessingException {
        String url = "https://eu1.locationiq.com/v1/search.php?key=pk.c5f4b42b05bfdb2f033d3be0590472cd&q="
                + address.getStreet() + "," + address.getBuildingNumber() + "," + address.getTown() + "&format=json";
        RestTemplate restTemplate = new RestTemplate();
        String locationResponse = restTemplate.getForObject(url, String.class);
        GeoResponse[] geoResponses = mapper.readValue(locationResponse, GeoResponse[].class);
        logger.info(geoResponses[0].toString());
        address.setLongitude(geoResponses[0].getLon());
        address.setLatitude(geoResponses[0].getLat());
        logger.info("Set coords: " + geoResponses[0].getLon() + ", " + geoResponses[0].getLat());
        return address;
    }
}
