package com.main.hubluzar.musicapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.main.hubluzar.musicapp.adapter.AdapterListGroups;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.base.LoaderData;
import com.main.hubluzar.musicapp.base.ReaderJSONData;
import com.main.hubluzar.musicapp.loader.LoaderDataImpl;
import com.main.hubluzar.musicapp.loader.ReaderJSONDataImpl;

import android.app.ProgressDialog;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;
    private AdapterListGroups adapter;
    private ReaderJSONData readerJSONDate;
    private ProgressDialog progressDialog;
    LoaderData loaderData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        settingViewElement();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        readerJSONDate = new ReaderJSONDataImpl(MainActivity.this);
        loaderData = new LoaderDataImpl(progressDialog, MainActivity.this, readerJSONDate, requestQueue);
        loaderData.sendRequest(requestQueue);
        adapter = new AdapterListGroups(this, new ArrayList<ItemMusicGroup>(), loaderData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void settingViewElement()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_title_main);
        listView = (ListView) findViewById(R.id.listView);
        settingProgressDialog();
    }

    public void notifyAdapterData()
    {
        adapter.notifyDataSetChanged();
    }

    private void settingProgressDialog()
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.process_dialog_loading));
        progressDialog.setCancelable(false);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, GroupMusicActivity.class);
        ItemMusicGroup currentGroupActivity = adapter.getItemMusicGroup(position);
        intent.putExtra(getString(R.string.common_labelGroup_description), currentGroupActivity.getDescription());
        intent.putExtra(getString(R.string.common_labelGroup_name), currentGroupActivity.getName());
        intent.putExtra(getString(R.string.common_labelGroup_linkBigImage), currentGroupActivity.getLinkBigImage());
        startActivity(intent);
    }
}
