package com.example.rajdeeppoc1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rajdeeppoc1.databinding.FragmentPeopleBinding;
import com.example.rajdeeppoc1.viewmodel.ContactViewModel;
public class PeopleFragment extends Fragment {

    FragmentPeopleBinding viewBinding;
    ContactViewModel contactViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_people,container,false);
        return viewBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        contactViewModel.getIsLoading();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewBinding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String et_title = viewBinding.editTitle.getText().toString();
                String et_desc = viewBinding.desEdit.getText().toString();

                if (!et_title.isEmpty() && !et_desc.isEmpty()){
                    Contact contact = new Contact(et_title,et_desc);
                    contactViewModel.insert(contact);
                }
                goToHomePage();
            }
        });
    }


    private void goToHomePage() {
        ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragment,new HomeFragment()).commit();
    }
}
