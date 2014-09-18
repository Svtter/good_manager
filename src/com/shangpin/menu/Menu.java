package com.shangpin.menu;

import java.util.Scanner;

import com.shangpin.database.GoodsDataBase;

public abstract class Menu {
	protected String item[];
	protected String title;
	protected String choice;
	protected Scanner input;
	protected int itemnum;
	protected InputJudge judge;
	protected GoodsDataBase db;
	
	protected void ShowMenu(){
		System.out.println(""+title+"");
		System.out.println("********************************************");
		System.out.println("*                                          *");
		for(int i = 0; i < itemnum; ++i){
			System.out.println("*		"+(i+1)+"."+item[i]+"		   *");
		}
		System.out.println("*                                          *");
		System.out.println("********************************************");
		System.out.println(choice);
		ChooseItem();
	}
	public Menu(int itemnum){
		input = new Scanner(System.in);
		judge = new InputJudge();
		item = new String[itemnum];
		this.itemnum = itemnum;
		db = new GoodsDataBase();
	}

	public abstract void UpToMenu();
	public abstract void ChooseItem();
}
