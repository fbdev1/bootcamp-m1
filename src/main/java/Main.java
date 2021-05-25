import models.City;
import services.CityService;
import services.СityServiceImp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static services.СityServiceImp.*;

public class Main {
    public static void message() {
        System.out.println("Выберите вариант меню: \n" +
                "1 - Вывести исходный список: \n" +
                "2 - Сортировка по городу \n" +
                "3 - Сортировка по округу \n" +
                "4 - Вывести самый большой город (население) \n" +
                "5 - Количество городов по регионам \n" +
                "q - Выход");
    }
    public static void main(String[] args) throws FileNotFoundException {
        CityService cityService = new СityServiceImp();
        List<City> list = new ArrayList<>();
        File file = new File("src/main/resources/listOfCities.txt");
        try (Scanner sc = new Scanner(file).useDelimiter(";")) {
            while (sc.hasNext()) {
                list.add(new City(Integer.parseInt(sc.next().trim()),sc.next().trim(), sc.next().trim(), sc.next().trim(), Integer.parseInt(sc.next()), sc.next().trim()));
            }
        } catch (IOException e) {
            e.getMessage();
        }

        Scanner readerScanner = new Scanner(System.in);
        message();

        while (!readerScanner.hasNext("q")) {
            switch (readerScanner.next()) {
                case "1":
                    for (City c : list) {
                        System.out.println(c);
                    }
                    message();
                    break;
                case "2":
                    for (City c : cityService.sortByCapital(list)) {
                        System.out.println(c);
                    }
                    message();
                    break;
                case "3":
                    for (City c : cityService.sortByDistrictAndName(list)) {
                        System.out.println(c);
                    }
                    message();
                    break;
                case "4":
                    System.out.println(cityService.listOfCitiesToArray(list));
                    message();
                    break;
                case "5":
                    cityService.numberOfCitiesInRegion(list);
                    message();
                    break;
                default:
                    System.out.println("Некорректный вариант");
                    message();
            }
        }
    }
}