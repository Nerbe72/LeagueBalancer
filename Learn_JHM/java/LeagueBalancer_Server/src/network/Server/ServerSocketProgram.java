package network.Server;

import java.io.*;
import java.net.*;
import java.util.*;

import execWork.ExecWorking;

public class ServerSocketProgram {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;	// ���� ����
		Socket socket = null;	// ����
		
		// ����� ������ ��Ʈ��
		InputStream inputStream = null;
		DataInputStream dataInputStream = null;
		OutputStream outputStream = null;
		DataOutputStream dataOutputStream = null;
		
		ExecWorking exec = null;	// Exec ���� ��ü
		Scanner scanner = null;

		try {
			serverSocket = new ServerSocket(9000);	// ���� ���� ����
			System.out.println("������ ��ٸ��� �ֽ��ϴ�.......");
			
			socket = serverSocket.accept();	// Ŭ���̾�Ʈ�� ���� ����
			System.out.println("���� �Ǿ����ϴ�.");
			
			// ����� ��Ʈ���� ���� ����
			inputStream = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			
			scanner = new Scanner(System.in);
			
			String names = dataInputStream.readUTF(); // ������ ����
			System.out.println("clientMessage:"+names);
			
			exec = new ExecWorking(names);	// ���ŵ� �����ͷ� exec ����
			System.out.println("serverMessage:"+exec.getMsg());
			
			dataOutputStream.writeUTF(exec.getMsg().toString() + "\n");	// ��� �۽�
			dataOutputStream.flush();	
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {	// ����� ��Ʈ�� �ݱ�
				if(dataOutputStream != null) dataOutputStream.close();
				if(outputStream != null) outputStream.close();
				if(dataInputStream != null) dataInputStream.close();
				if(inputStream != null) inputStream.close();			
			} catch (IOException e) {
				System.out.println("Ŭ���̾�Ʈ�� ä�� �� ������ �߻��߽��ϴ�.");
			}
		}
	}
}