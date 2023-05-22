package com.example.gearshop.ui.nguoidung;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.R;
import com.example.gearshop.ui.Quanli.UpdateActivity;
import com.example.gearshop.ui.home.HomeFragment;
import com.example.gearshop.ui.login_register.ui.login.InfoUser;
import com.example.gearshop.ui.login_register.ui.login.LoginActivity;
import com.example.gearshop.ui.login_register.ui.login.RegisterActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;


public class InfoFragment extends Fragment {
    String EMAIL_REGEX =   "^(.+)@(.+)$";
    String PHONE_REGEX =   "^[0-9._-]{10}$";
    String CMND_REGEX =   "^[0-9._-]{9}$";
    String CCCD_REGEX =   "^[0-9._-]{12}$";
    View view;
    EditText etTen, etEmail, etSDT, etCMND, etDiaChi;
    Button btnLuu;

    Connection connection;
    TextView tvThongBao;
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
                String Ten, email, SDT, CMND, diaChi;

                Pattern ptEmail = Pattern.compile(EMAIL_REGEX);
                Pattern ptPhone = Pattern.compile(PHONE_REGEX);
                Pattern ptCMND = Pattern.compile(CMND_REGEX);
                Pattern ptCCCD = Pattern.compile(CCCD_REGEX);
                Boolean checkEmail = true, checkSDT = true, checkCMND = true;

                Ten = etTen.getText().toString();
                email = etEmail.getText().toString();
                SDT = etSDT.getText().toString();
                CMND = etCMND.getText().toString();
                diaChi = etDiaChi.getText().toString();

                if(email.equals("") ||  Ten.equals("") ||  SDT.equals("") ||  CMND.equals("") ||  diaChi.equals("")){
                    tvThongBao.setText("Vui Lòng Điền Đầy Đủ Thông Tin !!!");
                }else if(!ptEmail.matcher(email).matches()){
                    tvThongBao.setText("Email không hợp lệ. Vui lòng kiểm tra lại Email !!!");
                } else if (!ptCMND.matcher(CMND).matches() && !ptCCCD.matcher(CMND).matches()) {
                    tvThongBao.setText("CMND/CCCD không hợp lệ. Vui lòng kiểm tra lại CMND/CCCD !!!");
                } else if (!ptPhone.matcher(SDT).matches()) {
                    tvThongBao.setText("Số điện thoại không hợp lệ. Vui lòng kiểm tra lại số điện thoại !!!");
                }else{
                    try {
                        ConnectSQL con = new ConnectSQL();
                        connection = con.CONN();
                        if(connection != null){
                            String query = "select * from TaiKhoan where email = '" + email + "' and MaTK != " + InfoUser.id_user;
                            Statement statement = connection.createStatement();
                            ResultSet rs = statement.executeQuery(query);
                            while (rs.next()){
                                checkEmail = false;
                            }
                            connection.close();
                        }
                    }catch (Exception ex){
                        System.err.print(ex.getMessage());
                    }

                    try {
                        ConnectSQL con = new ConnectSQL();
                        connection = con.CONN();
                        if(connection != null){
                            String query = "select * from TaiKhoan where SoDienThoai = '" + SDT + "' and MaTK != " + InfoUser.id_user;
                            Statement statement = connection.createStatement();
                            ResultSet rs = statement.executeQuery(query);
                            while (rs.next()){
                                checkSDT = false;
                            }
                            connection.close();
                        }
                    }catch (Exception ex){
                        System.err.print(ex.getMessage());
                    }

                    try {
                        ConnectSQL con = new ConnectSQL();
                        connection = con.CONN();
                        if(connection != null){
                            String query = "select * from TaiKhoan where CMND = '" + CMND + "' and MaTK != " + InfoUser.id_user;
                            Statement statement = connection.createStatement();
                            ResultSet rs = statement.executeQuery(query);
                            while (rs.next()){
                                checkCMND = false;
                            }
                            connection.close();
                        }
                    }catch (Exception ex){
                        System.err.print(ex.getMessage());
                    }
                    if(checkEmail && checkSDT && checkCMND){
                        try {
                            ConnectSQL con = new ConnectSQL();
                            connection = con.CONN();
                            String query = "UPDATE TaiKhoan SET email = ?, TenNguoiDung = ?, SoDienThoai = ?, CMND = ?, DiaChi = ? WHERE MaTK = ?";
                            PreparedStatement stmt = connection.prepareStatement(query);
                            stmt.setString(1, email);
                            stmt.setString(2, Ten);
                            stmt.setString(3, SDT);
                            stmt.setString(4, CMND);
                            stmt.setString(5, diaChi);
                            stmt.setInt(6, InfoUser.id_user);
                            stmt.executeUpdate();
                            Toast.makeText(InfoFragment.this.getContext(), "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                            InfoUser.Email_user = email;
                            InfoUser.name_user = Ten;
                            InfoUser.SDT_user = SDT;
                            InfoUser.CMND_user = CMND;
                            InfoUser.DiaChi_user = diaChi;
                            connection.close();
                            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                            transaction.replace(R.id.content_frame, new HomeFragment());
                            transaction.commit();
                        }catch (Exception ex){
                            System.err.print(ex.getMessage());
                        }
                    }else{
                        if(!checkEmail){
                            Toast.makeText(InfoFragment.this.getContext(), "Email đã tồn tại. Vui lòng chọn email khác !!!",Toast.LENGTH_LONG).show();
                        }
                        if(!checkSDT){
                            Toast.makeText(InfoFragment.this.getContext(), "SDT đã tồn tại. Vui lòng chọn SDT khác !!!",Toast.LENGTH_LONG).show();
                        }
                        if(!checkCMND){
                            Toast.makeText(InfoFragment.this.getContext(), "CMND đã tồn tại. Vui lòng chọn CMND khác !!!",Toast.LENGTH_LONG).show();
                        }
                    }
                }

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
        tvThongBao = view.findViewById(R.id.tvThongBaoInfo);

        etTen.setText(InfoUser.name_user.trim());
        etEmail.setText(InfoUser.Email_user.trim());
        etSDT.setText(InfoUser.SDT_user);
        etCMND.setText(InfoUser.CMND_user);
        etDiaChi.setText(InfoUser.DiaChi_user);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}