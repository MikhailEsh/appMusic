package com.main.hubluzar.musicapp.loader;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.base.AnimatorMainActivity;
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

    private Context context;
    private ReaderJSONData readerJSONDate;
    private ImageLoader imageLoader;
    private AnimatorMainActivity animator;
    private RequestQueue requestQueue;
    final private int timeWaitRequest;

    public LoaderDataImpl(AnimatorMainActivity animator, Context context,  ReaderJSONData readerJSONDate, RequestQueue requestQueue) {
        this.context = context;
        this.animator = animator;
        this.readerJSONDate = readerJSONDate;
        this.requestQueue = requestQueue;
        this.imageLoader = new ImageLoader(requestQueue,
                new LruBitmapCache(context));
        this.timeWaitRequest = context.getResources().getInteger(R.integer.timeWaitRequest);
    }

    //Функция подгружает данные в список( сделано для того чтобы не парстиь весь Json подгружать данные по мере надобности
    public void extentionListItemsMusicGroup(List<ItemMusicGroup> listItemsMusicGroup, int position) {
        if (readerJSONDate == null ) return;
        readerJSONDate.extentionListItemsMusicGroup(listItemsMusicGroup, position);
    }

    public Integer getSizeJSONArray()
    {
        if (readerJSONDate == null) return 0;
        return readerJSONDate.getSizeJSONArray();
    }

    public void setImageUrl(NetworkImageView networkImageView, String url)
    {
        networkImageView.setImageUrl(url, imageLoader);
    }


    //Создаем запрос выставляем политику посылки сообщений
    public void sendRequest()
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
            animator.showErrorLoadNotice();
        }
    }
//создаем объект запролса, CachingJsonArrayRequest - расширяет JsonArrayRequest, меняет политику кэширования
    private CachingJsonArrayRequest createJsonObjectRequest() throws JSONException
    {
        animator.showWaitingProgressDialog();
        CachingJsonArrayRequest reqArray = new CachingJsonArrayRequest(context.getString(R.string.url),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(context.getString(R.string.log_tag_info), "Ответ получен");
                        readerJSONDate.setJSONArray(response);
                        animator.notifyAdapterData();
                        animator.dismissProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(context.getString(R.string.log_tag_error), this.getClass().getSimpleName());
                animator.dismissProgressDialog();
                animator.showErrorLoadNotice();
            }
        });
        reqArray.setShouldCache(Boolean.TRUE);
        return reqArray;
    }

}
