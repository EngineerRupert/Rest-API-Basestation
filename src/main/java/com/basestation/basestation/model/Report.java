package com.basestation.basestation.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "mobile_station_id")
    private int mobileStationId;

    @Column(name = "distance")
    private float distance;

    @Column(name = "timestamp")
    private String timestamp;

    @ManyToOne
    private BaseStation baseStation;

    public Report() {
    }

    public Report(int mobileStationId, float distance, String timestamp, BaseStation baseStation) {
        this.mobileStationId = mobileStationId;
        this.distance = distance;
        this.timestamp = timestamp;
        this.baseStation = baseStation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMobileStationId() {
        return mobileStationId;
    }

    public void setMobileStationId(int mobileStationId) {
        this.mobileStationId = mobileStationId;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public BaseStation getBaseStation() {
        return baseStation;
    }

    public void setBaseStation(BaseStation baseStation) {
        this.baseStation = baseStation;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", mobileStationId=" + mobileStationId +
                ", distance=" + distance +
                ", timestamp='" + timestamp + '\'' +
                ", baseStation=" + baseStation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id && mobileStationId == report.mobileStationId && Float.compare(report.distance, distance) == 0 && Objects.equals(timestamp, report.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mobileStationId, distance, timestamp);
    }
}
