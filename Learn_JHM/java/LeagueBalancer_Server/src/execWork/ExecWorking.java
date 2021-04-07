package execWork;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecWorking {
	private String rootPath;	// ������Ʈ ��Ʈ���
	private String execPath;	// exec ���� ��ɾ�
	private String line;		// ���� ���ſ� ��Ʈ��
	private long start, end;	// ���̽� ���� ���� �ϰ� ���� ������ϴµ� �ɸ��� �ð�
	
	private Process process;	// exec�� ����ϱ� ���� ���μ���
	private BufferedReader input;	// ���۸���

	private StringBuffer message;	// ��� �޽���
	
	public ExecWorking(String names){
		rootPath = System.getProperty("user.dir");	// ������Ʈ ��Ʈ ���丮
		execPath = "python " + rootPath + "\\pyFile\\playerInfo.py" + names;	// ������Ʈ ���
		message = new StringBuffer("");
		try {
			start = System.currentTimeMillis();	// ���� �ð�
			
			process = Runtime.getRuntime().exec(execPath);	// exec ������� ���μ��� ����
			input = new BufferedReader(new InputStreamReader(process.getInputStream()));	// .py ���Ͽ��� ����� ���� ���۷� �޾ƿ�
			while((line = input.readLine())!=null) {
				message.append(line);
			}
			System.out.println(message);
			end = System.currentTimeMillis();	// ���� �ð�
			System.out.println("<Python>Running Time: " + (end - start) / 1000f + "s.");
		}	catch(Exception e) {
			e.printStackTrace();
		}
	}
	public StringBuffer getMsg() {	// ��� ����
		return message;
	}
}