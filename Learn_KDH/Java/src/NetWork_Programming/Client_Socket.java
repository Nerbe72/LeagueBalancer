package NetWork_Programming;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client_Socket {
	BufferedReader in;
	BufferedWriter out;
	Socket socket;

	public Client_Socket() {
		in = null;
		out = null;
		socket = null;
	}

	public void Connection() {
		// TODO Auto-generated method stub
		// BufferedReader in = null;
		// BufferedWriter out = null;
		// Socket socket = null;
		Scanner scanner = new Scanner(System.in);

		try {
			this.socket = new Socket("localhost", 9999);

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while (true) {
				System.out.println("������>> ");
				String outputMessage = scanner.nextLine();
				if (outputMessage.equalsIgnoreCase("bye")) {
					out.write(outputMessage + "\n");
					break;
				}
				out.write(outputMessage + "\n");
				out.flush();
				String inputMessage = in.readLine();
				System.out.println("���� : " + inputMessage);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			scanner.close();
			if (socket != null)
				socket.close();
		} catch (IOException e) {
			System.out.println("������ ä�� �� ������ �߻��߽��ϴ�.");
		}
	}

}
