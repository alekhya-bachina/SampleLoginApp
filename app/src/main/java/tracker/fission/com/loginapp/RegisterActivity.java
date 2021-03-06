package tracker.fission.com.loginapp;

/**
 * Created by fission on 10/9/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends Activity {

    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtPassword2;
    private Button btnSignup;
    String emailPattern;
    String passwordpattern;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        txtFirstName = (EditText) findViewById(R.id.edtfirstname1);
        txtEmail = (EditText) findViewById(R.id.edtmail1);
        txtPassword = (EditText) findViewById(R.id.edtPassword1);
        txtPassword2=(EditText)findViewById(R.id.edtPassword2);
        btnSignup = (Button) findViewById(R.id.btnsignup1);

        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        passwordpattern="[[0-9]&&[a-z]&&[A-Z]]";
        //txtUserName.setError("Please Enter UserName");
        Singleton.usernamelist.add(txtFirstName.getText().toString());
        btnSignup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtFirstName.getText().toString().toString().length()==0){
                    Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_SHORT).show();

                }else if(Singleton.usernamelist.contains(txtFirstName.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Sorry!!!!!!username already taken", Toast.LENGTH_SHORT).show();
                }



                else if (txtEmail.getText().toString().trim().length() == 0) {
                    //txtUserName.setError("Please Enter UserName");
                    Toast.makeText(getApplicationContext(), "Please enter Email", Toast.LENGTH_SHORT).show();



                }else if(!(txtPassword2.getText().toString().equals(txtPassword.getText().toString())))
                {
                    Toast.makeText(getApplicationContext(),"passwords doesnt match",Toast.LENGTH_SHORT).show();
                }else if(!txtPassword2.getText().toString().matches(passwordpattern)&&txtPassword2.getText().toString().length()<6)
                {
                    Toast.makeText(getApplicationContext(),"password should contain a capital letter and a number and the password length should be greater than 6",Toast.LENGTH_SHORT).show();
                }


                else if ((txtEmail.getText().toString().trim().matches(emailPattern))) {
                    Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
                } else {

                    Singleton.usernamelist.add(txtFirstName.getText().toString());
                    Toast.makeText(getApplicationContext(), "Your sucessfully register", Toast.LENGTH_SHORT).show();
                    SharedPreferences prefs = getSharedPreferences("your_file_name", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("username", txtFirstName.getText().toString());
                    editor.putString("password", txtPassword.getText().toString());
                    editor.commit();
                    Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });

    }



    @Override
    protected void onStop() {
        super.onStop();
        txtFirstName.setText("");

        txtEmail.setText("");
        txtPassword.setText("");
        txtPassword2.setText("");
    }
}





