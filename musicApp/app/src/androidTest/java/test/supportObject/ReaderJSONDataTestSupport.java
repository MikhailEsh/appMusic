package test.supportObject;

import android.content.Context;
import android.util.Log;

import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.base.ReaderJSONData;
import com.main.hubluzar.musicapp.contentExec.ReaderJSONDataImpl;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by Агент on 20.04.2016.
 */
public class ReaderJSONDataTestSupport implements ReaderJSONData {

    Context context;
    JSONArray jsonArray;
    private Integer testSizeOfJsonArray = 5;

    public Integer getTestSizeOfList() {
        return testSizeOfList;
    }

    private Integer testSizeOfList = 4;

    public ReaderJSONDataTestSupport(Context context) {
        this.context = context;
        this.jsonArray = getTestJsonArray();
    }

    public JSONArray getTestJsonArray()
    {
        jsonArray = null;
        try {
            jsonArray = new JSONArray(getDataTestJson());
        } catch (JSONException e) {
            Log.d(context.getString(R.string.log_unit_test_error), this.getClass().getSimpleName() + " Некорректный JSON");
        }
        return  jsonArray;
    }

    public ReaderJSONData getReaderJSONDataTest()
    {
        ReaderJSONData readerJSONData = new ReaderJSONDataImpl(context);
        readerJSONData.setJSONArray(jsonArray);
        return readerJSONData;
    }

    private String getDataTestJson()
    {
        String dataTestJson = "[{\"name\":\"Tove Lo\"},{\"id\":2915,\"name\":\"Ne-Yo\",\"genres\":[\"rnb\",\"pop\",\"rap\"],\"tracks\":256,\"albums\":152,\"link\":\"http://www.neyothegentleman.com/\",\"description\":\"обладатель трёх премии Грэмми, американский певец, автор песен, продюсер, актёр, филантроп. В 2009 году журнал Billboard поставил Ни-Йо на 57 место в рейтинге «Артисты десятилетия».\",\"cover\":{\"small\":\"http://avatars.yandex.net/get-music-content/15ae00fc.p.2915/300x300\",\"big\":\"http://avatars.yandex.net/get-music-content/15ae00fc.p.2915/1000x1000\"}},{\"id\":91546,\"name\":\"Usher\",\"genres\":[\"rnb\",\"pop\",\"rap\"],\"tracks\":450,\"albums\":183,\"link\":\"http://usherworld.com/\",\"description\":\"американский певец и актёр. Один из самых коммерчески успешных R&B-музыкантов афроамериканского происхождения. В настоящее время продано более 65 миллионов копий его альбомов по всему миру. Выиграл семь премий «Грэмми», четыре премии World Music Awards, четыре премии American Music Award и девятнадцать премий Billboard Music Awards. Владелец собственной звукозаписывающей компании US Records. Он занимает 21 место в списке самых успешных музыкантов по версии Billboard, а также второе место, уступив Эминему в списке самых успешных музыкантов 2000-х годов. В 2010 году журнал Glamour включил его в список 50 самых сексуальных мужчин.\",\"cover\":{\"small\":\"http://avatars.yandex.net/get-music-content/b0e14f75.p.91546/300x300\",\"big\":\"http://avatars.yandex.net/get-music-content/b0e14f75.p.91546/1000x1000\"}},{\"id\":100500,\"name\":\"Jay Sean\",\"genres\":[\"pop\",\"rap\",\"rnb\"],\"tracks\":106,\"albums\":38,\"description\":\"британский рэпер, являющийся выходцем из Индии. Родился в западном Лондоне, Англия. Выпустил три альбома Me Against Myself, My Own Way и All or Nothing.\",\"cover\":{\"small\":\"http://avatars.yandex.net/get-music-content/db35e57a.p.100500/300x300\",\"big\":\"http://avatars.yandex.net/get-music-content/db35e57a.p.100500/1000x1000\"}},{\"id\":74614,\"genres\":[\"rnb\",\"pop\",\"rap\"],\"tracks\":174,\"albums\":106,\"link\":\"http://www.kellyrowland.com/\",\"description\":\"американская певица и актриса. Выступает в стиле современный ритм-энд-блюз, является автором текстов песен.\",\"cover\":{\"small\":\"http://avatars.yandex.net/get-music-content/be7f0f49.p.74614/300x300\",\"big\":\"http://avatars.yandex.net/get-music-content/be7f0f49.p.74614/1000x1000\"}}]";
        return dataTestJson;
    }


    @Override
    public void extentionListItemsMusicGroup(List<ItemMusicGroup> listItemsMusicGroup, int position, int sizeOfExtention) {

    }

    @Override
    public Integer getSizeJSONArray() {
        return testSizeOfJsonArray;
    }

    @Override
    public void setJSONArray(JSONArray jSONArray) {

    }
}
