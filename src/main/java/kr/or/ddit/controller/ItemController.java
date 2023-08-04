package kr.or.ddit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.service.ItemService;
import kr.or.ddit.util.CommonFile;
import kr.or.ddit.vo.Item3VO;
import kr.or.ddit.vo.ItemVO;
import net.coobird.thumbnailator.Thumbnailator;
@PreAuthorize("hasRole('ROLE_MEMBER')")
@RequestMapping("/item")
@Controller
public class ItemController {
	private static final Logger log 
		= LoggerFactory.getLogger(ItemController.class);
	
	private String uploadFolder 
		= "C:\\eGovFrameDev-3.10.0-64bit\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";
	
	//DI, IoC
	@Autowired
	ItemService itemService;
	
	//요청URI : /item/register
	//요청방식 : get
	@GetMapping("/register")
	public String register(@ModelAttribute("itemVO") ItemVO itemVO) {
		//forwarding
		return "item/register";
	}
	
	//요청URI : /item/registerPost
	//요청방식 : post
	//요청파라미터 : {itemName=초코파이&price=2000
	//			&picture=파일객체&description=초코파이는맛있다}
	@PostMapping("/registerPost")
	public String registerPost(@Validated @ModelAttribute("itemVO") ItemVO itemVO, BindingResult bResult) {
		//itemVO : ItemVO [itemId=0, itemName=초코파이, price=2000
		//, description=<p>초코파이는 맛있어</p>
		//, pictureUrl=null, picture=org.sp...
		log.info("itemVO : " + itemVO);
		//pictureUrl는 picture.getOriginalFilename()
		
		//유효성 검사 결과 체킹//////////////////////////////
		if(bResult.hasErrors()) {//true : 오류 있음
			List<ObjectError> allErrors = bResult.getAllErrors();
			
			for(ObjectError objectError : allErrors) {
				log.error("allError : " + objectError);
			}
			
			//입력폼으로 다시 forwarding. 오류 정보도 함께 감.
			return "item/register";
		}
		
		//파일 업로드 시작 ////////////////////////////
		File uploadPath = new File(uploadFolder, getFolder());
		// upload/2023/05/08
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}
		
		MultipartFile picture = itemVO.getPicture();
		String uploadFileName = picture.getOriginalFilename();
		
		//UUID처리 시작
		UUID uuid = UUID.randomUUID();
		uploadFileName = uuid.toString() + "_" + uploadFileName;
		//UUID처리 끝
		
