package com.main.hubluzar.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.main.hubluzar.musicapp.Adapter.AdapterListGroups;
import android.app.ProgressDialog;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;
    private AdapterListGroups adapter;
    private ReaderJSONMusicGroup readerJSONMusicGroup;
    ProgressDialog PD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        settingViewElement();
        RequestQueue requestQueue = sendRequest();
        readerJSONMusicGroup = new ReaderJSONMusicGroup(MainActivity.this);
        adapter = new AdapterListGroups(this, new ArrayList<ItemMusicGroup>(), requestQueue, readerJSONMusicGroup);
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

    private RequestQueue sendRequest()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonReq = createJsonObjectRequest();
        jsonReq.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonReq);
        return requestQueue;
    }

    private void settingProgressDialog()
    {
        PD = new ProgressDialog(this);
        PD.setMessage(getString(R.string.process_dialog_loading));
        PD.setCancelable(false);
    }

    private JsonArrayRequest createJsonObjectRequest()
    {
        PD.show();
        JsonArrayRequest reqArray = new JsonArrayRequest(getString(R.string.url),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(getString(R.string.log_tag_info), "Ответ получен");
                        readerJSONMusicGroup.setjSONArray(response);
                        adapter.notifyDataSetChanged();
                        PD.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(getString(R.string.log_tag_error), this.getClass().getSimpleName());
                PD.dismiss();
                Toast errorToast = Toast.makeText(MainActivity.this, getString(R.string.toast_errorDownload), Toast.LENGTH_LONG);
                errorToast.show();
            }
        });
        return reqArray;
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
