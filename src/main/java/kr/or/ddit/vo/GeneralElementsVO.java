package kr.or.ddit.vo;

import lombok.Data;

//자바빈 클래스
@Data
public class GeneralElementsVO {
	private String text;
	private String textarea;
	private String textDisabled;
	private String textareaDisabled;
	private String inputWithSuccess;
	private String inputWithWarning;
	private String inputWithError;
	private String[] checkbox;
	private String radio;
	private String select;
	private String selectDisabled;
	private String[] selectMultiple;
	private String[] selectMultipleDisabled;
	
}
