package handout1;

public class Exercise3 {
	public static void main(String args[]){
		int x = -5, power=512, num=12, MAX=512,Sens=12;
		boolean y=true;
		double MIN=-12.0, item=1.5;
		char DAY='M';
		
		System.out.println((x>num)&&!y);
		System.out.println((item>MIN)||(DAY!='M'));
		System.out.println((num*128<power)&&y);
		System.out.println("power!=MAX : "+(!(power!=MAX)));
		System.out.println("Sense==num : "+(Sens==num));
		System.out.println((!(power!=MAX))&&(Sens==num));
		System.out.println(((MIN+x)<num)||(DAY=='M'));
		System.out.println((Sens*0)!=0);
		System.out.println((!true||y)&&(!y||false));
	}

}
