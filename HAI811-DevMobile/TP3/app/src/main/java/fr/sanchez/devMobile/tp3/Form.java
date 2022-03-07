package fr.sanchez.devMobile.tp3;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Form {

    private String nom, prenom, ddn, ndt, am;
    private boolean sport, musique, lecture;

    public Form(String nom, String prenom, String ddn, String ndt, String am, boolean sport, boolean musique, boolean lecture) {
        this.nom = nom;
        this.prenom = prenom;
        this.ddn = ddn;
        this.ndt = ndt;
        this.am = am;
        this.sport = sport;
        this.musique = musique;
        this.lecture = lecture;
    }
    @Override
    public String toString() {
        return "{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", ddn='" + ddn + '\'' +
                ", ndt='" + ndt + '\'' +
                ", am='" + am + '\'' +
                ", sport=" + sport +
                ", musique=" + musique +
                ", lecture=" + lecture +
                '}';
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getDdn() {
        return ddn;
    }
    public void setDdn(String ddn) {
        this.ddn = ddn;
    }
    public String getNdt() {
        return ndt;
    }
    public void setNdt(String ndt) {
        this.ndt = ndt;
    }
    public String getAm() {
        return am;
    }
    public void setAm(String am) {
        this.am = am;
    }
    public boolean isSport() {
        return sport;
    }
    public void setSport(boolean sport) {
        this.sport = sport;
    }
    public boolean isMusique() {
        return musique;
    }
    public void setMusique(boolean musique) {
        this.musique = musique;
    }
    public boolean isLecture() {
        return lecture;
    }
    public void setLecture(boolean lecture) {
        this.lecture = lecture;
    }
}
