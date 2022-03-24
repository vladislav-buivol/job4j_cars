package ru.job4j.repository.hql.adv;

import ru.job4j.model.adv.Image;
import ru.job4j.repository.Database;
import ru.job4j.repository.Store;
import ru.job4j.repository.database.psql.PsqlDatabase;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ImageRepository implements Store<Image> {

    private final Database<Image> databaseDelegate =
            new PsqlDatabase<>(Image.class);

    private ImageRepository() {
    }

    private static final class Lazy {
        private static final Store<Image> INST = new ImageRepository();
    }

    public static Store<Image> instOf() {
        return ImageRepository.Lazy.INST;
    }

    @Override
    public Image add(Image image) throws SQLException {
        return databaseDelegate.add(image);
    }

    @Override
    public boolean replace(String id, Image image) {
        throw new UnsupportedOperationException("replace not supported yet");
    }

    @Override
    public boolean delete(String id) {
        return databaseDelegate.delete(id);
    }

    @Override
    public List<Image> findAll() {
        return (List<Image>) databaseDelegate.findAll();
    }

    @Override
    public Image findById(String id) {
        return databaseDelegate.findById(id);
    }

    @Override
    public Collection<Image> executeSelect(String query, Map<String, Object> params) {
        return databaseDelegate.executeSelect(query, params);
    }

    @Override
    public boolean executeUpdate(String query, Map<String, Object> params) {
        return databaseDelegate.executeUpdate(query, params);
    }

}
