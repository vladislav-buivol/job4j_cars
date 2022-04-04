package ru.job4j.repository.store.car;

import ru.job4j.model.car.Engine;
import ru.job4j.repository.Database;
import ru.job4j.repository.Store;
import ru.job4j.repository.database.psql.PsqlDatabase;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class EngineRepo implements Store<Engine> {

    private final Database<Engine> databaseDelegate =
            new PsqlDatabase<>(Engine.class);

    private EngineRepo() {
    }

    private static final class Lazy {
        private static final Store<Engine> INST = new EngineRepo();
    }

    public static Store<Engine> instOf() {
        return Lazy.INST;
    }

    @Override
    public Engine add(Engine engine) throws SQLException {
        return databaseDelegate.add(engine);
    }

    @Override
    public boolean update(String id, Engine engine) {
        throw new UnsupportedOperationException("replace not supported yet");
    }

    @Override
    public boolean delete(String id) {
        return databaseDelegate.delete(id);
    }

    @Override
    public List<Engine> findAll() {
        return (List<Engine>) databaseDelegate.findAll();
    }

    @Override
    public Engine findById(String id) {
        return databaseDelegate.findById(id);
    }

    @Override
    public Collection<Engine> executeSelect(String query, Map<String, Object> params) {
        return databaseDelegate.executeSelect(query, params);
    }

    @Override
    public boolean executeUpdate(String query, Map<String, Object> params) {
        return databaseDelegate.executeUpdate(query, params);
    }
}
