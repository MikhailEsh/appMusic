package test.mainTest.androidTestCase;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.main.hubluzar.musicapp.base.AnimatorMainActivity;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.base.LoaderData;
import com.main.hubluzar.musicapp.base.ReaderJSONData;
import com.main.hubluzar.musicapp.loader.LoaderDataImpl;

import java.util.ArrayList;

import test.supportObject.ReaderJSONDataTestSupport;
import test.supportObject.AnimatorMainActivityTestSupport;
import test.supportObject.ItemMusicGroupTestSupport;

/**
 * Created by Агент on 19.04.2016.
 */
public class LoaderDataTest extends AndroidTestCase {
    private Context context;
    private LoaderData loaderDataTest;
    private Integer sizeJSONArrayTest;
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
        //готовим данные для тестов
        AnimatorMainActivity animatorMainActivityTest = new AnimatorMainActivityTestSupport();
        ReaderJSONDataTestSupport readerJSONDataTestSupport = new ReaderJSONDataTestSupport(context);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        ReaderJSONData readerJSONDataTest = readerJSONDataTestSupport.getReaderJSONDataTest();
        sizeJSONArrayTest = readerJSONDataTest.getSizeJSONArray();
        loaderDataTest = new LoaderDataImpl(animatorMainActivityTest, context,
                readerJSONDataTest ,requestQueue);
        itemMusicGroupTestSupport = new ItemMusicGroupTestSupport(context);
    }


    @SmallTest
    public void testLoaderData() throws Exception  {
        loaderDataTest.sendRequest();
        ItemMusicGroup expextedItemMusicGroup = itemMusicGroupTestSupport.getFillItemMusicGroupTest();
        NetworkImageView networkImageView = new NetworkImageView(context);
        loaderDataTest.setImageUrl(networkImageView, expextedItemMusicGroup.getLinkSmallImage());
    }

}
