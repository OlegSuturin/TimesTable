package com.example.timestable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listViewNumbers;
    private SeekBar seekBar;

    private ArrayList<Integer> numbers;
    private int max = 20;  // максимальное и мин. значения на сигбаре
    private int min = 1;
    private int count = 10; // кол-во элементов выводимых на таблицу

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNumbers = findViewById(R.id.listViewNumbers);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(max);

        numbers = new ArrayList<>();
        // чтобы разместить массив чисел в listView нужно использовать  адаптер

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numbers);
        listViewNumbers.setAdapter(adapter);
        //создаем слушателя событий на изменения в сигбаре
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //изменен прогресс на сигбаре
                //заполняем массив новыми значениями
                if(progress<min){  //если установили шкалу в 0
                    seekBar.setProgress(min);
                }

                    numbers.clear();
                    for (int i = min; i <= count; i++) {
                        numbers.add(seekBar.getProgress() * i);
                    }
                    adapter.notifyDataSetChanged();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //начинает двигать
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //заканчивает двигать

            }
        });
        seekBar.setProgress(10);
    }
}