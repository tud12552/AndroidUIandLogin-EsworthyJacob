package com.example.jse58.androiduiandlogin_jacobesworthy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jse58.androiduiandlogin_jacobesworthy.R;

import java.util.ArrayList;
import java.util.List;

public class ViewAllUsers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<RecyclerViewItem> recyclerViewItems;
    private List<UserProfile> userProfiles;
    private UserProfilePersistence mUserProfilePersistence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewItems = new ArrayList<>();
        mUserProfilePersistence = new UserProfilePersistence(this);

        userProfiles = mUserProfilePersistence.getDataFromDB();

        for(UserProfile currentUserProf : userProfiles)
        {
            RecyclerViewItem recyclerViewItem = new RecyclerViewItem(currentUserProf.getName() + " " + currentUserProf.getLastName(), currentUserProf.getBday());
            recyclerViewItems.add(recyclerViewItem);
        }

        adapter = new RecyclerViewAdapter(recyclerViewItems, this);

        recyclerView.setAdapter(adapter);
    }
}
