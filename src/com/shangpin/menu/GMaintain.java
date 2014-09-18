package com.shangpin.menu;

import java.io.IOException;
import java.util.InputMismatchException;

import com.shangpin.database.GoodsDB.goods;

public class GMaintain extends Menu {

	public GMaintain(){
		super(5);
		this.itemnum = 5;
		this.title = "商品维护系统 商品维护";
		this.choice = "请选择， 输入数字或按0返回上级菜单";
		item[0] = "添加商品";
		item[1] = "更改商品";
		item[2] = "删除商品";
		item[3] = "显示所有商品";
		item[4] = "查询商品";
		ShowMenu();
	}

	public void ChooseItem(){
		try{ 
			int a = -1;
			a = input.nextInt();
			while( a < 0 || a > 5){
				System.out.println("输入数字超出选项值，请重新输入：");
				a = input.nextInt();				
			}

			switch(a){
			case 0: UpToMenu();return;
			case 1: add();break;
			case 2: change();break;
			case 3: delete();break;
			case 4: disp();break;
			case 5: find();break;
			}

		}catch(InputMismatchException e){
			System.out.println("输入格式错误，请重新输入：");
			ChooseItem();
		}
		ShowMenu();
	}	
	public void add(){
		goods temp = new goods();

		System.out.println("执行商品添加操作："
				+'\n'+'\n'+'\n');
		System.out.println("添加商品名称：");
		temp.setName(input.next());
		System.out.println("添加商品价格：");
		double d = 0;
		d = judge.InputDouble();
		while(d <= 0){
			System.out.println("商品价格应大于0！请重新输入：");
			d = judge.InputDouble();
		}
		temp.setPrice(d);
		System.out.println("输入添加商品的数量：");
		int i = 0;
		i = judge.InputInt();
		while(i <= 0){
			System.out.println("商品价格应大于0！请重新输入：");
			i = judge.InputInt();
		}
		temp.setNum(i);
		db.list.add(temp);

		try {
			if(db.Save())
				System.out.println("保存成功，是否继续添加？(y/n)");
		} catch (ClassNotFoundException e) {
			System.out.println("class not find");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOExcepted");
			e.printStackTrace();
		}
		String charge = input.next();
		if(charge.equals("y")||charge.equals("Y"))
			add();
	}	
	public void change(){
		goods temp = new goods();

		System.out.println("执行商品更改操作："
				+'\n'+'\n'+'\n');

		System.out.println("输入商品名称：");
		String name = input.next();
		System.out.println("商品名称为："+name);

		temp = db.list.findByName(name);

		if(temp == null){
			System.out.println("没有该商品！");
			return;
		}
		System.out.println("商品名称		商品价格		商品数量");	
		System.out.println(temp.toString());
		System.out.println();

		System.out.println("请选择您要更改的内容：");
		System.out.println("1.更改商品名称；\n"
				+ "2.更改商品价格；\n"
				+ "3.更改商品数量；\n");
		int t;
		while(true){
			t = judge.InputInt();

			switch(t){
			case 1:
				System.out.println("请输入新的商品名称：");
				temp.setName(input.next());
				break;
			case 2: 
				System.out.println("请输入新的商品价格：");
				temp.setPrice(input.nextDouble());
				break;
			case 3: 
				System.out.println("请输入新的商品数量:");
				temp.setNum(input.nextInt());
				break;
			default: 
				System.out.println("输入错误，请重新输入");
				break;
			}
			if(t==1||t==2||t==3)
				break;
		}
		if(db.list.change(name, temp)){
			System.out.println("更改成功！");
		}
		try {
			db.Save();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("是否继续？(y/n)");
		name = input.next();
		if(name.equals("y")||name.equals("Y")){
			change();
		}
	}
	public void delete(){
		System.out.println("执行商品删除操作："
				+'\n'+'\n'+'\n');

		System.out.println("请输入商品名称：");
		String t = input.next();
		goods temp = null;
		try{
			temp = db.list.findByName(t);
		}catch(NullPointerException e){
			System.out.println("findByName error");
		}
		while(temp == null){
			System.out.println("删除失败，请检查商品名称是否正确。");
			return;
		}
		if(temp!=null){
			System.out.println("以下为要删除的商品信息，确认删除？（y/n）");
			System.out.println("商品名称 	商品价格 	商品数量");
			System.out.println(temp);
		}
		t = input.next();
		if(t.equals("y")||t.equals("Y")){
			if(db.list.delete(temp))
				System.out.println("删除成功，是否继续删除？（y/n)");
			else{
				System.out.println("删除失败，请检查代码");
				return;
			}
		}
		try {
			db.Save();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t = input.next();
		if(t.equals("y")||t.equals("Y")){
			delete();
		}
	}	
	public void disp(){
		System.out.println("执行商品删除操作："
				+'\n'+'\n'+'\n');

		System.out.println("商品名称   	商品数量		商品价格			备注");
		db.list.ShowList();
		System.out.println("\n\n已输出所有商品。。。");
		input.nextLine();		
	}
	public void find(){

		System.out.println("执行商品查询操作："
				+'\n'+'\n'+'\n');
		System.out.println("1.按商品数量升序查询：\n"
				+ "2.按商品价格升序查询\n"
				+ "3.输入关键字查询商品");
		System.out.println(choice);
		int num = db.list.GetNum();
		goods[] templist = new goods[num];
		templist = db.list.GetAll();

		
		int temp;
		temp = judge.InputInt();
		while(temp >3 || temp < 1){
			System.out.println("超出范围，请重新输入");
			temp = judge.InputInt();
		}
		System.out.println("正在读取数据。。。");
		
//		System.out.println(num);
//		for(int i = 0; i < num; i++)
//			System.out.println(templist[i]);

		switch(temp){
		case 1:
			for(int i = 0; i < num; i++)
				for(int j = 0; j<num-i-1; j++){
					if(templist[j].getNum()> templist[j+1].getNum()){
						goods temp1 = templist[j];
						templist[j] = templist[j+1];
						templist[j+1] = temp1;
					}
				}
			displaylist(templist,num);			
			break;
		case 2:
			for(int i = 0; i < num; i++)
				for(int j = 0; j<num-i-1; j++){
					if(templist[j].getPrice()> templist[j+1].getPrice()){
						goods temp1 = templist[j];
						templist[j] = templist[j+1];
						templist[j+1] = temp1;
					}
				}
			displaylist(templist,num);	
			break;
		case 3:
			System.out.println("请输入关键字：");
			String e = input.next();
			templist = db.list.findByKey(e);
			if(templist[0] == null){
				System.out.println("没有找到相关商品！");
			}
			else{
				System.out.println("以下是商品信息：");
				displaylist(templist);
			}
			break;
		default:
			System.out.println("error input");
			break;
		}
		System.out.println("是否继续查询？(y/n)");
		if(judge.InputString()){
			find();
		}
	}

	private void displaylist(goods[] templist) {
		// TODO Auto-generated method stub
		System.out.println("商品名称 	商品数量 	商品价格");
		for(int i = 0 ; i < templist.length; i++){
			if(templist[i] == null)
				break;
			System.out.println(templist[i]);
		}
	}

	private void displaylist(goods[] templist, int n) {
		// TODO Auto-generated method stub
		System.out.println("商品名称 	商品数量 	商品价格");
		for(int i = 0 ; i < n; i++){
			System.out.println(templist[i]);
		}
	}

	@Override
	public void UpToMenu() {

	}
}
