package ru.job4j.repository.postgres;

import ru.job4j.model.adv.Advertisement;
import ru.job4j.repository.Store;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class AdRepository extends PsqlStore<Advertisement> {

    private AdRepository() {
    }

    private static final class Lazy {
        private static final Store<Advertisement> INST = new AdRepository();
    }

    public static Store<Advertisement> instOf() {
        return Lazy.INST;
    }

    public List<Advertisement> lastDayAnnouncements() {
        Timestamp currantDate = Timestamp.valueOf(LocalDate.now().atStartOfDay());
        return this.execute(session -> session.createQuery("from Advertisement as adv "
                + "where adv.created>:startDate "

        ).setParameter("startDate", currantDate)
                .list());
    }

    public List<Advertisement> showAdvWithPhotos() {
        return this.execute(session -> session
                .createQuery("select adv from Advertisement adv, Image img "
                        + "join fetch adv.images "
                        + "where img.adv.id = adv.id").list());
    }

    public List<Advertisement> showAdvModel(int modelId) {
        return this.execute(session -> session
                .createQuery("select adv from Advertisement adv "
                        + "join fetch adv.car "
                        + "where adv.car.model.id=:mId"
                )
                .setParameter("mId", modelId)
                .list());
    }

    @Override
    public Advertisement add(Advertisement advertisement) throws SQLException {
        return this.execute(session -> {
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
        return execute(session -> session.createQuery("delete from Advertisement where id =:id")
                .setParameter("id", Integer.parseInt(id))
                .executeUpdate() == 1
        );
    }

    @Override
    public List<Advertisement> findAll() {
        return this
                .execute(session -> session.createQuery("from Advertisement order by id").list());
    }

    @Override
    public Advertisement findById(String id) {
        return findById(id, Advertisement.class);
    }
}
