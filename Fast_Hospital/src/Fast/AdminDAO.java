package Fast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import oracle.jdbc.OracleTypes;

public class AdminDAO {
	
	public AdminDAO() {
		
	}
	// 여기서 부터 
	public static int login2(Connection con, String 병원ID, String 병원비밀번호) { // 어떤 계정에 대한 실제로 로그인을 시도하는 함수, 인자값으로 ID와 Password를 받아 login을 판단함.
		String SQL = "SELECT 병원비밀번호 FROM 병원 WHERE 병원ID = ?"; // 실제로 DB에 입력될 명령어를 SQL 문장으로 만듬.
		try {
			PreparedStatement pstmt;
			ResultSet rs;
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1,  병원ID);
			rs = pstmt.executeQuery(); // 어떠한 결과를 받아오는 ResultSet 타입의 rs 변수에 쿼리문을 실행한 결과를 넣어줌 
			if (rs.next()) {
				if (rs.getString(1).contentEquals(병원비밀번호)) {
					return 1; // 로그인 성공
				}
				else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // DB 오류 
	}// 로그인 끝
	
	public static Boolean join(Connection con, AdminDTO admin) {
		String query = "INSERT INTO 병원(병원ID, 병원비밀번호, 병원이름, 진료과목, 병원주소, 병원연락처, 병원운영시간, 병원등록번호)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		var result = false;
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, admin.get병원ID());
			pstmt.setString(2, admin.get병원비밀번호());
			pstmt.setString(3, admin.get병원이름());
			pstmt.setString(4, admin.get진료과목());
			pstmt.setString(5, admin.get병원주소());
			pstmt.setString(6, admin.get병원연락처());
			pstmt.setString(7, admin.get병원운영시간());
			pstmt.setString(8, admin.get병원등록번호());
			
			result = pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public static String[][] SELECT_ALL_DOCTOR(Connection con, String admin_id)
	{
		String[][] ary = {};
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
			CallableStatement cstmt = con.prepareCall("{call SELECT_DOCTOR(?, ?) }");
			cstmt.setString(1,  admin_id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.executeQuery();
			ResultSet rs = (ResultSet)cstmt.getObject(2);
			while (rs.next()) {
				String [] doctor = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5) };
				list.add(doctor);
			}
			
			String[][] array = new String[list.size()][5];
			for(int i=0; i<list.size(); i++) {
				String[] temp  = { list.get(i)[0], list.get(i)[1], list.get(i)[2], list.get(i)[3], list.get(i)[4]  };
				array[i] = temp;
			}
			
			cstmt.close();
			rs.close();
			return array;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ary;
	}//병원 의사 목록 끝
	
	public static void DELETE_DOCTOR(Connection con, String info)
	{
		try {
			PreparedStatement pstmt = con.prepareStatement("Delete 의사 Where 의사번호 = ?");
			pstmt.setInt(1, Integer.parseInt(info.toString()));
			pstmt.executeUpdate();
			
			pstmt.close();
			//con.close();
		} catch (SQLException e) {e.printStackTrace();}
	}//의사 삭제
	
	public static void INSERT_DOCTOR(Connection con, String[] info) {
		try {
			CallableStatement cstmt = con.prepareCall("{call INSERT_의사(?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.parseInt(info[0].toString()));
			cstmt.setString(2, info[1].toString());
			cstmt.setString(3, info[2].toString());
			cstmt.setString(4, info[3].toString());
			cstmt.setString(5, info[4].toString());
			cstmt.setInt(6, Integer.parseInt(info[5].toString()));
			
			cstmt.executeUpdate();
			
			cstmt.close();
			con.close();
		}catch (SQLException e) {e.printStackTrace();}
	}//의사 추가
	

	public static String[] hospital(Connection con, String admin_id) { //병원 정보 
		String[] hospital = new String[3];
		
     	try {	
     		java.sql.Statement stmt= con.createStatement();
     		ResultSet rs = stmt.executeQuery("select 병원이름, 병원주소, 병원연락처"+ " from 병원 where 병원ID='" + admin_id + "'");
     		
     		while (rs.next()) {
     			hospital[0]=rs.getString("병원이름");
     			hospital[1]=rs.getString("병원주소");
     			hospital[2]=rs.getString("병원연락처");
     		}
     		
     		stmt.close(); rs.close();
     	}catch(SQLException e) {e.printStackTrace();}
		return hospital;
	}//병원 정보 끝
	
	public static void rectify(Connection con, String t1, String t2, String t3, String admin_id) {//병원 수정 update
		String sql ="update 병원 set 병원이름=?, 병원주소=?, 병원연락처=? where 병원ID=?";
		try {
	  		PreparedStatement pstmt = con.prepareStatement(sql);
	 		
	 		pstmt.setString(1, t1);
	 		pstmt.setString(2, t2);
	 		pstmt.setString(3, t3);
	 		pstmt.setString(4, admin_id);
	 		
	 		pstmt.executeUpdate();
	 		
	 		pstmt.close();
		} catch(SQLException e) {e.printStackTrace();}
	}//병원 수정 update 끝
	
	public static Boolean join2(Connection con, DoctorDTO Doc) {
		String query = "INSERT INTO 의사(의사번호, 의사이름, 경력, 진료가능요일, 전문분야, 병원등록번호)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		var result = false;
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, Doc.get의사번호());
			pstmt.setString(2, Doc.get의사이름());
			pstmt.setString(3, Doc.get경력());
			pstmt.setString(4, Doc.get진료가능요일());
			pstmt.setString(5, Doc.get전문분야());
			pstmt.setString(6, Doc.get병원등록번호());
			
			result = pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
}

