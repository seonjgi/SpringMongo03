package com.mongo.melon.service;

import java.util.List;

import com.mongo.melon.domain.MelonVO;

public interface MelonService {
	
	//������ ��� �뷡 ����
	int crawlingMelon() throws Exception;
	
	//���Ե� ��� �뷡 ��� ��������
	List<MelonVO> getMelonList() throws Exception;
	
	//������ ��Ʈ�� �ö� �뷡�� ��������
	List<MelonVO> getCntBySinger() throws Exception;
	
	List<MelonVO> getMelonListBysinger(String colName, String singer) throws Exception;
	
}
