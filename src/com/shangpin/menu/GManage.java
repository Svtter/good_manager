package com.shangpin.menu;

import com.shangpin.database.SolderDataBase;
import com.shangpin.database.GoodsDB.goods;


public class GManage extends Menu {
	SolderDataBase db2;
	public GManage(){
		super(3);
		// TODO �Զ����ɵĹ��캯�����
		db2 = new SolderDataBase();
		this.title = "��Ʒά��ϵͳ ��Ʒ����";
		this.choice = "��ѡ�� �������ֻ�0�����ϼ��˵�";
		item[0] = "��Ʒ���Ԥ������";
		item[1] = "������Ʒ���ۻ���";
		item[2] = "�ۻ�Ա�˺Ź���";
		ShowMenu();
	}


	public void Warn(){
		System.out.println("��Ʒ����		��Ʒ�۸�		��Ʒ����		����		��ע");
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
				System.out.print("*����Ʒ�����Ѳ���10����");
			}
			System.out.println();
		}
	}
	public void AmountSum(){
		System.out.println("�����۳���Ʒ��");
		System.out.println("��Ʒ����		��Ʒ�۸�		��Ʒ����		����		��ע");
		goods templist[] = new goods[db2.list.GetNum()];
		goods temp;
		if(db2.list.GetNum() == 0){
			System.out.println("�ñ�Ϊ��~��");
			return;
		}
		templist = db2.list.GetAll();
		for(int i = 0; i < db2.list.GetNum(); i++){
			temp = db.list.findByName(templist[i].getName());
			System.out.print(temp.getName()+"		"+temp.getPrice()+"		"+
					temp.getNum()+"		"+templist[i].getNum());
			if(temp.getNum() < 10){
				System.out.print("	*��ǰ��Ʒ����10����");
			}
			System.out.println();
		}
		System.out.println("����������ż���������");
		input.next();

	}
	public void UserManage(){
		new UMaintain();
	}

	@Override
	public void UpToMenu() {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void ChooseItem() {
		// TODO �Զ����ɵķ������
		int i = judge.InputInt();
		while(i > 3 || i < 0){
			System.out.println("����������������룺");
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
