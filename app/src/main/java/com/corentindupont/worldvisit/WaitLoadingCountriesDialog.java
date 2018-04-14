package com.corentindupont.worldvisit;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class WaitLoadingCountriesDialog extends DialogFragment {
    ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getActivity(), getTheme());
        progressDialog.setTitle("Veuillez Patienter");
        progressDialog.setMessage("Chargement des pays du monde ...");
        progressDialog.setMax(100);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return progressDialog;
    }

    public void setPercentageProgress(int percentage){
        if(percentage >= 0 && percentage <= progressDialog.getMax()){
            progressDialog.setProgress(percentage);
            if(progressDialog.getProgress() == progressDialog.getMax()){
                dismiss();
            }
        }
    }
}
