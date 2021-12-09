package com.basestation.basestation.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Component
@Entity
public class BaseStation {

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 150)
    private String name;

    @Column
    private float x;

    @Column
    private float y;

    @Column
    private float detectionRadiusInMeters;

    @OneToMany(mappedBy = "baseStation")
    private List<Report> reports;

    public BaseStation() {
    }

    public BaseStation(String name, float x, float y, float detectionRadiusInMeters) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.detectionRadiusInMeters = detectionRadiusInMeters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getDetectionRadiusInMeters() {
        return detectionRadiusInMeters;
    }

    public void setDetectionRadiusInMeters(float detectionRadiusInMeters) {
        this.detectionRadiusInMeters = detectionRadiusInMeters;
    }

    @Override
    public String toString() {
        return "BaseStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", detectionRadiusInMeters=" + detectionRadiusInMeters +
                ", reports=" + reports +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseStation that = (BaseStation) o;
        return id == that.id && Float.compare(that.x, x) == 0 && Float.compare(that.y, y) == 0 && Float.compare(that.detectionRadiusInMeters, detectionRadiusInMeters) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, x, y, detectionRadiusInMeters);
    }
}
