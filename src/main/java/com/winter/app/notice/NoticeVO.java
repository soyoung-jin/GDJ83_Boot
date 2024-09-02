package com.winter.app.notice;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Setter,@Getter  getter/setter를 만들어주는 롬복의 라이브러리를 사용하겠다는 어노테이션
//밑에 추가되지 않고 outline에 보면 있음!
//밑에 추가되지 않기 때문에 내가 변수명을 변경하거나 타입을 변경할 때 getter setter를 알아서 바꿔주니까 편함
//@AllArgsConstructor 매개변수가 있는 생성자를 만들어주는 롬복의 라이브러리
//@NoArgsConstructor 매개변수가 없는 생성자를 만들어주는 롬복의 라이브러리
//@ToString: 멤버변수에 있는 값들을 한번에 출력해주기 때문에 일일히 하나하나 print문으로 찍을 필요가 없는 어노테이션
//@Data: 라이브러리 전부다 사용하겠다는 어노테이션! 근데 보통 필요한 어노테이션을 추가해서 사용함

/*@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString*/

@Data
public class NoticeVO {
	
	//VO는 불변이기 때문에 final 추가
//	private Long board_num;
//	private String board_writer;
//	private String board_title;
//	private String board_contents;
//	private Date create_date;
	
	//DB는 _를 사용하지만 자바는 카멜케이스를 사용하므로 바꿔봄
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private Date createDate;
	
	
	
//	//VO는 보통 생성자로 데이터를 집어 넣음
//	public NoticeVO(Long board_num, String board_writer) {
//		this.board_num = board_num;
//		this.board_writer = board_writer;
//	}

}
