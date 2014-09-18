package com.shangpin.menu;

import java.util.InputMismatchException;

import com.shangpin.database.UserDataBase;
import com.shangpin.database.UserDB.User;

public class UMaintain extends Menu {

	UserDataBase db3;
	public UMaintain(){
		super(4);
		this.itemnum = 4;
		this.title = "��Ʒ����ϵͳ �û�����";
		this.choice = "��ѡ�� �������ֻ�0�����ϼ��˵�";
		db3 = new UserDataBase();
		item[0] = "����û�";
		item[1] = "�����û�";
		item[2] = "ɾ���û�";
		item[3] = "��ʾ�����û�";
		ShowMenu();
	}

	public void ChooseItem(){
		try{ 
			int a = -1;
			a = input.nextInt();
			while( a < 0 || a > 4){
				System.out.println("�������ֳ���ѡ��ֵ�����������룺");
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
			System.out.println("�����ʽ�������������룺");
			ChooseItem();
		}
		ShowMenu();
	}	
	public void add(){
		User temp = new User();

		System.out.println("ִ���û���Ӳ�����"
				+'\n'+'\n'+'\n');
		System.out.println("����û����ƣ�");
		temp.setName(input.next());
		System.out.println("����û����룺");
		String d;
		d = input.next();
		temp.setPasswd(d);
		db3.list.add(temp);
		if(db3.Save())
			System.out.println("����ɹ����Ƿ������ӣ�(y/n)");

		String charge = input.next();
		if(charge.equals("y")||charge.equals("Y"))
			add();
	}	
	public void change(){
		User temp = new User();

		System.out.println("ִ���û����Ĳ�����"
				+'\n'+'\n'+'\n');

		System.out.println("�����û����ƣ�");
		String name = input.next();
		temp = db3.list.findByName(name);

		System.out.println("�û�����		�û�����");	
		System.out.println(temp.toString());
		System.out.println();

		System.out.println("��ѡ����Ҫ���ĵ����ݣ�");
		System.out.println("1.�����û����ƣ�\n"
				+ "2.�����û����룻\n");
		int t;
		while(true){
			t = judge.InputInt();
			switch(t){
			case 1:
				System.out.println("�������µ��û����ƣ�");
				temp.setName(input.next());
				break;
			case 2: 
				System.out.println("�������µ��û��۸�");
				temp.setPasswd(input.next());
				break;
			default: 
				System.out.println("�����������������");
				break;
			}
			if(t==1||t==2||t==3)
				break;
		}
		if(db3.list.change(name, temp)){
			System.out.println("���ĳɹ���");
		}
		System.out.println("�Ƿ������(y/n)");
		name = input.next();
		if(name.equals("y")||name.equals("Y")){
			change();
		}
	}
	public void delete(){
		System.out.println("ִ���û�ɾ��������"
				+'\n'+'\n'+'\n');

		System.out.println("�������û����ƣ�");
		String t = input.next();
		User temp = db3.list.findByName(t);
		if(temp!=null){
			System.out.println("����ΪҪɾ�����û���Ϣ��ȷ��ɾ������y/n��");
			System.out.println("�û����� 	�û�����");
			System.out.println(temp);
		}
		else{
			System.out.println("ɾ��ʧ�ܣ������û������Ƿ���ȷ��");
			return;
		}
		t = input.next();
		if(t.equals("y")||t.equals("Y")){
			if(db3.list.delete(temp))
				System.out.println("ɾ���ɹ����Ƿ����ɾ������y/n)");
			else
				System.out.println("ɾ��ʧ�ܣ��������");
		}
		t = input.next();
		if(t.equals("y")||t.equals("Y")){
			delete();
		}
	}	
	public void disp(){
		System.out.println("ִ���û��б������"
				+'\n'+'\n'+'\n');

		System.out.println("�û�����   	�û�����");
		db3.list.ShowList();
		System.out.println("\n\n����������û�������");
		input.nextLine();		
	}
	@Override
	public void UpToMenu() {

	}
}
