package com.example.yuer.zahui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.zahui.R;
import com.example.yuer.zahui.utils.MyDBHelper;

public class LoginActivity extends AppCompatActivity {

    TextView tvRegeist;
    EditText etNickName,etPassword;
    Button btnLogin;
    MyDBHelper dbHelper;

    CheckBox rememberPass;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        pref= PreferenceManager.getDefaultSharedPreferences(this);
        tvRegeist=(TextView) findViewById(R.id.tvRegeist);
        tvRegeist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到注册界面
                Intent intent=new Intent(LoginActivity.this,RegeistActivity.class);
                startActivity(intent);
            }
        });
        etNickName=(EditText) findViewById(R.id.etNickName_login);
        etPassword=(EditText) findViewById(R.id.etPassword);
        rememberPass=(CheckBox) findViewById(R.id.cbRemember);
        dbHelper=new MyDBHelper(this,"MyDB.db",null,1);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        boolean isRemember=pref.getBoolean("remember_password",false);
        if (isRemember)
        {
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            etNickName.setText(account);
            etPassword.setText(password);
            rememberPass.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NICKNAME=etNickName.getText().toString();
                String password=etPassword.getText().toString();//取到用户输入的信息
                //拿到后和数据库中的数据进行比对
                //根据用户名取数据库中取对应密码，然后进行比对
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                //只取数据库中密码一列   查询有没有这个用户
               Cursor cursor= db.query("UserInfo",new String[]{"regeistPassword"},"nickName = ?",new String[]{NICKNAME},null,null,null);
//                Cursor cursor1= db.query("UserInfo",new String[]{"regeistName"},"regeistPassword = ?",new String[]{password},null,null,null);
                if (cursor.moveToFirst())
                {
                    //如果返回值有一个结果即true的话
                    //说明有值，有这个用户
                    //取这个密码
                    String pwdTemp=cursor.getString(0);
//                    String userTemp=cursor1.getString(0);
                    //比对
                    if (pwdTemp.equals(password))
                    {
                        editor=pref.edit();
                        if (rememberPass.isChecked())
                        {
                            editor.putBoolean("remember_password",true);
                            editor.putString("account",NICKNAME);
                            editor.putString("password",password);
                        }
                        else
                        {
                            editor.clear();
                        }
                        editor.apply();
                        Toast.makeText(LoginActivity.this,"来呀，一锅乱炖啊!",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("nickName",NICKNAME);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"密码错误，请重新输入！",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"不存在该用户，请注册!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
