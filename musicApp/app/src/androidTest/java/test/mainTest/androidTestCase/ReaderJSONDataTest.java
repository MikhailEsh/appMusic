package test.mainTest.androidTestCase;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.main.hubluzar.musicapp.base.ItemMusicGroup;

import java.util.ArrayList;

import test.supportObject.ReaderJSONDataTestSupport;
import test.supportObject.ItemMusicGroupTestSupport;

import  com.main.hubluzar.musicapp.base.ReaderJSONData;
import com.main.hubluzar.musicapp.contentExec.ReaderJSONDataImpl;

/**
 * Created by Агент on 16.04.2016.
 */
public class ReaderJSONDataTest extends AndroidTestCase {

    private Context context;
    private ReaderJSONDataTestSupport readerJSONDataTestSupport;
    private ReaderJSONData readerJSONData, readerJSONDataExpect;
    private ItemMusicGroupTestSupport itemMusicGroupTestSupport;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        context = getContext();
        setContext(context);
        assertNotNull(context);
        createTestData();
    }

    private void createTestData()
    {
        readerJSONDataTestSupport = new ReaderJSONDataTestSupport(context);
        readerJSONDataExpect = readerJSONDataTestSupport.getReaderJSONDataTest();
        readerJSONData = new ReaderJSONDataImpl(context);
        itemMusicGroupTestSupport = new ItemMusicGroupTestSupport(context);
    }

    @SmallTest
    public void testReaderJSONDataSucces() throws Exception  {
        readerJSONData.setJSONArray(readerJSONDataTestSupport.getTestJsonArray());
        assertEquals(readerJSONData.getSizeJSONArray(), readerJSONDataExpect.getSizeJSONArray());

        ArrayList<ItemMusicGroup> listItemMusicGroupTest = new ArrayList<ItemMusicGroup>();

        readerJSONData.extentionListItemsMusicGroup(listItemMusicGroupTest, 0, readerJSONDataTestSupport.getTestSizeOfList());
        assertEquals(readerJSONData.getSizeJSONArray(), readerJSONDataExpect.getSizeJSONArray());

        Integer positionFillItemMusicGroupTest = itemMusicGroupTestSupport.getPositionFillItemMusicGroupTest();
        itemMusicGroupTestSupport.assertEqualsItemMusicGroup(listItemMusicGroupTest.get(positionFillItemMusicGroupTest),
                itemMusicGroupTestSupport.getFillItemMusicGroupTest());

        Integer positionEmptyItemMusicGroupTest = itemMusicGroupTestSupport.getPositionEmptyItemMusicGroupTest();

        itemMusicGroupTestSupport.assertEqualsItemMusicGroup(listItemMusicGroupTest.get(positionEmptyItemMusicGroupTest),
                itemMusicGroupTestSupport.getEmptyItemMusicGroupTest());
    }


}
