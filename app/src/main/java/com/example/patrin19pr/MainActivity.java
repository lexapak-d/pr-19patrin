package com.example.patrin19pr;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    TextView timePick;
    Button btnTime, btnDate;
    Calendar dateTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTime = findViewById(R.id.btnTime);
        btnDate = findViewById(R.id.btnDate);
        timePick = findViewById(R.id.timePick);

        btnTime.setOnClickListener(v -> { getTime(); });
        btnDate.setOnClickListener(v -> { getDate(); });
        Button btnzxc = findViewById(R.id.btnzxc);

        TimeZone deviceTimeZone = TimeZone.getDefault();


        dateTime.setTimeZone(deviceTimeZone);

        setInitialDateTime();

        btnzxc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем диалоговое окно
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // Устанавливаем заголовок
                builder.setTitle("Вы мухаммед?");

                // Устанавливаем сообщение
                builder.setMessage("Если это вы нажмите Да");

                // Устанавливаем кнопку "OK" и слушатель события для нее
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Действия при нажатии на кнопку "OK"
                        dialog.dismiss(); // Закрыть диалоговое окно
                    }
                });

                // Устанавливаем кнопку "Отмена" и слушатель события для нее
                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Действия при нажатии на кнопку "Отмена"
                        dialog.dismiss(); // Закрыть диалоговое окно
                    }
                });

                // Показываем диалоговое окно
                builder.show();
            }
        });
    }

    private void setInitialDateTime(){
        timePick.setText(DateUtils.formatDateTime( this,
                dateTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    public void getDate(){
        new DatePickerDialog(MainActivity.this, d,
                dateTime.get(Calendar.YEAR),
                dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }
    public void getTime(){
        new TimePickerDialog(MainActivity.this, t,
                dateTime.get(Calendar.HOUR_OF_DAY),
                dateTime.get(Calendar.MINUTE), true)
                .show();
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

}