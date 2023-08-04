package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.ItextMapper;
import kr.or.ddit.service.ItextService;
import kr.or.ddit.vo.ItextVO;

@Service
public class ItextServiceImpl implements ItextService{
	@Autowired
	ItextMapper itextMapper;
	
	@Override
	public List<ItextVO> list(){
		return this.itextMapper.list();
	}
	
	
	
	

}
