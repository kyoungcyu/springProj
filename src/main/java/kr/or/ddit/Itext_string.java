package kr.or.ddit;
import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Itext_string {
	public static void main(String[] args) {
		// Java iText API 활용.
		String[] title = new String[]{"제목", "저자", "출판사", "이미지URL"};
		String[][] rows = new String[][] {
			{"강아지", "저자1", "출판사1", "http://image.yes24.com/goods/110791050/XL"},
			{"고양이", "저자2", "출판사2", "http://image.yes24.com/goods/110791050/XL"},
			{"흑염소", "저자3", "출판사3", "http://image.yes24.com/goods/110791050/XL"}
		};
		
		// PDF 생성 절차.
		// 1. 메모리에 PDF 파일 임시로 생성. (Document)
		Document doc = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(new File("book.pdf")));
			doc.open();
			
			// 한글 폰트
			BaseFont bf = BaseFont.createFont("corbel.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font titleFont = new Font(bf, 12);
			Font rowsFont = new Font(bf, 10);
			
			// Table 생성
			PdfPTable table = new PdfPTable(title.length);	// 컬럼 갯수 지정.
			table.setWidthPercentage(100);	// Table 의 폭 %로 조절 가능.
			
			// 테이블 컬럼 폭 지정
			float[] colwidth = new float[]{20f, 15f, 15f, 30f};
			table.setWidths(colwidth);
			
			// 헤더 생성
			for (String header : title) {
				// Cell 생성
				PdfPCell cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(10);	// 셀 여백 지정
				cell.setGrayFill(0.9f);	// 셀 배경 지정.
				cell.setPhrase(new Phrase(header, titleFont));	// 셀에 글자 작성.
				table.addCell(cell);
			}
//			table.completeRow();
			
			for (String[] row : rows) {
				for (String data : row) {
					Phrase phrase = new Phrase(data, rowsFont);
					PdfPCell cell = new PdfPCell(phrase);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setPaddingTop(20);
					cell.setPaddingRight(30);
					cell.setPaddingLeft(30);
					cell.setPaddingBottom(20);
					table.addCell(cell);
				}
//				table.completeRow();
			}
			
			PdfPCell cell4 = new PdfPCell(new Phrase("Cell 5"));
			cell4.setColspan(2);
			
			PdfPCell cell5 = new PdfPCell(new Phrase("Cell 6"));
			cell5.setColspan(2);
			
			table.addCell(cell4);
			table.addCell(cell5);
			
			doc.addTitle("PDF Table Demo");
			doc.add(table);
			
			System.out.println("PDF 생성 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			doc.close();
		}
	}
}
