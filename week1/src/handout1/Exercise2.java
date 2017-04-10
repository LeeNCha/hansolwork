package handout1;

import java.util.Scanner;

public class Exercise2 {
	private static Scanner scanner;
	public static void main(String args[]){
		boolean b=false,c=true;
		int z=0, i=0;
		System.out.println("숫자를 입력하세요.");
		scanner = new Scanner(System.in);
		i=scanner.nextInt();
		
		switch(i){
			case 11: 
				z=b? 8:!c?3:6;
				break;
			case 12:
			case 13:
				z=10;
				break;
			default :
					z=15;
		}
		
		System.out.println(z);
		
	}

}
