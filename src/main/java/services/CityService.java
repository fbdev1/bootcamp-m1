package services;

import models.City;

import java.util.List;

public interface CityService {

    List<City> sortByName(List<City> list);
    List<City> sortByDistrictAndName(List<City> list);
    String listOfCitiesToArray(List<City> list);
    List<String> numberOfCitiesInRegion(List<City> list);

}
