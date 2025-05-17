package com.example.syshotel.util;

import android.util.Base64;

public class Base64Util {
    public byte[] decodeImage(String imageBase64) {
        if (imageBase64 == null || imageBase64.isEmpty()) {
            return new byte[0];
        }
        return Base64.decode(imageBase64, Base64.DEFAULT);
    }
}
