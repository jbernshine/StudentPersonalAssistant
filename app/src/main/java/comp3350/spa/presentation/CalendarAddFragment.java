package comp3350.spa.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import comp3350.spa.R;

public class CalendarAddFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {

    NoticeDialogListener mListener;
    Spinner mySpinner;
    EditText textEdit;
    String eventPriority;

    public interface NoticeDialogListener {
        void onDialogPositiveClick(CalendarAddFragment dialog);
        void onDialogNegativeClick(CalendarAddFragment dialog);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (NoticeDialogListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View promptsView = inflater.inflate(R.layout.calendar_add, null);
        mySpinner= (Spinner) promptsView.findViewById(R.id.add_spinner);
        textEdit = (EditText) promptsView.findViewById(R.id.addEventName);
        String[] choices = {"High", "Medium", "Low"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, choices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setSelection(1);
        mySpinner.setOnItemSelectedListener(this);
        builder.setView(promptsView)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(CalendarAddFragment.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(CalendarAddFragment.this);
                    }
                });

        return builder.create();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        eventPriority = (String)parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}
