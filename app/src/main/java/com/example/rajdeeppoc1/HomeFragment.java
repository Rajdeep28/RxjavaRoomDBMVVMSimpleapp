package com.example.rajdeeppoc1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.rajdeeppoc1.config.AppConfig;
import com.example.rajdeeppoc1.database.ContactDataBase;
import com.example.rajdeeppoc1.databinding.FragmentHomeBinding;
import com.example.rajdeeppoc1.viewmodel.ContactViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding viewBinding;
    private ContactViewModel contactViewModel;
    private ContactAdapter contactAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ContactDataBase contactDataBase;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       viewBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
       return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewBinding.addContacts.setOnClickListener(view1 -> goToAddContactsPage());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        viewBinding.recyclerView.setLayoutManager(linearLayoutManager);
        getProductDetails();

    }

    private void getProductDetails() {
        Disposable disposable = contactViewModel.getAllContactDetails().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Contact>>() {
                    @Override
                    public void accept(List<Contact> contacts) throws Exception {
                        Log.d("DataBase", "accept: Called");
                        setDataToRecyclerView(contacts);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void setDataToRecyclerView(List<Contact> contactList) {
        contactAdapter = new ContactAdapter(contactList);
        viewBinding.recyclerView.setAdapter(contactAdapter);
    }

    private void goToAddContactsPage() {
        ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragment,new PeopleFragment()).commit();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (compositeDisposable != null) compositeDisposable.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
