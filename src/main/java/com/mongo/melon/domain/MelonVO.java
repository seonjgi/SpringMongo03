package com.mongo.melon.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
//@Document(collection = "melon_221216")
public class MelonVO {
	
	@Id
	private String id;
	private int ranking;//�뷡 ����
	private String songTitle;//�뷡 ����
	private String singer;//����
	private String ctime;//������ �ð�����
	private String albumImage;//�ٹ� �̹��� url
	
	//private int cntBySinger;//��Ʈ�� ��ϵ� ������ �뷡��
}
