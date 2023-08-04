package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.FruitVO;

public interface FruitMapper {
	//과일/채소 목록
	//<select id="selectFruit" parameterType="String" resultType="fruitVO">
	public List<FruitVO> selectFruit(String fruitGubun);
}







