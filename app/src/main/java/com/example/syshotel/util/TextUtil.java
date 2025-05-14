package com.example.syshotel.util;

import com.google.android.material.textfield.TextInputEditText;

public class TextUtil {
    public String safeText(TextInputEditText input) {
        return input != null && input.getText() != null ? input.getText().toString().trim() : "";
    }

}
