package com.ex.classes;

public class ConstructorDemo {
	
	int a;
	int b;
	int x, y, z;
	
	// ConstructorDemo cd = new ConstructorDemo();
	public ConstructorDemo() {
		System.out.println("in no args constructor");
	}
	
	public ConstructorDemo(int random) {
		this();
		this.a = random;
		System.out.println("in random constructor");
	}
	
	public ConstructorDemo(String num) {
		this();
		this.a = Integer.parseInt(num);

	}

	
	// ConstructorDemo cd = new ConstructorDemo(1, 2, 3, 4, 5);
	public ConstructorDemo(int a, int b, int x, int y, int z) {
		super();
		this.a = a;
		this.b = b;
		this.x = x;
		this.y = y;
		this.z = z;
		System.out.println("in all var constructor");
	}
	
	public static void main(String[] args) {
		ConstructorDemo test = new ConstructorDemo(5);
	}
}


class Test{
	
}