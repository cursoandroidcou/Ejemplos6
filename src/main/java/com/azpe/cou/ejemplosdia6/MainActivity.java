package com.azpe.cou.ejemplosdia6;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Calendar c = Calendar.getInstance();
    TextView display;
    TextView displayTime;
    //int cday, cmonth, cyear;

    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDialogCheck = (Button)findViewById(R.id.btnDialogCheck);
        Button btnCustomDialog = (Button)findViewById(R.id.btnCustomDialog);
        Button btnCalendar = (Button)findViewById(R.id.btnCalendar);
        Button btnTimer = (Button)findViewById(R.id.btntimer);
        Button btnNotification = (Button)findViewById(R.id.btnNotification);
        Button btnSaveInFile = (Button)findViewById(R.id.btnSaveInFile);
        Button btnReadFile = (Button)findViewById(R.id.btnReadFile);

        display = (TextView)findViewById(R.id.lblDate) ;
        displayTime = (TextView)findViewById(R.id.lblTime);

        btnDialogCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogCheck dgl = new DialogCheck();
                dgl.show(getFragmentManager(), null);

            }
        });

        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dgl = new CustomDialog();
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, d, c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener(){

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                displayTime.setText(hourOfDay + ":" + minute);
                            }
                        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false).show();*/

               TimePickerDialog dialog;
               dialog = new TimePickerDialog(MainActivity.this,
                        t, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false);

                dialog.setTitle("Seleccione la hora");
                dialog.show();

            }
        });

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder notif = new NotificationCompat.Builder(getApplicationContext());
                notif.setContentTitle("Nuevo correo");
                notif.setContentText("Recibido correo de amigo@gmail.com");
                notif.setSmallIcon(R.drawable.iconocorreo);
                notif.setContentInfo("Coreo electronico marcado con alta prioridad");

                long[] pattern = new long[]{1000, 500, 1000};
                notif.setVibrate(pattern);

                Intent result = new Intent(getApplicationContext(), Correo.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                stackBuilder.addParentStack(Correo.class);
                stackBuilder.addNextIntent(result);

                PendingIntent resultPending = stackBuilder.getPendingIntent(0,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                notif.setContentIntent(resultPending);

                NotificationManager manager =
                        (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0, notif.build());


            }
        });

        btnSaveInFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt = (EditText)findViewById(R.id.txtTextoAGuardar);

                try {
                    FileOutputStream fs = openFileOutput("MiFichero", Context.MODE_PRIVATE);
                    OutputStreamWriter sw = new OutputStreamWriter(fs);
                    sw.write(txt.getText().toString());
                    sw.flush();
                    sw.close();
                    txt.setText("");

                }
                catch (FileNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                catch (IOException ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnReadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt = (EditText)findViewById(R.id.txtTextoAGuardar);
                try{
                    String content = "";

                    FileInputStream fs = openFileInput("MiFichero");
                    InputStreamReader sr = new InputStreamReader(fs);
                    char[] inputBuffer = new char[READ_BLOCK_SIZE];
                    int readChars = 0;

                    while((readChars = sr.read(inputBuffer)) > 0){
                        String section = String.copyValueOf(inputBuffer, 0, readChars);
                        content += section;
                        inputBuffer = new char[READ_BLOCK_SIZE];
                    }

                    txt.setText(content);
                }
                catch (IOException ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            display.setText("Fecha seleccionada: " + dayOfMonth + "/" + (monthOfYear + 1) + "/"+ year);

        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            displayTime.setText(hourOfDay + ":" + minute);
        }
    };
}
