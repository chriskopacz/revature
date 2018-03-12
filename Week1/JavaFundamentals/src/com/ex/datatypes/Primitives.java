package com.ex.datatypes;

public class Primitives {
	/*
	 * int, char, long, boolean, double, float, short, byte
	 * Integer, Character, Long, Boolean, Double, Float, Short, Byte
	 */
	
	int x;
	final int test;
	
	public Primitives() {
		this(10);
	}
	
	public Primitives(int x) {
		this.x = x;
		this.test = 0;
	}



	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Math.pow(2,31));
		System.out.println(Long.MAX_VALUE);
		
		int x = 1_000_000_000;
	//	int wrong = _111___;
		double y = 100.0_100_0;
	//	double no = 100._100;
		
		long z = 10000000000L;
		
		Integer wrap = x; //autoboxing
		Integer unbox = new Integer(25);
		int a = unbox;
		
		short s = 5;
		
		int ex = s;

		int small = 10;
		s = (short) small;
		
		//octal numbers(digits 0-7) start with 0
		int oct = 017;
		// 8^1 8^0
		System.out.println(oct);
		
		//hex (0-9, A-F) - 0x OR 0X
		
		int hex = 0xFF;
		int h2 = 0XA23b;
		System.out.println(h2);
		
		//binary (0,1) - 0b or 0B
		int bin = 0b0101010101;
		int b2 = 0B1111001;
		

		
		
		
	}

}
