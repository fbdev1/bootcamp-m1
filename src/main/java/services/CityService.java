package services;

import models.City;

import java.util.List;

public interface CityService {

    List<City> sortByCapital(List<City> list);
    List<City> sortByDistrictAndName(List<City> list);
    String listOfCitiesToArray(List<City> list);
    void numberOfCitiesInRegion(List<City> list);
    void message();
}
