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

public class ChartMonthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_month);

        BarChart barChart = findViewById(R.id.monthBarChart);

        ArrayList<BarEntry> doanhthunam= new ArrayList<>();
        doanhthunam.add(new BarEntry(1,123));
        doanhthunam.add(new BarEntry(2,230));
        doanhthunam.add(new BarEntry(3,110));
        doanhthunam.add(new BarEntry(4,220));
        doanhthunam.add(new BarEntry(5,153));
        doanhthunam.add(new BarEntry(6,223));
        doanhthunam.add(new BarEntry(7,270));
        doanhthunam.add(new BarEntry(8,182));
        doanhthunam.add(new BarEntry(9,165));
        doanhthunam.add(new BarEntry(10,119));
        doanhthunam.add(new BarEntry(11,141));
        doanhthunam.add(new BarEntry(12,173));



        BarDataSet barDataSet = new BarDataSet(doanhthunam, "Doanh thu theo năm");
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