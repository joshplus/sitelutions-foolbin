package foolbin;

public class Main {
	public static void main(String[] args){
		Requester r = new Requester();
		System.out.println("Hi!");
		
		SysTray s = new SysTray();
		
		for (int i=0; i<10; i++){
			System.out.println(r.getBody("http://vm.verajosh.com/in.php?max="+i+"&min="+i));
		}
	}

}
