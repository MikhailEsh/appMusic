package test;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.base.ReaderJSONData;
import com.main.hubluzar.musicapp.loader.ReaderJSONDataImpl;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Агент on 16.04.2016.
 */
public class ReaderDataTest extends AndroidTestCase {

    Context context;
    JSONArray jSONArray = null;

    final private Integer sizeOfExpextJSONArray= 2;


    private ItemMusicGroup createExpextedItemMusicGroup()
    {
        String name = "Jay Sean";
        Integer id = 100500;
        String[] testGenres = new String[3];
        testGenres[0] = "pop";
        testGenres[1] = "rap";
        testGenres[2] = "rnb";
        Integer tracks = 106;
        String link = null;
        Integer albums = 38;
        String testDescription = "британский рэпер, являющийся выходцем из Индии. Родился в западном Лондоне, Англия. Выпустил три альбома Me Against Myself, My Own Way и All or Nothing.";
        String linkSmallImage = "http://avatars.yandex.net/get-music-content/db35e57a.p.100500/300x300";
        String linkBigImage = "http://avatars.yandex.net/get-music-content/db35e57a.p.100500/1000x1000";
        ItemMusicGroup testItemMusicGroup = new ItemMusicGroup(name, testDescription, link, linkSmallImage, linkBigImage, albums, tracks, id, testGenres);
        return testItemMusicGroup;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        context = getContext();
        setContext(context);
        assertNotNull(context);
        try {
            jSONArray = new JSONArray(getDataTestJson());
        } catch (JSONException e) {
                Log.d(context.getString(R.string.log_unit_test_error), this.getClass().getSimpleName() + " Некорректный JSON");
        }
        Log.d(context.getString(R.string.log_unit_test_info), this.getClass().getSimpleName() + " JSON создан");
    }

    @SmallTest
    public void testReaderJSONDataSucces() throws Exception  {

        ReaderJSONData readerJSONData = getSuccesReaderJSONData();
        ArrayList<ItemMusicGroup> listItemMusicGroup = new ArrayList<ItemMusicGroup>();
        readerJSONData.extentionListItemsMusicGroup(listItemMusicGroup, 0);
        assertEquals(readerJSONData.getSizeJSONArray(), sizeOfExpextJSONArray);
        ItemMusicGroup expextedItemMusicGroup = createExpextedItemMusicGroup();
        checkItemMusicGroup(listItemMusicGroup.get(1), expextedItemMusicGroup);
    }

    private void checkItemMusicGroup(ItemMusicGroup currentItemMusicGroupList, ItemMusicGroup expextedItemMusicGroup) throws Exception
    {
        assertEquals(currentItemMusicGroupList.getName(), expextedItemMusicGroup.getName());
        assertEquals(currentItemMusicGroupList.getDescription(), expextedItemMusicGroup.getDescription());
        assertEquals(currentItemMusicGroupList.getLink(), expextedItemMusicGroup.getLink());
        assertEquals(currentItemMusicGroupList.getLinkSmallImage(), expextedItemMusicGroup.getLinkSmallImage());
        assertEquals(currentItemMusicGroupList.getLinkBigImage(), expextedItemMusicGroup.getLinkBigImage());
        assertEquals(currentItemMusicGroupList.getTracks(), expextedItemMusicGroup.getTracks());
        assertEquals(currentItemMusicGroupList.getAlbums(), expextedItemMusicGroup.getAlbums());
        assertEquals(currentItemMusicGroupList.getId(), expextedItemMusicGroup.getId());
        String[] currentGenres = currentItemMusicGroupList.getGenres();
        String[] expextedGenres = expextedItemMusicGroup.getGenres();
        assertEquals(currentGenres.length, expextedGenres.length);
        for ( int i = 0 ; i < currentGenres.length; i++ )
            assertEquals(currentGenres[i], expextedGenres[i]);
    }

    private ReaderJSONData getSuccesReaderJSONData()
    {
        ReaderJSONData readerJSONData = new ReaderJSONDataImpl(context);
        readerJSONData.setJSONArray(jSONArray);
        return readerJSONData;
    }

    private String getDataTestJson()
    {
        String dataTestJson = "[{\"id\":1080505,\"name\":\"Tove Lo\",\"genres\":[\"pop\",\"dance\",\"electronics\"],\"tracks\":81,\"albums\":22,\"link\":\"http://www.tove-lo.com/\",\"description\":\"шведская певица и автор песен. Она привлекла к себе внимание в 2013 году с выпуском сингла «Habits», но настоящего успеха добилась с ремиксом хип-хоп продюсера Hippie Sabotage на эту песню, который получил название «Stay High». 4 марта 2014 года вышел её дебютный мини-альбом Truth Serum, а 24 сентября этого же года дебютный студийный альбом Queen of the Clouds. Туве Лу является автором песен таких артистов, как Icona Pop, Girls Aloud и Шер Ллойд.\",\"cover\":{\"small\":\"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/300x300\",\"big\":\"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/1000x1000\"}},{\"id\":100500,\"name\":\"Jay Sean\",\"genres\":[\"pop\",\"rap\",\"rnb\"],\"tracks\":106,\"albums\":38,\"description\":\"британский рэпер, являющийся выходцем из Индии. Родился в западном Лондоне, Англия. Выпустил три альбома Me Against Myself, My Own Way и All or Nothing.\",\"cover\":{\"small\":\"http://avatars.yandex.net/get-music-content/db35e57a.p.100500/300x300\",\"big\":\"http://avatars.yandex.net/get-music-content/db35e57a.p.100500/1000x1000\"}}]";
        return dataTestJson;
    }
}
