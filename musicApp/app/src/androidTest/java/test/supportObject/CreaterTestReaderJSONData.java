package test.supportObject;

import android.content.Context;
import android.util.Log;

import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.contentExec.ReaderJSONDataImpl;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Агент on 20.04.2016.
 */
public class CreaterTestReaderJSONData {

    Context context;
    JSONArray jsonArray;

    public CreaterTestReaderJSONData(Context context) {
        this.context = context;
        this.jsonArray = getTestJsonArray();
    }

    private JSONArray getTestJsonArray()
    {
        jsonArray = null;
        try {
            jsonArray = new JSONArray(getDataTestJson());
        } catch (JSONException e) {
            Log.d(context.getString(R.string.log_unit_test_error), this.getClass().getSimpleName() + " Некорректный JSON");
        }
        return  jsonArray;
    }

    public com.main.hubluzar.musicapp.base.ReaderJSONData getSuccesReaderJSONData()
    {
        com.main.hubluzar.musicapp.base.ReaderJSONData readerJSONData = new ReaderJSONDataImpl(context);
        readerJSONData.setJSONArray(jsonArray);
        return readerJSONData;
    }

    private String getDataTestJson()
    {
        String dataTestJson = "[{\"id\":1080505,\"name\":\"Tove Lo\",\"genres\":[\"pop\",\"dance\",\"electronics\"],\"tracks\":81,\"albums\":22,\"link\":\"http://www.tove-lo.com/\",\"description\":\"шведская певица и автор песен. Она привлекла к себе внимание в 2013 году с выпуском сингла «Habits», но настоящего успеха добилась с ремиксом хип-хоп продюсера Hippie Sabotage на эту песню, который получил название «Stay High». 4 марта 2014 года вышел её дебютный мини-альбом Truth Serum, а 24 сентября этого же года дебютный студийный альбом Queen of the Clouds. Туве Лу является автором песен таких артистов, как Icona Pop, Girls Aloud и Шер Ллойд.\",\"cover\":{\"small\":\"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/300x300\",\"big\":\"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/1000x1000\"}},{\"id\":100500,\"name\":\"Jay Sean\",\"genres\":[\"pop\",\"rap\",\"rnb\"],\"tracks\":106,\"albums\":38,\"description\":\"британский рэпер, являющийся выходцем из Индии. Родился в западном Лондоне, Англия. Выпустил три альбома Me Against Myself, My Own Way и All or Nothing.\",\"cover\":{\"small\":\"http://avatars.yandex.net/get-music-content/db35e57a.p.100500/300x300\",\"big\":\"http://avatars.yandex.net/get-music-content/db35e57a.p.100500/1000x1000\"}}]";
        return dataTestJson;
    }



    public Integer getSizeOfExpextJSONArray() {
        final  Integer sizeOfExpextJSONArray= 2;
        return sizeOfExpextJSONArray;
    }

}
