package fr.sanchez.devmobile.calendrier;

public class Evenement {

    private String date, titre;

    public Evenement(String date, String titre) {
        this.date = date;
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    @Override
    public String toString() {
        return date + " : " + titre;
    }

    public static Evenement stringToEvenement(String str) {
        String[] split = str.split(" : ") ;

        return new Evenement(split[0], split[1]) ;
    }
}
