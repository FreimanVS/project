package com.freimanvs.company.soapjaxws.geolocation;

import com.freimanvs.company.soapjaxws.geolocation.fromwsdl.GeoIPService;
import com.freimanvs.company.soapjaxws.geolocation.fromwsdl.GeoIPServiceSoap;

public class GeoClient {
    public static void main(String[] args) {
        GeoIPService geoIPService = new GeoIPService();
        GeoIPServiceSoap geoIPServiceSoap = geoIPService.getGeoIPServiceSoap();
        System.out.println(geoIPServiceSoap.getGeoIPContext().getCountryName());
    }
}
