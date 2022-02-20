package ru.job4j.repository.hql;

import ru.job4j.model.adv.Advertisement;
import ru.job4j.repository.Database;
import ru.job4j.repository.Store;
import ru.job4j.repository.hql.database.psql.PsqlDatabase;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AdsRepository implements Store<Advertisement> {

    private final Database<Advertisement> database =
            new PsqlDatabase<>(Advertisement.class);

    private AdsRepository() {
    }

    private static final class Lazy {
        private static final Store<Advertisement> INST = new AdsRepository();
    }

    public static Store<Advertisement> instOf() {
        return Lazy.INST;
    }

    public List<Advertisement> lastDayAnnouncements() {
        Timestamp currantDate = Timestamp.valueOf(LocalDate.now().atStartOfDay());
        return database.execute(session -> session.createQuery("from Advertisement as adv "
                + "where adv.created>:startDate "

        ).setParameter("startDate", currantDate)
                .list());
    }

    public List<Advertisement> showAdvWithPhotos() {
        return database.execute(
                session -> session.createQuery("select adv from Advertisement adv, Image img "
                        + "join fetch adv.images "
                        + "where img.adv.id = adv.id").list());
    }

    public List<Advertisement> showAdvModel(int modelId) {
        return database.execute(session -> session
                .createQuery("select adv from Advertisement adv "
                        + "join fetch adv.car "
                        + "where adv.car.model.id=:mId"
                )
                .setParameter("mId", modelId)
                .list());
    }

    @Override
    public Advertisement add(Advertisement advertisement) throws SQLException {
        return database.execute(session -> {
            Class<Advertisement> clazz = Advertisement.class;
            Integer id = (Integer) session.save(advertisement);
            advertisement.setId(id);
            return advertisement;
        });
    }

    @Override
    public boolean replace(String id, Advertisement advertisement) {
        throw new UnsupportedOperationException("replace not supported yet");
    }

    @Override
    public boolean delete(String id) {
        return database.delete(id);
    }

    @Override
    public List<Advertisement> findAll() {
        return (List<Advertisement>) database.findAll();
    }

    @Override
    public Advertisement findById(String id) {
        return database.findById(id);
    }

    @Override
    public Collection<Advertisement> executeSelect(String query, Map<String, Object> params) {
        return database.executeSelect(query, params);
    }

    @Override
    public boolean executeUpdate(String query, Map<String, Object> params) {
        return database.executeUpdate(query, params);
    }

}
