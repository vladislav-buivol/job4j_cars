package ru.job4j.repository;

import org.hibernate.Session;

import java.util.function.Function;

public interface Database<T> extends Store<T> {
    <T> T execute(final Function<Session, T> command);
}
