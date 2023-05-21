package com.example.gearshop.ui.Chart;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.example.gearshop.R;

import java.util.ArrayList;

public class ChartYeahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_yeah);

        BarChart barChart = findViewById(R.id.yearBarChart);

        ArrayList<BarEntry> doanhthunam= new ArrayList<>();
        doanhthunam.add(new BarEntry(2019,173));
        doanhthunam.add(new BarEntry(2020,230));
        doanhthunam.add(new BarEntry(2021,150));
        doanhthunam.add(new BarEntry(2022,220));
        doanhthunam.add(new BarEntry(2023,240));

        BarDataSet barDataSet = new BarDataSet(doanhthunam, "Doanh thu theo các năm");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Biểu đồ cột");
        barChart.animateY(2000);
    }
}