package services;

import models.City;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;
import java.util.stream.Collectors;

public class СityServiceImp implements CityService {

    public List<City> sortByName(List<City> list) {
        List<City> cities = new ArrayList<>(list);
        cities.sort(Comparator.comparing(City::getName, String::compareToIgnoreCase));
        return cities;

    }

    public List<City> sortByDistrictAndName(List<City> list) {
        List<City> cities = new ArrayList<>(list);
        cities.sort(Comparator.comparing(City::getDistrict, String::compareToIgnoreCase)
                .thenComparing(City::getName, String::compareToIgnoreCase));
        return cities;

    }

    public String listOfCitiesToArray(List<City> list) {
        City[] arrayOfCities = list.toArray(City[]::new);
        City cityWithMaxPopulation = arrayOfCities[0];
        for (int i = 0; i < arrayOfCities.length - 1; i++) {
            if (cityWithMaxPopulation.getPopulation() < arrayOfCities[i + 1].getPopulation()) {
                cityWithMaxPopulation = arrayOfCities[i + 1];
            }
        }
        return "[" + cityWithMaxPopulation.getId() + "]" + " = " + cityWithMaxPopulation.getPopulation();
    }

    public List<String> numberOfCitiesInRegion(List<City> list) {
        List<String> listOfCities = new ArrayList<>();
        Map<String, List<City>> regions = list.stream().collect(Collectors.groupingBy(City::getRegion));
        for (Map.Entry city : regions.entrySet()) {
            List<City> cities = (List) city.getValue();
            listOfCities.add(city.getKey() + ": " + cities.size());
        }
        return listOfCities;
    }


}

