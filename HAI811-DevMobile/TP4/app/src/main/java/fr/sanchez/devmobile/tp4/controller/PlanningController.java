package fr.sanchez.devmobile.tp4.controller;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.sanchez.devmobile.tp4.model.PlanningEntity;
import fr.sanchez.devmobile.tp4.repository.PlanningDao;

public class PlanningController extends ViewModel {

    private PlanningDao plandao;

    public void init(Context context){
        AppDataBase db = Room.databaseBuilder(context,AppDataBase.class,"database-name").allowMainThreadQueries().build();
        this.plandao = db.planningDao();
        PlanningEntity n1 = new PlanningEntity(0,"19/04/2022","Test","Test","Test","Test");
        PlanningEntity n2 = new PlanningEntity(1,"22/03/2022","Test2","Test2","Test2","Test2");
        PlanningEntity n3 = new PlanningEntity(2,"20/04/2022","Test3","Test3","Test3","Test3");
        PlanningEntity n4 = new PlanningEntity(3,"22/04/2022","Test4","Test4","Test4","Test4");
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