		//설계
		File saveFile = new File(uploadPath, uploadFileName);		
		try {
			picture.transferTo(saveFile);
			//파일 업로드 끝 ////////////////////////////
			// /2023/05/08/asadflkj_개똥이.jpg
			itemVO.setPictureUrl("/"+getFolder().replace("\\", "/")+"/"+uploadFileName);
			
//			ItemVO [itemId=0, itemName=초코파이, price=2000
			//, description=<p>초코파이는 맛있어</p>
			//, pictureUrl=/2023/05/08/asadflkj_개똥이.jpg, picture=org.sp...
			log.info("insert할 itemVO : " + itemVO);
			//insert 실행
			int result = this.itemService.create(itemVO);
			log.info("result : " + result);
			//SELECT NVL(MAX(ITEM_ID),0)+1 FROM ITEM
			//ItemVO [itemId=1...
			
			//redirect : 요청URI를 재요청
			return "redirect:/item/detail?itemId=" + itemVO.getItemId();
		} catch (IllegalStateException | IOException e) {
			log.error(e.getMessage());
			//오류 발생 시 입력 화면으로 재요청
			return "redirect:/item/register";
		}
	}
	
	//요청URI : /item/registerPost
	//요청방식 : post
	//요청파라미터 : {itemName=초코파이&price=2000
	//			&picture=파일객체&picture2=파일객체2&description=초코파이는맛있다}
	@PostMapping("/registerPost2")
	public String registerPost2(@ModelAttribute ItemVO itemVO) {
		//itemVO : ItemVO [itemId=0, itemName=초코파이, price=2000
		//, description=<p>초코파이는 맛있어</p>
		//, pictureUrl=null,pictureUrl2=null, picture=org.sp...
		log.info("itemVO2 : " + itemVO);
		//pictureUrl는 picture.getOriginalFilename()
		
		//파일 업로드 시작 ////////////////////////////
		File uploadPath = new File(uploadFolder, getFolder());
		// upload/2023/05/08
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}
		
		MultipartFile picture = itemVO.getPicture();
		MultipartFile picture2 = itemVO.getPicture2();
		String uploadFileName = picture.getOriginalFilename();
		String uploadFileName2 = picture2.getOriginalFilename();
		
		//UUID처리 시작
		UUID uuid = UUID.randomUUID();
		uploadFileName = uuid.toString() + "_" + uploadFileName;
		uuid = UUID.randomUUID();
		uploadFileName2 = uuid.toString() + "_" + uploadFileName2;
		//UUID처리 끝
		
		//설계
		File saveFile = new File(uploadPath, uploadFileName);		
		File saveFile2 = new File(uploadPath, uploadFileName2);		
		try {
			picture.transferTo(saveFile);
			picture2.transferTo(saveFile2);
			//파일 업로드 끝 ////////////////////////////
			// /2023/05/08/asadflkj_개똥이.jpg
			itemVO.setPictureUrl("/"+getFolder().replace("\\", "/")+"/"+uploadFileName);
			itemVO.setPictureUrl2("/"+getFolder().replace("\\", "/")+"/"+uploadFileName2);
			
//				ItemVO [itemId=0, itemName=초코파이, price=2000
			//, description=<p>초코파이는 맛있어</p>
			//, pictureUrl=/2023/05/08/asadflkj_개똥이.jpg, picture=org.sp...
			log.info("insert할 itemVO : " + itemVO);
			//insert 실행
			int result = this.itemService.create2(itemVO);
			log.info("result : " + result);
			//SELECT NVL(MAX(ITEM_ID),0)+1 FROM ITEM
			//ItemVO [itemId=1...
			
			//redirect : 요청URI를 재요청
			return "redirect:/item/detail?itemId=" + itemVO.getItemId();
		} catch (IllegalStateException | IOException e) {
			log.error(e.getMessage());
			//오류 발생 시 입력 화면으로 재요청
			return "redirect:/item/register";
		}
	}
	
	//요청URI : /item/detail?itemId=1
	//요청파라미터 : itemId=1
	//요청방식 : get
	//ItemVO [itemId=1, itemName=null, price=0...
	@GetMapping("/detail")
	public String detail(ItemVO itemVO, Model model) {
		log.info("itemVO : " + itemVO);
		
		itemVO = this.itemService.detail(itemVO);
		log.info("detail=>itemVO : " + itemVO);
		
		model.addAttribute("data", itemVO);
		
		//forwarding
		return "item/detail";
	}
	
	//요청URI : /item/list
	//요청방식 : get
	@GetMapping("/list")
	public String list(Model model) {
		
		List<ItemVO> data = this.itemService.list();
		log.info("list=>data : " + data);
		
		model.addAttribute("data", data);
		
		//forwarding
		return "item/list";
	}
	
	//요청URI : /item/updatePost
	//요청파라미터 : {itemId=3&itemName=초코파이&price=2000
	//			&picture=파일객체&description=초코파이는맛있다}
	//요청방식 : post
	@PostMapping("/updatePost")
	public String updatePost(ItemVO itemVO) {
		log.info("updatePost->itemVO : " + itemVO);
		
		int itemId = itemVO.getItemId();
		
		//수정모드에서 이미지를 업로드하면 다시 보정해주자
		itemVO.setPictureUrl("");
		
		//이미지도 함께 변경 시 처리 시작 /////////////////
		MultipartFile picture = itemVO.getPicture();
		
		//연월일 폴더
		File uploadPath = new File(uploadFolder, getFolder());
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}
		
		//원래 파일명
		String uploadFileName = "";
		uploadFileName = picture.getOriginalFilename();
		
		//이미지가 있을 때에만 처리
		if(uploadFileName.length()>0) {
			//UUID처리
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			//설계(연월일폴더 + uuid파일명
			File saveFile = new File(uploadPath, uploadFileName);
			try {
				//복사실행
				picture.transferTo(saveFile);
				
				//썸네일
				if(CommonFile.checkImageType(saveFile)) {
					//설계
					FileOutputStream thumbnail = new FileOutputStream(
							new File(uploadPath, "s_" + uploadFileName)
						);
					//실행
					Thumbnailator.createThumbnail(picture.getInputStream(),
							thumbnail,100,100);
					thumbnail.close();
				}
				
				itemVO.setPictureUrl("/" 
						+ CommonFile.getFolder().replace("\\", "/")
						+ "/" + uploadFileName);
			} catch (IllegalStateException | IOException e) {
				log.error(e.getMessage());
			}
		}
		
		//이미지도 함께 변경 시 처리 끝 /////////////////
		
		int result = this.itemService.create(itemVO);
		log.info("updatePost->result : " + result);
		
		//리다이렉트(URI 재요청)
		return "redirect:/item/detail?itemId="+(itemId+"");
	}
	
	//요청URI : /item/deletePost
	//요청데이터 : let data = {"itemId":3}
	//요청방식 : post
	//json데이터를 받을 땐 골뱅이RequestBody를 사용해야함
	@ResponseBody
	@PostMapping("/deletePost")
	public String deletePost(@RequestBody ItemVO itemVO) {
		log.info("itemVO : " + itemVO);
		//업데이트된 행의 수
		int result = this.itemService.deletePost(itemVO);
		
		if(result>0) {//성공
			return "success";
		}else {//실패
			return "fail";
		}
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
	
	//ITEM3 : ITEM_ATTACH = 1 : N
	//요청URI : /item/multiRegister
	//요청방식 : get
	@GetMapping("/multiRegister")
	public String multiRegister() {
		//forwarding
		return "item/multiRegister";
	}
	
	/*
	 요청URI : /item/multiRegisterPost
	요청파라미터 : {itemName=초코파이,price=10000,description=상세내용&pictures=파일객체}
	요청방식 : post
	
	트랜잭션 : 데이터베이스를 변경(I/U/D)하기위해 수행되어야 할 논리적 단위. 여러개의 SQL로 구성.
	
	트랜잭션 원칙(ACID(산성비) 원칙)
	Atomicity(원자성) : All or Nothing. 모 아니면 도. 되거나 안되거나.
	Consistency(일관성) : 성공 시 모든 데이터는 일관적이어야 함
	Isolation(격리성) : 화장실교양. 화장실에 누군가 있으면 건들지 마라
	Durability(영속성) : 성공 시 결과는 영속적으로 보관됨
	
	스프링에서는 트랜잭션을 스프링설정(root-context.xml)에서 처리해줌
	 */
	@PostMapping("/multiRegisterPost")
	public String multiRegisterPost(Item3VO item3VO) {
		//Item3VO [rnum=0, itemId=0, itemName=초코파이, price=12000, description=<p>상세 설명</p>
		//, pictures=[org.springframework.web
		log.info("item3VO : " + item3VO);
		
		int result = this.itemService.multiRegisterPost(item3VO);
		log.info("multiRegisterPost->result : " + result);
		
		//redirect(URI 재요청)
		return "redirect:/item/multiDetail?itemId="+item3VO.getItemId();
	}
	
	//상품상세
	//요청URI : /item/multiDetail?itemId=1
	//요청파라미터 : itemId=1
	//요청방식 : get
	@GetMapping("/multiDetail")
	public String multiDetail(@ModelAttribute Item3VO item3VO,
			Model model) {
		log.info("item3VO : " + item3VO);
		
		Item3VO data = this.itemService.detailMulti(item3VO);
		/*
		 Item3VO [rnum=0, itemId=1, itemName=초코파이, price=1000, description=맛있다, pictures=null, 
		 	itemAttachVOList=[
			 	ItemAttachVO [itemId=1, seq=1, fullname=/2023/05/12/asdfdas_개똥이.jpg, regdate=Fri May 12 15:30:06 KST 2023], 
			 	ItemAttachVO [itemId=1, seq=2, fullname=/2023/05/12/asdfdas_쉬는시간.jpg, regdate=Fri May 12 15:30:06 KST 2023]
		 	]
		 ]
		 */
		log.info("multiDetail->data : " + data);
		
		model.addAttribute("data", data);
		
		//forwarding
		return "item/multiDetail";
	}
	
	//멀티 이미지 목록
	//요청URI : /item/multiList
	//요청방식 : get
	@GetMapping("/multiList")
	public String multiList(Model model) {
		
		List<Item3VO> data = this.itemService.multiList();
		
		log.info("data : " + data);
		
		model.addAttribute("data", data);
		
		//forwarding
		return "item/multiList";
	}
}









