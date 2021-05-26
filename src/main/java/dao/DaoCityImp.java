package dao;

import hiberConfig.H2JDBCUtils;
import models.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaoCityImp implements DaoCity {

    private static String INSERT_CITIES_SQL = "INSERT INTO cities" +
            "  (id, name, region, district, population, foundation) VALUES " +
            " (?, ?, ?, ?, ?, ?);";



    public void insertRecord(City city) throws SQLException {
        System.out.println(INSERT_CITIES_SQL);
        try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CITIES_SQL)) {
            preparedStatement.setInt(1, city.getId());
            preparedStatement.setString(2, city.getName());
            preparedStatement.setString(3, city.getRegion());
            preparedStatement.setString(4, city.getDistrict());
            preparedStatement.setInt(5, city.getPopulation());
            preparedStatement.setString(6, city.getFoundation());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }

    public List<City> getRecords() {
        List<City> listOfCities = new ArrayList<>();
        try (Connection connection = H2JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from cities");) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                listOfCities.add(new City(rs.getInt("id"), rs.getString("name"), rs.getString("region")
                        , rs.getString("district"), rs.getInt("population"), rs.getString("foundation")));
            }
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
        return listOfCities;
    }
}