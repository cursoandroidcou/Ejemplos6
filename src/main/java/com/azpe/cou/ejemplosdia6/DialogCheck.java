package com.azpe.cou.ejemplosdia6;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jose on 22/07/2016.
 */
public class DialogCheck extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle a){
        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());

        ad.setTitle("Departamento");

        final String[] departamentos = {"Ventas", "Administración", "Técnico", "Transporte"};
        final ArrayList<String> userOptions = new ArrayList<String>();

        ad.setMultiChoiceItems(departamentos, null,
                new DialogInterface.OnMultiChoiceClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int wich, boolean isChecked){

                        String departament = "";
                        departament = departamentos[wich];

                        if (isChecked){
                            userOptions.add(departament);
                        }
                        else{
                            userOptions.remove(departament);
                        }

                    }
        });

        ad.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){

            @Override
            public  void onClick(DialogInterface dialog, int which){
                String result = "";
                if (userOptions.size() > 0){
                    result = "Has elegido: ";
                    for (int index = 0; index < userOptions.size(); index++){
                        result += userOptions.get(index) + ";";
                    }
                }
                else{
                    result = "No has seleccionado nada";
                }

                Toast.makeText(getActivity().getBaseContext(), result, Toast.LENGTH_SHORT).show();
            }
        });

        return  ad.create();
    }
}
