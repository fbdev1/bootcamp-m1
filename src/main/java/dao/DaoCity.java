package dao;

import models.City;

import java.sql.SQLException;
import java.util.List;

public interface DaoCity {
    void insertRecord(City city) throws SQLException;
    List<City> getRecords();
}
