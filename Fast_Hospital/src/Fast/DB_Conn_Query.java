package Fast;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DB_Conn_Query {
	Connection con = null;
	String url = "jdbc:oracle:thin:@hxlab.co.kr:51521:XE"; 
	String id = "RESERVATION"; String password = "1234";

	public DB_Conn_Query() {
	try { Class.forName("oracle.jdbc.driver.OracleDriver");
	System.out.println("드라이버 적재 성공");
	} catch (ClassNotFoundException e) { System.out.println("No Driver."); }
	}

	public void DB_Connect() {
		try { con = DriverManager.getConnection(url, id, password);
		System.out.println("DB 연결 성공");
		} catch (SQLException e) { System.out.println("Connection Fail"); }
		}
		
	
	public ArrayList<Reservation_People_DTO> hospital_reservation_people(String 병원ID) {	 //예약한 모든인원저장							
		DB_Connect();
        String sql = "select 환자이름,환자주민등록번호,환자연락처,환자주소,예약시간,증상" 
                + " from 예약 join 병원 on 예약.병원등록번호=병원.병원등록번호"
                + " join 환자 on 예약.예약번호=환자.예약번호 where 병원ID=?";

     	
     	ArrayList<Reservation_People_DTO> rpd=  new ArrayList<>();
     	
     	try {
     		PreparedStatement pstmt = con.prepareStatement(sql);
     		
     		pstmt.setString(1,병원ID);

     		ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Reservation_People_DTO RP=  new Reservation_People_DTO();
                RP.setname(rs.getString("환자이름"));
                RP.setrrn(rs.getString("환자주민등록번호"));
                RP.setphone(rs.getString("환자연락처"));
                RP.setaddress(rs.getString("환자주소"));
                RP.setreservation_time(rs.getString("예약시간"));
                RP.setsymptom(rs.getString("증상"));
                rpd.add(RP);
             }

     		
     		pstmt.close();
     	}catch(SQLException e) {System.out.println("실패");}
     	finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("데이터베이스에 연결이 되지 않았습니다.");}
     		}
     	return rpd;
     }
	
	public void hospital_storage(String rrn) {
		DB_Connect();
		String sql = "delete from 예약 where 예약번호 = (select 환자.예약번호 from 환자, 예약 WHERE 예약.예약번호=환자.예약번호 and 환자주민등록번호=?)"; //환자테이블 예약번호 삭제
		
		try {
  		PreparedStatement pstmt = con.prepareStatement(sql);
 		
 		pstmt.setString(1,rrn);
 		
 		int rs = pstmt.executeUpdate();
 		
 		pstmt.close();
		}catch(SQLException e) {System.out.println("실패");}
		finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("데이터베이스에 연결이 되지 않았습니다.");}
     			}
	}
	
	public ArrayList<Reservation_People_DTO> hospital_search(String comboselect, String textget, String 병원id) {		//예약검색저장						
		DB_Connect();
		String sql = null;
		
	      if (comboselect=="이름") {
	           sql = "select 환자이름,환자주민등록번호,환자연락처,환자주소,예약시간,증상 "
	           		+ "from 예약 join 병원 on 예약.병원등록번호=병원.병원등록번호 "
	           		+ "join 환자 on 예약.예약번호=환자.예약번호 where 병원ID=? and 환자이름 like ?";
	      }

		
		else if (comboselect=="주민번호")  {
	           sql = "select 환자이름,환자주민등록번호,환자연락처,환자주소,예약시간,증상 "
	           		+ "from 예약 join 병원 on 예약.병원등록번호=병원.병원등록번호 "
	           		+ "join 환자 on 예약.예약번호=환자.예약번호 where 병원ID =? and 환자주민등록번호 like ?";
	        }

     	ArrayList<Reservation_People_DTO> rpd=  new ArrayList<>();
     	
     	try {
     		PreparedStatement pstmt = con.prepareStatement(sql);
     		
     		textget="%"+textget+"%";
     		pstmt.setString(1,병원id);
     		pstmt.setString(2,textget);
     		
     		ResultSet rs = pstmt.executeQuery();
     		while (rs.next()) {
                Reservation_People_DTO RP=  new Reservation_People_DTO();
                RP.setname(rs.getString("환자이름"));
                RP.setrrn(rs.getString("환자주민등록번호"));
                RP.setphone(rs.getString("환자연락처"));
                RP.setaddress(rs.getString("환자주소"));
                RP.setreservation_time(rs.getString("예약시간"));
                RP.setsymptom(rs.getString("증상"));
                rpd.add(RP);
             }

     		
     		pstmt.close(); rs.close();
     	}catch(SQLException e) {System.out.println("실패");}
     	finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("데이터베이스에 연결이 되지 않았습니다.");}
     		}
     	return rpd;
     }
	
	public String[] my_page_people(String 환자id) { //마이페이지 환자개인정보 
		DB_Connect();
		String[] people_data= new String[3];
		
		
     	try {	
     		Statement stmt= con.createStatement();
     		ResultSet rs = stmt.executeQuery("select 환자이름, 환자연락처, 환자주소"
     				+ " from 환자 where 환자ID='" + 환자id+"'");
     		
     		while (rs.next()) {
     			people_data[0]=rs.getString("환자이름");
     			people_data[1]=rs.getString("환자연락처");
     			people_data[2]=rs.getString("환자주소");
     		}
     		
     		stmt.close(); rs.close();
     	}catch(SQLException e) {System.out.println("실패");}
     	finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("데이터베이스에 연결이 되지 않았습니다.");}
     		}
		
		
		return people_data;
	}
	
	public String[] my_page_reservation(String 환자id) {//마이페이지 환자 예약데이터
		DB_Connect();
		String[] people_data_reservation= new String[4];
		
		
     	try {	
     		Statement stmt= con.createStatement();
     		ResultSet rs = stmt.executeQuery("select 병원이름, 의사이름, 예약시간, 증상  "
     				+ "from 환자,의사,예약,병원 "
     				+ "where 환자.예약번호=예약.예약번호 and 의사.의사번호=예약.의사번호 "
     				+ "and 병원.병원등록번호=예약.병원등록번호 and 환자id='"+환자id+"'");
     		
     		while (rs.next()) {
     			people_data_reservation[0]=rs.getString("병원이름");
     			people_data_reservation[1]=rs.getString("의사이름");
     			people_data_reservation[2]=rs.getString("예약시간");
     			people_data_reservation[3]=rs.getString("증상");
     		}
     		
     		stmt.close(); rs.close();
     	}catch(SQLException e) {System.out.println("실패");}
     	finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("데이터베이스에 연결이 되지 않았습니다.");}
     		}
		
		
		return people_data_reservation;
	}
	
	
	public void personal_information_Store(String t1, String t2, String t3, String 환자id) {  //환자정보 저장
		DB_Connect();
		String sql ="update 환자 set 환자이름=?, 환자연락처=?, 환자주소=? where 환자ID=?"; //환자정보저장
		
		try {
  		PreparedStatement pstmt = con.prepareStatement(sql);
 		
 		pstmt.setString(1,t1);
 		pstmt.setString(2,t2);
 		pstmt.setString(3,t3);
 		pstmt.setString(4,환자id);
 		
 		int rs = pstmt.executeUpdate();
 		
 		 JOptionPane.showMessageDialog(null, "저장되었습니다.");
 		 
 		pstmt.close();
		}catch(SQLException e) { JOptionPane.showMessageDialog(null, "오류가발생하였습니다.");}
		finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("데이터베이스에 연결이 되지 않았습니다.");}
     			}
		
	}
	
	public void mypage_reservation_cancel(String 환자id) {
		DB_Connect();
		String query="delete from 예약 where 예약번호 = (select 환자.예약번호 from 환자, 예약 WHERE 예약.예약번호=환자.예약번호 and 환자ID='"+환자id+"')";
		try {
			Statement stmt= con.createStatement();
			int r=stmt.executeUpdate(query);
			
 			stmt.close();
		}catch(SQLException e) {JOptionPane.showInputDialog("다시시도해주세요.");}
		finally {if (con !=null)
			try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("데이터베이스에 연결이 되지 않았습니다.");}
     			}	
	}
	
	//예약의사,증상,예약시간,의사번호,병록등록번호 예약버튼 기능
	public int reservation_insert(String 환자id,String 증상, String 예약시간, int ddocnum, int hoscode){
		int c=0; //예약성공시 1 예약실패시 0
		DB_Connect();
		String sql ="insert into 예약 values (RESERVATION_NUMBER.NEXTVAL,?,?,?,?,?)";
		String sql2 ="select RESERVATION_NUMBER.CURRVAL from dual";
		String sql3="update 환자 set 예약번호=? where 환자ID = ?";
		String sql4="select 예약번호 from 환자 where 환자ID= ?"; //중복검사용
		try {
			PreparedStatement pstmt=con.prepareStatement(sql4);
	 		pstmt.setString(1,환자id);
	 		ResultSet rs = pstmt.executeQuery();
	 		String re = null;
	 		while(rs.next()) {
	 			re=rs.getString(1);
	 		}

	 	if(re==null) {
  		pstmt = con.prepareStatement(sql);
 		pstmt.setString(1,증상);
 		pstmt.setString(2,String.valueOf(LocalDate.now()));
 		pstmt.setString(3,예약시간);
 		pstmt.setInt(4,ddocnum);
 		pstmt.setInt(5,hoscode);
 		pstmt.executeUpdate();
 		
 		Statement stmt= con.createStatement();
 		rs = stmt.executeQuery(sql2);
 		
 		int reservation_number=0;
 		while(rs.next()) {
 			reservation_number=rs.getInt(1);
 		}

 		if (reservation_number != 0) {
 		pstmt = con.prepareStatement(sql3); //환자테이블에 예약번호 삽입
 		pstmt.setInt(1,reservation_number);
 		pstmt.setString(2,환자id);
 		pstmt.executeUpdate();
 		}
 		

 		JOptionPane.showMessageDialog(null, "예약이 성공하였습니다.");
 		c=1; //예약성공시 1 실패시 0그대로 반환
 		
 		pstmt.close(); rs.close(); //stmt.close();
 		
	 	}
 		else {
 			throw new Exception();
 		}
	 	}catch(SQLException e) { JOptionPane.showMessageDialog(null, "예약 실패\n다시 확인해주세요");} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "예약 실패\n 이미 예약된 상태이거나 오류가 발생했습니다.");
		}
		finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("데이터베이스에 연결이 되지 않았습니다.");}
		}
		return c;
		
	}
	
	public ArrayList<HospitalDTO> search(String comboselect, String textget) {		//예약검색저장
		DB_Connect();
		String sql = null;
		
		if (comboselect=="병원이름") {
			sql = "select 병원등록번호, 병원ID, 병원비밀번호,병원이름, 진료과목, 병원주소, 병원연락처, 병원운영시간, 예약인원수 from 병원 where 병원이름 like ?";
		}
		
		else if (comboselect=="진료과목") {
	     	sql = "select 병원등록번호, 병원ID, 병원비밀번호,병원이름, 진료과목, 병원주소, 병원연락처, 병원운영시간, 예약인원수 from 병원 where 진료과목 like ?";
		}
	
     	ArrayList<HospitalDTO> hospital=  new ArrayList<>();
     	
     	try {
     		PreparedStatement pstmt = con.prepareStatement(sql);
     		
     		textget="%"+textget+"%";
     		pstmt.setString(1,textget);
     		ResultSet rs = pstmt.executeQuery();
     		while (rs.next()) {
     			HospitalDTO AD=  new HospitalDTO();
     			AD.setA_num(rs.getInt("병원등록번호"));
    			AD.setId(rs.getString("병원ID"));
    			AD.setPw(rs.getString("병원비밀번호"));
     			AD.setName(rs.getString("병원이름"));
     			AD.setSub(rs.getString("진료과목"));
     			AD.setAdd(rs.getString("병원주소"));
     			AD.setNum(rs.getString("병원연락처"));
     			AD.setTime(rs.getString("병원운영시간"));
     			AD.setRes_num(rs.getInt("예약인원수"));


     			hospital.add(AD);
     		}
     		
     		pstmt.close(); rs.close();
     	} catch (Exception e) {
			e.printStackTrace();
		} 
     	return hospital;
     }

}
