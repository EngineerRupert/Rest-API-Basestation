package com.basestation.basestation.service;

import java.util.ArrayList;
import java.util.List;

public class Reports {

    private int baseStationId;

    private List<ReportsMS> reports = new ArrayList<>();

    public Reports(int baseStationId, int mobileStationId, float distance, String timestamp) {
        this.baseStationId = baseStationId;
    }

    public int getBaseStationId() {
        return baseStationId;
    }

    public void setBaseStationId(int baseStationId) {
        this.baseStationId = baseStationId;
    }

    public List<ReportsMS> getReportsMs() {
        return reports;
    }

    public void setReportsMs(List<ReportsMS> reportsMs) {
        this.reports = reportsMs;
    }

    public Reports() {
    }

    public Reports(int baseStationId) {
        this.baseStationId = baseStationId;
    }

    public static class ReportsMS {
        private int mobileStationId;
        private float distance;
        private String timestamp;

        public ReportsMS(int mobileStationId, float distance, String timestamp) {
            this.mobileStationId = mobileStationId;
            this.distance = distance;
            this.timestamp = timestamp;
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
    }

}
