package execWork;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecWorking {
	private String rootPath;	// 프로젝트 루트경로
	private String execPath;	// exec 실행 명령어
	private String line;		// 버퍼 수신용 스트링
	private long start, end;	// 파이썬 파일 실행 하고 파일 입출력하는데 걸리는 시간
	
	private Process process;	// exec를 사용하기 위한 프로세스
	private BufferedReader input;	// 버퍼리더

	private StringBuffer message;	// 결과 메시지
	
	public ExecWorking(String names){
		rootPath = System.getProperty("user.dir");	// 프로젝트 루트 디렉토리
		execPath = "python " + rootPath + "\\pyFile\\playerInfo.py" + names;	// 프로젝트 경로
		message = new StringBuffer("");
		try {
			start = System.currentTimeMillis();	// 시작 시간
			
			process = Runtime.getRuntime().exec(execPath);	// exec 방식으로 프로세스 실행
			input = new BufferedReader(new InputStreamReader(process.getInputStream()));	// .py 파일에서 출력한 정보 버퍼로 받아옴
			while((line = input.readLine())!=null) {
				message.append(line);
			}
			System.out.println(message);
			end = System.currentTimeMillis();	// 종료 시간
			System.out.println("<Python>Running Time: " + (end - start) / 1000f + "s.");
		}	catch(Exception e) {
			e.printStackTrace();
		}
	}
	public StringBuffer getMsg() {	// 결과 전송
		return message;
	}
}