package fr.sanchez.devMobile.train;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Train {

    private int idTrain;

    private String dep, arr;
    private String datedep, datearr;

    private static int countTrain = 100000;
    public Train(String dep, String datedep, String arr, String datearr) {
        this.idTrain = countTrain++;
        this.dep = dep;
        this.arr = arr;
        this.datedep = datedep;
        this.datearr = datearr;
    }

    private static int getRandom(int min, int max) {
        if (!(min <= max)) {
            Random r = new Random();
            return r.nextInt((max - min) + 1) + min;
        }
        return 0;
    }

    public static ArrayList<Train> makeTrainList(int howMany) {
        String[] villes = {"Montpellier",
                            "Perpignan",
                            "Narbonne",
                            "Carcassone",
                            "Castelnau",
                            "Agde",
                            "Toulouse",
                            "Venise"};

        ArrayList<Train> ret = new ArrayList<>();
        for (int i = 0; i< howMany; i++) {
            Train t = new Train(villes[getRandom(0, villes.length)], "01/01/01",
                                villes[getRandom(0, villes.length)], "02/02/02");
            ret.add(t);
        }
        return ret;
    }
    public int getIdTrain() {
        return idTrain;
    }
    public String getDep() {
        return dep;
    }
    public String getArr() {
        return arr;
    }
    public String getDatedep() {
        return datedep;
    }
    public String getDatearr() {
        return datearr;
    }
    public static int getCountTrain() {
        return countTrain;
    }
}
