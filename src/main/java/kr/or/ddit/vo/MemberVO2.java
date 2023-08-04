package kr.or.ddit.vo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

//자바빈 클래스
public class MemberVO2 {
	//{userId=gaeddongi,password=java,coin=100}
	private String userId;
	private String password;
	private int coin;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateOfBirth;
	//<select name="nationality">
	private String nationality;
	//<select name="cars" multiple>
	private String cars;
	//<select name="foodArray" multiple>
	private String[] foodArray;
	private String foods;	//MEMBER2.FOODS 컬럼
	//<input type="checkbox" class="chkClass" id="hobby1" name="hobby"
	private String[] hobby;
	private String hobbys;	//MEMBER2.HOBBYS 컬럼
	//<input type="checkbox" class="chkClass" id="job1" name="job"
	private List<String> job;
	private String jobs;	//MEMBER2.JOBS 컬럼
	//중첩된 자바빈즈
	//MemberVO2 : Address = 1 : 1
	private Address address;
	//생체정보
	//MemberVO2 : Bital = 1 : 1
	private Bital bital;
	//카드 목록
	//MemberVO2 : CardVO = 1 : N
	private List<CardVO> cardList;
	//첨부파일(단일)
	private MultipartFile picture;
	//첨부파일(단일)
	private MultipartFile picture2;
	
	public MemberVO2() {}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCars() {
		return cars;
	}

	public void setCars(String cars) {
		this.cars = cars;
	}

	public String[] getFoodArray() {
		return foodArray;
	}

	public void setFoodArray(String[] foodArray) {
		this.foodArray = foodArray;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public List<String> getJob() {
		return job;
	}

	public void setJob(List<String> job) {
		this.job = job;
	}

	
	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Bital getBital() {
		return bital;
	}

	public void setBital(Bital bital) {
		this.bital = bital;
	}

	public String getFoods() {
		return foods;
	}

	public void setFoods(String foods) {
		this.foods = foods;
	}

	public String getHobbys() {
		return hobbys;
	}

	public void setHobbys(String hobbys) {
		this.hobbys = hobbys;
	}

	public String getJobs() {
		return jobs;
	}

	public void setJobs(String jobs) {
		this.jobs = jobs;
	}

	public List<CardVO> getCardList() {
		return cardList;
	}

	public void setCardList(List<CardVO> cardList) {
		this.cardList = cardList;
	}

	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

	public MultipartFile getPicture2() {
		return picture2;
	}

	public void setPicture2(MultipartFile picture2) {
		this.picture2 = picture2;
	}

	@Override
	public String toString() {
		return "MemberVO2 [userId=" + userId + ", password=" + password + ", coin=" + coin + ", dateOfBirth="
				+ dateOfBirth + ", nationality=" + nationality + ", cars=" + cars + ", foodArray="
				+ Arrays.toString(foodArray) + ", foods=" + foods + ", hobby=" + Arrays.toString(hobby) + ", hobbys="
				+ hobbys + ", job=" + job + ", jobs=" + jobs + ", address=" + address + ", bital=" + bital
				+ ", cardList=" + cardList + ", picture=" + picture + ", picture2=" + picture2 + "]";
	}

}







