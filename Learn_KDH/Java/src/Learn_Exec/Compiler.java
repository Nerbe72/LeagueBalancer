package Learn_Exec;

public class Compiler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cmd cmd = new Cmd();
		
		
		// 닉네임 5, 10개 inputCommand로 넘겨주기
		
		String command = cmd.inputCommand("python ../test.py 123111 123 123");
		
		// 결과 result로 받아오기
		String result = cmd.execCommand(command);
		
		System.out.println(result);
	}

}