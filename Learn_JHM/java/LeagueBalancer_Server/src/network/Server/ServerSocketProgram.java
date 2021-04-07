package network.Server;

import java.io.*;
import java.net.*;
import java.util.*;

import execWork.ExecWorking;

public class ServerSocketProgram {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;	// 서버 소켓
		Socket socket = null;	// 소켓
		
		// 입출력 데이터 스트림
		InputStream inputStream = null;
		DataInputStream dataInputStream = null;
		OutputStream outputStream = null;
		DataOutputStream dataOutputStream = null;
		
		ExecWorking exec = null;	// Exec 실행 객체
		Scanner scanner = null;

		try {
			serverSocket = new ServerSocket(9000);	// 서버 소켓 열기
			System.out.println("연결을 기다리고 있습니다.......");
			
			socket = serverSocket.accept();	// 클라이언트와 소켓 연결
			System.out.println("연결 되었습니다.");
			
			// 입출력 스트림에 소켓 연결
			inputStream = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			
			scanner = new Scanner(System.in);
			
			String names = dataInputStream.readUTF(); // 데이터 수신
			System.out.println("clientMessage:"+names);
			
			exec = new ExecWorking(names);	// 수신된 데이터로 exec 실행
			System.out.println("serverMessage:"+exec.getMsg());
			
			dataOutputStream.writeUTF(exec.getMsg().toString() + "\n");	// 결과 송신
			dataOutputStream.flush();	
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {	// 입출력 스트림 닫기
				if(dataOutputStream != null) dataOutputStream.close();
				if(outputStream != null) outputStream.close();
				if(dataInputStream != null) dataInputStream.close();
				if(inputStream != null) inputStream.close();			
			} catch (IOException e) {
				System.out.println("클라이언트와 채팅 중 오류가 발생했습니다.");
			}
		}
	}
}