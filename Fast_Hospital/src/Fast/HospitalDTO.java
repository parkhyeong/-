package Fast;

public class HospitalDTO {
	private int a_num; //병원등록번호
	private String id;
	private String pw;
	private String name;
	private String sub; //진료과목
	private String add; //주소
	private String num; //전화번호
	private String time; //운영시간
	private int res_num; //예약인원수
	
	public HospitalDTO() {
		
	}
	
	public HospitalDTO(String id, String pw, String name, String sub, String add, String num, String time, int a_num,
			int res_num) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.sub = sub;
		this.add = add;
		this.num = num;
		this.time = time;
		this.a_num = a_num;
		this.res_num = res_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getA_num() {
		return a_num;
	}
	public void setA_num(int a_num) {
		this.a_num = a_num;
	}
	public int getRes_num() {
		return res_num;
	}
	public void setRes_num(int res_num) {
		this.res_num = res_num;
	}

	
}
