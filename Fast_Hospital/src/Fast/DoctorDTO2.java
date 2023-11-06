package Fast;


public class DoctorDTO2 {
	private int docnum;
	private String docname;
	private String doccareer;
	private String docday;
	private String docsub;
	private int hos_num;
	
	public DoctorDTO2(int docnum, String docname, String doccareer, String docday, String docsub, int hos_num) {
		super();
		this.docnum = docnum;
		this.docname = docname;
		this.doccareer = doccareer;
		this.docday = docday;
		this.docsub = docsub;
		this.hos_num = hos_num;
	}
	
	public int getDocnum() {
		return docnum;
	}
	public void setDocnum(int docnum) {
		this.docnum = docnum;
	}
	public String getDocname() {
		return docname;
	}
	public void setDocname(String docname) {
		this.docname = docname;
	}
	public String getDoccareer() {
		return doccareer;
	}
	public void setDoccareer(String doccareer) {
		this.doccareer = doccareer;
	}
	public String getDocday() {
		return docday;
	}
	public void setDocday(String docday) {
		this.docday = docday;
	}
	public String getDocsub() {
		return docsub;
	}
	public void setDocsub(String docsub) {
		this.docsub = docsub;
	}
	public int getHos_num() {
		return hos_num;
	}
	public void setHos_num(int hos_num) {
		this.hos_num = hos_num;
	}
	
	
}
