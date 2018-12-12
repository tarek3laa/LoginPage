package com.apps.example.alaa.tarek.loginpage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdsmdg.tastytoast.TastyToast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    EditText metUserName, metPassword;
    LinearLayout layout;
    TextView textView;
    CheckBox mcbRememberMe;
    Button mbtLogin;
    SharedPreferences sharedPreferences;
    private final String FILE_NAME = "com.apps.example.alaa.tarek.loginpage";
    private final String LOGIN_KEY = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        metUserName = (EditText) findViewById(R.id.tv_username);
        metPassword = (EditText) findViewById(R.id.tv_password);
        layout = (LinearLayout) findViewById(R.id.layout);
        textView = (TextView) findViewById(R.id.tv_good);
        mcbRememberMe = (CheckBox) findViewById(R.id.cb_remember_me);
        mbtLogin = (Button) findViewById(R.id.bt_login);
        sharedPreferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);


        /** ************************************   */

        // Check the time whether it is morning or evening
        if (getCurrentHour() >= 16) {
            // change background to evening background
            layout.setBackgroundResource(R.drawable.login4);
            textView.setText(R.string.good_night);
        }

        if (sharedPreferences.getBoolean(LOGIN_KEY, false)) {
            TastyToast.makeText(this, "authentication Succeeded", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
        }
        mbtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mcbRememberMe.isChecked()) putBoolean(LOGIN_KEY, true);
                goToMainActivity();
            }
        });


    }

    /**
     *  but boolean in sharedPreferences
     * @param s Key
     * @param b Value
     */

    private void putBoolean(String s, boolean b) {
        sharedPreferences.edit().putBoolean(s, b).apply();
    }

    private void goToMainActivity() {
        String username = metUserName.getText().toString();
        String password = metPassword.getText().toString();
        // check if there is an empty field
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password))

            makeTastyToast("There is an empty field", TastyToast.INFO);
        else {

            // check if username and password are correct
            if (username.equals("admin") && password.equals("admin")) {

                makeTastyToast("authentication Succeeded", TastyToast.SUCCESS);
                // startActivity(new Intent());
            } else
                makeTastyToast("username or password incorrect", TastyToast.ERROR);

        }
    }

    /**
     *
     * @param s Messages that appear in the toast
     * @param toast type
     */
    private void makeTastyToast(String s, int toast) {
        TastyToast.makeText(this, s, TastyToast.LENGTH_LONG, toast);
    }

    /**
     * @return Current Time in 24 hour format
     */
    private int getCurrentHour() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH");
        int formattedTime = Integer.parseInt(dateFormat.format(date));
        return formattedTime;
    }
}
