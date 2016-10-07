package com.example.mercadodebolso.mercadodebolso.util;

import android.content.Context;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Cais - Automação on 18/04/2016.
 */
public class Util {

    private static NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    private static Toast alertToast;

    public static String formatReal(double valor){
        return currency.format(valor);
    }


    public static void alert(Context context, String msg){
        if(alertToast != null){
            alertToast.cancel();
        }
        alertToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        //alertToast.setText(msg);
        alertToast.show();
    }
}
