package com.example.gearshop.ui.Chart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.gearshop.R;

import androidx.appcompat.app.AppCompatActivity;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        findViewById(R.id.btnYearChartBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChartYeahActivity.class));
            }
        });

        findViewById(R.id.btnMonthChartBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ChartMonthActivity.class));
            }
        });
    }
}