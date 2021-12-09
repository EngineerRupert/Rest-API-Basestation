package com.basestation.basestation.dao;

import com.basestation.basestation.model.BaseStation;
import com.basestation.basestation.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReportsDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void create(int mobileStationId, double distance, String timestamp, BaseStation baseStation) {
        Report report = new Report(mobileStationId, (float) distance, timestamp, baseStation);
        entityManager.persist(report);
    }

    @Transactional
    public List<Report> findAllReports() {
        try {
            return entityManager.createQuery("from Report", Report.class)
                    .getResultStream()
                    .collect(Collectors.toList());
        } catch (NoResultException notFound) {
            return null;
        }
    }

    @Transactional
    public List<Report> findAllReportsByBaseStationId(BaseStation baseStation) {
        try {
            return entityManager.createQuery("from Report where baseStation = :baseStation", Report.class)
                    .setParameter("baseStation", baseStation)
                    .getResultStream()
                    .collect(Collectors.toList());
        } catch (NoResultException notFound) {
            return null;
        }
    }

}
