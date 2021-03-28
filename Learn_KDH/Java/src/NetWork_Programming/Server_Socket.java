package NetWork_Programming;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server_Socket {

	public static void main(String[] args) {
		BufferedReader in = null;
		PrintWriter out = null;
		ServerSocket listener = null;
		Socket socket = null;

		Scanner scanner = new Scanner(System.in);

		try {
			listener = new ServerSocket(9900);
			System.out.println("연결을 기다리고 있습니다.......");
			
			socket = listener.accept();
			System.out.println("연결 되었습니다.");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			
			while (true) {
				String inputMessage = in.readLine();
				
				
				if (inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("클라이언트에서 bye로 연결을 종료하였음");
					break;
				}
				
				System.out.println("클라이언트 : " + inputMessage);
				System.out.print("보내기>> ");
				String outputMessage = scanner.nextLine();
				out.write(outputMessage + "\n");
				out.flush();
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			scanner.close();
			socket.close();
			listener.close();
		} catch (IOException e) {
			System.out.println("클라이언트와 채팅 중 오류가 발생했습니다.");
		}

	}
}