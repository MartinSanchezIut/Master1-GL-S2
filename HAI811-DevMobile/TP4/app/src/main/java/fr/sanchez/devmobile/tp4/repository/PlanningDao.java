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
    @Query("SELECT * FROM planningentity WHERE date LIKE :first")
    PlanningEntity findByDate(String first);


    @Query("SELECT * FROM planningentity WHERE date IN (:planDate)")
    PlanningEntity loadAllByDate(String planDate);

    @Query("SELECT horaire1 FROM planningentity WHERE date IN (:planDate)")
    String loadAllByDatec1(String planDate);

    @Query("SELECT horaire2 FROM planningentity WHERE date IN (:planDate)")
    String loadAllByDatec2(String planDate);

    @Query("SELECT horaire3 FROM planningentity WHERE date IN (:planDate)")
    String loadAllByDatec3(String planDate);

    @Query("SELECT horaire4 FROM planningentity WHERE date IN (:planDate)")
    String loadAllByDatec4(String planDate);


    //Insérer un seul élément
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PlanningEntity planningEntity);

}
