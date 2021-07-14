package com.company.restaurant.controller;

import com.company.restaurant.domain.Address;
import com.company.restaurant.service.AddressServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("streetMap")
public class StreetMapController {
    private final Logger logger = LoggerFactory.getLogger(StreetMapController.class);
    private final AddressServiceImpl addressService;

    @ModelAttribute("allAddressesArray")
    public Address[] getAddressesArray() throws Exception {
        List<Address> addresses = addressService.findAll();
        Address[] addressesArray = new Address[1];
        addressesArray[0] = addresses.get(0);
        addressService.setGeographicalCoords(addresses.get(0).getId());
        logger.info(addressesArray[0].toString());
//        Address[] addressesArray = new Address[addresses.size()];
//        addresses.stream().forEach(address -> {
//            try {
//                addressService.setGeographicalCoords(address.getId());
//                logger.info(address.toString());
//                for(int i=0; i<addresses.size(); i++){
//                    addressesArray[i] = addresses.get(i);
//
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
        return addressesArray;
    }

    @GetMapping("map")
    public String showMap(){
        return "streetMap.html";
    }



}
