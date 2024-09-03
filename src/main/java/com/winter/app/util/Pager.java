package com.winter.app.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Pager {
	
	//한 페이지에 몇개씩 볼 것이랴는 것, 1페이지에 10개의 글을 가져오겠다.
	private Long perPage=10L;
	
	//startRow는 계산 해야함, 몇번부터 시작해서 perPage(10개)를 보여줄건지.
	private Long startRow;
	//page가 1이면 startRow가 0이고, perPage가 10이니까, 0-9번(총 10개)가 나온다. 즉 DB에서 limit 0, 10이 되니까 1-10번으로 나옴
	//page가 2이면 startRow가 10이고 10-19번이 나온다. 마찬가지로 DB에선 limit 10 ,10 이므로 11-20번이 나온다.
	//pager가 3이면 startRow가 30이고.. 반복 --> 이걸 makeRow에서 계산해줌
	//pager 설정 안해줘도 되는데 일단 1L로 설정해둠
	private Long page=1L;
	
	private String kind;
	private String search;
	
	public void makeRow() {
		
		this.startRow = (page-1)*perPage;
	}
	
	//롬복에서 getter, setter 만들어주니까, getter 직접 선언
	//mapper에서 choose 썼으면 null일 경우가 없으므로 이거 안해줘도 됨
	public String getKind() {
		if(this.kind == null) {
			this.kind = "k1";
		}
		return this.kind;
	}
	
	public String getSearch() {
		if(this.search == null) {
			this.search = "";
		}
		return this.search;
	}
	
	
	

}
