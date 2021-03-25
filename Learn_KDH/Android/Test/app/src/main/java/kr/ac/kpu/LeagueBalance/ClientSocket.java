package kr.ac.kpu.LeagueBalance;


import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.*;
import java.net.*;
import java.util.*;

import android.os.Handler;

public class ClientSocket extends Thread {
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket socket;
    TextView textView;
    Handler handler;

    public ClientSocket(TextView textView, Handler handler) {
        in = null;
        out = null;
        socket = null;
        this.textView = textView;
        this.handler = handler;
    }

    public void run() {
        // TODO Auto-generated method stub
        // BufferedReader in = null;
        // BufferedWriter out = null;
        // Socket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            socket = new Socket();
            SocketAddress addr = new InetSocketAddress("192.168.219.100", 9999);
            socket.connect(addr);

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            out.writeObject("안녕!");
            out.flush();
            Log.d("ClientThread", "서버로 보냄");

            final Object input = in.readObject();
            Log.d("ClientThread", "받은 데이터" + input);

            handler.post(new Runnable() {
                public void run() {
                    textView.setText("받은 데이터: " + input);
                }
            });

//            while (true) {

//                System.out.println("보내기>> ");
//                String outputMessage = scanner.nextLine();
//                if (outputMessage.equalsIgnoreCase("bye")) {
//                    out.write(outputMessage + "\n");
//                    break;
//                }
//                out.write(outputMessage + "\n");
//                out.flush();
//                String inputMessage = in.readLine();
//                System.out.println("서버 : " + inputMessage);
//            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            scanner.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            System.out.println("서버와 채팅 중 오류가 발생했습니다.");
        }
    }

}