import models.City;
import services.CityService;
import services.СityServiceImp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static services.СityServiceImp.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
       CityService cityService = new СityServiceImp();
        List<City> list = new ArrayList<>();
        File file = new File("src/main/resources/listOfCities.txt");
        try (Scanner sc = new Scanner(file).useDelimiter(";")) {
            while (sc.hasNext()) {
                list.add(new City(sc.next().trim(), sc.next().trim(), sc.next().trim(), Integer.parseInt(sc.next()), sc.next().trim()));
            }
        } catch (IOException e) {
            e.getMessage();
        }

        Scanner readerScanner = new Scanner(System.in);
        cityService.message();

        while (!readerScanner.hasNext("q")) {
            switch (readerScanner.next()) {
                case "1":
                    for (City c : list) {
                        System.out.println(c);
                    }
                    cityService.message();
                    break;
                case "2":
                    for (City c : cityService.sortByCapital(list)) {
                        System.out.println(c);
                    }
                    cityService.message();
                    break;
                case "3":
                    for (City c : cityService.sortByDistrictAndName(list)) {
                        System.out.println(c);
                    }
                    cityService.message();
                    break;
                case "4":
                    System.out.println(cityService.listOfCitiesToArray(list));
                    cityService.message();
                    break;
                case "5":
                    cityService.numberOfCitiesInRegion(list);
                    cityService.message();
                    break;
                default:
                    System.out.println("Некорректный вариант");
                    cityService.message();
            }
        }
    }
}