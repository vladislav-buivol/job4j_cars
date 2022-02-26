package ru.job4j.repository.hql;

import ru.job4j.model.user.Account;
import ru.job4j.repository.Database;
import ru.job4j.repository.Store;
import ru.job4j.repository.hql.database.psql.PsqlDatabase;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AccountRepository implements Store<Account> {

    private final Database<Account> databaseDelegate =
            new PsqlDatabase<>(Account.class);

    private AccountRepository() {
    }

    private static final class Lazy {
        private static final Store<Account> INST = new AccountRepository();
    }

    public static Store<Account> instOf() {
        return Lazy.INST;
    }

    public List<Account> findAccountByEmail(String email) {
        try {
            return databaseDelegate.execute(
                    session -> session.createQuery("from Account as acc where email =:email")
                            .setParameter("email", email).list());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Account add(Account account) throws SQLException {
        return databaseDelegate.execute(session -> {
            Class<Account> clazz = Account.class;
            Integer id = (Integer) session.save(account);
            account.setId(id);
            return account;
        });
    }

    @Override
    public boolean replace(String id, Account account) {
        throw new UnsupportedOperationException("replace not supported yet");
    }

    @Override
    public boolean delete(String id) {
        return databaseDelegate.delete(id);
    }

    @Override
    public Collection<Account> findAll() {
        return (List<Account>) databaseDelegate.findAll();
    }

    @Override
    public Account findById(String id) {
        return databaseDelegate.findById(id);
    }

    @Override
    public Collection<Account> executeSelect(String query, Map<String, Object> params) {
        return databaseDelegate.executeSelect(query, params);
    }

    @Override
    public boolean executeUpdate(String query, Map<String, Object> params) {
        return databaseDelegate.executeUpdate(query, params);
    }
}
