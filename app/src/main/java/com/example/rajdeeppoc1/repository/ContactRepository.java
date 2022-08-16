package com.example.rajdeeppoc1.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.rajdeeppoc1.Contact;
import com.example.rajdeeppoc1.database.ContactDataBase;
import com.example.rajdeeppoc1.database.dao.ContactDao;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class ContactRepository {
    private static ContactRepository contactRepository;
    private ContactDao contactDao;
    private ContactDataBase contactDataBase;
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public static final String TAG = "ContactRepository";

    private ContactRepository(Context context){
        contactDataBase = ContactDataBase.getInstance(context);
        contactDao = contactDataBase.getContactDao();
    }
    //Get Loading State
    public MutableLiveData<Boolean> getIsLoading(){
        return isLoading;
    }
    public static ContactRepository getInstance(Context context){

        if (contactRepository == null){
            contactRepository = new ContactRepository(context);
        }
        return contactRepository;
    }
    public void insertContacts(final Contact contactList){

        isLoading.setValue(true);

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                contactDao.insert(contactList);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: Called");
                    }

                    @Override
                    public void onComplete() {
                        isLoading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }
                });
    }

    public Observable<List<Contact>> getAllContacts(){
        return contactDao.getAllContacts();
    }
}

