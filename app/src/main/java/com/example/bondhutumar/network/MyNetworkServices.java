package com.example.bondhutumar.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MyNetworkServices {
    Context context;

    public MyNetworkServices(Context context) {
        this.context = context;
    }

    public boolean isActiveNetwork() {

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }
}
