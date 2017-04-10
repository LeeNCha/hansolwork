package handout1;

public class Exercise1 {
	public static void main(String args[]){

		for (int i=1;i<20;i++){
			if(i%3==0)
				continue;
			if(i%4==0)
				i++;
			if(i>10)
				break;
			
			System.out.println(i);
		}
	}
}
