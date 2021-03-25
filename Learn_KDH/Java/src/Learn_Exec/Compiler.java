package Learn_Exec;

public class Compiler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cmd cmd = new Cmd();
		
		String command = cmd.inputCommand("python D:\\GitHub\\LeagueBalancer\\Learn_KDH\\Java\\src\\Learn_Exec\\test.py");
		String result = cmd.execCommand(command);
		
		System.out.println(result);
	}

}
