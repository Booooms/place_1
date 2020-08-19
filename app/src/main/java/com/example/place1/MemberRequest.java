package com.example.place1;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MemberRequest extends StringRequest {

    // 서버 URL 설정 (깃허브)
    final static private String URL = "https://github.com/Booooms/place.git";
    private Map<String, String> map;


    public MemberRequest(String userID, String userPwd, String userPwdCheck, String userName,
                         int userBirth, int userNumber, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPwd", userPwd);
        map.put("userPwdCheck", userPwdCheck);
        map.put("userName", userName);
        map.put("userBirth", userBirth + "");
        map.put("userNumber", userNumber + "");

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
