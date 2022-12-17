package com.mongo.ex01;

import static com.mongodb.client.model.Filters.eq;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

public class PostsManager {
	MongoClient mclient;
	MongoDatabase mongodb;
	MongoCollection<Document> mcol;
	Scanner sc=new Scanner(System.in);
	public PostsManager() {
		mclient=MongoClients.create("mongodb://localhost:27017");
		mongodb=mclient.getDatabase("mydb");
		mcol=mongodb.getCollection("posts");
		System.out.println("����� mydb �����ͺ��̽� ����");
	}
	
	public void close() {
		if (mclient!=null) {
			mclient.close();
		}
	}//-------------------------------------------
	//������ �����ϱ�
	public void insertPosts() {
		System.out.print("�ۼ��� �Է�: ");
		String author=sc.nextLine();
		System.out.println("POST ���� �Է�: ");
		String title=sc.nextLine();
		Date today=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String wdate=sdf.format(today);
		
		Document doc=new Document();
		doc.append("author", author)
			.append("title", title)
			.append("wdate", wdate);
		InsertOneResult res=mcol.insertOne(doc);
		System.out.println(res.getInsertedId()+" ��ť��Ʈ�� �����Ǿ����");
	}//-------------------------------------------
	//��� ��� ��������
	public void selectPostsAll() {
		 FindIterable<Document> cursor=mcol.find();
		 for(Document doc : cursor) {
			 String title=doc.get("title").toString();
			 //System.out.println(title);
			 System.out.println(doc.toJson());//json���ڿ��� ��ȯ
		 }
		
	}//-------------------------------------------
	public void selectPostsAll2() {
		FindIterable<Document> cr=mcol.find();
		MongoCursor<Document> mcr=cr.iterator();
		while (mcr.hasNext()) {
			Document doc=mcr.next();
			String title=doc.getString("title");
			String author=doc.getString("author");
			String wdate=doc.getString("wdate");
			System.out.println(title+"\t"+author+"\t"+wdate);
		}
	}//-------------------------------------------
	
	public void deletePosts() {
		System.out.println("������ ���� �ۼ��ڸ��� �Է�: ");
		String author=sc.nextLine();
		
		//DeleteResult res=mcol.deleteOne(eq("author", author));// �Ѱ��� ����
		DeleteResult res=mcol.deleteMany(eq("author", author));// ������ ����
		long n =res.getDeletedCount();
		System.out.println(n+"���� ��ť��Ʈ ������");
	}//-------------------------------------------
	
	public void updatePosts() {
		System.out.println("�˻��� �ۼ��ڸ�: ");
		String author=sc.nextLine();
		System.out.println("������ ���� �Է�: ");
		String title=sc.nextLine();
		
	    Bson query=eq("author", author);
	    Bson edit1=Updates.combine(Updates.set("title", title));
		UpdateResult res=mcol.updateOne(query, edit1);
		long n=res.getModifiedCount();
		System.out.println(n+"���� ��ť��Ʈ�� �����Ǿ����");
		
	}//-------------------------------------------
	
	public static void main(String[] args) {
		PostsManager app=new PostsManager();
		//app.insertPosts();
		System.out.println("---POSTS��� ��������------");
		app.selectPostsAll();
		
		//app.deletePosts();
		app.updatePosts();
		System.out.println("---POSTS��� ��������2------");
		app.selectPostsAll2();
		
	}//-------------------------------------------

	

	
}
