package com.example.odev_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 =findViewById(R.id.editText1);
        editText2 =findViewById(R.id.editText2);
        editText3=findViewById(R.id.editText3);
        button = findViewById(R.id.button);


    }

    public void calculateResult(View view){

        if(!TextUtils.isEmpty(editText1.getText()) &&
                !TextUtils.isEmpty(editText2.getText()) &&
                !TextUtils.isEmpty(editText3.getText())){
            int day = Integer.parseInt(String.valueOf(editText1.getText()));
            int month = Integer.parseInt(String.valueOf(editText2.getText()));
            int year = Integer.parseInt(String.valueOf(editText3.getText()));


            int days =calculateDays(day, month, year);

            TextView textView4=findViewById(R.id.textView4);

            textView4.setText("You are "+days+" days old.");

        }
    }

    private int calculateDays(int day, int month, int year) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int yearDifference = currentYear-year-1;
        int days = 0;

        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);


//Yılın kalan günlerini hesaplama
        days += yearDifference * 365;
        // Gün sayısı hesaplama
        for (int i = 1; i <= currentMonth; i++) {
            days += getDaysInMonth(i, currentYear);
        }

//        while(year < currentYear) {
//            for (int i = 1; i < month; i++) {
//                days += getDaysInMonth(i, year);
//            }
//            year++;
//        }


        days += currentDay;
        // Artık yılları ekleyerek günlük sayısını düzeltme
        days += countLeapYears(year, currentYear);

        // Doğum gününü ekleme
        days += -day;


        //Doğulan yıl bir sonraki yıla bütünlenene kadar geçen gün
        int monthsBeforeNextYear = month;
        while(monthsBeforeNextYear <13) {
            for (int i = month; i < 13; i++) {
                days += getDaysInMonth(i, year);
           }
            monthsBeforeNextYear++;
       }

        return days;
    }

    private int getDaysInMonth(int month, int year) {
        switch (month%12) {
            case 0:case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                if (year % 4 == 0 ) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1; // Geçersiz ay
        }
    }

    private int countLeapYears(int year, int currentYear) {

        int daysToBeAdded = 0;
        for(int i = year-1; i<currentYear; i++){
            if(i % 4==0) daysToBeAdded++;
        }
        return daysToBeAdded;
    }

}