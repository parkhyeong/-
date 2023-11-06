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
	// ���⼭ ���� 
	public static int login2(Connection con, String ����ID, String ������й�ȣ) { // � ������ ���� ������ �α����� �õ��ϴ� �Լ�, ���ڰ����� ID�� Password�� �޾� login�� �Ǵ���.
		String SQL = "SELECT ������й�ȣ FROM ���� WHERE ����ID = ?"; // ������ DB�� �Էµ� ��ɾ SQL �������� ����.
		try {
			PreparedStatement pstmt;
			ResultSet rs;
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1,  ����ID);
			rs = pstmt.executeQuery(); // ��� ����� �޾ƿ��� ResultSet Ÿ���� rs ������ �������� ������ ����� �־��� 
			if (rs.next()) {
				if (rs.getString(1).contentEquals(������й�ȣ)) {
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
	}// �α��� ��
	
	public static Boolean join(Connection con, AdminDTO admin) {
		String query = "INSERT INTO ����(����ID, ������й�ȣ, �����̸�, �������, �����ּ�, ��������ó, ������ð�, ������Ϲ�ȣ)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		var result = false;
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, admin.get����ID());
			pstmt.setString(2, admin.get������й�ȣ());
			pstmt.setString(3, admin.get�����̸�());
			pstmt.setString(4, admin.get�������());
			pstmt.setString(5, admin.get�����ּ�());
			pstmt.setString(6, admin.get��������ó());
			pstmt.setString(7, admin.get������ð�());
			pstmt.setString(8, admin.get������Ϲ�ȣ());
			
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
	}//���� �ǻ� ��� ��
	
	public static void DELETE_DOCTOR(Connection con, String info)
	{
		try {
			PreparedStatement pstmt = con.prepareStatement("Delete �ǻ� Where �ǻ��ȣ = ?");
			pstmt.setInt(1, Integer.parseInt(info.toString()));
			pstmt.executeUpdate();
			
			pstmt.close();
			//con.close();
		} catch (SQLException e) {e.printStackTrace();}
	}//�ǻ� ����
	
	public static void INSERT_DOCTOR(Connection con, String[] info) {
		try {
			CallableStatement cstmt = con.prepareCall("{call INSERT_�ǻ�(?,?,?,?,?,?)}");
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
	}//�ǻ� �߰�
	

	public static String[] hospital(Connection con, String admin_id) { //���� ���� 
		String[] hospital = new String[3];
		
     	try {	
     		java.sql.Statement stmt= con.createStatement();
     		ResultSet rs = stmt.executeQuery("select �����̸�, �����ּ�, ��������ó"+ " from ���� where ����ID='" + admin_id + "'");
     		
     		while (rs.next()) {
     			hospital[0]=rs.getString("�����̸�");
     			hospital[1]=rs.getString("�����ּ�");
     			hospital[2]=rs.getString("��������ó");
     		}
     		
     		stmt.close(); rs.close();
     	}catch(SQLException e) {e.printStackTrace();}
		return hospital;
	}//���� ���� ��
	
	public static void rectify(Connection con, String t1, String t2, String t3, String admin_id) {//���� ���� update
		String sql ="update ���� set �����̸�=?, �����ּ�=?, ��������ó=? where ����ID=?";
		try {
	  		PreparedStatement pstmt = con.prepareStatement(sql);
	 		
	 		pstmt.setString(1, t1);
	 		pstmt.setString(2, t2);
	 		pstmt.setString(3, t3);
	 		pstmt.setString(4, admin_id);
	 		
	 		pstmt.executeUpdate();
	 		
	 		pstmt.close();
		} catch(SQLException e) {e.printStackTrace();}
	}//���� ���� update ��
	
	public static Boolean join2(Connection con, DoctorDTO Doc) {
		String query = "INSERT INTO �ǻ�(�ǻ��ȣ, �ǻ��̸�, ���, ���ᰡ�ɿ���, �����о�, ������Ϲ�ȣ)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		var result = false;
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, Doc.get�ǻ��ȣ());
			pstmt.setString(2, Doc.get�ǻ��̸�());
			pstmt.setString(3, Doc.get���());
			pstmt.setString(4, Doc.get���ᰡ�ɿ���());
			pstmt.setString(5, Doc.get�����о�());
			pstmt.setString(6, Doc.get������Ϲ�ȣ());
			
			result = pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
}

