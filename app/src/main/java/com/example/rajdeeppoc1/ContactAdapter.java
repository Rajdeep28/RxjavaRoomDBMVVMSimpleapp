package com.example.rajdeeppoc1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHoldaer> {

    private List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHoldaer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list, parent,false);
        ContactViewHoldaer contactViewHoldaer = new ContactViewHoldaer(view);
        return contactViewHoldaer;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHoldaer holder, int position) {
        Contact contact = contactList.get(position);
        holder.tvTitle.setText(contact.getTitle());
        holder.tvDesc.setText(contact.getDescription());
        
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactViewHoldaer extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDesc;
        public ContactViewHoldaer(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.contact_title);
            tvDesc=itemView.findViewById(R.id.contact_desc);
        }
    }
}
