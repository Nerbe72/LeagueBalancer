package kr.ac.kpu.LeagueBalance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        ClientSocket clientSocket = new ClientSocket(textView, handler);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientSocket.start();
            }
        });
    }


}