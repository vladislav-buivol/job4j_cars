package ru.job4j.repository.hql.adv;

import ru.job4j.model.adv.Advertisement;
import ru.job4j.repository.Database;
import ru.job4j.repository.Store;
import ru.job4j.repository.database.psql.PsqlDatabase;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AdsRepository implements Store<Advertisement> {

    private final Database<Advertisement> databaseDelegate =
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
        return databaseDelegate.execute(session -> session.createQuery("from Advertisement as adv "
                + "where adv.created>:startDate "

        ).setParameter("startDate", currantDate)
                .list());
    }

    public List<Advertisement> showAdvWithPhotos() {
        return databaseDelegate.execute(
                session -> session.createQuery("select adv from Advertisement adv, Image img "
                        + "join fetch adv.images "
                        + "where img.adv.id = adv.id").list());
    }

    public List<Advertisement> showAdvModel(int modelId) {
        return databaseDelegate.execute(session -> session
                .createQuery("select adv from Advertisement adv "
                        + "join fetch adv.car "
                        + "where adv.car.model.id=:mId"
                )
                .setParameter("mId", modelId)
                .list());
    }

    public List<Advertisement> showAdvData() {
        return databaseDelegate.execute(
                session -> session.createQuery("select distinct adv from Advertisement adv "
                        + "left join fetch adv.images "
                        + "left join fetch adv.account "
                        + "left join fetch adv.car as c "
                        + "left join fetch c.drivers "
                        + "left join fetch c.model "
                        + "left join fetch c.engine "
                        + "").list());
    }

    public List<Advertisement> showUserAdvData(Integer accountId) {
        return databaseDelegate.execute(
                session -> session.createQuery("select distinct adv from Advertisement adv "
                        + "left join fetch adv.images "
                        + "left join fetch adv.account "
                        + "left join fetch adv.car as c "
                        + "left join fetch c.drivers "
                        + "left join fetch c.model "
                        + "left join fetch c.engine "
                        + "where adv.account.id=:id"
                ).setParameter("id", accountId)
                        .list());
    }

    @Override
    public Advertisement add(Advertisement advertisement) throws SQLException {
        return databaseDelegate.add(advertisement);
    }

    @Override
    public boolean update(String id, Advertisement advertisement) {
        Advertisement adv = findById(id);
        adv.setStatus(advertisement.isStatus());
        adv.addAllImage(advertisement.getImages());
        adv.setPrice(advertisement.getPrice());
        adv.setCar(advertisement.getCar());
        adv.setDescription(advertisement.getDescription());
        return databaseDelegate.update(id, advertisement);
    }

    @Override
    public boolean delete(String id) {
        return databaseDelegate.delete(id);
    }

    @Override
    public List<Advertisement> findAll() {
        return (List<Advertisement>) databaseDelegate.findAll();
    }

    @Override
    public Advertisement findById(String id) {
        return ((Advertisement) databaseDelegate.execute(
                session -> session.createQuery("select distinct adv from Advertisement adv "
                        + "left join fetch adv.images "
                        + "left join fetch adv.account "
                        + "left join fetch adv.car as c "
                        + "left join fetch c.drivers "
                        + "left join fetch c.model "
                        + "left join fetch c.engine "
                        + "where adv.id=:id"
                ).setParameter("id", Integer.parseInt(id))
                        .list()).get(0));
        //return databaseDelegate.findById(id);
    }

    @Override
    public Collection<Advertisement> executeSelect(String query, Map<String, Object> params) {
        return databaseDelegate.executeSelect(query, params);
    }

    @Override
    public boolean executeUpdate(String query, Map<String, Object> params) {
        return databaseDelegate.executeUpdate(query, params);
    }

}
