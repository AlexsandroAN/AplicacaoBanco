package br.com.alex.aplicacaobanco.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import br.com.alex.aplicacaobanco.R;

/**
 * Created by 39091 on 07/01/2016.
 */
public class Util {

    public static void showMsgToast(Activity activity, String txt) {

        //Congigurar Mensagem na Tela
        LayoutInflater infrater = activity.getLayoutInflater();
        View lytToast = infrater.inflate(R.layout.toast_template, (ViewGroup) activity.findViewById(R.id.lytToast));
        TextView txtToast = (TextView) lytToast.findViewById(R.id.txtToast);
        txtToast.setText(txt);
        Toast toast = new Toast(activity);
        toast.setView(lytToast);
        //toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

    }
}
