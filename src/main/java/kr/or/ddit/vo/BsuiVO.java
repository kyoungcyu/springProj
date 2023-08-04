package kr.or.ddit.vo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class BsuiVO {
	private String busiId;
	
	@NotBlank(message="아이디를 입력해주세요")
	private String lginId;
	private String busiPw;
	
	private String busiNm;
	private String busiLoc;
	private String busiRep;
	private int busiScr;
	private String busiX;
	private String busiImg;
	private String busiDc;
	private String busiCode;
	private String busiNo;
	private int lati;
	private int longi;
	private String busiInfo;
	private String busiEmail;
	private String busiTel;
	private int wMoney;
	
	private List<BsuiAuthVO> BsuiAuthVOList;
}
