package com.example.gearshop.ui.nguoidung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.MainActivity;
import com.example.gearshop.R;
import com.example.gearshop.ui.home.HomeFragment;
import com.example.gearshop.ui.login_register.ui.login.InfoUser;
import com.example.gearshop.ui.model.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PasswordFragment extends Fragment {

    View view;
    EditText PW1, PW2, PW3;
    Button btnThayDoi;

    Connection connection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_password, container, false);
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
        btnThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass1, pass2, pass3;
                pass1 = PW1.getText().toString();
                pass2 = PW2.getText().toString();
                pass3 = PW3.getText().toString();
                if(pass1.equals("") || pass2.equals("") || pass3.equals("")){
                    Toast.makeText(PasswordFragment.this.getContext(), "Vui Lòng nhập đầy đủ thông tin !!!",Toast.LENGTH_LONG).show();
                }else if(!pass2.equals(pass3)){
                    Toast.makeText(PasswordFragment.this.getContext(), "Password mới nhập không trùng khớp !!!",Toast.LENGTH_LONG).show();
                }else{
                    Boolean checkMK = false;
                    try {
                        ConnectSQL con = new ConnectSQL();
                        connection = con.CONN();
                        if(connection != null){
                            String query = "select * from TaiKhoan where MaTK='"+ InfoUser.id_user + "' and MatKhau='" + pass1 + "'" ;
                            Statement statement = connection.createStatement();
                            ResultSet rs = statement.executeQuery(query);
                            while (rs.next()){
                                checkMK = true;
                            }
                            connection.close();
                        }
                    }catch (Exception ex){
                        System.err.print(ex.getMessage());
                    }

                    if(checkMK){
                        try {
                            ConnectSQL con = new ConnectSQL();
                            connection = con.CONN();
                            String query = "UPDATE TaiKhoan SET MatKhau = ? WHERE MaTK = ?";
                            PreparedStatement stmt = connection.prepareStatement(query);
                            stmt.setString(1, pass2);
                            stmt.setInt(2, InfoUser.id_user);
                            stmt.executeUpdate();
                            Toast.makeText(PasswordFragment.this.getContext(), "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                            connection.close();
                            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                            transaction.replace(R.id.content_frame, new HomeFragment());
                            transaction.commit();
                        }catch (Exception ex){
                            System.err.print(ex.getMessage());
                        }
                    }else{
                        Toast.makeText(PasswordFragment.this.getContext(), "Password hiện tại bạn nhập không đúng !!!",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void setControl() {
        PW1 = view.findViewById(R.id.PW1);
        PW2 = view.findViewById(R.id.PW2);
        PW3 = view.findViewById(R.id.PW3);
        btnThayDoi = view.findViewById(R.id.btnThaydoiPW);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}