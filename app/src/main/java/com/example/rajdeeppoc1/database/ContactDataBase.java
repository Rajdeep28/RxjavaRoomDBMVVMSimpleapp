package com.example.rajdeeppoc1.database;

import static com.example.rajdeeppoc1.config.AppConfig.DB_NAME;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rajdeeppoc1.Contact;
import com.example.rajdeeppoc1.database.dao.ContactDao;

@Database(entities = {Contact.class},version = 1)
public abstract class ContactDataBase extends RoomDatabase {

    private static ContactDataBase instance;

    public static synchronized ContactDataBase getInstance(Context context){
        if (instance == null && context!= null){
            instance = Room.databaseBuilder(context.getApplicationContext(),ContactDataBase.class, DB_NAME)
                    .build();
        }
        return instance;
    }

    public abstract ContactDao getContactDao();

}
