package com.mongo.ex01;


import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
public class TestMongo {

	public static void main(String[] args) {
		String db="mydb";
		String table="posts";
		MongoClient mongo_client=null;
		MongoDatabase mongodb=null;
		MongoCollection<Document> collection =null;
		
		mongo_client=MongoClients.create("mongodb://localhost:27017");
		mongodb=mongo_client.getDatabase(db);
		
		collection=mongodb.getCollection(table);
		System.out.println(table+"�÷��� ���� ����!");
		
		Document doc=new Document();
		doc.append("author", "ȫ�浿");
		doc.append("title", "������ �׽�Ʈ �մϴ�");
		doc.append("wdate", "2022-12-12");
		
		collection.insertOne(doc);
		System.out.println("posts �÷��ǿ� ��ť��Ʈ ���� ����");
		
		mongo_client.close();
	}

}
