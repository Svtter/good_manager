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
		this.choice = "��ѡ�� �������ֻ�0�����ϼ��˵�";
		this.title = "��Ʒά��ϵͳ ǰ̨����";
		item[0] = "ǰ̨��½";
		item[1] = "�������";
		db2 = new SolderDataBase();
		ShowMenu();
	}

	public boolean log(){
		return log;
	}
	public void account(){
		if(log!=true){
			System.out.println("��δ��¼��");
			System.out.println("���������ַ�����������");
			input.next();
			return;
		}
		if(db.list.GetNum() == 0){
			System.out.println("��Ʒ��Ϊ�գ����˳���ѡ��");
			return;
		}
		
		double sum;
		
		System.out.println("\n		�������");
		System.out.println("������Ʒ�ؼ��֣�");
		
		goods g[] = new goods[db.list.GetNum()];
		
		System.out.println("������ؼ��֣�");
		String e1 = input.next();
		g = db.list.findByKey(e1);
		if(g[0] == null){
			System.out.println("û���ҵ������Ʒ��");
		}
		else{
			System.out.println("��������Ʒ��Ϣ��");
			displaylist(g);
		}
		
		System.out.println("��ѡ����Ʒ��");
		String name = input.next();
		
		while(db.list.findByName(name) == null){
			System.out.println("�������ƴ������������룺");
			name = input.next();
		}
		goods t = db.list.findByName(name);
		
		System.out.println("�����빺��������");
		int num = judge.InputInt();
		while( num < 0 || num > t.getNum()){
			System.out.println("���������������������룺");
			num = judge.InputInt();
		}
		System.out.println("��ȷ����Ϣ��");
	
		sum = t.getPrice()*num;
		System.out.println(t.getName()+"	��"+t.getPrice()+"	��������"+num+"	"+t.getName()+"�ܼ�"+
				t.getPrice()*num);
		System.out.println("�Ƿ��������y/n��");
		if(judge.InputString()){
			t.setNum(t.getNum() - num);
			if(db.list.change(name, t)){
				System.out.println("�ѴӲֿ�۳�");
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
			
			System.out.println("�ܼƣ�"+sum);
			System.out.println("\n������ʵ�ʽɷѽ�");
			double s;
			s = judge.InputDouble();
			while( s < sum){
				System.out.println("��Ǯ���㣬�����¸���");
				s = judge.InputDouble();
			}
			System.out.println("���㣺"+(s - sum));
			System.out.println("лл���٣�");
		}
		else
			return;
		
		
	}
	
	
	@Override
	public void UpToMenu() {
		// TODO �Զ����ɵķ������
		

	}


	@Override
	public void ChooseItem() {
		// TODO �Զ����ɵķ�����g
		int i;
		i = judge.InputInt();
		while(i < 0|| i>2){
			System.out.println("�����С�������������룺");
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
			System.out.println("���ִ���");
			break;
		}
		ShowMenu();		
	}
	private void displaylist(goods[] templist) {
		// TODO Auto-generated method stub
		System.out.println("��Ʒ���� 	��Ʒ�۸� 	��Ʒ����");
		for(int i = 0 ; i < templist.length; i++){
			System.out.println(templist[i]);
			if(templist[i] == null)
				break;
		}
	}
}
