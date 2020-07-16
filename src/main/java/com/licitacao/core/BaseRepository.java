package com.licitacao.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract public class BaseRepository {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        return factory.createEntityManager();
    }
}
