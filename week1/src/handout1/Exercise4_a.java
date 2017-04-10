package handout1;

public class Exercise4_a {
	public static void main(String[] args){
		int i;
		
		for(i=1;i<=5;i++){
			String result ="";
			
			for(int j=5-i;j>0;j--){
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
