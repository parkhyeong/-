package Fast;

public class Reservation_People_DTO {					
	private String name; //�̸�
	private String rrn; //�ֹε�Ϲ�ȣ
	private String phone; //����ó
	private String address; //�ּ�
	private String reservation_time;  //����ð�
	private String symptom;  //����
	
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
