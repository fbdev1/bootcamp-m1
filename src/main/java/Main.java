import models.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static services.cityService.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<City> list = new ArrayList<>();
        File file = new File("src/main/resources/listOfCities.txt");
        try (Scanner sc = new Scanner(file).useDelimiter(";")) {
            while (sc.hasNext()) {
                list.add(new City(sc.next(), sc.next(), sc.next(), Integer.parseInt(sc.next()), sc.next()));
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
                    for (City c : sortByCapital(list)) {
                        System.out.println(c);
                    }
                    message();
                    break;
                case "3":
                    for (City c : sortByDistrictAndName(list)) {
                        System.out.println(c);
                    }
                    message();
                    break;
                case "4":
                    System.out.println(listOfCitiesToArray(list));
                    message();
                    break;
                case "5":
                    numberOfCitiesInRegion(list);
                    message();
                    break;
                default:
                    System.out.println("Некорректный вариант");
                    message();
            }
        }
    }
}