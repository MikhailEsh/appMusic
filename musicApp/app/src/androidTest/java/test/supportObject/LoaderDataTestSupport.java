package test.supportObject;

import com.android.volley.toolbox.NetworkImageView;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.base.LoaderData;

import java.util.List;

/**
 * Created by Агент on 23.04.2016.
 */
public class LoaderDataTestSupport implements LoaderData {
    @Override
    public void extentionListItemsMusicGroup(List<ItemMusicGroup> listItemsMusicGroup, int position) {

    }

    @Override
    public Integer getSizeJSONArray() {
        return 5;
    }

    @Override
    public void sendRequest() {
    }

    @Override
    public void setImageUrl(NetworkImageView networkImageView, String url) {
    }
}
