package com.example.medicalapp.users.pacienti;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.R;
import com.example.medicalapp.utility.AsyncResponse;
import com.example.medicalapp.utility.async_pacienti.ChartAsync;
import com.example.medicalapp.utility.async_pacienti.StatisticiAsync;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChartPuls extends AppCompatActivity implements AsyncResponse {

    private String user;
    private String cnp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_puls);

        String value = getIntent().getStringExtra("EXTRA_USER");

        int position = value.indexOf(",");
        int length = value.length();

        user = value.substring(0, position);
        cnp = value.substring(position + 1, length);

        ChartAsync asyncTask = new ChartAsync();
        asyncTask.delegate = this;
        asyncTask.execute(cnp);
    }

    @Override
    public void processFinish(Integer output) {

    }

    @Override
    public void processFinish(JSONObject output) throws JSONException {

        BarChart barChart = findViewById(R.id.chart);
        ArrayList<BarEntry> res = new ArrayList<>();
        float[] medieZile = new float[100];
        float sum = 0;
        float numberValuesDays = 0;
        float average = 0;
        int index;
        int number = 0;
        int size = Integer.parseInt(output.getString("size"));

        for (int i = 0; i < size; ++i) {
            sum = Float.parseFloat(output.getString("puls" + i));
            numberValuesDays = 1;
            index = output.getString("date" + i).indexOf("M");
            String date = output.getString("date" + i).substring(0, index - 1);
            for (int j = i + 1; j < size; ++j) {
                if (date.equals(output.getString("date" + j).substring(0, index - 1))) {
                    sum += Float.parseFloat(output.getString("puls" + j));
                    ++numberValuesDays;
                } else {
                    i = j - 1;
                    j = size;
                    average = sum / numberValuesDays;
                    ++number;
                    res.add(new BarEntry(number, average));
                }
            }
        }

        average = sum / numberValuesDays;
        ++number;
        res.add(new BarEntry(number, average));

        BarDataSet barDataSet = new BarDataSet(res, "rezultate");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Diagrama Puls");
        barChart.animateY(2000);

    }

    @Override
    public void processFinish(Boolean output) throws JSONException {

    }

    @Override
    public void processFinish(ArrayList<String> output) {

    }
}