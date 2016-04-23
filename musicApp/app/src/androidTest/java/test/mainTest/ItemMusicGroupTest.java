package test.mainTest;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;

import java.util.ArrayList;

import test.supportObject.CreaterTestReaderJSONData;
import test.supportObject.TestItemMusicGroup;

/**
 * Created by Агент on 21.04.2016.
 */
public class ItemMusicGroupTest extends AndroidTestCase {

    private Context context;
    TestItemMusicGroup testItemMusicGroup;
    String generesString, tracksString, albumsString;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        context = getContext();
        setContext(context);
        assertNotNull(context);
        testItemMusicGroup = new TestItemMusicGroup(context);
        generesString = "pop rap rnb ";
        tracksString = "106" + " " + context.getString(R.string.list_item_textView_countSing);
        albumsString = "38" + " " + context.getString(R.string.list_item_textView_countAlbum);
    }

    @SmallTest
    public void testReaderJSONDataSucces() throws Exception  {
        ItemMusicGroup itemMusicGroup = testItemMusicGroup.createTestItemMusicGroup();
        assertEquals(itemMusicGroup.getGenresString(), generesString);
        assertEquals(itemMusicGroup.getTracksString(), tracksString);
        assertEquals(itemMusicGroup.getAlbumsString(), albumsString);

    }

}
