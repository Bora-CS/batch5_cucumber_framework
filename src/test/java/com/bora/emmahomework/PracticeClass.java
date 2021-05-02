package com.bora.emmahomework;

public class PracticeClass {
	public static void main(String[] args) {

		String test = "I love Java";

		int len = test.length();
		String reversedTest = "";
		for (int i = len - 1; i >= 0; i--) {
			reversedTest = reversedTest + test.charAt(i);
		}

		System.out.println("Reversed Test is : " + reversedTest);

		swapNumsWithTemprorayVar(7, 11);
		swapNumWithOutTemprorayVar(7, 11);
		FizzBuzz(500);
		Fibonacci(10);
		int finonacci=Fibonacci__2(10);
		System.out.println("Fibonacci Value :"+ finonacci);

	}

	public static void swapNumsWithTemprorayVar(int a, int b) {
		System.out.println("Before swap value is " + a + " " + b);

		int temp = a;

		a = b;
		b = temp;

		System.out.println("After swaped value is " + a + " " + b);

	}

	public static void swapNumWithOutTemprorayVar(int a, int b) {
		System.out.println("Before swap value is " + a + " " + b);
		a += b; // a= a+b >> a= 7+11=18
		b = a - b;// b=11, b=a-b=18-11 >>>b=7
		a = a - b;// a=18,a=a-b=18-7 >>>a=11
		System.out.println("After swap: " + a + " " + b);
	}

	public static void FizzBuzz(int n) {
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 && i % 11 == 0) {
				System.out.println("This is FizzBuzz :" + i);
			} else if (i % 11 == 0) {
				System.out.println("This is Fizz :" + i);
			} else if (i % 3 == 0) {
				System.out.println("This is Buzz :" + i);
			}
		}
	}

	public static int Fibonacci(int k) {
		if (k <= 1)
			return 0;
		if (k == 2)
			return 1;
		int[] nums = new int[k];
		nums[0] = 0;
		nums[1] = 1;
		for (int i = 2; i < k; i++) {

			nums[i] = nums[i - 2] + nums[i - 1];
		}
		return nums[k - 1];
	}

	public static int Fibonacci__2(int n) {
		if (n <= 1)
			return 0;
		if (n == 2)
			return 1;

		int a = 0;
		int b = 1;
		int c = 0;
		for (int i = 2; i < n; i++) {

			c = a + b;
			a = b;
			b = c;
		}

		return c;

	}
}
