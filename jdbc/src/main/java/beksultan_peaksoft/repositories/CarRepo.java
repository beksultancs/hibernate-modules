package beksultan_peaksoft.repositories;

import beksultan_peaksoft.database.connection.DatabaseConnection;
import beksultan_peaksoft.models.Car;
import beksultan_peaksoft.models.EngineType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Beksultan
 */
public class CarRepo {

    private static final Connection connection = DatabaseConnection.getConnection();

    // create table
    public void createCarTable() {
        String query = """
                create table if not exists cars (
                id serial primary key,
                brand varchar not null,
                model varchar not null,
                price integer not null,
                engine_type varchar not null);
                """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("table car successfully created!");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    // save
    public void save(Car car) {
        String query = """
                insert into cars (brand, model, price, engine_type)
                values (?, ?, ?, ?);
                """;
        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, car.brand());
            statement.setString(2, car.model());
            statement.setInt(3, car.price());
            statement.setString(4, car.engineType().name());
            statement.execute();
            System.out.printf("car with model = %s successfully saved to database\n", car.model());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    // findAll
    public List<Car> findAll() {
        String query = "select * from cars;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            List<Car> carList = new ArrayList<>();

            findByResultSet(resultSet, carList);
            return carList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    private void findByResultSet(ResultSet resultSet, List<Car> carList) throws SQLException {
        while (resultSet.next()) {
            Car car = new Car(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    EngineType.valueOf(resultSet.getString(5))
            );
            carList.add(car);
        }
    }

    // findById
    public Car findById(Long id) {
        String query = "select * from cars where id = ?";
        Car car = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                car = new Car(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        EngineType.valueOf(resultSet.getString(5))
                );
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return car;
    }

    //findByEngineType
    public List<Car> findByEngineType(EngineType engineType) {
        String query = "select * from cars where engine_type = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, engineType.name());
            ResultSet resultSet = statement.executeQuery();
            List<Car> carList = new ArrayList<>();
            findByResultSet(resultSet, carList);
            return carList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    // deleteById
    public void deleteById(Long id) {
        String query = "delete from cars where id = ?";
        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setLong(1, id);
            if (statement.execute()) {
                System.out.printf("car with id = %d has successfully deleted", id);
            } else {
                System.err.println("failed to delete");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    // drop table
    public void dropCarTable() {
        String query = "drop table if exists cars";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("table cars successfully dropped");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
