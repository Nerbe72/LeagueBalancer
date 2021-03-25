package Learn_Exec;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cmd {
	
	private StringBuffer buffer;
	private Process process;
	private BufferedReader bufferedReader;
	private StringBuffer readBuffer;
	
	public String inputCommand(String cmd) {
		buffer = new StringBuffer();
		buffer.append("cmd.exe "); // 명령 프롬프트 실행
		buffer.append("/c "); // /c 드라이브
		buffer.append(cmd); // cmd 실행
		
		return buffer.toString();
	}
	
	public String execCommand(String cmd) {
		try {
			process = Runtime.getRuntime().exec(cmd);
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			readBuffer = new StringBuffer();
			
			while((line = bufferedReader.readLine())!=null) { // 버퍼에 있는 내용 다 읽어오기
				readBuffer.append(line);
				readBuffer.append("\n");
			}
			return readBuffer.toString();
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
