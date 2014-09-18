package com.shangpin.Main;

import java.io.IOException;
import java.util.InputMismatchException;

import com.shangpin.menu.*;

public class GMainten extends Menu{

	boolean login;
	GCash cash;
	GMaintain maintain;
	GManage manage;

	public GMainten() throws ClassNotFoundException, IOException{
		super(3);
		login = false;
		this.itemnum = 3;
		this.title = "商品维护系统 	主目录";
		this.choice = "请选择， 输入数字或者输入0退出系统：";
		item[0] = "商品维护";
		item[1] = "前台收银";
		item[2] = "商品管理";
		ShowMenu();
	}


	@Override
	public void UpToMenu() {
		System.out.println();
	}

	@Override
	public void ChooseItem(){
		int i = 0;
		boolean yes = false;
		try{
			i = input.nextInt();
			while(i > 3 || i < 0){
				System.out.println("数字输入有误，请重新输入：");
				i = input.nextInt();				
			}
			if(i >= 0 && i <= 3){
				yes = true;
			}
		}catch(InputMismatchException e){
			System.out.println("输入有误，请输入合法数字：");
			input.nextLine();
			yes = false;
			ChooseItem();
		}
		if(yes){
			switch(i){
			case 0: 
				System.out.println("系统已退出。。。");
				System.exit(0);
				break;				
			case 1: 
				if(login)
					maintain = new GMaintain(); 
				else{
					System.out.println("尚未登录无法使用该功能！请至前台收银登录！");
					System.out.println("输入任意字符继续。。。");
					input.next();
				}
				break;			
			case 2: 
				cash = new GCash(login);
				login = cash.log();
				break;
			case 3: 
				if(login)
					manage = new GManage();
				else{
					System.out.println("尚未登录无法使用该功能！请至前台收银登录！");
					System.out.println("输入任意字符继续。。。");
					input.next();
				}break;
			default:
				System.out.println("error input, check your code");
				break;
			}
		}
		try {
			db.Save();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ShowMenu();
	}
//	public static GMainten getMainMenu(){
//	}
}
