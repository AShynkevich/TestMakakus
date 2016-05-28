package com.deniel.system.repository;

import java.sql.SQLException;

/**
 * Created by Deniel on 22.04.2016.
 */
public interface ICrud<K, E> {

    void create(E entity);

    E read(K id);

    void update(E entity);

    boolean delete(K id);
}
