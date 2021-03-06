import dao.DaoCity;
import dao.DaoCityImp;
import hiberConfig.H2CreateExample;
import models.City;
import services.CityService;
import services.–°ityServiceImp;

import java.sql.SQLException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final CityService cityService = new –°ityServiceImp();


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
                    System.out.println("–Ě–Ķ–ļ–ĺ—Ä—Ä–Ķ–ļ—ā–Ĺ—č–Ļ –≤–į—Ä–ł–į–Ĺ—ā");
                    menuMessage();
            }
        }


    }

    public static void menuMessage() {
        System.out.println("\n –í—č–Ī–Ķ—Ä–ł—ā–Ķ –≤–į—Ä–ł–į–Ĺ—ā –ľ–Ķ–Ĺ—é: \n" +
                "0 - –°–ĺ–∑–ī–į—ā—Ć —ā–į–Ī–Ľ–ł—Ü—É –≤ –Ď–Ē: \n" +
                "1 - –ó–į–Ņ–ł—Ā–į—ā—Ć –ī–į–Ĺ–Ĺ—č–Ķ –ł–∑ —Ą–į–Ļ–Ľ–į: \n" +
                "2 - –í—č–≤–Ķ—Ā—ā–ł –ł—Ā—Ö–ĺ–ī–Ĺ—č–Ļ —Ā–Ņ–ł—Ā–ĺ–ļ: \n" +
                "3 - –°–ĺ—Ä—ā–ł—Ä–ĺ–≤–ļ–į –Ņ–ĺ –ł–ľ–Ķ–Ĺ–ł: \n" +
                "4 - –°–ĺ—Ä—ā–ł—Ä–ĺ–≤–ļ–į –Ņ–ĺ –ĺ–ļ—Ä—É–≥—É: \n" +
                "5 - –í—č–≤–Ķ—Ā—ā–ł —Ā–į–ľ—č–Ļ –Ī–ĺ–Ľ—Ć—ą–ĺ–Ļ –≥–ĺ—Ä–ĺ–ī (–Ĺ–į—Ā–Ķ–Ľ–Ķ–Ĺ–ł–Ķ) \n" +
                "6 - –ö–ĺ–Ľ–ł—á–Ķ—Ā—ā–≤–ĺ –≥–ĺ—Ä–ĺ–ī–ĺ–≤ –Ņ–ĺ —Ä–Ķ–≥–ł–ĺ–Ĺ–į–ľ \n" +
                "q - –í—č—Ö–ĺ–ī");
    }

}