package kr.or.ddit.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.mapper.ItemMapper;
import kr.or.ddit.service.ItemService;
import kr.or.ddit.util.CommonFile;
import kr.or.ddit.vo.Item3VO;
import kr.or.ddit.vo.ItemAttachVO;
import kr.or.ddit.vo.ItemVO;

@Service
public class ItemServiceImpl implements ItemService {
	//DI(의존성 주입), IoC(제어의 역전)
	//골뱅이Autowired
	@Inject
	ItemMapper itemMapper;
	
	private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	//Item 테이블에 inserterface의 메소드명이 일치해야 함
	@Override
	public int create(ItemVO itemVO) {
		//ItemController로 몇 행이 insert됐는지 그 행수를 리턴
		return this.itemMapper.create(itemVO);
	}
	//이미지가 2개일 때
	@Override
	public int create2(ItemVO itemVO) {
		//ItemController로 몇 행이 insert됐는지 그 행수를 리턴
		return this.itemMapper.create2(itemVO);
	}
	
	//상세보기
	@Override
	public ItemVO detail(ItemVO itemVO) {
		return this.itemMapper.detail(itemVO);
	}
	//목록보기
	@Override
	public List<ItemVO> list(){
		return this.itemMapper.list();
	}
	
	//상품삭제
	@Override
	public int deletePost(ItemVO itemVO) {
		return this.itemMapper.deletePost(itemVO);
	}
	//상품 다중이미지 등록
	@Transactional
	@Override
	public int multiRegisterPost(Item3VO item3VO) {
		//ITEM3테이블에 insert
		//before item3VO : Item3VO [rnum=0, itemId=0, itemName=초코파이, price=12000, ..
		int result = this.itemMapper.insertItem3(item3VO);
		//after item3VO : Item3VO [rnum=0, itemId=1, itemName=초코파이, price=12000, ..
		List<ItemAttachVO> itemAttachVOList = new ArrayList<ItemAttachVO>();//*******
		MultipartFile[] pictures =  item3VO.getPictures();
		
		//카운터 변수
		int seq = 1;
		
		//원래 파일명
		String uploadFileName = "";
		
		for(MultipartFile picture : pictures) {
			//연월일 폴더 생성
			File uploadPath = new File(CommonFile.uploadFolder, CommonFile.getFolder());
			if(uploadPath.exists()==false) {
				uploadPath.mkdirs();
			}
			//파일명 중복 방지
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + picture.getOriginalFilename();
			
			//설계. 복사될 폴더와 파일명
			File saveFile = new File(uploadPath, uploadFileName);
			//복사 실행
			try {
				picture.transferTo(saveFile);
				
				ItemAttachVO vo = new ItemAttachVO();
				vo.setItemId(item3VO.getItemId());//방금전 등록된 상품의 기본키 데이터
				vo.setSeq(seq++);
				// /연/월/일/uuid_파일명
				vo.setFullname("/" + CommonFile.getFolder().replace("\\", "/") + "/" + uploadFileName);
				
				itemAttachVOList.add(vo);
			} catch (IllegalStateException | IOException e) {
				log.error(e.getMessage());
				return 0;
			}
			
		}//end for
		log.info("itemAttachVOList : " + itemAttachVOList);
		result += this.itemMapper.insertItemAttach(itemAttachVOList);
		
		return result;
	}
	
	//상품 상세
	@Override
	public Item3VO detailMulti(Item3VO item3VO) {
		return this.itemMapper.detailMulti(item3VO);
	}
	
	//멀티 이미지 목록
	@Override
	public List<Item3VO> multiList(){
		return this.itemMapper.multiList();
	}
}








