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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.gearshop.R;
import com.example.gearshop.ui.model.CustomAdapter_SP;
import com.example.gearshop.ui.model.SPAdapter;
import com.example.gearshop.ui.model.SanPham;

import java.util.ArrayList;

public class ChuotFragment extends Fragment {
    String[] items = {"Corsair", "Dare-u", "Fuhlen", "Logitech",  "Razer"};
    AutoCompleteTextView autoComplateChuot;
    ArrayAdapter<String> adapterItems;
    View view;

    SanPham SP = null;
    ArrayList<SanPham> dataSP = new ArrayList<>();

    ListView lvDanhSachSP;
    RecyclerView rcvSP;
    CustomAdapter_SP adapter_SP;
    SPAdapter spAdapter;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chuot, container, false);
        setControl();
        setEvent();
        autoComplateChuot = view.findViewById(R.id.autoComplete_chuot);
        adapterItems = new ArrayAdapter<String>(view.getContext(), R.layout.thuong_hieu, items);

        autoComplateChuot.setAdapter(adapterItems);

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
        spAdapter.setListSP(dataSP);
        rcvSP.setAdapter(spAdapter);
        //        adapter_SP = new CustomAdapter_SP(view.getContext(), R.layout.san_pham, dataSP);
//        lvDanhSachSP.setAdapter(adapter_SP);
    }

    private void KhoiTao(){
        dataSP.add(new SanPham("SP01", "Sản Phẩm 1", "hinhAnh1", "hinhAnh2", "hinhAnh3", 1, 1, 1, 100F));
        dataSP.add(new SanPham("SP02", "Sản Phẩm 2", "hinhAnh1", "hinhAnh2", "hinhAnh3", 2, 2, 2, 200F));
        dataSP.add(new SanPham("SP03", "Sản Phẩm 3", "hinhAnh1", "hinhAnh2", "hinhAnh3", 3, 3, 3, 300F));
        dataSP.add(new SanPham("SP04", "Sản Phẩm 4", "hinhAnh1", "hinhAnh2", "hinhAnh3", 4, 4, 4, 400F));
        dataSP.add(new SanPham("SP05", "Sản Phẩm 5", "hinhAnh1", "hinhAnh2", "hinhAnh3", 5, 5, 5, 500F));
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
