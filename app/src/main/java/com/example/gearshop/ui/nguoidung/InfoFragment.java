package com.example.gearshop.ui.nguoidung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.gearshop.R;
import com.example.gearshop.ui.login_register.ui.login.InfoUser;


public class InfoFragment extends Fragment {
    View view;
    EditText etTen, etEmail, etSDT, etCMND, etDiaChi;
    Button btnLuu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info, container, false);
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }


    private void setControl() {
        etTen = view.findViewById(R.id.etTenNguoiDung);
        etEmail = view.findViewById(R.id.etEmail);
        etSDT = view.findViewById(R.id.etSDT);
        etCMND = view.findViewById(R.id.etCMND);
        etDiaChi = view.findViewById(R.id.etDiaChi);
        btnLuu = view.findViewById(R.id.btnLuuThongTinNguoiDung);

        etTen.setText(InfoUser.name_user);
        etEmail.setText(InfoUser.Email_user);
        etSDT.setText(InfoUser.SDT_user);
        etCMND.setText(InfoUser.CMND_user);
        etDiaChi.setText(InfoUser.DiaChi_user);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}