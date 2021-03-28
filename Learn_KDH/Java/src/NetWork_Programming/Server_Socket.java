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
			System.out.println("������ ��ٸ��� �ֽ��ϴ�.......");
			
			socket = listener.accept();
			System.out.println("���� �Ǿ����ϴ�.");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			
			while (true) {
				String inputMessage = in.readLine();
				
				
				if (inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("Ŭ���̾�Ʈ���� bye�� ������ �����Ͽ���");
					break;
				}
				
				System.out.println("Ŭ���̾�Ʈ : " + inputMessage);
				System.out.print("������>> ");
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
			System.out.println("Ŭ���̾�Ʈ�� ä�� �� ������ �߻��߽��ϴ�.");
		}

	}
}