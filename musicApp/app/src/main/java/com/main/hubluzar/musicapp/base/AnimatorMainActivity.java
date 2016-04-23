package com.main.hubluzar.musicapp.base;


/**
 * Created by Агент on 20.04.2016.
 */
public interface AnimatorMainActivity {
    public void setAdapter(AdapterListGroups adapter);
    public void showWaitingDialogLoading();
    public void finishedDialogLoadingSucces();
    public void finishedDialogLoadingError();
    public void notifyAdapterData();
}
