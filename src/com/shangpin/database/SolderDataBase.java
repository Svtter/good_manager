package com.shangpin.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.shangpin.database.GoodsDB.goodslist;

//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Arrays;

@SuppressWarnings("serial")
public class SolderDataBase implements Serializable{
	
	File file;
	public goodslist list;
	public SolderDataBase(){
		list = new goodslist();
		file = new File("Solderdb.txt");
		Open();
	}
	public boolean Save() throws IOException, ClassNotFoundException{//保存
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
			list = (goodslist)objectIn.readObject();
			objectIn.close();
			return true;
		}
		catch(ClassNotFoundException event){
			System.out.println("不能读出对象");
			return false;
		}
		catch(IOException event){
			System.out.println(event);
			return false;
		}
	}
	public goodslist getList() {
		return list;
	}
	public void setList(goodslist list) {
		this.list = list;
	}
}

//	public void Open(){
//		BufferedWriter bw = null;
//		try {
//			File file = new File("e:\\GoodsDQ.txt");
//			if(file.exists ()){
//				bw = new BufferedWriter(new FileWriter("e:\\GoodsDQ.txt"));
//				String content = intToString(GoodsDQ);
//				bw.write(content);
//				bw.close();	
//			}
//		} catch (IOException e){
//			e.printStackTrace();
//		}
//	}
//	static String intToString(int [] ary){
//		StringBuilder sb = new StringBuilder();
//		for(int i = 0; i < ary.length; ++i){
//			sb.append(ary[i]).append(",");
//		}
//		sb.deleteCharAt(sb.length() - 1);
//		return sb.toString();
//	}
//	
//	public void read(){
//		BufferedReader br = null;
//		try{
//			br = new BufferedReader(new FileReader("e:\\cname.txt"));
//			String content = br.readLine();
//			int [] wout = strToInt(content);
//			out = Arrays.copyOf(wout, wout.length);
//		} catch (IOException e){
//			e.printStackTrace();
//		}
//	}
//	static int[] strToInt(String str){
//		String [] strAry = str.split(",");
//		int [] ary = new int [strAry.length];
//		for(int i = 0; i < strAry.length; ++i){
//			ary[i] = Integer.parseInt(strAry[i]);
//		}
//		return ary;		
//	}
//}








