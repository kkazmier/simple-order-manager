package com.company.restaurant.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoResponse {
    private String lat;
    private String lon;

    @Override
    public String toString() {
        return "GeoResponse{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
