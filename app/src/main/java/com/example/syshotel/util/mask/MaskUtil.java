package com.example.syshotel.util.mask;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;

public class MaskUtil {

    public static TextWatcher maskCpf(final TextInputEditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString().replaceAll("[^\\d]", "");
                String formatted = "";

                if (count == 0) isUpdating = true;

                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }

                if (str.length() > 11)
                    str = str.substring(0, 11);

                int len = str.length();
                if (len <= 3)
                    formatted = str;
                else if (len <= 6)
                    formatted = str.substring(0, 3) + "." + str.substring(3);
                else if (len <= 9)
                    formatted = str.substring(0, 3) + "." + str.substring(3, 6) + "." + str.substring(6);
                else
                    formatted = str.substring(0, 3) + "." + str.substring(3, 6) + "." + str.substring(6, 9) + "-" + str.substring(9);

                isUpdating = true;
                editText.setText(formatted);
                editText.setSelection(formatted.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        };
    }

    public static TextWatcher maskCep(final TextInputEditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString().replaceAll("[^\\d]", "");
                String formatted = "";

                if (count == 0) isUpdating = true;

                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }

                if (str.length() > 8)
                    str = str.substring(0, 8);

                if (str.length() <= 5)
                    formatted = str;
                else
                    formatted = str.substring(0, 5) + "-" + str.substring(5);

                isUpdating = true;
                editText.setText(formatted);
                editText.setSelection(formatted.length());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
    }
    public static TextWatcher maskPhone(final TextInputEditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString().replaceAll("[^\\d]", "");
                String formatted = "";

                if (count == 0) isUpdating = true;

                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }

                if (str.length() > 11)
                    str = str.substring(0, 11);

                int len = str.length();
                if (len <= 2)
                    formatted = "(" + str;
                else if (len <= 7)
                    formatted = "(" + str.substring(0, 2) + ") " + str.substring(2);
                else
                    formatted = "(" + str.substring(0, 2) + ") " + str.substring(2, 7) + "-" + str.substring(7);

                isUpdating = true;
                editText.setText(formatted);
                editText.setSelection(formatted.length());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
    }
}
