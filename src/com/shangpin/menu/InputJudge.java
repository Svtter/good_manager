package com.shangpin.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputJudge {
	Scanner input;
	InputJudge(){
		input = new Scanner(System.in);
	}
	public int InputInt(){
		try{
			int i = input.nextInt();
			return i;
		}catch(InputMismatchException e){
			System.out.println("输入类型错误，请重新输入：");
			input.nextLine();
			return InputInt();
		}
	}
	public double InputDouble(){
		try{
			double i = input.nextDouble();
			return i;
		}catch(InputMismatchException e){
			System.out.println("输入类型错误，请重新输入：");
			input.nextLine();
			return InputDouble();
		}
	}
	public boolean InputString(){
		String t;
		t = input.next();
		if(t.equals("y")||t.equals("Y")){
			return true;
		}
		else
			return false;
	}
}
