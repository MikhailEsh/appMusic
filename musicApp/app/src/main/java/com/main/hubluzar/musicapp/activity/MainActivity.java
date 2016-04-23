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
import com.main.hubluzar.musicapp.base.AdapterListGroups;
import com.main.hubluzar.musicapp.base.AnimatorMainActivity;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.display.AdapterListGroupsImpl;
import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.base.LoaderData;
import com.main.hubluzar.musicapp.base.ReaderJSONData;
import com.main.hubluzar.musicapp.display.AnimatorMainActivityImpl;
import com.main.hubluzar.musicapp.loader.LoaderDataImpl;
import com.main.hubluzar.musicapp.contentExec.ReaderJSONDataImpl;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;
    private AdapterListGroups adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        settingViewElement();
        //Создаем общие объекты для работы приложения
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //readerJSONDate Объект парсит json
        ReaderJSONData readerJSONDate = new ReaderJSONDataImpl(MainActivity.this);
        //loaderData Объект занимается закгрузкой данных, обработкой
        AnimatorMainActivity animator = new AnimatorMainActivityImpl(MainActivity.this);
        LoaderData loaderData = new LoaderDataImpl(animator, MainActivity.this, readerJSONDate, requestQueue);
        loaderData.sendRequest();
        settingAdapter(animator, loaderData);
    }

    private void settingAdapter(AnimatorMainActivity animator, LoaderData loaderData)
    {
        AdapterListGroupsImpl adapterImpl = new AdapterListGroupsImpl(this, new ArrayList<ItemMusicGroup>(), loaderData);
        listView.setAdapter(adapterImpl);
        adapter = adapterImpl;
        animator.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void settingViewElement()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_title_main);
        listView = (ListView) findViewById(R.id.listView);
    }

    //обработка нажатия на view в ListView, вызываем GroupMusicActivity
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, GroupMusicActivity.class);
        ItemMusicGroup currentGroupActivity = adapter.getItemMusicGroup(position);
        intent.putExtra(getString(R.string.common_labelGroup_description), currentGroupActivity.getDescription());
        //Передаем уже готовые блоки текстов для track, genres, albums
        intent.putExtra(getString(R.string.common_labelGroup_tracks), currentGroupActivity.getTracksString());
        intent.putExtra(getString(R.string.common_labelGroup_albums), currentGroupActivity.getAlbumsString());
        intent.putExtra(getString(R.string.common_labelGroup_genresArray), currentGroupActivity.getGenresString());

        intent.putExtra(getString(R.string.common_labelGroup_link), currentGroupActivity.getLink());
        intent.putExtra(getString(R.string.common_labelGroup_name), currentGroupActivity.getName());
        intent.putExtra(getString(R.string.common_labelGroup_linkBigImage), currentGroupActivity.getLinkBigImage());
        startActivity(intent);
    }
}
