package test.mainTest;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.activity.MainActivity;
import com.main.hubluzar.musicapp.base.AdapterListGroups;
import com.main.hubluzar.musicapp.base.AnimatorMainActivity;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.display.AnimatorMainActivityImpl;

import java.util.ArrayList;

import test.supportObject.AdapterListGroupsTest;
import test.supportObject.CreaterTestReaderJSONData;
import test.supportObject.TestItemMusicGroup;

/**
 * Created by Агент on 23.04.2016.
 */
public class AnimatorMainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity mainActivity;

    public AnimatorMainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mainActivity = (MainActivity)getActivity();
    }

    @SmallTest
    public void testAnimatorMainActivity() throws Exception  {
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                testAnimatorMainActivityFunction();
            }
        });
    }

    private void testAnimatorMainActivityFunction()
    {
        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) mainActivity.findViewById(R.id.main_refresh_layout);
        AnimatorMainActivity animatorMainActivity = new AnimatorMainActivityImpl(mainActivity.getBaseContext(), mSwipeRefreshLayout);

        animatorMainActivity.showWaitingDialogLoading();
        animatorMainActivity.finishedDialogLoadingSucces();

        animatorMainActivity.notifyAdapterData();

        AdapterListGroups adapterListGroups = new AdapterListGroupsTest();

        animatorMainActivity.setAdapter(adapterListGroups);
        animatorMainActivity.notifyAdapterData();
    }
}
