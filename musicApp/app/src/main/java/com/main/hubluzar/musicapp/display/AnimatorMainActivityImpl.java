package com.main.hubluzar.musicapp.display;

import android.app.ProgressDialog;
import android.content.Context;
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

    public AnimatorMainActivityImpl(Context context)  {
        this.progressDialog =  new ProgressDialog(context);
        this.context = context;
        this.adapter = null;
        settingProgressDialog();
    }

    public void setAdapter(AdapterListGroups adapter)
    {
        this.adapter = adapter;
    }

    public void showWaitingProgressDialog()
    {
        progressDialog.show();
    }

    public void dismissProgressDialog()
    {
        progressDialog.dismiss();
    }

    public void showErrorLoadNotice()
    {
        Toast errorToast = Toast.makeText(context, context.getString(R.string.toast_errorDownload), Toast.LENGTH_LONG);
        errorToast.show();
    }

    public void notifyAdapterData()
    {
        adapter.notifyDataSetChanged();
    }

    private void settingProgressDialog()
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.process_dialog_loading));
        progressDialog.setCancelable(false);
    }
}
