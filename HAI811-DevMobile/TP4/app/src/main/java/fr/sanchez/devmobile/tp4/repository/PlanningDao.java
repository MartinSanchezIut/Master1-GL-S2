package fr.sanchez.devmobile.tp4.repository;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.sanchez.devmobile.tp4.model.PlanningEntity;

@Dao
public interface PlanningDao {

    //Récupérer toutes les instances
    @Query("SELECT * FROM planningentity")
    List<PlanningEntity> getAll();

    //Récupérer une instance par sa date (contient quatre horaires et trois trucs pour les dates)
    @Query("SELECT * FROM planningentity WHERE jour LIKE :first AND mois LIKE :middle AND annee LIKE :last")
    PlanningEntity findByDate(String first, String middle, String last);


    @Query("SELECT * FROM planning WHERE date IN (:planDate)")
    PlanningEntity loadAllByDate(String planDate);

    @Query("SELECT creneau1 FROM planning WHERE date IN (:planDate)")
    String loadAllByDatec1(String planDate);

    @Query("SELECT creneau2 FROM planning WHERE date IN (:planDate)")
    String loadAllByDatec2(String planDate);

    @Query("SELECT creneau3 FROM planning WHERE date IN (:planDate)")
    String loadAllByDatec3(String planDate);

    @Query("SELECT creneau4 FROM planning WHERE date IN (:planDate)")
    String loadAllByDatec4(String planDate);


    //Insérer un seul élément
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PlanningEntity planningEntity);

}
