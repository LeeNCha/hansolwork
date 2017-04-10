package chap05;

public class circle {
	public static void main (String args[]){
		
	}//method
}//class

class Circles{
	public double radius;
	public double area() {return radius * radius * 3.14;}
};

class Cylinder extends Circles{
	public double height;
	public double volume(){
		return super.area()*height;
	}
	
	public double area(){ //when you override same name
		return 2 * super.area()+(2*radius*3.14)*height; //you should be careful when you use overridden method
	}//overriding is related to runtime
};
