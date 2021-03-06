package com.main.hubluzar.musicapp.contentExec;

import android.content.Context;
import android.util.Log;

import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.base.ItemMusicGroup;
import com.main.hubluzar.musicapp.base.ReaderJSONData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Агент on 06.04.2016.
 */
public class ReaderJSONDataImpl implements ReaderJSONData  {

    private JSONArray jSONArray;
    private Context context;



    public ReaderJSONDataImpl(Context context) {
        this.jSONArray = null;
        this.context = context;
    }

    public Integer getSizeJSONArray() {
        if (jSONArray == null) return 0;
        return this.jSONArray.length();
    }

    public void setJSONArray(JSONArray jSONArray) {
        this.jSONArray = jSONArray;
    }

    //Функция подгружает данные в список( сделано для того чтобы не парстиь весь Json подгружать данные по мере надобности
    public void extentionListItemsMusicGroup(List<ItemMusicGroup> listItemsMusicGroup, int position, int sizeOfExtention) {
        if (this.jSONArray == null ) return;
        readJSONMusicGroup(listItemsMusicGroup, position, sizeOfExtention);
    }



    //Парсим один jSONArray в зависимости от настроенного размера подгружаеммых данных
    private List<ItemMusicGroup> readJSONMusicGroup(List<ItemMusicGroup> listItemMusicGroup, int position, int sizeOfRead)
    {
        for (int i = position; i < position + sizeOfRead; i++) {
            try {
                JSONObject joItemGroup = jSONArray.getJSONObject(i);
                ItemMusicGroup currentMusicGroup = readItemMusicGroup(joItemGroup);
                if ( currentMusicGroup != null )
                    listItemMusicGroup.add(currentMusicGroup);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //Парсим один JSONObject, если нет имени группы, то объект не создается, остальные параметры в случае отсутвия заполняются по дефолту
    private ItemMusicGroup readItemMusicGroup(JSONObject joItemGroup)
    {
        try {
            String name = readNameMusicGroup(joItemGroup);
            Integer id = readParametrMusicGroupInteger(joItemGroup,  R.string.common_labelGroup_id);
            String description = readParametrMusicGroupString(joItemGroup,  R.string.common_labelGroup_description);
            String[] genres = getGenres(joItemGroup);
            Integer tracks = readParametrMusicGroupInteger(joItemGroup,  R.string.common_labelGroup_tracks);
            Integer albums = readParametrMusicGroupInteger(joItemGroup,  R.string.common_labelGroup_albums);
            String link = readParametrMusicGroupString(joItemGroup,  R.string.common_labelGroup_link);
            String linkSmallImage = null;
            String linkrBigImage = null;
            try {
                JSONObject cover = joItemGroup.getJSONObject(context.getString(R.string.common_labelGroup_cover));
                linkSmallImage = readParametrMusicGroupString(cover,  R.string.common_labelGroup_linkSmallImage);
                linkrBigImage = readParametrMusicGroupString(cover,  R.string.common_labelGroup_linkBigImage);
            } catch (JSONException e) {
            }
            ItemMusicGroup currentItemMusicGroup = new ItemMusicGroupImpl(name, description, link,
                    linkSmallImage, linkrBigImage, albums, tracks, id, genres, context );
            return  currentItemMusicGroup;

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(context.getString(R.string.log_tag_error), this.getClass().getSimpleName() + joItemGroup.toString());
            return null;
        }
    }



    private String readNameMusicGroup(JSONObject joItemGroup) throws JSONException
    {
        String name = joItemGroup.getString(context.getString(R.string.common_labelGroup_name));
        return name;
    }

    private String readParametrMusicGroupString(JSONObject joItemGroup, int resLabelGroupParametr)
    {
        try {
            String parametr = joItemGroup.getString(context.getString(resLabelGroupParametr));
            return parametr;
        }
            catch (JSONException e) {
                return null;
            }
    }


    private Integer readParametrMusicGroupInteger(JSONObject joItemGroup, int resLabelGroupParametr)
    {
        try {
            Integer parametr = joItemGroup.getInt(context.getString(resLabelGroupParametr));
            return parametr;
        }
        catch (JSONException e) {
            return null;
        }
    }


    private String[] getGenres(JSONObject joItemGroup)
    {
        try {
            JSONArray joGenres = joItemGroup.getJSONArray(context.getString(R.string.common_labelGroup_genresArray));
            String[] genres = new String[joGenres.length()];
            for (int i = 0; i < joGenres.length(); i++) {
                try {
                    genres[i] = joGenres.getString(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return genres;
        } catch (JSONException e) {
            return null;
        }
    }


}
