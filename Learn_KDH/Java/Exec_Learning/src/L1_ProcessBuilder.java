import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class L1_ProcessBuilder {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("test");

		Process process = new ProcessBuilder("cmd", "dir", "/w").start();

		BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String str;
		// 표준출력 상태를 출력
		while ((str = stdOut.readLine()) != null) {
			System.out.println(str);
		}

	}
}