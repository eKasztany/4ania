package queueapp.eu.wilek.kolejelasu.services;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import queueapp.eu.wilek.kolejelasu.R;

/**
 * Created by mateuszwilczynski on 25.09.2016.
 */

public class ConfirmationDialog extends DialogFragment {

    public interface OnCloseListener {
        void onClose(int number);
    }

    private int number;

    private int setValue;

    private OnCloseListener onCloseListener;

    public ConfirmationDialog() {
        //nop
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_confirmation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().setTitle(R.string.confirmation);

        NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.picker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(number - 1);
        numberPicker.setValue(3 < number ? 3 : number - 1);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                setValue = newVal;
            }
        });

        ((TextView) view.findViewById(R.id.waiting_number)).setText(String.valueOf(number + 1));
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (onCloseListener != null) {
            onCloseListener.onClose(setValue);
        }
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setOnCloseListener(@Nullable OnCloseListener onCloseListener) {
        this.onCloseListener = onCloseListener;
    }
}
