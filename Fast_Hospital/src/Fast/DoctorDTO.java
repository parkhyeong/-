package Fast;

public class DoctorDTO{
	private String 의사번호;
	private String 의사이름;
	private String 경력;
	private String 진료가능요일;
	private String 전문분야;
	private String 병원등록번호;
	
	public DoctorDTO() {
		
	}

	public String get의사번호() {
		return 의사번호;
	}

	public void set의사번호(String 의사번호) {
		this.의사번호 = 의사번호;
	}

	public String get의사이름() {
		return 의사이름;
	}

	public void set의사이름(String 의사이름) {
		this.의사이름 = 의사이름;
	}

	public String get경력() {
		return 경력;
	}

	public void set경력(String 경력) {
		this.경력 = 경력;
	}

	public String get진료가능요일() {
		return 진료가능요일;
	}

	public void set진료가능요일(String 진료가능요일) {
		this.진료가능요일 = 진료가능요일;
	}

	public String get전문분야() {
		return 전문분야;
	}

	public void set전문분야(String 전문분야) {
		this.전문분야 = 전문분야;
	}

	public String get병원등록번호() {
		return 병원등록번호;
	}

	public void set병원등록번호(String 병원등록번호) {
		this.병원등록번호 = 병원등록번호;
	}
	
}