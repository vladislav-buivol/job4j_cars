package ru.job4j.repository.store.car;


import ru.job4j.model.car.Car;
import ru.job4j.repository.Database;
import ru.job4j.repository.Store;
import ru.job4j.repository.database.psql.PsqlDatabase;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CarRepository implements Store<Car> {

    private final Database<Car> databaseDelegate =
            new PsqlDatabase<>(Car.class);

    public CarRepository() {
    }

    private static final class Lazy {
        private static final Store<Car> INST = new CarRepository();
    }

    public static Store<Car> instOf() {
        return CarRepository.Lazy.INST;
    }

    @Override
    public Car add(Car car) throws SQLException {
        return databaseDelegate.add(car);
    }

    @Override
    public boolean update(String id, Car car) {
        throw new UnsupportedOperationException("replace not supported yet");
    }

    @Override
    public boolean delete(String id) {
        return databaseDelegate.delete(id);
    }

    @Override
    public List<Car> findAll() {
        return (List<Car>) databaseDelegate.findAll();
    }

    @Override
    public Car findById(String id) {
        return databaseDelegate.findById(id);
    }

    @Override
    public Collection<Car> executeSelect(String query, Map<String, Object> params) {
        return databaseDelegate.executeSelect(query, params);
    }

    @Override
    public boolean executeUpdate(String query, Map<String, Object> params) {
        return databaseDelegate.executeUpdate(query, params);
    }
}
