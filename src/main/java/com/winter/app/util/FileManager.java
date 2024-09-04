package com.winter.app.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {

	
	public String fileSave(String path, MultipartFile multipartFile) throws Exception{
		//어디에 저장? 저장 위치를 String으로 받아와야 함
		File file = new File(path);
		
		//파일 경로가 존재하지 않으면 만들어라
		if(!(file.exists())) {
			file.mkdir();
		}
		
		//저장할 파일명 생성, 중복이 되면 안됨
		//1. 시간을 이용하는 방법
		//2. UUID라는 클래스를 사용하는 방법
		//뒤에 확장자 붙여주기 위해 multipartFile 매개변수로 추가, getOriginalFilename에 확장자까지 들어가있음.
		String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		
		//파일을 하드디스크에 저장
		//이 경로에(File(path)) 이 파일명의 정보를 담으려고 함
		file = new File(file, fileName);
		//요청에 의해 전송된 파일을 위에서 담았고, 그걸 이제 저장해줌
		multipartFile.transferTo(file);

		//저장된 파일명 리턴
		return fileName;
		
	}

}
