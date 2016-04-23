package test.mainTest;

import android.support.v4.widget.SwipeRefreshLayout;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.view.ViewGroup;

import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.activity.MainActivity;
import com.main.hubluzar.musicapp.base.AdapterListGroups;
import com.main.hubluzar.musicapp.base.AnimatorMainActivity;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.base.LoaderData;
import com.main.hubluzar.musicapp.display.AdapterListGroupsImpl;
import com.main.hubluzar.musicapp.display.AnimatorMainActivityImpl;

import java.util.ArrayList;
import java.util.List;

import test.supportObject.ItemMusicGroupTestSupport;
import test.supportObject.LoaderDataTestSupport;

/**
 * Created by Агент on 23.04.2016.
 */
public class AdapterListGroupsTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;
    private ItemMusicGroupTestSupport itemMusicGroupTestSupport;
    private Integer countTestItemMusicGroup = 5;

    public AdapterListGroupsTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mainActivity = (MainActivity)getActivity();
        itemMusicGroupTestSupport = new ItemMusicGroupTestSupport(mainActivity);
    }

    @SmallTest
    public void testAdapterListGroups() throws Exception  {
        testAdapterListGroupsFunction();

        /*mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });*/
    }

    private void testAdapterListGroupsFunction() throws Exception
    {
        List<ItemMusicGroup> listItemsMusicGroup =  new ArrayList<ItemMusicGroup>();
        fillListItemsMusicGroup(listItemsMusicGroup);
        LoaderData loaderData = new LoaderDataTestSupport();
        AdapterListGroups adapterListGroups = new AdapterListGroupsImpl(mainActivity.getBaseContext(), listItemsMusicGroup, loaderData );
        if ( adapterListGroups.getCount() != countTestItemMusicGroup) throw new Exception();
        if ( adapterListGroups.getItemId(countTestItemMusicGroup - 1) != countTestItemMusicGroup - 1) throw new Exception();
        ItemMusicGroup itemMusicGroupView = (ItemMusicGroup)adapterListGroups.getItem(countTestItemMusicGroup - 1);
        itemMusicGroupTestSupport.checkItemMusicGroup(itemMusicGroupView, listItemsMusicGroup.get(countTestItemMusicGroup - 1));
        ItemMusicGroup itemMusicGroup = adapterListGroups.getItemMusicGroup(countTestItemMusicGroup - 1);
        itemMusicGroupTestSupport.checkItemMusicGroup(itemMusicGroup, listItemsMusicGroup.get(countTestItemMusicGroup - 1));
        adapterListGroups.notifyDataSetChanged();
    }

    private void fillListItemsMusicGroup(List<ItemMusicGroup> listItemsMusicGroup)
    {
        for ( int i = 0; i < countTestItemMusicGroup; i++)
            listItemsMusicGroup.add(itemMusicGroupTestSupport.createTestItemMusicGroup());
    }
}
