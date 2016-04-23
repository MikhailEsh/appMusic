package test.mainTest;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.main.hubluzar.musicapp.base.AnimatorMainActivity;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.base.LoaderData;
import com.main.hubluzar.musicapp.loader.LoaderDataImpl;

import java.util.ArrayList;

import test.supportObject.CreaterTestReaderJSONData;
import test.supportObject.AnimatorMainActivityTestSupport;
import test.supportObject.ItemMusicGroupTestSupport;

/**
 * Created by Агент on 19.04.2016.
 */
public class LoaderDataTest extends AndroidTestCase {
    private Context context;
    private LoaderData loaderDataTest;
    private CreaterTestReaderJSONData createrTestReaderJSONData;
    private ItemMusicGroupTestSupport itemMusicGroupTestSupport;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        context = getContext();
        setContext(context);
        assertNotNull(context);
        AnimatorMainActivity animatorMainActivityTest = new AnimatorMainActivityTestSupport();
        createrTestReaderJSONData = new CreaterTestReaderJSONData(context);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        loaderDataTest = new LoaderDataImpl(animatorMainActivityTest, context,
                createrTestReaderJSONData.getSuccesReaderJSONData(),requestQueue);
        itemMusicGroupTestSupport = new ItemMusicGroupTestSupport(context);
    }

    @SmallTest
    public void testLoaderData() throws Exception  {
        assertEquals(loaderDataTest.getSizeJSONArray(), createrTestReaderJSONData.getSizeOfExpextJSONArray());
        loaderDataTest.sendRequest();
        ArrayList<ItemMusicGroup> listItemMusicGroup = new ArrayList<ItemMusicGroup>();
        loaderDataTest.extentionListItemsMusicGroup(listItemMusicGroup, 0);
        assertEquals(loaderDataTest.getSizeJSONArray(), createrTestReaderJSONData.getSizeOfExpextJSONArray());
        ItemMusicGroup expextedItemMusicGroup = itemMusicGroupTestSupport.createTestItemMusicGroup();
        NetworkImageView networkImageView = new NetworkImageView(context);
        loaderDataTest.setImageUrl(networkImageView, expextedItemMusicGroup.getLinkSmallImage());
        itemMusicGroupTestSupport.checkItemMusicGroup(listItemMusicGroup.get(1), expextedItemMusicGroup);

    }
}
