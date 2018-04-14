package com.corentindupont.worldvisit;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.corentindupont.worldvisit.Activity.AddCountryActivity;
import com.corentindupont.worldvisit.Activity.VisitDatePickerActivity;
import com.corentindupont.worldvisit.Entity.Country;
import com.google.gson.Gson;

public class AddCountryAlertDialogFragment extends DialogFragment {

    private Country selectedCountry;

    //Force user to create the dialog with a country in param.
    public static AddCountryAlertDialogFragment newInstance(Country selectedCountry) {
        AddCountryAlertDialogFragment dialogFragment = new AddCountryAlertDialogFragment();
        dialogFragment.setSelectedCountry(selectedCountry);
        return dialogFragment;
    }

    //setter for the country
    public void setSelectedCountry(Country selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    //When the dialog is created ...
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Create simple alert dialog, using a positive button and a negative button.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Le pays "+selectedCountry.getName()+" est déja dans votre liste. Voulez vous l'ajouter une deuxième fois ?")
            .setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Launch date picker activity
                    Intent activity = new Intent(getActivity(), VisitDatePickerActivity.class);
                    activity.putExtra(Constant.COUNTRY_OBJECT, new Gson().toJson(selectedCountry));
                    startActivity(activity);
                }
            })
            .setNegativeButton("NON", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //user cancel his action. Close the dialog.
                    dismiss();
                }

            });
            return builder.create();
    }
}
