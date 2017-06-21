package rayan.avik.firebasefcm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView displayToken, title, message;
    Button btnShowToken;
    String FcmToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayToken = (TextView) findViewById(R.id.tv_show_token);
        btnShowToken = (Button) findViewById(R.id.btn_show);
        title = (TextView) findViewById(R.id.tittle_tv);
        message = (TextView) findViewById(R.id.message_tv);



        /////////////Set Notification Message /////////////////
        if(getIntent().getExtras()!= null){

            for (String key : getIntent().getExtras().keySet()){

                if (key.equals("title"))
                    title.setText(getIntent().getExtras().getString(key));
                else if (key.equals("message"))
                    message.setText(getIntent().getExtras().getString(key));
            }
        }

        //////////Show Token on Button Click ////////////////
        btnShowToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the token
                FcmToken = FirebaseInstanceId.getInstance().getToken();

                Log.d(TAG, "Token: " + FcmToken);
                Toast.makeText(MainActivity.this, FcmToken, Toast.LENGTH_SHORT).show();
                displayToken.setText(FcmToken);
            }
        });
    }
}
