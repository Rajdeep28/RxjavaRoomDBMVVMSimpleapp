package com.example.rajdeeppoc1.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.rajdeeppoc1.Contact;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface ContactDao {

    @Query("Select * from contacts")
    Observable<List<Contact>> getAllContacts();

    @Insert
    void insert(Contact contact);
}
