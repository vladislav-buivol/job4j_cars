package ru.job4j.repository.store.car;

import ru.job4j.model.car.Model;
import ru.job4j.repository.Database;
import ru.job4j.repository.Store;
import ru.job4j.repository.database.psql.PsqlDatabase;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CarModelRepo implements Store<Model> {

    private final Database<Model> databaseDelegate =
            new PsqlDatabase<>(Model.class);

    private CarModelRepo() {
    }

    private static final class Lazy {
        private static final Store<Model> INST = new CarModelRepo();
    }

    public static Store<Model> instOf() {
        return Lazy.INST;
    }

    @Override
    public Model add(Model model) throws SQLException {
        return databaseDelegate.add(model);
    }

    @Override
    public boolean update(String id, Model model) {
        throw new UnsupportedOperationException("replace not supported yet");
    }

    @Override
    public boolean delete(String id) {
        return databaseDelegate.delete(id);
    }

    @Override
    public List<Model> findAll() {
        return (List<Model>) databaseDelegate.findAll();
    }

    @Override
    public Model findById(String id) {
        return databaseDelegate.findById(id);
    }

    @Override
    public Collection<Model> executeSelect(String query, Map<String, Object> params) {
        return databaseDelegate.executeSelect(query, params);
    }

    @Override
    public boolean executeUpdate(String query, Map<String, Object> params) {
        return databaseDelegate.executeUpdate(query, params);
    }
}
