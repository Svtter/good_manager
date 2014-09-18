package com.shangpin.menu;

import java.util.InputMismatchException;

import com.shangpin.database.UserDataBase;
import com.shangpin.database.UserDB.User;

public class UMaintain extends Menu {

	UserDataBase db3;
	public UMaintain(){
		super(4);
		this.itemnum = 4;
		this.title = "商品管理系统 用户管理";
		this.choice = "请选择， 输入数字或按0返回上级菜单";
		db3 = new UserDataBase();
		item[0] = "添加用户";
		item[1] = "更改用户";
		item[2] = "删除用户";
		item[3] = "显示所有用户";
		ShowMenu();
	}

	public void ChooseItem(){
		try{ 
			int a = -1;
			a = input.nextInt();
			while( a < 0 || a > 4){
				System.out.println("输入数字超出选项值，请重新输入：");
				a = input.nextInt();				
			}

			switch(a){
			case 0: UpToMenu();return;
			case 1: add();break;
			case 2: change();break;
			case 3: delete();break;
			case 4: disp();break;
			default:
				System.out.println("Umanage error");
				break;
			}

		}catch(InputMismatchException e){
			System.out.println("输入格式错误，请重新输入：");
			ChooseItem();
		}
		ShowMenu();
	}	
	public void add(){
		User temp = new User();

		System.out.println("执行用户添加操作："
				+'\n'+'\n'+'\n');
		System.out.println("添加用户名称：");
		temp.setName(input.next());
		System.out.println("添加用户密码：");
		String d;
		d = input.next();
		temp.setPasswd(d);
		db3.list.add(temp);
		if(db3.Save())
			System.out.println("保存成功，是否继续添加？(y/n)");

		String charge = input.next();
		if(charge.equals("y")||charge.equals("Y"))
			add();
	}	
	public void change(){
		User temp = new User();

		System.out.println("执行用户更改操作："
				+'\n'+'\n'+'\n');

		System.out.println("输入用户名称：");
		String name = input.next();
		temp = db3.list.findByName(name);

		System.out.println("用户名称		用户密码");	
		System.out.println(temp.toString());
		System.out.println();

		System.out.println("请选择您要更改的内容：");
		System.out.println("1.更改用户名称；\n"
				+ "2.更改用户密码；\n");
		int t;
		while(true){
			t = judge.InputInt();
			switch(t){
			case 1:
				System.out.println("请输入新的用户名称：");
				temp.setName(input.next());
				break;
			case 2: 
				System.out.println("请输入新的用户价格：");
				temp.setPasswd(input.next());
				break;
			default: 
				System.out.println("输入错误，请重新输入");
				break;
			}
			if(t==1||t==2||t==3)
				break;
		}
		if(db3.list.change(name, temp)){
			System.out.println("更改成功！");
		}
		System.out.println("是否继续？(y/n)");
		name = input.next();
		if(name.equals("y")||name.equals("Y")){
			change();
		}
	}
	public void delete(){
		System.out.println("执行用户删除操作："
				+'\n'+'\n'+'\n');

		System.out.println("请输入用户名称：");
		String t = input.next();
		User temp = db3.list.findByName(t);
		if(temp!=null){
			System.out.println("以下为要删除的用户信息，确认删除？（y/n）");
			System.out.println("用户名称 	用户密码");
			System.out.println(temp);
		}
		else{
			System.out.println("删除失败，请检查用户名称是否正确。");
			return;
		}
		t = input.next();
		if(t.equals("y")||t.equals("Y")){
			if(db3.list.delete(temp))
				System.out.println("删除成功，是否继续删除？（y/n)");
			else
				System.out.println("删除失败，请检查代码");
		}
		t = input.next();
		if(t.equals("y")||t.equals("Y")){
			delete();
		}
	}	
	public void disp(){
		System.out.println("执行用户列表操作："
				+'\n'+'\n'+'\n');

		System.out.println("用户名称   	用户密码");
		db3.list.ShowList();
		System.out.println("\n\n已输出所有用户。。。");
		input.nextLine();		
	}
	@Override
	public void UpToMenu() {

	}
}
