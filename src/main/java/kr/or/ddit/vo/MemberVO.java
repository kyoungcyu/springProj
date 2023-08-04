package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//자바빈 클래스
@Data
public class MemberVO{
	/* 입력값 검증 규칙
	 - NotBlank : null 체크, trim 후 길이가 0인지 체크
	 - NotNull : 빈 값 체크
	 - Size : 글자 수 체크
	 - Email : 이메일 주소 형식 체크
	 - Past : 오늘보다 과거 날짜(ex. 생일)인지 체크
	 - Future : 미래 날짜인지 체크(ex. 예약일)
	 */
	//멤버변수
	private int rnum;	//행번호
	//입력값 검증 규칙을 지정
	@NotBlank(message="아이디를 입력해주세요")
	private String memId;
	private String memPass;
	//여러 개의 입력값 검증 규칙을 지정할 수 있음
	@NotBlank
	@Size(max=3)
	private String memName;
	private String memRegno1;
	private String memRegno2;
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	@NotBlank
	private String memHp;
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date memMemorialday;
	private int memMileage;
	private String memDelete;
	
	//<form:checkboxes items="달러{memLikeMap}" path="memLikeMap"/>
	private List<String> hobbyList;
	private String[] hobbyArray;
	private String hobby;
	
	//개발자여부(Y/N)
	private String developer;
	//외국인여부(true/false)
	private boolean foreigner;
	
	//성별
	private String gender;
	
	//국적
	private String nationality;

	//보유자동차
	private String[] carArray;
	private List<String> carList;
	
	//중첩된 자바빈즈
	//MemberVO(부모) : AddressVO(자식) = 1 : 1
	//입력값 검증을 적용(carList=[], addressVO=AddressVO(memId=null, postCode=, location=, detLocation=))
	@Valid
	private AddressVO addressVO;
	
	//MemberVO(부모) : CardVO(자식) = 1 : N
	// P.k : mem_Id     F.K : mem_Id
	@Valid
	private List<CardVO> cardVO;
	
	private String enabled;
	//회원의 증명사진
	private String memImg;
	
	//MemberVO : MemberAuth = 1 : N
	private List<MemberAuthVO> memberAuthVOList;
}




