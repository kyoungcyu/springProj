package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.Item3VO;
import kr.or.ddit.vo.ItemAttachVO;
import kr.or.ddit.vo.ItemVO;

public interface ItemMapper {
	//Item 테이블에 insert
	//<insert id="create" parameterType="itemVO">
	// 매퍼 interface를 사용하려면 매퍼xml의 id값과 매퍼interface의 메소드명이 일치해야 함
	public int create(ItemVO itemVO);
	//이미지가 2개일 때
	public int create2(ItemVO itemVO);
	//상세보기
	//<select id="detail" parameterType="itemVO" resultType="itemVO">
	public ItemVO detail(ItemVO itemVO);
	//목록보기
	//<select id="list" resultType="itemVO">
	public List<ItemVO> list();
	//상품삭제
	//<update id="deletePost" parameterType="itemVO">
	public int deletePost(ItemVO itemVO);
	//1) 상품(ITEM3) 테이블에 insert
	//<insert id="insertItem3" parameterType="item3VO">
	public int insertItem3(Item3VO item3VO);
	
	//2) ITEM_ATTACH 테이블에 insert 
	//<update id="insertItemAttach" parameterType="java.util.List">
	public int insertItemAttach(List<ItemAttachVO> itemAttachVOList);
	
	//상품 상세
	//<select id="detailMulti" parameterType="item3VO" resultMap="item3Map">
	public Item3VO detailMulti(Item3VO item3VO);
	
	//멀티 이미지 목록
	//<select id="multiList" parameterType="item3VO" resultMap="item3Map">
	public List<Item3VO> multiList();
}







