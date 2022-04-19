package fr.sanchez.devmobile.tp4.model;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.sanchez.devmobile.tp4.repository.PlanningDao;

public class PlanningModel extends ViewModel {


    private PlanningDao plandao;



    public void init(Context context){
        AppDataBase db = Room.databaseBuilder(context,AppDataBase.class,"database-name").allowMainThreadQueries().build();
        this.plandao = db.planningDao();
        PlanningEntity n1 = new PlanningEntity(0,"24/03/2022","TRAVAILLE1","TRAVAILLE1","TRAVAILLE1","TRAVAILLE1");
        PlanningEntity n2 = new PlanningEntity(1,"25/03/2022","TRAVAILLE2","TRAVAILLE2","TRAVAILLE2","TRAVAILLE2");
        PlanningEntity n3 = new PlanningEntity(2,"26/03/2022","TRAVAILLE3","TRAVAILLE3","TRAVAILLE3","TRAVAILLE3");
        PlanningEntity n4 = new PlanningEntity(3,"27/03/2022","TRAVAILLE4","TRAVAILLE4","TRAVAILLE4","TRAVAILLE4");
        this.plandao.insert(n1);
        this.plandao.insert(n2);
        this.plandao.insert(n3);
        this.plandao.insert(n4);


    }
    public PlanningEntity getPlanning() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return plandao.loadAllByDate(formatter.format(now));

    }

    public String getCreneaux1() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return plandao.loadAllByDatec1(formatter.format(now));
    }
    public String getCreneaux2() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return plandao.loadAllByDatec2(formatter.format(now));
    }
    public String getCreneaux3() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return plandao.loadAllByDatec3(formatter.format(now));
    }
    public String getCreneaux4() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return plandao.loadAllByDatec4(formatter.format(now));
    }

    private void createPlanning(){



    }
    @Override
    protected void onCleared(){
        super.onCleared();
    }


}

