package com.bora.emmahomework;

import java.util.Scanner;

public class FibonacciPractice {

	//reverNum
	public static void revseNum(int a ,int b){
		// swap nums with out the third part tenprprayVar  
		a+=b;
		b=a-b;
		a=a-b;
		System.out.println(a);
		System.out.println(b);
	}
	
	
	public static void main(String[] args) {

		revseNum(10, 20);
		
		// Fibonacci with Scanner

		int k, a = 0, b = 1;
		Scanner sc = new Scanner(System.in);
		System.out.println(">>>Fibonacci test :.....");
		System.out.println("Please enter some Number :");

		int n = sc.nextInt();
		sc.close();
		System.out.print(a+" ");
		System.out.print(b+" ");
		k = 0;
		while (k <= n) {
			k = a + b;
			System.out.print(k + " ");
			a = b;
			b = k;

		}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>我是华丽的分割线 ，加油吧 少年 ！  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
		System.out.println(" ");

		// FizzeBuzz
		System.out.println(">>>FizzBuzz test:.....");
		
		printFizzBuzz(100);

		System.out.println(" ");
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>我是华丽的分割线 ，加油吧 少年 ！ >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//

		// solution 1 : reverse the String

		System.out.println(">>>Reverse String test:.....");
		String text = "Hello";

		String reversedText = "";
		
		for (int i = text.length() - 1; i >= 0; i--) {

			reversedText = reversedText + text.charAt(i);
			
		}
		System.out.println("Reverse String solution1 :" + reversedText);

		String word = reverse("Hello");
		System.out.println("Reverse String solution2 :" + word);
	}

	// solution 2 :reverse the String >>return Type
	public static String reverse(String s) {

		char[] letters = new char[s.length()];
		int lettersIndex = 0;

		for (int i = s.length() - 1; i >= 0; i--) {

			letters[lettersIndex] = s.charAt(i);
			lettersIndex++;
		}
		String reverseText = "";
		for (int i = 0; i < s.length(); i++) {
			reverseText = reverseText + letters[i];
		}

		return reverseText;

	}

	// FizzeBuzz Method

	public static void printFizzBuzz(int n) {
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 && i % 5 == 0) {

				System.out.println("This is Fizz Buzz");
			} else if (i % 3 == 0) {
				System.out.println("This is Fizz ");
			} else if (i % 5 == 0) {
				System.out.println("This is Buzz");
			} else {
				System.out.println(i);
			}
		}
	}

}
