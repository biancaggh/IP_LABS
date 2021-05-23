package com.example.medicalapp.users.pacienti;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.medicalapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartPuls extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_puls);

        BarChart barChart = findViewById(R.id.chart);
        ArrayList<BarEntry> days = new ArrayList<>();
        days.add(new BarEntry(1, 200));
        days.add(new BarEntry(2, 150));
        days.add(new BarEntry(3, 200));
        days.add(new BarEntry(4, 205));
        days.add(new BarEntry(5, 250));
        days.add(new BarEntry(6, 100));
        days.add(new BarEntry(7, 150));



        BarDataSet barDataSet = new BarDataSet(days, "zile");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Diagrama Puls");
        barChart.animateY(2000);
    }
}