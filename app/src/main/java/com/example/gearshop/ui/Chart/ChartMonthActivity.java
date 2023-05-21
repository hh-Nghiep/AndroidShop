package com.example.gearshop.ui.Chart;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gearshop.ConnectSQL;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.example.gearshop.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChartMonthActivity extends AppCompatActivity {
    Connection connection;
    int T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_month);

        khoitao();
        BarChart barChart = findViewById(R.id.monthBarChart);

        ArrayList<BarEntry> doanhthunam= new ArrayList<>();
        doanhthunam.add(new BarEntry(1,T1));
        doanhthunam.add(new BarEntry(2,T2));
        doanhthunam.add(new BarEntry(3,T3));
        doanhthunam.add(new BarEntry(4,T4));
        doanhthunam.add(new BarEntry(5,T5));
        doanhthunam.add(new BarEntry(6,T6));
        doanhthunam.add(new BarEntry(7,T7));
        doanhthunam.add(new BarEntry(8,T8));
        doanhthunam.add(new BarEntry(9,T9));
        doanhthunam.add(new BarEntry(10,T10));
        doanhthunam.add(new BarEntry(11,T11));
        doanhthunam.add(new BarEntry(12,T12));

        BarDataSet barDataSet = new BarDataSet(doanhthunam, "Doanh thu năm");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(10f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.animateY(2000);
    }

    public void khoitao(){
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            if(connection != null){
                String query = "select NgayTaoDH from DonHang";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    Date date = rs.getDate("NgayTaoDH");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int month = cal.get(Calendar.MONTH);
//                    int yeah = cal.get(Calendar.YEAR);
                    if (month == Calendar.JANUARY){
                        T1 = T1+1;
                    } else if (month == Calendar.FEBRUARY) {
                        T2 = T2+1;
                    }else if (month == Calendar.MARCH) {
                        T3 = T3+1;
                    }else if (month == Calendar.APRIL) {
                        T4 = T4+1;
                    }else if (month == Calendar.MAY) {
                        T5 = T5+1;
                    }else if (month == Calendar.JUNE) {
                        T6 = T6+1;
                    }else if (month == Calendar.JULY) {
                        T7 = T7+1;
                    }
                    else if (month == Calendar.AUGUST) {
                        T8 = T8+1;
                    }else if (month == Calendar.SEPTEMBER) {
                        T9 = T9+1;
                    }else if (month == Calendar.OCTOBER) {
                        T10 = T10+1;
                    }else if (month == Calendar.NOVEMBER) {
                        T11 = T11+1;
                    }
                    else if (month == Calendar.DECEMBER) {
                        T12 = T12+1;
                    }
                }
            }
        }catch (Exception ex){
            System.err.print(ex.getMessage());
        }
    }
}