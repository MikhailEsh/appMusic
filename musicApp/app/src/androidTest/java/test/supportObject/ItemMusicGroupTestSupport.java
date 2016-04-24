package test.supportObject;

import android.content.Context;
import android.test.AndroidTestCase;

import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.contentExec.ItemMusicGroupImpl;

/**
 * Created by Агент on 20.04.2016.
 */
public class ItemMusicGroupTestSupport {

    private Context context;
    private Integer positionFillItemMusicGroupTest;
    private Integer positionEmptyItemMusicGroupTest;

    public Integer getPositionFillItemMusicGroupTest() {
        return positionFillItemMusicGroupTest;
    }

    public Integer getPositionEmptyItemMusicGroupTest() {
        return positionEmptyItemMusicGroupTest;
    }

    public ItemMusicGroupTestSupport(Context context) {
        this.context = context;
        positionFillItemMusicGroupTest = 3;
        positionEmptyItemMusicGroupTest = 0;
    }

    public ItemMusicGroupImpl getEmptyItemMusicGroupTest()
    {
        String name = "Tove Lo";
        Integer id = null;
        String[] testGenres = null;
        Integer tracks = null;
        String link = null;
        Integer albums = null;
        String testDescription = null;
        String linkSmallImage = null;
        String linkBigImage = null;
        ItemMusicGroupImpl testItemMusicGroup = new ItemMusicGroupImpl(name, testDescription, link, linkSmallImage, linkBigImage, albums, tracks, id, testGenres, context);
        return testItemMusicGroup;
    }

    public ItemMusicGroupImpl getFillItemMusicGroupTest()
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
        ItemMusicGroupImpl testItemMusicGroup = new ItemMusicGroupImpl(name, testDescription, link, linkSmallImage, linkBigImage, albums, tracks, id, testGenres, context);
        return testItemMusicGroup;
    }

    public void assertEqualsItemMusicGroup(ItemMusicGroup currentItemMusicGroupList, ItemMusicGroup expextedItemMusicGroup) throws Exception
    {
        AndroidTestCase.assertEquals(currentItemMusicGroupList.getName(), expextedItemMusicGroup.getName());
        AndroidTestCase.assertEquals(currentItemMusicGroupList.getDescription(), expextedItemMusicGroup.getDescription());
        AndroidTestCase.assertEquals(currentItemMusicGroupList.getLink(), expextedItemMusicGroup.getLink());
        AndroidTestCase.assertEquals(currentItemMusicGroupList.getLinkSmallImage(), expextedItemMusicGroup.getLinkSmallImage());
        AndroidTestCase.assertEquals(currentItemMusicGroupList.getLinkBigImage(), expextedItemMusicGroup.getLinkBigImage());
        AndroidTestCase.assertEquals(currentItemMusicGroupList.getTracks(), expextedItemMusicGroup.getTracks());
        AndroidTestCase.assertEquals(currentItemMusicGroupList.getAlbums(), expextedItemMusicGroup.getAlbums());
        AndroidTestCase.assertEquals(currentItemMusicGroupList.getId(), expextedItemMusicGroup.getId());
        String[] currentGenres = currentItemMusicGroupList.getGenres();
        String[] expextedGenres = expextedItemMusicGroup.getGenres();
        if ( currentGenres == null && expextedGenres == null) return;
        AndroidTestCase.assertEquals(currentGenres.length, expextedGenres.length);
        for ( int i = 0 ; i < currentGenres.length; i++ )
            AndroidTestCase.assertEquals(currentGenres[i], expextedGenres[i]);
    }
}
