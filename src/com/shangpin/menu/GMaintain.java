package com.shangpin.menu;

import java.io.IOException;
import java.util.InputMismatchException;

import com.shangpin.database.GoodsDB.goods;

public class GMaintain extends Menu {

	public GMaintain(){
		super(5);
		this.itemnum = 5;
		this.title = "��Ʒά��ϵͳ ��Ʒά��";
		this.choice = "��ѡ�� �������ֻ�0�����ϼ��˵�";
		item[0] = "�����Ʒ";
		item[1] = "������Ʒ";
		item[2] = "ɾ����Ʒ";
		item[3] = "��ʾ������Ʒ";
		item[4] = "��ѯ��Ʒ";
		ShowMenu();
	}

	public void ChooseItem(){
		try{ 
			int a = -1;
			a = input.nextInt();
			while( a < 0 || a > 5){
				System.out.println("�������ֳ���ѡ��ֵ�����������룺");
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
			System.out.println("�����ʽ�������������룺");
			ChooseItem();
		}
		ShowMenu();
	}	
	public void add(){
		goods temp = new goods();

		System.out.println("ִ����Ʒ��Ӳ�����"
				+'\n'+'\n'+'\n');
		System.out.println("�����Ʒ���ƣ�");
		temp.setName(input.next());
		System.out.println("�����Ʒ�۸�");
		double d = 0;
		d = judge.InputDouble();
		while(d <= 0){
			System.out.println("��Ʒ�۸�Ӧ����0�����������룺");
			d = judge.InputDouble();
		}
		temp.setPrice(d);
		System.out.println("���������Ʒ��������");
		int i = 0;
		i = judge.InputInt();
		while(i <= 0){
			System.out.println("��Ʒ�۸�Ӧ����0�����������룺");
			i = judge.InputInt();
		}
		temp.setNum(i);
		db.list.add(temp);

		try {
			if(db.Save())
				System.out.println("����ɹ����Ƿ������ӣ�(y/n)");
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

		System.out.println("ִ����Ʒ���Ĳ�����"
				+'\n'+'\n'+'\n');

		System.out.println("������Ʒ���ƣ�");
		String name = input.next();
		System.out.println("��Ʒ����Ϊ��"+name);

		temp = db.list.findByName(name);

		if(temp == null){
			System.out.println("û�и���Ʒ��");
			return;
		}
		System.out.println("��Ʒ����		��Ʒ�۸�		��Ʒ����");	
		System.out.println(temp.toString());
		System.out.println();

		System.out.println("��ѡ����Ҫ���ĵ����ݣ�");
		System.out.println("1.������Ʒ���ƣ�\n"
				+ "2.������Ʒ�۸�\n"
				+ "3.������Ʒ������\n");
		int t;
		while(true){
			t = judge.InputInt();

			switch(t){
			case 1:
				System.out.println("�������µ���Ʒ���ƣ�");
				temp.setName(input.next());
				break;
			case 2: 
				System.out.println("�������µ���Ʒ�۸�");
				temp.setPrice(input.nextDouble());
				break;
			case 3: 
				System.out.println("�������µ���Ʒ����:");
				temp.setNum(input.nextInt());
				break;
			default: 
				System.out.println("�����������������");
				break;
			}
			if(t==1||t==2||t==3)
				break;
		}
		if(db.list.change(name, temp)){
			System.out.println("���ĳɹ���");
		}
		try {
			db.Save();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�Ƿ������(y/n)");
		name = input.next();
		if(name.equals("y")||name.equals("Y")){
			change();
		}
	}
	public void delete(){
		System.out.println("ִ����Ʒɾ��������"
				+'\n'+'\n'+'\n');

		System.out.println("��������Ʒ���ƣ�");
		String t = input.next();
		goods temp = null;
		try{
			temp = db.list.findByName(t);
		}catch(NullPointerException e){
			System.out.println("findByName error");
		}
		while(temp == null){
			System.out.println("ɾ��ʧ�ܣ�������Ʒ�����Ƿ���ȷ��");
			return;
		}
		if(temp!=null){
			System.out.println("����ΪҪɾ������Ʒ��Ϣ��ȷ��ɾ������y/n��");
			System.out.println("��Ʒ���� 	��Ʒ�۸� 	��Ʒ����");
			System.out.println(temp);
		}
		t = input.next();
		if(t.equals("y")||t.equals("Y")){
			if(db.list.delete(temp))
				System.out.println("ɾ���ɹ����Ƿ����ɾ������y/n)");
			else{
				System.out.println("ɾ��ʧ�ܣ��������");
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
		System.out.println("ִ����Ʒɾ��������"
				+'\n'+'\n'+'\n');

		System.out.println("��Ʒ����   	��Ʒ����		��Ʒ�۸�			��ע");
		db.list.ShowList();
		System.out.println("\n\n�����������Ʒ������");
		input.nextLine();		
	}
	public void find(){

		System.out.println("ִ����Ʒ��ѯ������"
				+'\n'+'\n'+'\n');
		System.out.println("1.����Ʒ���������ѯ��\n"
				+ "2.����Ʒ�۸������ѯ\n"
				+ "3.����ؼ��ֲ�ѯ��Ʒ");
		System.out.println(choice);
		int num = db.list.GetNum();
		goods[] templist = new goods[num];
		templist = db.list.GetAll();

		
		int temp;
		temp = judge.InputInt();
		while(temp >3 || temp < 1){
			System.out.println("������Χ������������");
			temp = judge.InputInt();
		}
		System.out.println("���ڶ�ȡ���ݡ�����");
		
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
			System.out.println("������ؼ��֣�");
			String e = input.next();
			templist = db.list.findByKey(e);
			if(templist[0] == null){
				System.out.println("û���ҵ������Ʒ��");
			}
			else{
				System.out.println("��������Ʒ��Ϣ��");
				displaylist(templist);
			}
			break;
		default:
			System.out.println("error input");
			break;
		}
		System.out.println("�Ƿ������ѯ��(y/n)");
		if(judge.InputString()){
			find();
		}
	}

	private void displaylist(goods[] templist) {
		// TODO Auto-generated method stub
		System.out.println("��Ʒ���� 	��Ʒ���� 	��Ʒ�۸�");
		for(int i = 0 ; i < templist.length; i++){
			if(templist[i] == null)
				break;
			System.out.println(templist[i]);
		}
	}

	private void displaylist(goods[] templist, int n) {
		// TODO Auto-generated method stub
		System.out.println("��Ʒ���� 	��Ʒ���� 	��Ʒ�۸�");
		for(int i = 0 ; i < n; i++){
			System.out.println(templist[i]);
		}
	}

	@Override
	public void UpToMenu() {

	}
}
