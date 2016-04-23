package test.mainTest;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.main.hubluzar.musicapp.base.ItemMusicGroup;

import java.util.ArrayList;

import test.supportObject.CreaterTestReaderJSONData;
import test.supportObject.TestItemMusicGroup;

import  com.main.hubluzar.musicapp.base.ReaderJSONData;

/**
 * Created by Агент on 16.04.2016.
 */
public class ReaderJSONDataTest extends AndroidTestCase {

    private Context context;
    private CreaterTestReaderJSONData createrTestReaderJSONData;
    private ReaderJSONData readerJSONData;
    private TestItemMusicGroup testItemMusicGroup;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        context = getContext();
        setContext(context);
        assertNotNull(context);
        createrTestReaderJSONData = new CreaterTestReaderJSONData(context);
        readerJSONData = createrTestReaderJSONData.getSuccesReaderJSONData();
        testItemMusicGroup = new TestItemMusicGroup(context);
    }

    @SmallTest
    public void testReaderJSONDataSucces() throws Exception  {
        ArrayList<ItemMusicGroup> listItemMusicGroup = new ArrayList<ItemMusicGroup>();
        readerJSONData.extentionListItemsMusicGroup(listItemMusicGroup, 0);
        assertEquals(readerJSONData.getSizeJSONArray(), createrTestReaderJSONData.getSizeOfExpextJSONArray());
        ItemMusicGroup expextedItemMusicGroup = testItemMusicGroup.createTestItemMusicGroup();
        testItemMusicGroup.checkItemMusicGroup(listItemMusicGroup.get(1), expextedItemMusicGroup);
    }


}
