package com.shangpin.database.UserDB;

import java.io.Serializable;


@SuppressWarnings("serial")
public class UserList implements Serializable{
	User data;
	UserList next;
	UserList pre;

	public User getData() {
		return data;
	}

	public void setData(User data) {
		this.data = data;
	}

	public UserList getNext() {
		return next;
	}

	public void setNext(UserList next) {
		this.next = next;
	}

	public UserList getPre() {
		return pre;
	}

	public void setPre(UserList pre) {
		this.pre = pre;
	}

	public UserList() {
		this.next = null;
		this.pre = null;
		this.data = null;
	}

	public void add(User f){
		try{
			UserList e;
			e = new UserList();
			e.data = f;
			if(this.next == null)
				this.next = e;
			else
			{
				e.next = this.next;
				this.next = e;
				e.pre = this;
				e.next.pre = e;
			}
		}catch(NullPointerException e){
			System.out.println("list�� ���ʧ��");
		}
	}

	public boolean delete(User e){
		UserList temp;
		temp = this;
		while(e!=temp.data && temp!=null) {
			temp = temp.next;
		}
		if(temp != null && temp.data == e){
			temp.pre.next = temp.next;
			return true;
		}
		else
			return false;
	}

	public boolean change(String e, User n){
		UserList temp;
		temp = this;
		if(temp.next == null){
			System.out.println("�ֿ�Ϊ��");
			return false;
		}
		temp = temp.next;
		while(temp!= null){
			if(n.getName().equals(temp.data.getName())){
				temp.data = n;
				return true;
			}			
			temp = temp.next;
		}
		return false;
	}

	public User findByName(String e){
		try{
			UserList temp;
			temp = this;
			if(temp.next == null){
				System.out.println("�ֿ�Ϊ��");
				return null;
			}
			else
				temp = temp.next;
			while(temp != null ){
				if(e.equals(temp.data.getName())){
					return temp.data;
				}
				temp = temp.next;
			}
			return null;
		}
		catch(NullPointerException e1){
			return null;
		}
	}
	
	public boolean findUser(String name, String passwd){
		UserList temp;
		temp = this;
		if(temp.next == null)
			return false;
		temp = temp.next;
		while(temp != null){
			if(name.equals(temp.data.getName())&&passwd.equals(temp.data.getPasswd())){
				return true;
			}				
		}
		return false;
	}
	public User[] findByKey(char a[]){//�˴�����ʹ��kmp�㷨
		UserList temp = this;
		User[] templist = new User[GetNum()];
		int no = 0;
		char name[];
		if(temp.next == null){
			System.out.println("�û�Ϊ��");
			return null;
		}
		temp = temp.next;
		while(temp!=null){
			name = temp.data.name.toCharArray();
			boolean fuhe = false;
			for(int i = 0; i < name.length; i++){
				for(int j = 0; j < name.length; j++){
					if(name[i] == a[j]){
						fuhe = true;
						break;
					}
				}
			}
			if(fuhe){
				templist[no] = temp.data;
				no++;
				break;
			}
			temp = temp.next;
		}
		return templist;	
	}
	

	public User[] GetAll(){
		UserList temp = this;
		User[] templist = new User[GetNum()];
		if(temp.next == null)
			return null;
		int no = 0;
		temp = temp.next;
		while(temp!=null){
			templist[no] = temp.data;
			no++;
			temp = temp.next;
		}
		return templist;		
	}
	
	public int GetNum(){
		UserList temp = this;
		if(temp.next == null)
			return 0;
		int no = 0;
		while(temp.next!=null){
			no++;
			temp = temp.next;
		}
		return no;
	}

	public void ShowList(){
		try{
			UserList temp = this;
			if(temp.next == null)
				System.out.println("�ñ�Ϊ��");
			while(temp.next!=null){
				System.out.print(temp.next.data);
				temp = temp.next;
				if(temp.next == null){
					break;
				}
			}
		}catch(NullPointerException e){
		}
	}
}
