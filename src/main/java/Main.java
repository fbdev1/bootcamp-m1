import dao.DaoCity;
import dao.DaoCityImp;
import hiberConfig.H2CreateExample;
import models.City;
import services.CityService;
import services.СityServiceImp;

import java.sql.SQLException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final CityService cityService = new СityServiceImp();


    public static void main(String[] args) throws SQLException {


        List<City> listListFromTxt = new ArrayList<>();

        File file = new File("src/main/resources/listOfCities.txt");
        try (Scanner sc = new Scanner(file).useDelimiter(";")) {
            while (sc.hasNext()) {
                listListFromTxt.add(new City(Integer.parseInt(sc.next().trim()), sc.next().trim(),
                        sc.next().trim(), sc.next().trim(), Integer.parseInt(sc.next()), sc.next().trim()));
            }
        } catch (IOException e) {
            e.getMessage();
        }


        Scanner readerScanner = new Scanner(System.in);
        menuMessage();

        while (!readerScanner.hasNext("q")) {
            switch (readerScanner.next()) {

                case "0":
                    H2CreateExample createTableExample = new H2CreateExample();
                    createTableExample.createTable();
                    menuMessage();
                    break;
                case "1":
                    for (City city : listListFromTxt) {
                        cityService.insertRecord(city);
                    }
                    menuMessage();
                    break;
                case "2":
                    for (City c : cityService.getRecords()) {
                        System.out.println(c);
                    }
                    menuMessage();
                    break;
                case "3":
                    for (City c : cityService.sortByName(cityService.getRecords())) {
                        System.out.println(c);
                    }
                    menuMessage();
                    break;
                case "4":
                    for (City c : cityService.sortByDistrictAndName(cityService.getRecords())) {
                        System.out.println(c);
                    }
                    menuMessage();
                    break;
                case "5":
                    System.out.println(cityService.listOfCitiesToArray(cityService.getRecords()));
                    menuMessage();
                    break;
                case "6":
                    for (String s : cityService.numberOfCitiesInRegion(cityService.getRecords())) {
                        System.out.println(s);
                    }
                    menuMessage();
                    break;
                default:
                    System.out.println("Некорректный вариант");
                    menuMessage();
            }
        }


    }

    public static void menuMessage() {
        System.out.println("\n Выберите вариант меню: \n" +
                "0 - Создать таблицу в БД: \n" +
                "1 - Записать данный из файла: \n" +
                "2 - Вывести исходный список: \n" +
                "3 - Сортировка по имени: \n" +
                "4 - Сортировка по округу: \n" +
                "5 - Вывести самый большой город (население) \n" +
                "6 - Количество городов по регионам \n" +
                "q - Выход");
    }

}