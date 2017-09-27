package com.example.jacob.repositorios.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkHelper {
    public static boolean hasNetworkAccess(Context context) {
        try {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            return activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }
}
