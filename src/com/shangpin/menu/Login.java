package com.shangpin.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.shangpin.database.UserDataBase;

public class Login {
	Scanner input;
	boolean login;
	UserDataBase db;
	public Login(boolean login) {

		this.login = login;
		input = new Scanner(System.in);
		db = new UserDataBase();
		ShowMenu();
	}
	public void ShowMenu() {
		System.out.println("欢迎使用商超购物管理系统\n");
		System.out.println("1.登陆系统\n");
		System.out.println("2.退出\n");
		System.out.println("************************************************");
		System.out.println('\n');
		System.out.print("请选择，输入数字：");
		ChooseItem();
	}
	public boolean getLogin() {
		return login;
	}

	void ChooseItem(){
		try{
			int t;
			while(true){
				t = input.nextInt();
				switch(t){
				case 1:
					if(login){
						System.out.println("您已登入，是否更换用户？");
						String temp;
						temp = input.next();
						if(temp.equals("y")||temp.equals("Y")){
							login = false;
							System.out.println("已登出当前用户， 请重新输入登入信息");
						}
						else
							return;
					}
					int i = 3;
					while(!login && i>=1){
						i--;

						System.out.print("\n请输入用户名：");
						String temp = input.next();
						System.out.print("\n请输入密码：");
						String temp2 = input.next();
						//					System.out.println("输入的用户名为："+temp+"\n输入的密码为："+temp2);
						if(db.list.findUser(temp, temp2)){//查找用户
							login = true;
							System.out.println();
							System.out.println("用户名密码验证成功！即将登入系统。。。");
							return;
						}
						else if(temp.equals("xiaoming")&&temp2.equals("123")){
							login = true;
							System.out.println();
							System.out.println("用户名密码验证成功！即将登入系统。。。");
							return;
						}
						else if(i!=0){
							System.out.println("用户名密码不匹配！");
							System.out.println("您还有"+i+"次登陆机会，请重新输入：");
						}
					}
					if( login != true )
					{
						System.out.println("您没有机会了，系统关闭。");
						System.exit(1);
					}
					break;
				case 2:
					System.out.println("系统即将退出。。。");
					System.exit(0);
					break;
				default:
					System.out.println("请输入正确的数字！请再一次输入：");
					break;
				}
				if( t == 1)
					continue;
			}
		}catch(InputMismatchException e){
			System.out.print("输入格式错误！请重新输入数字：");
			input.nextLine();
			ChooseItem();
		}
	}
}
