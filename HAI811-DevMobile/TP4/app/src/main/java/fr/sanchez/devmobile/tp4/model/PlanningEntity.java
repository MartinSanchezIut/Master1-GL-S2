package fr.sanchez.devmobile.tp4.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PlanningEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="horaire1")
    private String horaire1;

    @ColumnInfo(name="horaire2")
    private String horaire2;

    @ColumnInfo(name="horaire3")
    private String horaire3;

    @ColumnInfo(name="horaire4")
    private String horaire4;

    @ColumnInfo(name="date")
    private String date;


    public PlanningEntity(int id, String horaire1, String horaire2, String horaire3, String horaire4, String date) {
        this.id = id;
        this.horaire1 = horaire1;
        this.horaire2 = horaire2;
        this.horaire3 = horaire3;
        this.horaire4 = horaire4;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoraire1() {
        return horaire1;
    }

    public void setHoraire1(String horaire1) {
        this.horaire1 = horaire1;
    }

    public String getHoraire2() {
        return horaire2;
    }

    public void setHoraire2(String horaire2) {
        this.horaire2 = horaire2;
    }

    public String getHoraire3() {
        return horaire3;
    }

    public void setHoraire3(String horaire3) {
        this.horaire3 = horaire3;
    }

    public String getHoraire4() {
        return horaire4;
    }

    public void setHoraire4(String horaire4) {
        this.horaire4 = horaire4;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
