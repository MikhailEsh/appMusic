package test.mainTest.ActivityInstrumentationTestCase2;

import android.support.v4.widget.SwipeRefreshLayout;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.activity.MainActivity;
import com.main.hubluzar.musicapp.base.AdapterListGroups;
import com.main.hubluzar.musicapp.base.AnimatorMainActivity;
import com.main.hubluzar.musicapp.display.AnimatorMainActivityImpl;

import test.supportObject.AdapterListGroupsTestSupport;

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

        AdapterListGroups adapterListGroups = new AdapterListGroupsTestSupport();

        animatorMainActivity.setAdapter(adapterListGroups);
        animatorMainActivity.notifyAdapterData();
    }
}
