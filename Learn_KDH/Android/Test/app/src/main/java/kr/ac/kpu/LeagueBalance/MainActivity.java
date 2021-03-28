package kr.ac.kpu.LeagueBalance;

import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class MainActivity extends Activity {
    private Socket socket;
    PrintWriter out;
    BufferedReader in;
    EditText input;
    Button button;
    TextView recvView, sendView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.connectButton);
        input = (EditText) findViewById(R.id.editText);
        recvView = (TextView) findViewById(R.id.RecvView);
        sendView = (TextView) findViewById(R.id.SendView);


    }

    public void ClientSocketOpen(View view) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket();
                    SocketAddress addr = new InetSocketAddress("192.168.219.100", 9900);
                    socket.connect(addr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(socket.getOutputStream(), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while (true) {
                    String msg;
                    try {
                        msg = in.readLine();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recvView.setText("[RECV] : " + msg);

                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    public void SendMessage(View view) {
        if (out == null) return;

        new Thread(new Runnable() {
            @Override
            public void run() {
                String msg = input.getText().toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String msg = input.getText().toString();
                        sendView.setText("[SEND]" + msg);
                        input.setText("");
                    }
                });
                out.println(msg);
            }
        }).start();
    }

    protected void onStop() {
        super.onStop();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}