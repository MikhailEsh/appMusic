package com.main.hubluzar.musicapp.display;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.main.hubluzar.musicapp.R;
import com.main.hubluzar.musicapp.base.AdapterListGroups;
import com.main.hubluzar.musicapp.base.AnimatorMainActivity;

/**
 * Created by Агент on 20.04.2016.
 */
public class AnimatorMainActivityImpl implements AnimatorMainActivity {
    private ProgressDialog progressDialog;
    Context context;
    private AdapterListGroups adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    public AnimatorMainActivityImpl(Context context, SwipeRefreshLayout mSwipeRefreshLayout)  {
        this.context = context;
        this.progressDialog =  new ProgressDialog(context);
        settingProgressDialog();
        this.adapter = null;
        this.mSwipeRefreshLayout = mSwipeRefreshLayout;
    }

    public void setAdapter(AdapterListGroups adapter)
    {
        this.adapter = adapter;
    }

    public void showWaitingDialogLoading()
    {
        progressDialog.show();
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1);
    }

    public void finishedDialogLoadingSucces()
    {
        mSwipeRefreshLayout.setRefreshing(false);
        progressDialog.dismiss();
        Toast.makeText(context, R.string.toast_succes_download, Toast.LENGTH_SHORT).show();
    }

    public void finishedDialogLoadingError()
    {
        mSwipeRefreshLayout.setRefreshing(false);
        progressDialog.dismiss();
        Toast.makeText(context, R.string.toast_error_download, Toast.LENGTH_SHORT).show();
    }


    public void notifyAdapterData()
    {
        if ( this.adapter != null)
            this.adapter.notifyDataSetChanged();
    }

    private void settingProgressDialog()
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.process_dialog_loading));
        progressDialog.setCancelable(false);
    }
}
