package kr.or.ddit.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//자바빈 클래스
public class ItemVO {
	private int rnum;
	private int itemId;
	@NotBlank(message="상품명을 작성해주세요")
	private String itemName;	
	@Min(value=1000,message="1000원 이상으로 작성해주세요")
	private int price;
	private String description;
	private String pictureUrl;
	private String pictureUrl2;
	private MultipartFile picture;
	private MultipartFile picture2;
	//삭제여부
	private String delYn;
	
	public ItemVO() {}

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

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getPictureUrl2() {
		return pictureUrl2;
	}

	public void setPictureUrl2(String pictureUrl2) {
		this.pictureUrl2 = pictureUrl2;
	}

	public MultipartFile getPicture2() {
		return picture2;
	}

	public void setPicture2(MultipartFile picture2) {
		this.picture2 = picture2;
	}

	@Override
	public String toString() {
		return "ItemVO [rnum=" + rnum + ", itemId=" + itemId + ", itemName=" + itemName + ", price=" + price
				+ ", description=" + description + ", pictureUrl=" + pictureUrl + ", pictureUrl2=" + pictureUrl2
				+ ", picture=" + picture + ", picture2=" + picture2 + ", delYn=" + delYn + "]";
	}

	
	
}
