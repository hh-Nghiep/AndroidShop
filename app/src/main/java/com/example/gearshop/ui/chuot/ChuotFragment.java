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
    AutoCompleteTextView autoComplateChuot;
    View view;

    SanPham SP = null;
    ArrayList<SanPham> dataSP = new ArrayList<>();

    ListView lvDanhSachSP;
    RecyclerView rcvSP;
    SPAdapter spAdapter;

    Connection connection;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chuot, container, false);
        setControl();
        setEvent();
        autoComplateChuot = view.findViewById(R.id.autoComplete_chuot);
        autoComplateChuot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        //adapter_SP = new CustomAdapter_SP(view.getContext(), R.layout.san_pham, dataSP);
        //lvDanhSachSP.setAdapter(adapter_SP);
    }

    private void KhoiTao(){
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            if(connection != null){
                String query = "select * from SanPham";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    dataSP.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
                }
            }
        }catch (Exception ex){
            System.err.print(ex.getMessage());
        }
//        dataSP.add(new SanPham("G102", "Sản Phẩm 1", "https://drive.google.com/file/d/1Ik50QrnkZf79DV-mtAS3t_bsp-nJ7ii9/view", "https://drive.google.com/file/d/17mX8ppREgri1VfoT59qHqPQl1wwSvRDe/view?usp=share_link", "https://drive.google.com/file/d/1Ba0yNq3Hz_GYtyGWAquJg8uryLgOHukW/view?usp=share_link", 1, 1, 1, 100F));
//        dataSP.add(new SanPham("G403", "Sản Phẩm 2", "https://drive.google.com/file/d/17mX8ppREgri1VfoT59qHqPQl1wwSvRDe/view?usp=share_link", "hinhAnh2", "hinhAnh3", 2, 2, 2, 200F));
//        dataSP.add(new SanPham("G502", "Sản Phẩm 3", "https://drive.google.com/file/d/1Ba0yNq3Hz_GYtyGWAquJg8uryLgOHukW/view?usp=share_link", "hinhAnh2", "hinhAnh3", 3, 3, 3, 300F));
//        dataSP.add(new SanPham("G903", "Sản Phẩm 4", "https://drive.google.com/file/d/13Jv1_vTtGH6p2XHcTyiBs_-_8pLK_IvH/view?usp=share_link", "hinhAnh2", "hinhAnh3", 4, 4, 4, 400F));
//        dataSP.add(new SanPham("G-Pro", "Sản Phẩm 5", "https://drive.google.com/file/d/112Vj3BQvoobCJxt4tzDcvNs6juBhnUhT/view?usp=share_link", "hinhAnh2", "hinhAnh3", 5, 5, 5, 500F));
    }

    private void setControl() {
        lvDanhSachSP = view.findViewById(R.id.lvChuot);
        rcvSP = view.findViewById(R.id.rcvSP);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
