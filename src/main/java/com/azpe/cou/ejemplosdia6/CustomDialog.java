package com.azpe.cou.ejemplosdia6;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jose on 22/07/2016.
 */
public class CustomDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle a){
        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());

        //ad.setIcon()
        ad.setTitle("Solicitud de nombre");

        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.customdialog, null);

        ad.setView(v);
        final EditText txt = (EditText)v.findViewById(R.id.txtNombre);
        final TextView label = (TextView)getActivity().findViewById(R.id.txtNombreDialog);

        ad.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nombre =  txt.getText().toString();

                label.setText("El nombre indicado es: " + nombre);

               /* Toast.makeText(getActivity().getBaseContext(), "El nombre introducido es: " + nombre,
                        Toast.LENGTH_SHORT).show();*/


            }
        });

        return  ad.create();
    }
}
