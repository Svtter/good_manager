package com.shangpin.database.GoodsDB;

import java.io.Serializable;

@SuppressWarnings("serial")
public class goodslist implements Serializable{
	goods data;
	goodslist next;
	goodslist pre;

	public goods getData() {
		return data;
	}

	public void setData(goods data) {
		this.data = data;
	}

	public goodslist getNext() {
		return next;
	}

	public void setNext(goodslist next) {
		this.next = next;
	}

	public goodslist getPre() {
		return pre;
	}

	public void setPre(goodslist pre) {
		this.pre = pre;
	}

	public goodslist() {
		this.next = null;
		this.pre = null;
		this.data = null;
	}

	public void add(goods f){
		try{
			goodslist e;
			e = new goodslist();
			e.data = f;
			if(this.next == null){
				this.next = e;
				e.next = null;
			}
			else
			{
				e.next = this.next;
				this.next = e;
				e.pre = this;
				e.next.pre = e;
			}
		}catch(NullPointerException e){
			System.out.println("list表 添加失败");
		}
	}

	public boolean delete(goods e){
		goodslist temp;
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

	public boolean change(String e, goods n){
		goodslist temp;
		temp = this;
		if(temp.next == null){
			System.out.println("仓库为空");
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

	public goods findByName(String e){
		try{
			goodslist temp;
			temp = this;
			if(temp.next == null){
				System.out.println("仓库为空");
				return null;
			}

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
	public goods[] findByKey(String e){//此处可以使用kmp算法
		char a[] = e.toCharArray();
		goodslist temp = this;
		goods[] templist = new goods[GetNum()];
		int no = 0;
		char name[];
		if(temp.next == null){
			System.out.println("仓库为空");
			return null;
		}
		temp = temp.next;
		while(temp!=null){
			name = temp.data.name.toCharArray();
			boolean fuhe = false;
			for(int i = 0; i < name.length; i++){
				for(int j = 0; j < a.length; j++){
					if(name[i] == a[j]){
						fuhe = true;
						continue;
					}			
				}
			}
			if(fuhe){
				templist[no] = temp.data;
				no++;
			}
			temp = temp.next;
		}
		return templist;
	}

	public goods[] GetAll(){
		goodslist temp = this;
		goods[] templist = new goods[GetNum()];
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
		goodslist temp = this;
		int num = 0;
		if(temp.next == null)
			return 0;
		while(temp.next!=null){
			num++;
			temp = temp.next;
		}
		return num;
	}

	public void ShowList(){
		try{
			goodslist temp = this;
			if(temp.next == null)
				System.out.println("该表为空");
			while(temp.next!=null){
				System.out.print(temp.next.data);
				if(temp.next.data.getNum()<10){
					System.out.print("		*该商品数量已不足10件！");
				}
				System.out.println();
				temp = temp.next;
				if(temp.next == null){
					break;
				}
			}
		}catch(NullPointerException e){
		}
	}	public void ShowSoldList(){
		try{
			goodslist temp = this;
			if(temp.next == null)
				System.out.println("该表为空");
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
