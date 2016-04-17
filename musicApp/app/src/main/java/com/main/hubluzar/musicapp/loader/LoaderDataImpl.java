package com.main.hubluzar.musicapp.loader;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.activity.MainActivity;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.base.LoaderData;
import com.main.hubluzar.musicapp.base.ReaderJSONData;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by Агент on 17.04.2016.
 */
public class LoaderDataImpl implements LoaderData {

    private ProgressDialog progressDialog;
    private Context context;
    private MainActivity mainActivity;
    private ReaderJSONData readerJSONDate;
    private ImageLoader imageLoader;
    final private int timeWaitRequest;

    public LoaderDataImpl(ProgressDialog progressDialog, MainActivity mainActivity, ReaderJSONData readerJSONDate, RequestQueue requestQueue) {
        this.progressDialog = progressDialog;
        this.context = mainActivity.getBaseContext();
        this.mainActivity = mainActivity;
        this.readerJSONDate = readerJSONDate;
        this.imageLoader = new ImageLoader(requestQueue,
                new LruBitmapCache(context));
        this.timeWaitRequest = context.getResources().getInteger(R.integer.timeWaitRequest);
    }

    public void extentionListItemsMusicGroup(List<ItemMusicGroup> listItemsMusicGroup, int position) {
        if (readerJSONDate == null ) return;
        readerJSONDate.extentionListItemsMusicGroup(listItemsMusicGroup, position);
    }

    public int getSizeJSONArray()
    {
        if (readerJSONDate == null) return 0;
        return readerJSONDate.getSizeJSONArray();
    }

    public void sendRequest(RequestQueue requestQueue)
    {
        CachingJsonArrayRequest jsonReq;
        try {
            jsonReq = createJsonObjectRequest();
            jsonReq.setRetryPolicy(new DefaultRetryPolicy(timeWaitRequest,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonReq);
        } catch (JSONException e)
        {
            Toast errorToast = Toast.makeText(context, context.getString(R.string.toast_errorDownload), Toast.LENGTH_LONG);
            errorToast.show();
        }
    }

    private CachingJsonArrayRequest createJsonObjectRequest() throws JSONException
    {
        progressDialog.show();
        CachingJsonArrayRequest reqArray = new CachingJsonArrayRequest(context.getString(R.string.url),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(context.getString(R.string.log_tag_info), "Ответ получен");
                        readerJSONDate.setJSONArray(response);
                        mainActivity.notifyAdapterData();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(context.getString(R.string.log_tag_error), this.getClass().getSimpleName());
                progressDialog.dismiss();
                Toast errorToast = Toast.makeText(context, context.getString(R.string.toast_errorDownload), Toast.LENGTH_LONG);
                errorToast.show();
            }
        });
        reqArray.setShouldCache(Boolean.TRUE);
        return reqArray;
    }

    public ImageLoader getImageLoader ()
    {
        return this.imageLoader;
    }
}
