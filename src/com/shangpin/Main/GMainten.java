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
		this.title = "��Ʒά��ϵͳ 	��Ŀ¼";
		this.choice = "��ѡ�� �������ֻ�������0�˳�ϵͳ��";
		item[0] = "��Ʒά��";
		item[1] = "ǰ̨����";
		item[2] = "��Ʒ����";
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
				System.out.println("���������������������룺");
				i = input.nextInt();				
			}
			if(i >= 0 && i <= 3){
				yes = true;
			}
		}catch(InputMismatchException e){
			System.out.println("��������������Ϸ����֣�");
			input.nextLine();
			yes = false;
			ChooseItem();
		}
		if(yes){
			switch(i){
			case 0: 
				System.out.println("ϵͳ���˳�������");
				System.exit(0);
				break;				
			case 1: 
				if(login)
					maintain = new GMaintain(); 
				else{
					System.out.println("��δ��¼�޷�ʹ�øù��ܣ�����ǰ̨������¼��");
					System.out.println("���������ַ�����������");
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
					System.out.println("��δ��¼�޷�ʹ�øù��ܣ�����ǰ̨������¼��");
					System.out.println("���������ַ�����������");
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
