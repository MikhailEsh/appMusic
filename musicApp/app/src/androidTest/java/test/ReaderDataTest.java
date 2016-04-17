package test;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import com.main.hubluzar.musicapp.loader.ReaderJSONDate;

/**
 * Created by Агент on 16.04.2016.
 */
public class ReaderDataTest extends AndroidTestCase {

    Context context;
    ReaderJSONDate test;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        context = new MockContext();
        setContext(context);
        assertNotNull(context);
        Log.d("INFO", "setUp");
    }

    // Fake failed test
    @SmallTest
    public void testSomething() throws Exception  {
        assertEquals(true, true);
        Log.d("INFO", "testSomething");
        test = new ReaderJSONDate(context);

    }


    public void testSomethingA() throws Exception  {
        assertEquals(true, true);
        Log.d("INFO", "testSomethingA");

    }
}
