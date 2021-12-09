package com.basestation.basestation.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
public class MobileStation {

    @Id
    @GeneratedValue
    public int id;

    @Column
    private float lastKnownX;

    @Column
    private float lastKnownY;

    public MobileStation() {
    }

    public MobileStation(float lastKnownX, float lastKnownY) {
        this.lastKnownX = lastKnownX;
        this.lastKnownY = lastKnownY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLastKnownX() {
        return lastKnownX;
    }

    public void setLastKnownX(float lastKnownX) {
        this.lastKnownX = lastKnownX;
    }

    public float getLastKnownY() {
        return lastKnownY;
    }

    public void setLastKnownY(float lastKnownY) {
        this.lastKnownY = lastKnownY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MobileStation that = (MobileStation) o;
        return id == that.id && Float.compare(that.lastKnownX, lastKnownX) == 0 && Float.compare(that.lastKnownY, lastKnownY) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastKnownX, lastKnownY);
    }

    @Override
    public String toString() {
        return "MobileStation{" +
                "id=" + id +
                ", lastKnownX=" + lastKnownX +
                ", lastKnownY=" + lastKnownY +
                '}';
    }
}
