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
	// ���⼭ ���� 
	public static int login(Connection con, String ȯ��ID, String ȯ�ں�й�ȣ) { // � ������ ���� ������ �α����� �õ��ϴ� �Լ�, ���ڰ����� ID�� Password�� �޾� login�� �Ǵ���.
		String SQL = "SELECT ȯ�ں�й�ȣ FROM ȯ�� WHERE ȯ��ID = ?"; // ������ DB�� �Էµ� ��ɾ SQL �������� ����.
		try {
			PreparedStatement pstmt;
			ResultSet rs;
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1,  ȯ��ID);
			rs = pstmt.executeQuery(); // ��� ����� �޾ƿ��� ResultSet Ÿ���� rs ������ �������� ������ ����� �־��� 
			if (rs.next()) {
				if (rs.getString(1).contentEquals(ȯ�ں�й�ȣ)) {
					return 1; // �α��� ����
				}
				else {
					return 0; // ��й�ȣ ����ġ
				}
			}
			return -1; // ���̵� ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // DB ���� 
	} //�α��� ��
	
	public static Boolean join(Connection con, UserDTO user) {
		String query = "INSERT INTO ȯ��(ȯ��ID, ȯ�ں�й�ȣ, ȯ���̸�, ȯ���ֹε�Ϲ�ȣ, ȯ�ڿ���ó, ȯ���ּ�)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		var result = false;
		
		try {
			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setString(1, user.getȯ��ID());
			pstm.setString(2, user.getȯ�ں�й�ȣ());
			pstm.setString(3, user.getȯ���̸�());
			pstm.setString(4, user.getȯ���ֹε�Ϲ�ȣ());
			pstm.setString(5, user.getȯ�ڿ���ó());
			pstm.setString(6, user.getȯ���ּ�());
			
			result = pstm.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
}