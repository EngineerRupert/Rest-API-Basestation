package com.basestation.basestation.dao;

import com.basestation.basestation.model.BaseStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BaseStationDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public BaseStation create(BaseStation baseStation) {
        entityManager.persist(baseStation);
        return baseStation;
    }

    @Transactional
    public List<BaseStation> findAll() {
        try {
            return entityManager.createQuery("from BaseStation b", BaseStation.class)
                    .getResultStream()
                    .collect(Collectors.toList());
        } catch (NoResultException notFound) {
            return null;
        }
    }

}
