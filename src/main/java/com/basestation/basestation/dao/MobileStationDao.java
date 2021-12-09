package com.basestation.basestation.dao;

import com.basestation.basestation.model.BaseStation;
import com.basestation.basestation.model.MobileStation;
import com.basestation.basestation.service.Reports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MobileStationDao {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BaseStationDao baseStationDao;

    @Autowired
    private ReportsDao reportsDao;

    @Transactional
    public MobileStation create(float lastKnownX, float lastKnownY) {
        MobileStation mobileStation = new MobileStation(lastKnownX, lastKnownY);
        entityManager.persist(mobileStation);
        return mobileStation;
    }

    @Transactional
    public List<MobileStation> findAll() {
        try {
            return entityManager.createQuery("from MobileStation m", MobileStation.class)
                    .getResultStream()
                    .collect(Collectors.toList());
        } catch (NoResultException notFound) {
            return null;
        }
    }

    @Transactional
    public MobileStation findById(int id) {
        try {
            return entityManager.createQuery("from MobileStation m where m.id = :id", MobileStation.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException notFound) {
            return null;
        }
    }

    @Transactional
    public List<Reports> mobileStationPositionReport(MobileStation mobileStation) {
        List<BaseStation> baseStationList = baseStationDao.findAll()
                .stream()
                .filter(e ->
                                mobileStation.getLastKnownX() >= calculationOfRadius(mobileStation, e).get(0) &&
                                mobileStation.getLastKnownX() <= calculationOfRadius(mobileStation, e).get(1) &&
                                mobileStation.getLastKnownY() >= calculationOfRadius(mobileStation, e).get(2) &&
                                mobileStation.getLastKnownY() <= calculationOfRadius(mobileStation, e).get(3)
                ).collect(Collectors.toList());


        if (!baseStationList.isEmpty()) {
            baseStationList.forEach(e ->
                    reportsDao.create(
                            mobileStation.getId(),
                            Math.sqrt((e.getY() - mobileStation.getLastKnownY()) *
                                    (e.getY() - mobileStation.getLastKnownY()) +
                                    (e.getX() - mobileStation.getLastKnownX()) * (e.getX() - mobileStation.getLastKnownX())),
                            String.valueOf(new Date()),
                            e
                    ));
            return sendReport(baseStationList);
        } else {
            return null;
        }
    }

    private List<Float> calculationOfRadius(MobileStation mobileStation, BaseStation baseStation) {
        List<Float> list = new ArrayList<>();
        float numberForRange = baseStation.getDetectionRadiusInMeters() / 2;
        list.add(baseStation.getX() - numberForRange);
        list.add(baseStation.getX() + numberForRange);
        list.add(baseStation.getY() - numberForRange);
        list.add(baseStation.getY() + numberForRange);
        return list;
    }

    private List<Reports> sendReport(List<BaseStation> lisOfBaseStations) {
        List<Reports> reportsList = new ArrayList<>();
        for (BaseStation j:lisOfBaseStations) {
            Reports report = new Reports(j.getId());
            reportsDao.findAllReportsByBaseStationId(j)
                    .stream()
                    .forEach(e -> report.getReportsMs()
                            .add(new Reports.ReportsMS(e.getMobileStationId(), e.getDistance(), e.getTimestamp())));
            reportsList.add(report);
        }
        return reportsList;
    }
}