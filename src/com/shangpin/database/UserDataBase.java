package com.shangpin.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.shangpin.database.UserDB.UserList;

@SuppressWarnings("serial")
public class UserDataBase implements Serializable{

	File file;
	public UserList list;
	public UserDataBase(){
		list = new UserList();
		file = new File("Userdb.txt");
		Open();
	}
	public boolean Save(){//保存
		try{
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(list);
			objectOut.close();	
			return true;
		}		
		catch(IOException event){
			System.out.println(event);
			return false;
		}	
	}
	public boolean Open(){
		try{
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			list = (UserList)objectIn.readObject();
			objectIn.close();
			return true;
		}
		catch(ClassNotFoundException event){
			System.out.println("不能读出对象");
			return false;
		}
		catch(IOException event){
			System.out.println("IO错误");
			System.out.println(event);
			return false;
		}
	}
	public UserList getList() {
		return list;
	}
	public void setList(UserList list) {
		this.list = list;
	}
}

