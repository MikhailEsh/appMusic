package test.mainTest.ActivityInstrumentationTestCase2;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.main.hubluzar.musicapp.activity.MainActivity;
import com.main.hubluzar.musicapp.base.AdapterListGroups;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.base.LoaderData;
import com.main.hubluzar.musicapp.display.AdapterListGroupsImpl;

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
    AdapterListGroups adapterListGroups;
    List<ItemMusicGroup> listItemsMusicGroup;

    public AdapterListGroupsTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mainActivity = (MainActivity)getActivity();
        createTestData();
    }

    private void createTestData()
    {
        //готовим данные для тестов
        itemMusicGroupTestSupport = new ItemMusicGroupTestSupport(mainActivity);
        LoaderData loaderData = new LoaderDataTestSupport();
        listItemsMusicGroup =  new ArrayList<ItemMusicGroup>();
        fillListItemsMusicGroup( listItemsMusicGroup);
        adapterListGroups = new AdapterListGroupsImpl(mainActivity.getBaseContext(), listItemsMusicGroup, loaderData, countTestItemMusicGroup  );
    }

    @SmallTest
    public void testAdapterListGroups() throws Exception  {
        if ( adapterListGroups.getCount() != countTestItemMusicGroup) throw new Exception();
        if ( adapterListGroups.getItemId(countTestItemMusicGroup - 1) != countTestItemMusicGroup - 1) throw new Exception();
        ItemMusicGroup itemMusicGroupView = (ItemMusicGroup)adapterListGroups.getItem(countTestItemMusicGroup - 1);
        itemMusicGroupTestSupport.assertEqualsItemMusicGroup(itemMusicGroupView, listItemsMusicGroup.get(countTestItemMusicGroup - 1));
        ItemMusicGroup itemMusicGroup = adapterListGroups.getItemMusicGroup(countTestItemMusicGroup - 1);
        itemMusicGroupTestSupport.assertEqualsItemMusicGroup(itemMusicGroup, listItemsMusicGroup.get(countTestItemMusicGroup - 1));
        adapterListGroups.notifyDataSetChanged();
    }

    private void fillListItemsMusicGroup(List<ItemMusicGroup> listItemsMusicGroup)
    {
        for ( int i = 0; i < countTestItemMusicGroup; i++)
            listItemsMusicGroup.add(itemMusicGroupTestSupport.getFillItemMusicGroupTest());
    }
}
