package com.shangpin.menu;

import java.io.IOException;

import com.shangpin.database.SolderDataBase;
import com.shangpin.database.GoodsDB.goods;


public class GCash extends Menu {

	boolean log;
	Login login;
	SolderDataBase db2;
	public GCash(boolean log){
		super(2);
		this.log = log;
		this.itemnum = 2;
		this.choice = "请选择， 输入数字或按0返回上级菜单";
		this.title = "商品维护系统 前台收银";
		item[0] = "前台登陆";
		item[1] = "购物结算";
		db2 = new SolderDataBase();
		ShowMenu();
	}

	public boolean log(){
		return log;
	}
	public void account(){
		if(log!=true){
			System.out.println("尚未登录！");
			System.out.println("输入任意字符继续。。。");
			input.next();
			return;
		}
		if(db.list.GetNum() == 0){
			System.out.println("商品库为空，已退出该选项");
			return;
		}
		
		double sum;
		
		System.out.println("\n		购物结算");
		System.out.println("输入商品关键字：");
		
		goods g[] = new goods[db.list.GetNum()];
		
		System.out.println("请输入关键字：");
		String e1 = input.next();
		g = db.list.findByKey(e1);
		if(g[0] == null){
			System.out.println("没有找到相关商品！");
		}
		else{
			System.out.println("以下是商品信息：");
			displaylist(g);
		}
		
		System.out.println("请选择商品：");
		String name = input.next();
		
		while(db.list.findByName(name) == null){
			System.out.println("输入名称错误，请重新输入：");
			name = input.next();
		}
		goods t = db.list.findByName(name);
		
		System.out.println("请输入购买数量：");
		int num = judge.InputInt();
		while( num < 0 || num > t.getNum()){
			System.out.println("输入数量错误，请重新输入：");
			num = judge.InputInt();
		}
		System.out.println("请确认信息：");
	
		sum = t.getPrice()*num;
		System.out.println(t.getName()+"	￥"+t.getPrice()+"	购买数量"+num+"	"+t.getName()+"总价"+
				t.getPrice()*num);
		System.out.println("是否继续？（y/n）");
		if(judge.InputString()){
			t.setNum(t.getNum() - num);
			if(db.list.change(name, t)){
				System.out.println("已从仓库扣除");
			}
			try {
				db.Save();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.setNum(num);
			db2.list.add(t);
			try {
				db2.Save();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("总计："+sum);
			System.out.println("\n请输入实际缴费金额：");
			double s;
			s = judge.InputDouble();
			while( s < sum){
				System.out.println("金钱不足，请重新付费");
				s = judge.InputDouble();
			}
			System.out.println("找零："+(s - sum));
			System.out.println("谢谢光临！");
		}
		else
			return;
		
		
	}
	
	
	@Override
	public void UpToMenu() {
		// TODO 自动生成的方法存根
		

	}


	@Override
	public void ChooseItem() {
		// TODO 自动生成的方法存g
		int i;
		i = judge.InputInt();
		while(i < 0|| i>2){
			System.out.println("输入大小错误，请重新输入：");
			i = judge.InputInt();
		}
		switch(i){
		case 0: UpToMenu();return;
		case 1:
			if(login == null)
				login = new Login(log);
			else
				login.ShowMenu();
			log = login.login;
			break;
		case 2:
			account();
			break;
		default:
			System.out.println("数字错误");
			break;
		}
		ShowMenu();		
	}
	private void displaylist(goods[] templist) {
		// TODO Auto-generated method stub
		System.out.println("商品名称 	商品价格 	商品数量");
		for(int i = 0 ; i < templist.length; i++){
			System.out.println(templist[i]);
			if(templist[i] == null)
				break;
		}
	}
}
