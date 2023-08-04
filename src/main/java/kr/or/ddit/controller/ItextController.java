package kr.or.ddit.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import kr.or.ddit.service.ItextService;
import kr.or.ddit.vo.ItextVO;

@RequestMapping("/itext")
@Controller
public class ItextController {

	@Autowired
	ItextService itextService;

	private static final Logger log = LoggerFactory.getLogger(ItextController.class);

	@RequestMapping("/pdf")
	public String register(Model model) {
		log.info("web단 list");

		List<ItextVO> list = this.itextService.list();

		model.addAttribute("data", list);
		return "ItextPdf";
	}

	@RequestMapping("/pdfReq")
	public String mkPDF() throws IOException {
		log.info("java 도착 ");

		List<ItextVO> list = this.itextService.list();

		Document document = new Document();
		// 폴더 경로
		String uploadPath = "C:\\eGovFrameDev-3.10.0-64bit\\workspace\\springProj\\src\\main\\webapp\\resources\\itextFolder";

		File path = new File(uploadPath,getFolder());
        
        // 폴더가 없으면 생성
        if(path.exists()==false) {
			path.mkdirs();
		}
        
        // 파일 명 정의시 + currentTime메소드를 이용해서 중복값 제거 
        String fileName = "myMember" + System.currentTimeMillis() + ".pdf";
        
		File file = new File(path,  fileName);
		
		try {
            // 2) Writer와 Document 사이의 연관을 맺어줍니다.
            // Writer를 이용해 문서를 하드디스크에 생성할 수 있습니다. ItextExample1.PDF 파일을 작성해보겠습니다.
            PdfWriter.getInstance(document, new FileOutputStream(file));    
 
            // 3)  문서를 오픈합니다.   document = 문서객체 ,a4용 지 개념
            document.open();
            // 4) 문서에 내용을 첨부합니다.
            BaseFont baseFont = BaseFont.createFont("C:\\eGovFrameDev-3.10.0-64bit\\workspace\\springProj\\src\\main\\webapp\\WEB-INF\\font\\malgunsl.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font objFont1 = new Font(baseFont, 20);
            Font objFont = new Font(baseFont, 12);

            PdfPTable table = new PdfPTable(5);//5개의 열을 가진 테이블생성
           
            Chunk chunk = new Chunk("ItextPdf", objFont1); //제목 
            
            Paragraph ph = new Paragraph(chunk);//문단 객체 만들어서
            ph.setAlignment(Element.ALIGN_CENTER); //가운대 정렬
            
            document.add(ph);// 문서에 문단 설정 추가

            document.add(Chunk.NEWLINE); //br 태그라는 개념
//            document.add(Chunk.NEWLINE);
            
            PdfPCell cell1 = new PdfPCell(new Phrase("회원 아이디",objFont));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell2 = new PdfPCell(new Phrase("회원 이름",objFont));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell3 = new PdfPCell(new Phrase("회원 번호",objFont));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell4 = new PdfPCell(new Phrase("회원 주소",objFont));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell5 = new PdfPCell(new Phrase("날짜",objFont));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            
            // 회원아이디 , 회원이름 , 회원 번호 , 회원주소, 날짜 
            // date 타입의 regdt 객체를 string 으로 파싱을하기위해
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            
            // 행을 만들어주는 셀
            for (int i = 0; i < list.size(); i++) {
            	
            	ItextVO vo= list.get(i);
            	
            	PdfPCell cellMemId = 
                        new PdfPCell (new Phrase(vo.getMemId(), objFont));
            	PdfPCell cellMemName = 
            			new PdfPCell (new Phrase(vo.getMemName(), objFont));
            	PdfPCell cellMemTel = 
            			new PdfPCell (new Phrase(vo.getMemTel(), objFont));
            	PdfPCell cellMemAddr = 
            			new PdfPCell (new Phrase(vo.getMemAddr(), objFont));
            	PdfPCell cellRedt = 
            			new PdfPCell (new Phrase(sdf.format(vo.getRegDt()), objFont));
            	
            	table.addCell(cellMemId);
            	table.addCell(cellMemName);
            	table.addCell(cellMemTel);
            	table.addCell(cellMemAddr);
            	table.addCell(cellRedt);
			}

            document.add(table);
            
            //document.add(new Phrase("--------------------영어english ,숫자123123 ,기호!!!!------------------"));    
            //document.add(new Phrase("문장추가를 원하시면 여기에", objFont));    
            //document.add(Chunk.NEWLINE);  // br 태그
            
            System.out.println(file+" 파일을 성공적으로 생성하였습니다.");
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            // 5) 문서를 닫습니다.
            document.close();    
        }
 
        // 6) Chrome 으로 방금 생성한 pdf 파일을 바로 실행해서 확인 합니다.
		
        String chrome = "C:/Program Files/Google/Chrome/Application/chrome.exe";
        try {
            new ProcessBuilder(chrome,file.getAbsolutePath()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		log.info("list : " + list);

		return "redirect:/itext/pdf";
	}
	
	private static String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		// 날짜 객체 생성
		Date date = new Date();
		// 2023-05-02
		String str = sdf.format(date);
		
		return str;
	}

}
