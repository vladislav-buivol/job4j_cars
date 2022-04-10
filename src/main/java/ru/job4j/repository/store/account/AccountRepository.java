package ru.job4j.repository.store.account;

import ru.job4j.model.user.Account;
import ru.job4j.repository.Database;
import ru.job4j.repository.Store;
import ru.job4j.repository.database.psql.PsqlDatabase;

import java.sql.SQLException;
import java.util.*;

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
            return Collections.emptyList();
        }
    }

    @Override
    public Account add(Account account) throws SQLException {
        return databaseDelegate.add(account);
    }

    @Override
    public boolean update(String id, Account account) {
        throw new UnsupportedOperationException("replace not supported yet");
    }

    @Override
    public boolean delete(String id) {
        return databaseDelegate.delete(id);
    }

    @Override
    public Collection<Account> findAll() {
        return databaseDelegate.findAll();
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
