package kr.or.ddit.vo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//자바빈 클래스
public class Item3VO {
	private int rnum;
	private int itemId;
	private String itemName;
	private int price;
	private String description;
	//<input type="file" class="custom-file-input" id="pictures" 
	//		name="pictures" multiple />
	private MultipartFile[] pictures;
	
	//중첩된(nested) 자바빈
	//ITEM3 : ITEM_ATTACH = 1 : N
	private List<ItemAttachVO> itemAttachVOList;
	
	public Item3VO() {}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile[] getPictures() {
		return pictures;
	}

	public void setPictures(MultipartFile[] pictures) {
		this.pictures = pictures;
	}

	public List<ItemAttachVO> getItemAttachVOList() {
		return itemAttachVOList;
	}

	public void setItemAttachVOList(List<ItemAttachVO> itemAttachVOList) {
		this.itemAttachVOList = itemAttachVOList;
	}

	@Override
	public String toString() {
		return "Item3VO [rnum=" + rnum + ", itemId=" + itemId + ", itemName=" + itemName + ", price=" + price
				+ ", description=" + description + ", pictures=" + Arrays.toString(pictures) + ", itemAttachVOList="
				+ itemAttachVOList + "]";
	}

}
