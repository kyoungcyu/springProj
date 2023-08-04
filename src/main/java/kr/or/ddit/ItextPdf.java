package kr.or.ddit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import kr.or.ddit.vo.ItextVO;


public class ItextPdf {
	
	 public static void mkPdf (List<ItextVO> list) {
		 
	        // 1) com.lowagie.text.Document 클래스 인스턴스를 생성합니다.
	        Document document = new Document();  
	        //폴더 경로
	        String uploadPath ="C:\\eGovFrameDev-3.10.0-64bit\\workspace\\itextPdf\\src\\main\\webapp\\resources\\upload" ;
	        
	        File path = new File(uploadPath,getFolder());
	        
	        
	        // 폴더가 없으면 생성
	        if(path.exists()==false) {
				path.mkdirs();
			}
	        
	        File file = new File(path,"ItextExam.PDF");
	 
	        try {
	            // 2) Writer와 Document 사이의 연관을 맺어줍니다.
	            // Writer를 이용해 문서를 하드디스크에 생성할 수 있습니다. ItextExample1.PDF 파일을 작성해보겠습니다.
	            PdfWriter.getInstance(document, new FileOutputStream(file));    
	 
	            // 3)  문서를 오픈합니다.
	            document.open();
	            // 4) 문서에 내용을 첨부합니다.
	            document.add(new Paragraph("This is first Itext pdfexample ????"));    
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
	 
	    }

	 // 연,월,일 폴더 생성 걍해봄 
	private static String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		// 날짜 객체 생성
		Date date = new Date();
		// 2023-05-02
		String str = sdf.format(date);
		return str;
	}
	public static void main(String[] args) {
		ItextPdf pdf  = new ItextPdf();
		List<String> arrayList = new ArrayList<String>();
		String test= "sdsad test";
		arrayList.add(test);
	}
}
