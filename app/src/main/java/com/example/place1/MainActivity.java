package com.example.place1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Member;

public class MainActivity extends AppCompatActivity {
    private TextView textViewInput1;
    private EditText edtinput_id1, edtinput_pwd1, edtinput_pwdcheck1, edtinput_name1,
            edtinput_birthdate1, edtinput_number1;
    private RadioGroup radiogroup1;
    private RadioButton rg_btnmale, rg_btnfemale;
    private Button btninput_submit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 아이디값 찾아주기
        edtinput_id1 = findViewById(R.id.edtinput_id1);
        edtinput_pwd1 = findViewById(R.id.edtinput_pwd1);
        edtinput_pwdcheck1 = findViewById(R.id.edtinput_pwdcheck1);
        edtinput_name1 = findViewById(R.id.edtinput_name1);
        edtinput_birthdate1 = findViewById(R.id.edtinput_birthdate1);
        edtinput_number1 = findViewById(R.id.edtinput_number1);

        // 가입버튼 클릭 시 수행
        btninput_submit1 = findViewById(R.id.btninput_submit1);
        btninput_submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userID = edtinput_id1.getText().toString();
                String userPwd = edtinput_pwd1.getText().toString();
                String userPwdCheck = edtinput_pwdcheck1.getText().toString();
                String userName = edtinput_name1.getText().toString();
                int userBirth = Integer.parseInt(edtinput_birthdate1.getText().toString());
                int userNumber = Integer.parseInt(edtinput_number1.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 회원가입에 성공한 경우
                                Toast.makeText(getApplicationContext(), "회원 가입에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, Memberlogin.class);
                                startActivity(intent);
                            } else {    // 회원가입에 실패한 경우
                                Toast.makeText(getApplicationContext(), "회원 가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                // 서버로 volley 를 이용하여 요청청
               MemberRequest memberRequest = new MemberRequest(userID, userPwd, userPwdCheck, userName, userBirth, userNumber, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(memberRequest);

            }
        });
    }
}
