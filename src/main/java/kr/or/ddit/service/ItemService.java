package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.Item3VO;
import kr.or.ddit.vo.ItemVO;

public interface ItemService {
	//메소드 시그니처
	
	//ITEM 테이블에 insert
	public int create(ItemVO itemVO);
	//이미지가 2개일 때
	public int create2(ItemVO itemVO);

	//상세보기
	public ItemVO detail(ItemVO itemVO);

	//목록보기
	public List<ItemVO> list();

	//상품 삭제
	public int deletePost(ItemVO itemVO);
	
	//상품 다중이미지 등록
	public int multiRegisterPost(Item3VO item3vo);
	
	//상품 상세
	public Item3VO detailMulti(Item3VO item3vo);
	
	//멀티 이미지 목록
	public List<Item3VO> multiList();
	
}
