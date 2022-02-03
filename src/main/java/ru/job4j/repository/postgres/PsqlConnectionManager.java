package ru.job4j.repository.postgres;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class PsqlConnectionManager implements AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata()
            .buildSessionFactory();

    private PsqlConnectionManager() {
    }

    private static final class Lazy {
        private static final PsqlConnectionManager
                INST = new PsqlConnectionManager();
    }

    public static PsqlConnectionManager instOf() {
        return Lazy.INST;
    }

    public StandardServiceRegistry getRegistry() {
        return registry;
    }

    public SessionFactory getSf() {
        return sf;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
