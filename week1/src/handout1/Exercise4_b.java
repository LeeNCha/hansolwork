package handout1;

import java.util.*;

public class Exercise4_b {
	private static Scanner scanner;

	public static void main(String[] args){
		int num;
		System.out.println("숫자를 입력하세요.");
		scanner = new Scanner(System.in);
		num=scanner.nextInt();
		
		int i;
		
		for(i=1;i<=num;i++){
			String result ="";
			
			for(int j=num-i;j>0;j--){
				result=result+" ";
			}
			for(int j=i;j>0;j--){
				result=result+j;
			}
			for(int j=1;j<=i;j++){
				if(j>1) result=result+j;
			}
			System.out.println(result);
		}
		
	}
}
