package com.NewsNow.newsnowMa.ApiService;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.NewsNow.newsnowMa.ImplemenApi.CallAble;
import com.NewsNow.newsnowMa.ImplemenApi.Constants;
import com.NewsNow.newsnowMa.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
   private String sentCategory;
    private ActionBarDrawerToggle mtoggle;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        mtoggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=findViewById(R.id.NavicationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                int id = Item.getItemId();
                switch (id) {
                    case R.id.Technology:
                        OpenListAct("technology");
                        return true;
                    case R.id.sop:
                        OpenListAct("sports");
                        return true;
                    case R.id.Health:
                        OpenListAct("health");
                        return true;
                        case R.id.science:
                        OpenListAct("science");
                            return true;
                    case R.id.masr:
                        Uri link=Uri.parse("https://www.almasryalyoum.com");
                        Intent in=new Intent(Intent.ACTION_VIEW,link);
                        startActivity(in);
                        return true;
                    case R.id.youm:
                        Uri link1=Uri.parse("https://www.youm7.com");
                        Intent i=new Intent(Intent.ACTION_VIEW,link1);
                        startActivity(i);
                        return true;
                    case R.id.figo:
                    Uri hh = Uri.parse("https://www.filgoal.com");
                        Intent intent=new Intent(Intent.ACTION_VIEW,hh);
                    startActivity(intent);
                    return true;
                        default:
                        return true;
                }
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        sentCategory=getIntent().getStringExtra(Constants.CATEGORY_KEY);
        loadData(sentCategory);


    }
    private void showListView(final ArrayList<Article>articles){
        CustomAdapter adapter=new CustomAdapter(articles,this);
        ListView lv=findViewById(R.id.lv);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uri link=Uri.parse(articles.get(i).getUrl());
                Intent intent=new Intent(Intent.ACTION_VIEW,link);
                startActivity(intent);

            }
        });
    }
    private void loadData(String category){
        final ProgressBar progressBar=findViewById(R.id.pb);

        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CallAble callAble= retrofit.create(CallAble.class);
        Call<NewsModel>newsModelCall= callAble.getData(category);


        newsModelCall.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                progressBar.setVisibility(View.GONE);
                NewsModel newsModel= response.body();
                assert newsModel != null;
                ArrayList<Article>articles=newsModel.articles;
                showListView(articles);
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "No Internet Access", Toast.LENGTH_LONG).show();
                Log.d("json","Error: "+t.getMessage());

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.ref_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mtoggle.onOptionsItemSelected(item)) {
            return true;
        } else if (item.getItemId() == R.id.refresh_id) {
            loadData(sentCategory);
            Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
            return true;}
            else
            return super.onOptionsItemSelected(item);
        }

    private void OpenListAct(String Key){
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra(Constants.CATEGORY_KEY,Key);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        getOnBackPressedDispatcher().onBackPressed();

    }

}
