package Fast;

public class Reservation_People_DTO {					
	private String name; //이름
	private String rrn; //주민등록번호
	private String phone; //연락처
	private String address; //주소
	private String reservation_time;  //예약시간
	private String symptom;  //증상
	
	Reservation_People_DTO(){}
	
	public String getname() {
		return name;
	}	
	public void setname(String name) {
		this.name = name;
	}
		
	public String getrrn() {
		return rrn;
	}	
	public void setrrn(String rrn) {
		this.rrn = rrn;
	}
	
	public String getphone() {
		return phone;
	}	
	public void setphone(String phone) {
		this.phone = phone;
	}
	
	
	public String getaddress() {
		return address;
	}	
	public void setaddress(String address) {
		this.address = address;
	}
	
	public String getreservation_time() {
		return reservation_time;
	}	
	public void setreservation_time(String reservation_time) {
		this.reservation_time = reservation_time;
	}
	
	public String getsymptom() {
		return symptom;
	}	
	public void setsymptom(String symptom) {
		this.symptom = symptom;
	}

}
