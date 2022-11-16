package com.example.mywallet.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Pie;
import com.anychart.charts.TagCloud;
import com.example.mywallet.DataBaseHelper;
import com.example.mywallet.R;
import com.example.mywallet.UnosZapisa;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.List;

public class HomeFragment extends Fragment {

    Button btn_dodaj;
    private HomeViewModel homeViewModel;
    private Activity activity;
    TextView tv_prihod, tv_trosak;
    DataBaseHelper dataBaseHelper;
    PieChart pieChart1, pieChart2;
    AnyChartView anyChartView1, anyChartView2;

    public void ImportFragment(Activity activity) {
        this.activity=activity;
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        tv_prihod=(TextView)root.findViewById(R.id.tv_prihod);
        tv_trosak=(TextView)root.findViewById(R.id.tv_trosak);

        pieChart1=root.findViewById(R.id.pie1);
        pieChart2=root.findViewById(R.id.pie2);


        dataBaseHelper = new DataBaseHelper(getContext());
        int prihod=dataBaseHelper.ukupniPrihod();
        int trosak=dataBaseHelper.ukupniTrosak();
        tv_prihod.setText(Integer.toString(prihod)+"kn");
        tv_trosak.setText(Integer.toString(trosak)+"kn");


        setupPieChart();
        setupPieChart1();


        btn_dodaj = (Button)root.findViewById(R.id.btn_dodaj);
        btn_dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UnosZapisa.class);
                startActivity(intent);

            }
        });

        return root;
    }

    public void setupPieChartPrihod() {
        DataBaseHelper db = new DataBaseHelper(getContext());
        Pie pie1 = AnyChart.pie();
        List<DataEntry> lista_prihod=db.ReadDataToListPrihod();
        pie1.data(lista_prihod);
        anyChartView1.setChart(pie1);



    }
    public void setupPieChartTrosak() {
        DataBaseHelper db1 = new DataBaseHelper(getContext());
        Pie pie2 = AnyChart.pie();
        List<DataEntry> lista_trosak=db1.ReadDataToListTrosak();
        pie2.data(lista_trosak);
        anyChartView2.setChart(pie2);
    }

    public void setupPieChart() {
        DataBaseHelper db = new DataBaseHelper(getContext());
        List<PieEntry> lista=db.ReadDataToListP();
        PieDataSet pieDataSet1 = new PieDataSet(lista, "");
        pieDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet1.setValueTextSize(16f);

        PieData pieData1 = new PieData(pieDataSet1);
        pieData1.setValueFormatter(new PercentFormatter(pieChart1));
        pieData1.setValueTextColor(Color.WHITE);


        pieChart1.setData(pieData1);
        pieChart1.getDescription().setEnabled(false);
        pieChart1.setCenterText("Prihod");
        pieChart1.setCenterTextColor(Color.WHITE);
        pieChart1.setHoleColor(Color.parseColor("#97129F"));
        pieChart1.setCenterTextSize(30f);
        pieChart1.setUsePercentValues(true);


        pieChart1.animate();


    }
    public void setupPieChart1() {

        DataBaseHelper db = new DataBaseHelper(getContext());
        List<PieEntry> lista=db.ReadDataToListT();
        PieDataSet pieDataSet2 = new PieDataSet(lista, "");
        pieDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet2.setValueTextSize(16f);
        pieDataSet2.setValueTextColor(Color.WHITE);

        PieData pieData2 = new PieData(pieDataSet2);
        pieData2.setValueFormatter(new PercentFormatter(pieChart2));

        pieChart2.setData(pieData2);
        pieChart2.getDescription().setEnabled(false);
        pieChart2.setCenterText("Trošak");
        pieChart2.setCenterTextColor(Color.WHITE);
        pieChart2.setHoleColor(Color.parseColor("#97129F"));
        pieChart2.setCenterTextSize(30f);
        pieChart2.setUsePercentValues(true);


        pieChart2.animate();

    }

    
}