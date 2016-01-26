package com.bleyl.recurrence.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.bleyl.recurrence.R;

public class RepeatSelector extends DialogFragment {

    public interface RepeatSelectionListener {
        void onRepeatSelection(DialogFragment dialog, int interval, String repeatText);
    }

    RepeatSelectionListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (RepeatSelectionListener) activity;
    }

    @Override @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] repeatArray = getResources().getStringArray(R.array.repeat_array);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.Dialog);
        builder.setItems(repeatArray, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == 6) {
                    DialogFragment daysOfWeekDialog = new DaysOfWeekSelector();
                    daysOfWeekDialog.show(getActivity().getSupportFragmentManager(), "DaysOfWeekSelector");
                }  else if (which == 7) {
                    DialogFragment advancedDialog = new AdvancedRepeatSelector();
                    advancedDialog.show(getActivity().getSupportFragmentManager(), "AdvancedSelector");
                } else {
                    mListener.onRepeatSelection(RepeatSelector.this, which, repeatArray[which]);
                }
            }
        });
        return builder.create();
    }

}