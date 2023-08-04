package kr.or.ddit.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.MemberVO2;

@RequestMapping("/file")
@Controller
public class FileController {
	private static final Logger log = 
			LoggerFactory.getLogger(FileController.class);
	
	//요청URI : /file/registerFile01
	@GetMapping("/registerFile01")
	public String registerFile01() {
		log.info("registerFile01에 왔다");
		
		//forwarding
		return "file/registerFile01";
	}
	
	/*
	 요청URI : /file/registerFile01 
	요청파라미터 : {picture=파일객체}
	
	파일업로드 폼 파일 요소값을 스프링 MVC가 지원하는 MultipartFile 매개변수로 처리함
	 */
	@ResponseBody
	@PostMapping("/registerFile01")
	public String registerFile01Post(MultipartFile picture) {
		log.info("registerFile01Post");
		
		log.info("원본파일명 : " + picture.getOriginalFilename());
		log.info("이미지크기 : " + picture.getSize());
		log.info("컨텐츠타입 : " + picture.getContentType());
		
		String uploadFolder 
			= "C:\\eGovFrameDev-3.10.0-64bit\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";
		String uploadFileName = picture.getOriginalFilename();
		
		//연/월/일 폴더 생성(2023-05-02) 시작-------------
		File path = new File(uploadFolder, getFolder());
		
		if(path.exists()==false) {
			path.mkdirs();
		}
		//연/월/일 폴더 생성(2023-05-02) 끝-------------
		
		//파일명 중복 방지 시작-------------
		//java.util.UUID => 랜덤값 생성
		UUID uuid = UUID.randomUUID();
		//원래의 파일 이름과 구분하기 위해 uuid값_원본파일명
		uploadFileName = uuid.toString() + "_" + uploadFileName;
		//파일명 중복 방지 끝-------------
		
		//File객체 설계(복사할 대상 경로, 파일명)
		File saveFile = new File(path, uploadFileName);
		//이미지원본파일객체.복사하겠다(설계대로)
		try {
			//파일 복사 실행
			picture.transferTo(saveFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return uploadFileName;
	}
	
	//요청URI : /file/registerFile01
	@GetMapping("/registerFile02")
	public String registerFile02() {
		log.info("registerFile02에 왔다");
		
		//forwarding
		return "file/registerFile02";
	}
	
	/*
	 요청URI : /file/registerFile02 
	요청파라미터 : {userId=gaeddongi&password=java&picture=파일객체}
	 */
	//파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와
	//문자열 매개변수로 처리함
	@ResponseBody
	@PostMapping("/registerFile02")
	public String registerFile02Post(String userId, String password,
				MultipartFile picture) {
		log.info("registerFile02Post()에 왔다");
		
		log.info("userId : " + userId);
		log.info("password : " + password);
		
		log.info("원본파일명 : " + picture.getOriginalFilename());
		log.info("이미지크기 : " + picture.getSize());
		log.info("컨텐츠타입 : " + picture.getContentType());
		
		String uploadPath 
			= "C:\\eGovFrameDev-3.10.0-64bit\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";
		
		//연/월/일 폴더 생성(2023-05-02) 시작-------------
		//파일객체를 통해서 연월일 폴더 생성. 
		//.../resources/upload/2023/05/02
		File path = new File(uploadPath,getFolder());
		
		//만약 연/월/일 해당 폴더가 없으면 생성
		if(path.exists()==false) {
			path.mkdirs();
		}
		//연/월/일 폴더 생성(2023-05-02) 끝-------------
		
		String uploadFileName = picture.getOriginalFilename();
		
		//파일명 중복 방지 시작-------------
		UUID uuid = UUID.randomUUID();
		
		uploadFileName = uuid.toString() + "_" + uploadFileName;
		//파일명 중복 방지 끝-------------
		
		//File객체 설계(복사할 대상 경로, 파일명)
		File saveFile = new File(path, uploadFileName);
		//원본.복사하겠다(설계대로)
		try {
			picture.transferTo(saveFile);
		} catch (IllegalStateException | IOException e) {
			log.error(e.getMessage());
			return null;
		}
		
		return uploadFileName;
	}
	
	//연/월/일 폴더 생성
	public String getFolder() {
		//간단한 날짜 형식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//날짜 객체 생성(java.util 패키지)
		Date date = new Date();
		//2023-05-02
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
	//요청URI : /file/registerFile03
	@GetMapping("/registerFile03")
	public String registerFile03() {
		log.info("registerFile03에 왔다");
		
		//forwarding
		return "file/registerFile03";
	}
	
	/*
	 요청URI : /file/registerFile03 
	요청파라미터 : {userId=gaeddongi&password=java&picture=파일객체}
	 */
	//파일업로드 폼 파일 요소값과 텍스트 필드 요소값을
	//MemberVO2 타입의 자바빈즈 매개변수로 처리함
	@ResponseBody
	@PostMapping("/registerFile03")
	public String registerFile03Post(@ModelAttribute MemberVO2 memberVO2, 
			MultipartFile picture2) {
		log.info("picture2 : " + picture2);
		log.info("registerFile03Post()에 왔다");
		
		log.info("userId : " + memberVO2.getUserId());
		log.info("password : " + memberVO2.getPassword());
		
		MultipartFile picture = memberVO2.getPicture();
		
		log.info("원본파일명 : " + picture.getOriginalFilename());
		log.info("이미지크기 : " + picture.getSize());
		log.info("컨텐츠타입 : " + picture.getContentType());
		
		return "success";
	}
	
	//요청URI : /file/registerFile04
	@GetMapping("/registerFile04")
	public String registerFile04() {
		log.info("registerFile04에 왔다");
		
		//forwarding
		return "file/registerFile04";
	}
	
	/*
	 요청URI : /file/registerFile03 
	요청파라미터 : {userId=gaeddongi&password=java&picture=파일객체&picture2=파일객체}
	 */
	//파일업로드 폼 파일 요소값과 텍스트 필드 요소값을
	//MemberVO2 타입의 자바빈즈 매개변수로 처리함
	@ResponseBody
	@PostMapping("/registerFile04")
	public String registerFile04Post(@ModelAttribute MemberVO2 memberVO2) {
		log.info("registerFile04Post()에 왔다");
		
		log.info("userId : " + memberVO2.getUserId());
		log.info("password : " + memberVO2.getPassword());
		
		MultipartFile picture = memberVO2.getPicture();		
		log.info("원본파일명 : " + picture.getOriginalFilename());
		log.info("이미지크기 : " + picture.getSize());
		log.info("컨텐츠타입 : " + picture.getContentType());
		
		MultipartFile picture2 = memberVO2.getPicture2();
		log.info("원본파일명2 : " + picture2.getOriginalFilename());
		log.info("이미지크기2 : " + picture2.getSize());
		log.info("컨텐츠타입2 : " + picture2.getContentType());
		
		return "success";
	}
	
	/*
	 요청URI : /file/registerFile05
	요청파라미터 : {userId=gaeddongi&password=java&pictureList[0]=파일객체&pictureList[1]=파일객체}
	안됨
	 */
	@ResponseBody
	@PostMapping("/registerFile05")
	public String registerFile05(String userId, String password,
			List<MultipartFile> pictureList) {
		log.info("registerFile05에 왔다");
		
		log.info("userId : " + userId);
		log.info("password : " + password);
		
		log.info("pictureList.size() : " + pictureList.size());
		
		for(MultipartFile picture : pictureList) {
			log.info("원본파일명 : " + picture.getOriginalFilename());
			log.info("이미지크기 : " + picture.getSize());
			log.info("컨텐츠타입 : " + picture.getContentType());
		}
		
		return "success";
	}
	
	/*
	 요청URI : /file/registerFile06
	요청파라미터 : {userId=gaeddongi&password=java&pictureArray[0]=파일객체&pictureArray[1]=파일객체}
	성공! */
	@ResponseBody
	@PostMapping("/registerFile06")
	public String registerFile06(String userId, String password,
			MultipartFile[] pictureArray) {
		log.info("registerFile06에 왔다");
		
		log.info("userId : " + userId);
		log.info("password : " + password);
		
		log.info("pictureArray.length : " + pictureArray.length);
		
		for(MultipartFile picture : pictureArray) {
			log.info("원본파일명 : " + picture.getOriginalFilename());
			log.info("이미지크기 : " + picture.getSize());
			log.info("컨텐츠타입 : " + picture.getContentType());
		}
		
		return "success";
	}
}










