package execWork;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayPy {
	private String rootPath;
	private String execPath;
//	private String filePath;
	private String line;
	private long start, end;	// ���̽� ���� ���� �ϰ� ���� ������ϴµ� �ɸ��� �ð�
	
	private Process p;
	private BufferedReader input;
//	private BufferedWriter output;
	private StringBuffer names = new StringBuffer();
	
	public PlayPy(StringBuffer names){
		this.names = names;
		rootPath = System.getProperty("user.dir");	// ������Ʈ ��Ʈ ���丮
		execPath = "python " + rootPath + "\\pyFile\\playerInfo.py" + names;	// ������Ʈ ���
//		filePath = rootPath + "\\pyFile\\playerInfo.txt";	// ���ϰ��
		
		try {
			start = System.currentTimeMillis();	// ���� �ð�
			
			p = Runtime.getRuntime().exec(execPath);	// exec ������� ���μ��� ����
			input = new BufferedReader(new InputStreamReader(p.getInputStream()));	// .py ���Ͽ��� ����� ���� ���۷� �޾ƿ�
			while((line = input.readLine())!=null) {
				System.out.println(line);
			}
//			output = new BufferedWriter(new FileWriter(filePath));
//			while((line = input.readLine())!=null) {
//				output.write(line+"\n");
//			}
//			output.close();
			end = System.currentTimeMillis();	// ���� �ð�
			System.out.println("<Python>Running Time: " + (end - start) / 1000f + "s.");
		}	catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}