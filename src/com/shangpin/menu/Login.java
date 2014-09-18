package com.shangpin.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.shangpin.database.UserDataBase;

public class Login {
	Scanner input;
	boolean login;
	UserDataBase db;
	public Login(boolean login) {

		this.login = login;
		input = new Scanner(System.in);
		db = new UserDataBase();
		ShowMenu();
	}
	public void ShowMenu() {
		System.out.println("��ӭʹ���̳��������ϵͳ\n");
		System.out.println("1.��½ϵͳ\n");
		System.out.println("2.�˳�\n");
		System.out.println("************************************************");
		System.out.println('\n');
		System.out.print("��ѡ���������֣�");
		ChooseItem();
	}
	public boolean getLogin() {
		return login;
	}

	void ChooseItem(){
		try{
			int t;
			while(true){
				t = input.nextInt();
				switch(t){
				case 1:
					if(login){
						System.out.println("���ѵ��룬�Ƿ�����û���");
						String temp;
						temp = input.next();
						if(temp.equals("y")||temp.equals("Y")){
							login = false;
							System.out.println("�ѵǳ���ǰ�û��� ���������������Ϣ");
						}
						else
							return;
					}
					int i = 3;
					while(!login && i>=1){
						i--;

						System.out.print("\n�������û�����");
						String temp = input.next();
						System.out.print("\n���������룺");
						String temp2 = input.next();
						//					System.out.println("������û���Ϊ��"+temp+"\n���������Ϊ��"+temp2);
						if(db.list.findUser(temp, temp2)){//�����û�
							login = true;
							System.out.println();
							System.out.println("�û���������֤�ɹ�����������ϵͳ������");
							return;
						}
						else if(temp.equals("xiaoming")&&temp2.equals("123")){
							login = true;
							System.out.println();
							System.out.println("�û���������֤�ɹ�����������ϵͳ������");
							return;
						}
						else if(i!=0){
							System.out.println("�û������벻ƥ�䣡");
							System.out.println("������"+i+"�ε�½���ᣬ���������룺");
						}
					}
					if( login != true )
					{
						System.out.println("��û�л����ˣ�ϵͳ�رա�");
						System.exit(1);
					}
					break;
				case 2:
					System.out.println("ϵͳ�����˳�������");
					System.exit(0);
					break;
				default:
					System.out.println("��������ȷ�����֣�����һ�����룺");
					break;
				}
				if( t == 1)
					continue;
			}
		}catch(InputMismatchException e){
			System.out.print("�����ʽ�����������������֣�");
			input.nextLine();
			ChooseItem();
		}
	}
}
