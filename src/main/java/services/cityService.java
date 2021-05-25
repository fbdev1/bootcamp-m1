package services;

import models.City;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class cityService {

    public static List<City> sortByCapital(List<City> list) {
        return list.stream()
                .sorted(Comparator.comparing(City::getName, String::compareToIgnoreCase))
                .collect(Collectors.toList());
    }

    public static List<City> sortByDistrictAndName(List<City> list) {
        return list.stream()
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.groupingBy(City::getDistrict))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static String listOfCitiesToArray(List<City> list) {
        City[] arrayOfCities = list.stream().toArray(City[]::new);
        City cityWithMaxPopulation = arrayOfCities[0];
        int index = 0;
        for (int i = 0; i < arrayOfCities.length - 1; i++) {
            if (cityWithMaxPopulation.getPopulation() < arrayOfCities[i + 1].getPopulation()) {
                cityWithMaxPopulation = arrayOfCities[index = i + 1];

            }
        }
        return "[" + index + "]" + " = " + cityWithMaxPopulation.getPopulation();
    }

    public static void numberOfCitiesInRegion(List<City> list) {
        Map<String, List<City>> map = list.stream().collect(Collectors.groupingBy(City::getRegion));
        int numberOfCity = 0;

        for (Map.Entry city : map.entrySet()) {
            List<City> ar = (List) city.getValue();
            System.out.println(city.getKey() + ": " + ar.size());
        }
    }

    public static void message() {
        System.out.println("Выберите вариант меню: \n" +
                "1 - Вывести исходный список: \n" +
                "2 - Сортировка по городу \n" +
                "3 - Сортировка по округу \n" +
                "4 - Сортировка по населению \n" +
                "5 - Количество городов по регионам \n" +
                "q - Выход");
    }


}

