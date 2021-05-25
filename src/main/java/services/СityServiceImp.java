package services;

import models.City;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class СityServiceImp implements CityService{

    public List<City> sortByCapital(List<City> list) {
        return list.stream()
                .sorted(Comparator.comparing(City::getName, String::compareToIgnoreCase))
                .collect(Collectors.toList());
    }

    public List<City> sortByDistrictAndName(List<City> list) {
        return list.stream()
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.groupingBy(City::getDistrict))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public String listOfCitiesToArray(List<City> list) {
        City[] arrayOfCities = list.stream().toArray(City[]::new);
        City cityWithMaxPopulation = arrayOfCities[0];
        int index = 0;
        for (int i = 0; i < arrayOfCities.length - 1; i++) {
            if (cityWithMaxPopulation.getPopulation() < arrayOfCities[i + 1].getPopulation()) {
                cityWithMaxPopulation = arrayOfCities[index = i + 1];

            }
        }
        return "[" + cityWithMaxPopulation.getId() + "]" + " = " + cityWithMaxPopulation.getPopulation();
    }

    public void numberOfCitiesInRegion(List<City> list) {
        Map<String, List<City>> regions = list.stream().collect(Collectors.groupingBy(City::getRegion));
        for (Map.Entry city : regions.entrySet()) {
            List<City> cities = (List) city.getValue();
            System.out.println(city.getKey() + ": " + cities.size());
        }
    }




}

