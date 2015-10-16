package tracker.fission.com.loginapp;

/**
 * Created by fission on 10/9/2015.
 */


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ForgotActivity extends Activity {
    private EditText edtUserName1;
    private EditText edtmail2;
    private Button btnpassword1;
    TextView btnAccount3;
    String Password;
    String emailPattern;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotactivity);
        SharedPreferences prefs = getSharedPreferences("your_file_name",
                MODE_PRIVATE);
        final String username = prefs.getString("username","null");

        Password = prefs.getString("password","null");
        edtUserName1=(EditText )findViewById(R.id.edtUserName1);
        // edtmail2=(EditText )findViewById(R.id.edtmail2);
        btnpassword1=(Button) findViewById(R.id.btnpassword1);
        btnAccount3=(TextView)findViewById(R.id.btnAccount3);

        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        btnAccount3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAccount3.setPaintFlags(btnAccount3.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                Intent i=new Intent(ForgotActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        btnpassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUserName1.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_SHORT).show();
                }

                else if(edtUserName1.getText().toString().equals(username)){

                    /*Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] { edtmail2.getText().toString() });
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Pasword Recovery");
                    intent.putExtra(Intent.EXTRA_TEXT, "Your password is"+"   "+Password);
                    startActivity(Intent.createChooser(intent, ""));
*/

                    Toast.makeText(getApplicationContext(),"Your password is "+Password,Toast.LENGTH_LONG).show();



                }
                else
                {
                    Toast.makeText(getApplicationContext(),"You dont have account please register",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnAccount3.setPaintFlags(0);
    }
}



