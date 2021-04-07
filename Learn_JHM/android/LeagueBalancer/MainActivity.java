package com.cookandroid.leaguebalancer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private Socket socket;  // 소켓
    DataInputStream in;     // 데이터 입력 스트림
    DataOutputStream out;   // 데이터 출력 스트림

    ArrayList<EditText> editTexts;  // 닉네임 입력 리스트
    // 닉네임 입력 EditText의 아이디 리스트
    int[] ids = {R.id.nickName0,R.id.nickName1,R.id.nickName2,R.id.nickName3,R.id.nickName4,
            R.id.nickName5,R.id.nickName6,R.id.nickName7,R.id.nickName8,R.id.nickName9};
    Button connectServerBtn;    // 서버 연결 버튼
    TextView connectionStatus;  // 서버 연결 상태 텍스트 뷰
    Button makeTeamBtn; // 데이터 전송 및 수신 버튼
    TextView result;    // 데이터 수신 결과 텍스트 뷰
    String resultText="";   // 데이터 수신 결과를 받는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTexts = new ArrayList<EditText>(10);
        for(int i=0;i<10;i++)   // 반복문으로 ArrayList에 버튼 할당
            editTexts.add(findViewById(ids[i]));

        // 각종 view 할당
        connectServerBtn = (Button)findViewById(R.id.connectServerBtn);
        connectionStatus = (TextView)findViewById(R.id.connectionStatus);
        makeTeamBtn = (Button)findViewById(R.id.makeTeamBtn);
        result = (TextView)findViewById(R.id.result);
    }
    public void ConnectServer(View view) throws IOException{    // 서버 연결 함수
        new Thread(new Runnable() { // 쓰레드 이용
            @Override
            public void run() {
                try{
                    String ip = "192.168.40.173";   // IP 주소
                    int port = 9000;                // PORT 번호
                    socket = new Socket(InetAddress.getByName(ip), port);   // IP와 PORT로 소켓 생성

                    // 데이터 입출력 스트림 생성
                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());

                    runOnUiThread(new Runnable() {  // 메인쓰레드 외에 쓰레드에서 UI 변경시 사용해야하는 함수
                        @Override
                        public void run() {
                            connectionStatus.setText("서버와의 연결상태: 연결됨"); // 서버 연결상태 표시
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    public void MakeTeam(View view){    // 데이터 송수신
        if (out == null) return;    // 서버와 연결이 되어있는지 확인

        StringBuffer names = new StringBuffer("");  // 닉네임을 저장할 스트링버퍼
        for(int i=0;i<10;i++)   // 반복문으로 각 EditText에 입력한 스트링 스트링 버퍼에 저장
            names.append(" " + editTexts.get(i).getText().toString());

        new Thread(new Runnable() { // 쓰레드 이용
            @Override
            public void run() {
                try{
                    runOnUiThread(new Runnable() {  // 메인쓰레드 외에 쓰레드에서 UI 변경시 사용해야하는 함수
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,names,
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    out.writeUTF(names.toString()); // 닉네임을 송신(UTF방식으로 한글 송신 가능)
                    out.flush();    // 닉네임 송신후 버퍼비우기

                    resultText = in.readUTF();  // 결과 수신(UTF방식으로 한글 수신 가능)
                    runOnUiThread(new Runnable() {  // 메인쓰레드 외에 쓰레드에서 UI 변경시 사용해야하는 함수
                        @Override
                        public void run() {
                            result.setText("결과: " + resultText);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    protected void onStop(){
        super.onStop();
        try{
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}