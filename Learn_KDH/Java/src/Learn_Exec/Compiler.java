package Learn_Exec;

public class Compiler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cmd cmd = new Cmd();
		
		
		// �г��� 5, 10�� inputCommand�� �Ѱ��ֱ�
		
		String command = cmd.inputCommand("python ../test.py 123111 123 123");
		
		// ��� result�� �޾ƿ���
		String result = cmd.execCommand(command);
		
		System.out.println(result);
	}

}