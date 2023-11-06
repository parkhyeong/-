package Fast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class UserDAO {
	
	public UserDAO() {
	 	// ...
	}
	// 여기서 부터 
	public static int login(Connection con, String 환자ID, String 환자비밀번호) { // 어떤 계정에 대한 실제로 로그인을 시도하는 함수, 인자값으로 ID와 Password를 받아 login을 판단함.
		String SQL = "SELECT 환자비밀번호 FROM 환자 WHERE 환자ID = ?"; // 실제로 DB에 입력될 명령어를 SQL 문장으로 만듬.
		try {
			PreparedStatement pstmt;
			ResultSet rs;
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1,  환자ID);
			rs = pstmt.executeQuery(); // 어떠한 결과를 받아오는 ResultSet 타입의 rs 변수에 쿼리문을 실행한 결과를 넣어줌 
			if (rs.next()) {
				if (rs.getString(1).contentEquals(환자비밀번호)) {
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
	} //로그인 끝
	
	public static Boolean join(Connection con, UserDTO user) {
		String query = "INSERT INTO 환자(환자ID, 환자비밀번호, 환자이름, 환자주민등록번호, 환자연락처, 환자주소)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		var result = false;
		
		try {
			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setString(1, user.get환자ID());
			pstm.setString(2, user.get환자비밀번호());
			pstm.setString(3, user.get환자이름());
			pstm.setString(4, user.get환자주민등록번호());
			pstm.setString(5, user.get환자연락처());
			pstm.setString(6, user.get환자주소());
			
			result = pstm.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
}