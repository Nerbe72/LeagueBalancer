package execWork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayPy {
	private String rootPath;
	private String execPath;
	private String filePath;
	private String line;
	private long start, end;	// 파이썬 파일 실행 하고 파일 입출력하는데 걸리는 시간
	
	private Process p;
	private BufferedReader input;
	private BufferedWriter output; 
	
	public PlayPy() {		
		rootPath = System.getProperty("user.dir");	// 프로젝트 루트 디렉토리
		execPath = "python " + rootPath + "\\pyFile\\playerInfo.py";	// 프로젝트 경로
		filePath = rootPath + "\\pyFile\\playerInfo.txt";	// 파일경로
		try {
			start = System.currentTimeMillis();	// 시작 시간
			
			p = Runtime.getRuntime().exec(execPath);	// exec 방식으로 프로세스 실행
			input = new BufferedReader(new InputStreamReader(p.getInputStream()));	// .py 파일에서 출력한 정보 버퍼로 받아옴
			output = new BufferedWriter(new FileWriter(filePath));
			while((line = input.readLine())!=null) {
				output.write(line+"\n");
			}
			output.close();
			end = System.currentTimeMillis();	// 종료 시간
			System.out.println("<Python>Running Time: " + (end - start) / 1000f + "s.");
		}	catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}