package pastaPrincipal;

public class Server {
	
	public static void main(String [] args)  {
		int i = 0;
		while(true) {
		System.out.println("Teste: " + i++);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
}
