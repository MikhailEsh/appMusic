package com.main.hubluzar.musicapp.loader;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.Response.Listener;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Агент on 13.04.2016.
 */

//Выставляем параметры кэширования
public class CachingJsonArrayRequest extends JsonArrayRequest {

    //static private final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
    static private final long cacheHitButRefreshed = 0; // in 0 minutes cache will be hit, but also refreshed on background
    static private final long cacheExpired = 24 * 60 * 60 * 1000;   // in 24 hours this cache entry expires completely

    public CachingJsonArrayRequest(String url,
                         Listener<JSONArray> listener, ErrorListener errorListener)
            throws JSONException {
        super(Method.GET ,url, listener,
                errorListener);
        this.setShouldCache(Boolean.TRUE);
    }

    //Переопределяем обработку ответа, для этого подменяем parseCacheHeaders на parseIgnoreCacheHeaders
    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
            return Response.success(new JSONArray(jsonString), parseIgnoreCacheHeaders(response));
        }catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    //parseIgnoreCacheHeaders - полностю перекладываем ответ выставляя нужные нам   entry.softTtl = softExpire; entry.ttl
    //которые отвечают за время жизни КЭШа
    public static Cache.Entry parseIgnoreCacheHeaders(NetworkResponse response) {
        long now = System.currentTimeMillis();

        Map<String, String> headers = response.headers;
        long serverDate = 0;
        String serverEtag = null;
        String headerValue;

        headerValue = headers.get("Date");
        if (headerValue != null) {
            serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
        }

        serverEtag = headers.get("ETag");
        final long softExpire = now + cacheHitButRefreshed;
        final long ttl = now + cacheExpired;

        Cache.Entry entry = new Cache.Entry();
        entry.data = response.data;
        entry.etag = serverEtag;
        entry.softTtl = softExpire;
        entry.ttl = ttl;
        entry.serverDate = serverDate;
        entry.responseHeaders = headers;

        return entry;
    }
}
