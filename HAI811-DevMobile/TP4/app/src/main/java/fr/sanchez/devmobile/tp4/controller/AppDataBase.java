package fr.sanchez.devmobile.tp4.controller;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import fr.sanchez.devmobile.tp4.model.PlanningEntity;
import fr.sanchez.devmobile.tp4.repository.PlanningDao;

@Database(entities = {PlanningEntity.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PlanningDao planningDao();
    }

