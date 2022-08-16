package com.example.rajdeeppoc1.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rajdeeppoc1.Contact;
import com.example.rajdeeppoc1.repository.ContactRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository contactRepository;


    public ContactViewModel(@NonNull Application application){
        super(application);
        contactRepository = ContactRepository.getInstance(application);
    }

    public Observable<List<Contact>> getAllContactDetails(){
        return contactRepository.getAllContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public MutableLiveData<Boolean> getIsLoading(){
        return contactRepository.getIsLoading();
    }

    public void insert(Contact contact){
         contactRepository.insertContacts(contact);
    }
}
