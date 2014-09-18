package com.shangpin.menu;

import com.shangpin.database.SolderDataBase;
import com.shangpin.database.GoodsDB.goods;


public class GManage extends Menu {
	SolderDataBase db2;
	public GManage(){
		super(3);
		// TODO 自动生成的构造函数存根
		db2 = new SolderDataBase();
		this.title = "商品维护系统 商品管理";
		this.choice = "请选择， 输入数字或按0返回上级菜单";
		item[0] = "商品库存预警管理";
		item[1] = "当日商品销售汇总";
		item[2] = "售货员账号管理";
		ShowMenu();
	}


	public void Warn(){
		System.out.println("商品名称		商品价格		商品数量		销量		备注");
		goods templist[] = new goods[db.list.GetNum()];
		templist = db.list.GetAll();
		for(int i = 0; i < db.list.GetNum(); i ++){
			System.out.print(templist[i].getName()+"		"+templist[i].getPrice()+"		"+
					templist[i].getNum()+"		");
			goods t = db2.list.findByName(templist[i].getName());
			if(t!=null){
				System.out.print(t.getNum()+"		");
			}
			else
				System.out.print("		");
			if(templist[i].getNum()<10){
				System.out.print("*该商品数量已不足10件！");
			}
			System.out.println();
		}
	}
	public void AmountSum(){
		System.out.println("今日售出商品：");
		System.out.println("商品名称		商品价格		商品数量		销量		备注");
		goods templist[] = new goods[db2.list.GetNum()];
		goods temp;
		if(db2.list.GetNum() == 0){
			System.out.println("该表为空~！");
			return;
		}
		templist = db2.list.GetAll();
		for(int i = 0; i < db2.list.GetNum(); i++){
			temp = db.list.findByName(templist[i].getName());
			System.out.print(temp.getName()+"		"+temp.getPrice()+"		"+
					temp.getNum()+"		"+templist[i].getNum());
			if(temp.getNum() < 10){
				System.out.print("	*当前商品不足10件！");
			}
			System.out.println();
		}
		System.out.println("输入任意符号继续。。。");
		input.next();

	}
	public void UserManage(){
		new UMaintain();
	}

	@Override
	public void UpToMenu() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void ChooseItem() {
		// TODO 自动生成的方法存根
		int i = judge.InputInt();
		while(i > 3 || i < 0){
			System.out.println("输入错误，请重新输入：");
			i = judge.InputInt();
		}
		switch(i){
		case 0:UpToMenu();return;
		case 1:
			Warn();
			break;
		case 2:
			AmountSum();
			break;
		case 3:
			UserManage();
			break;
		}
		ShowMenu();
	}



}
