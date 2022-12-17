package com.mongo.melon.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
//@Document(collection = "melon_221216")
public class MelonVO {
	
	@Id
	private String id;
	private int ranking;//노래 순위
	private String songTitle;//노래 제목
	private String singer;//가수
	private String ctime;//수집된 시간정보
	private String albumImage;//앨범 이미지 url
	
	//private int cntBySinger;//차트에 등록된 가수별 노래수
}
