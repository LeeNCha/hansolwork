package chap02;

public class ByteExample {
	public static void main(String[] args){
		byte var1=-128;
		byte var2=-30;
		byte var3=0;
		byte var4=30;
		byte var5=(byte) 128;
		
		System.out.println(var1);
		System.out.println(var2);
		System.out.println(var3);
		System.out.println(var4);
		System.out.println(var5);//byte의 경우 -128~127만 가능하므로, 128은 다시 -128로 저장된다
	}
}
