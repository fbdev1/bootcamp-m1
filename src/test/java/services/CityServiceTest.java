package services;

import junit.framework.TestCase;
import models.City;
import org.junit.Assert;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;

public class CityServiceTest extends TestCase {

    List<City> expected;
    List<City> actual;
    CityService cityService = new СityServiceImp();

    @Before
    public void setUp() throws Exception {

        actual = Arrays.asList(new City(2, "Тавда", "Свердловская область", "Урал", 40000, "1840")
                , new City(1, "Ревда", "Свердловская область", "Аурал", 45000, "1930")
                , new City(3, "Челябинск", "Челябинская область", "Урал", 950000, "1900")
        );
    }

    public void testSortByName() {
        expected = Arrays.asList(new City(1, "Ревда", "Свердловская область", "Аурал", 45000, "1930")
                , new City(2, "Тавда", "Свердловская область", "Урал", 40000, "1840")
                , new City(3, "Челябинск", "Челябинская область", "Урал", 950000, "1900")
        );
        Assert.assertEquals(expected, cityService.sortByName(actual));
    }

    public void testSortByDistrictAndName() {
        expected = Arrays.asList(new City(1, "Ревда", "Свердловская область", "Аурал", 45000, "1930")
                , new City(2, "Тавда", "Свердловская область", "Урал", 40000, "1840")
                , new City(3, "Челябинск", "Челябинская область", "Урал", 950000, "1900")
        );
        Assert.assertEquals(expected, cityService.sortByDistrictAndName(actual));
    }

    public void testListOfCitiesToArray() {
        String expectedString = "[3] = 950000";
        Assert.assertEquals(expectedString, cityService.listOfCitiesToArray(actual));
    }

    public void testNumberOfCitiesInRegion() {
        List<String> expectedList = Arrays.asList("Свердловская область: 2", "Челябинская область: 1");
        Assert.assertEquals(expectedList, cityService.numberOfCitiesInRegion(actual));
    }
}

