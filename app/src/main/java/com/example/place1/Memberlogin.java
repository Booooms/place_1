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

public class Memberlogin extends AppCompatActivity {
    private EditText EdtId1, EdtPwd1;
    private Button BtnLog1, BtnRegister1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberlogin);

        EdtId1 = findViewById(R.id.EdtId1);
        EdtPwd1 = findViewById(R.id.EdtPwd1);
        BtnLog1 = findViewById(R.id.BtnLog1);
        BtnRegister1 = findViewById(R.id.BtnRegister1);


        // 회원가입 버튼을 클릭 시 수행
        BtnRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Memberlogin.this, MainActivity.class);
                startActivity(intent);

            }
        });




        BtnLog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 입력되어 있는 값을 가져온다
                String userID = EdtId1.getText().toString();
                final String userPwd = EdtPwd1.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 로그인에 성공한 경우
                                String userID = jsonObject.getString("userID");
                                String userPwd = jsonObject.getString("userPwd");
                                Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Memberlogin.this, SaveStart.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPwd", userPwd);
                                startActivity(intent);
                            } else {    // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(userID, userPwd, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Memberlogin.this);
                queue.add(loginRequest);

            }

        });
    }
}