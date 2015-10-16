package tracker.fission.com.loginapp;

/**
 * Created by fission on 10/9/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by fission on 3/18/2015.
 */
public class MainActivity extends Activity {
    private EditText edtUserName;

    private EditText edtPassword;
    private Button btnlogin;
    TextView btnAccount1;
    TextView forgotpassword;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnAccount1 = (TextView) findViewById(R.id.btnAccount1);
        forgotpassword = (TextView) findViewById(R.id.forgotpassword);

        btnAccount1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAccount1.setPaintFlags(btnAccount1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotpassword.setPaintFlags(forgotpassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                Intent i = new Intent(MainActivity.this, ForgotActivity.class);
                startActivity(i);

            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUserName.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_SHORT).show();
                } else if (edtPassword.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences prefs = getSharedPreferences("your_file_name",
                            MODE_PRIVATE);


                    String username = prefs.getString("username", "null");
                    String Password = prefs.getString("password", "null");
                    if (edtUserName.getText().toString().equals(username) && edtPassword.getText().toString().equals(Password)) {
                        Intent i = new Intent(MainActivity.this, WelcomePage.class);
                        startActivity(i);

                    } else {
                        Toast.makeText(getApplicationContext(), "please Register", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }


    /*@Override
    protected void onStart() {
        super.onStart();
        btnAccount1.setPaintFlags(0);
        forgotpassword.setPaintFlags(0);
    }*/

    @Override
    protected void onStop() {
        super.onStop();
        edtUserName.setText("");
        edtPassword.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnAccount1.setPaintFlags(0);
        forgotpassword.setPaintFlags(0);
    }
    /*
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }*/
}



