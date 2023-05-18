package com.example.gearshop.ui.chuot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.R;
import com.example.gearshop.ui.model.SPAdapter;
import com.example.gearshop.ui.model.SanPham;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ChuotFragment extends Fragment {
    String[] items = {"Corsair", "Dare-u", "Fuhlen", "Logitech",  "Razer"};
    AutoCompleteTextView autoComplate;
    ArrayAdapter<String> adapterItems;
    View view;

    ArrayList<SanPham> dataSP = new ArrayList<>();

    ListView lvDanhSachSP;
    RecyclerView rcvSP;
    SPAdapter spAdapter;

    Connection connection;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_danh_sach_san_pham, container, false);
        setControl();
        setEvent();
        autoComplate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getActivity().getApplicationContext(), item,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void setEvent() {
        KhoiTao();
        spAdapter = new SPAdapter();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rcvSP.setLayoutManager(staggeredGridLayoutManager);
        spAdapter.setListSP(this.getContext(), dataSP);
        rcvSP.setAdapter(spAdapter);
        adapterItems = new ArrayAdapter<String>(this.getContext(), R.layout.list_item, items);
        autoComplate.setAdapter(adapterItems);
    }

    private void KhoiTao(){
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            if(connection != null){
                String query = "select sp.MaSP, sp.TenSP, sp.GiaSP, sp.MaTL, th.TenTH, sp.MieuTaSP, sp.HinhAnh1, sp.HinhAnh2, sp.HinhAnh3, sp.SoLuong from SanPham as sp left join ThuongHieu as th on th.MaTH = sp.MaTH where MaTL = 1";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    dataSP.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10)));
                }
            }
        }catch (Exception ex){
            System.err.print(ex.getMessage());
        }
    }

    private void setControl() {
        lvDanhSachSP = view.findViewById(R.id.lvChuot);
        rcvSP = view.findViewById(R.id.rcvSP);
        autoComplate = view.findViewById(R.id.autoComplete);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
