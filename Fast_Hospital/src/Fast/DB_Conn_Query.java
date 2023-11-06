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
	System.out.println("����̹� ���� ����");
	} catch (ClassNotFoundException e) { System.out.println("No Driver."); }
	}

	public void DB_Connect() {
		try { con = DriverManager.getConnection(url, id, password);
		System.out.println("DB ���� ����");
		} catch (SQLException e) { System.out.println("Connection Fail"); }
		}
		
	
	public ArrayList<Reservation_People_DTO> hospital_reservation_people(String ����ID) {	 //������ ����ο�����							
		DB_Connect();
        String sql = "select ȯ���̸�,ȯ���ֹε�Ϲ�ȣ,ȯ�ڿ���ó,ȯ���ּ�,����ð�,����" 
                + " from ���� join ���� on ����.������Ϲ�ȣ=����.������Ϲ�ȣ"
                + " join ȯ�� on ����.�����ȣ=ȯ��.�����ȣ where ����ID=?";

     	
     	ArrayList<Reservation_People_DTO> rpd=  new ArrayList<>();
     	
     	try {
     		PreparedStatement pstmt = con.prepareStatement(sql);
     		
     		pstmt.setString(1,����ID);

     		ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Reservation_People_DTO RP=  new Reservation_People_DTO();
                RP.setname(rs.getString("ȯ���̸�"));
                RP.setrrn(rs.getString("ȯ���ֹε�Ϲ�ȣ"));
                RP.setphone(rs.getString("ȯ�ڿ���ó"));
                RP.setaddress(rs.getString("ȯ���ּ�"));
                RP.setreservation_time(rs.getString("����ð�"));
                RP.setsymptom(rs.getString("����"));
                rpd.add(RP);
             }

     		
     		pstmt.close();
     	}catch(SQLException e) {System.out.println("����");}
     	finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("�����ͺ��̽��� ������ ���� �ʾҽ��ϴ�.");}
     		}
     	return rpd;
     }
	
	public void hospital_storage(String rrn) {
		DB_Connect();
		String sql = "delete from ���� where �����ȣ = (select ȯ��.�����ȣ from ȯ��, ���� WHERE ����.�����ȣ=ȯ��.�����ȣ and ȯ���ֹε�Ϲ�ȣ=?)"; //ȯ�����̺� �����ȣ ����
		
		try {
  		PreparedStatement pstmt = con.prepareStatement(sql);
 		
 		pstmt.setString(1,rrn);
 		
 		int rs = pstmt.executeUpdate();
 		
 		pstmt.close();
		}catch(SQLException e) {System.out.println("����");}
		finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("�����ͺ��̽��� ������ ���� �ʾҽ��ϴ�.");}
     			}
	}
	
	public ArrayList<Reservation_People_DTO> hospital_search(String comboselect, String textget, String ����id) {		//����˻�����						
		DB_Connect();
		String sql = null;
		
	      if (comboselect=="�̸�") {
	           sql = "select ȯ���̸�,ȯ���ֹε�Ϲ�ȣ,ȯ�ڿ���ó,ȯ���ּ�,����ð�,���� "
	           		+ "from ���� join ���� on ����.������Ϲ�ȣ=����.������Ϲ�ȣ "
	           		+ "join ȯ�� on ����.�����ȣ=ȯ��.�����ȣ where ����ID=? and ȯ���̸� like ?";
	      }

		
		else if (comboselect=="�ֹι�ȣ")  {
	           sql = "select ȯ���̸�,ȯ���ֹε�Ϲ�ȣ,ȯ�ڿ���ó,ȯ���ּ�,����ð�,���� "
	           		+ "from ���� join ���� on ����.������Ϲ�ȣ=����.������Ϲ�ȣ "
	           		+ "join ȯ�� on ����.�����ȣ=ȯ��.�����ȣ where ����ID =? and ȯ���ֹε�Ϲ�ȣ like ?";
	        }

     	ArrayList<Reservation_People_DTO> rpd=  new ArrayList<>();
     	
     	try {
     		PreparedStatement pstmt = con.prepareStatement(sql);
     		
     		textget="%"+textget+"%";
     		pstmt.setString(1,����id);
     		pstmt.setString(2,textget);
     		
     		ResultSet rs = pstmt.executeQuery();
     		while (rs.next()) {
                Reservation_People_DTO RP=  new Reservation_People_DTO();
                RP.setname(rs.getString("ȯ���̸�"));
                RP.setrrn(rs.getString("ȯ���ֹε�Ϲ�ȣ"));
                RP.setphone(rs.getString("ȯ�ڿ���ó"));
                RP.setaddress(rs.getString("ȯ���ּ�"));
                RP.setreservation_time(rs.getString("����ð�"));
                RP.setsymptom(rs.getString("����"));
                rpd.add(RP);
             }

     		
     		pstmt.close(); rs.close();
     	}catch(SQLException e) {System.out.println("����");}
     	finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("�����ͺ��̽��� ������ ���� �ʾҽ��ϴ�.");}
     		}
     	return rpd;
     }
	
	public String[] my_page_people(String ȯ��id) { //���������� ȯ�ڰ������� 
		DB_Connect();
		String[] people_data= new String[3];
		
		
     	try {	
     		Statement stmt= con.createStatement();
     		ResultSet rs = stmt.executeQuery("select ȯ���̸�, ȯ�ڿ���ó, ȯ���ּ�"
     				+ " from ȯ�� where ȯ��ID='" + ȯ��id+"'");
     		
     		while (rs.next()) {
     			people_data[0]=rs.getString("ȯ���̸�");
     			people_data[1]=rs.getString("ȯ�ڿ���ó");
     			people_data[2]=rs.getString("ȯ���ּ�");
     		}
     		
     		stmt.close(); rs.close();
     	}catch(SQLException e) {System.out.println("����");}
     	finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("�����ͺ��̽��� ������ ���� �ʾҽ��ϴ�.");}
     		}
		
		
		return people_data;
	}
	
	public String[] my_page_reservation(String ȯ��id) {//���������� ȯ�� ���൥����
		DB_Connect();
		String[] people_data_reservation= new String[4];
		
		
     	try {	
     		Statement stmt= con.createStatement();
     		ResultSet rs = stmt.executeQuery("select �����̸�, �ǻ��̸�, ����ð�, ����  "
     				+ "from ȯ��,�ǻ�,����,���� "
     				+ "where ȯ��.�����ȣ=����.�����ȣ and �ǻ�.�ǻ��ȣ=����.�ǻ��ȣ "
     				+ "and ����.������Ϲ�ȣ=����.������Ϲ�ȣ and ȯ��id='"+ȯ��id+"'");
     		
     		while (rs.next()) {
     			people_data_reservation[0]=rs.getString("�����̸�");
     			people_data_reservation[1]=rs.getString("�ǻ��̸�");
     			people_data_reservation[2]=rs.getString("����ð�");
     			people_data_reservation[3]=rs.getString("����");
     		}
     		
     		stmt.close(); rs.close();
     	}catch(SQLException e) {System.out.println("����");}
     	finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("�����ͺ��̽��� ������ ���� �ʾҽ��ϴ�.");}
     		}
		
		
		return people_data_reservation;
	}
	
	
	public void personal_information_Store(String t1, String t2, String t3, String ȯ��id) {  //ȯ������ ����
		DB_Connect();
		String sql ="update ȯ�� set ȯ���̸�=?, ȯ�ڿ���ó=?, ȯ���ּ�=? where ȯ��ID=?"; //ȯ����������
		
		try {
  		PreparedStatement pstmt = con.prepareStatement(sql);
 		
 		pstmt.setString(1,t1);
 		pstmt.setString(2,t2);
 		pstmt.setString(3,t3);
 		pstmt.setString(4,ȯ��id);
 		
 		int rs = pstmt.executeUpdate();
 		
 		 JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�.");
 		 
 		pstmt.close();
		}catch(SQLException e) { JOptionPane.showMessageDialog(null, "�������߻��Ͽ����ϴ�.");}
		finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("�����ͺ��̽��� ������ ���� �ʾҽ��ϴ�.");}
     			}
		
	}
	
	public void mypage_reservation_cancel(String ȯ��id) {
		DB_Connect();
		String query="delete from ���� where �����ȣ = (select ȯ��.�����ȣ from ȯ��, ���� WHERE ����.�����ȣ=ȯ��.�����ȣ and ȯ��ID='"+ȯ��id+"')";
		try {
			Statement stmt= con.createStatement();
			int r=stmt.executeUpdate(query);
			
 			stmt.close();
		}catch(SQLException e) {JOptionPane.showInputDialog("�ٽýõ����ּ���.");}
		finally {if (con !=null)
			try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("�����ͺ��̽��� ������ ���� �ʾҽ��ϴ�.");}
     			}	
	}
	
	//�����ǻ�,����,����ð�,�ǻ��ȣ,���ϵ�Ϲ�ȣ �����ư ���
	public int reservation_insert(String ȯ��id,String ����, String ����ð�, int ddocnum, int hoscode){
		int c=0; //���༺���� 1 ������н� 0
		DB_Connect();
		String sql ="insert into ���� values (RESERVATION_NUMBER.NEXTVAL,?,?,?,?,?)";
		String sql2 ="select RESERVATION_NUMBER.CURRVAL from dual";
		String sql3="update ȯ�� set �����ȣ=? where ȯ��ID = ?";
		String sql4="select �����ȣ from ȯ�� where ȯ��ID= ?"; //�ߺ��˻��
		try {
			PreparedStatement pstmt=con.prepareStatement(sql4);
	 		pstmt.setString(1,ȯ��id);
	 		ResultSet rs = pstmt.executeQuery();
	 		String re = null;
	 		while(rs.next()) {
	 			re=rs.getString(1);
	 		}

	 	if(re==null) {
  		pstmt = con.prepareStatement(sql);
 		pstmt.setString(1,����);
 		pstmt.setString(2,String.valueOf(LocalDate.now()));
 		pstmt.setString(3,����ð�);
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
 		pstmt = con.prepareStatement(sql3); //ȯ�����̺� �����ȣ ����
 		pstmt.setInt(1,reservation_number);
 		pstmt.setString(2,ȯ��id);
 		pstmt.executeUpdate();
 		}
 		

 		JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�.");
 		c=1; //���༺���� 1 ���н� 0�״�� ��ȯ
 		
 		pstmt.close(); rs.close(); //stmt.close();
 		
	 	}
 		else {
 			throw new Exception();
 		}
	 	}catch(SQLException e) { JOptionPane.showMessageDialog(null, "���� ����\n�ٽ� Ȯ�����ּ���");} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���� ����\n �̹� ����� �����̰ų� ������ �߻��߽��ϴ�.");
		}
		finally {if (con != null)
     		try{
     			con.close();
     			con = null;
     			}catch(SQLException e) {System.out.println("�����ͺ��̽��� ������ ���� �ʾҽ��ϴ�.");}
		}
		return c;
		
	}
	
	public ArrayList<HospitalDTO> search(String comboselect, String textget) {		//����˻�����
		DB_Connect();
		String sql = null;
		
		if (comboselect=="�����̸�") {
			sql = "select ������Ϲ�ȣ, ����ID, ������й�ȣ,�����̸�, �������, �����ּ�, ��������ó, ������ð�, �����ο��� from ���� where �����̸� like ?";
		}
		
		else if (comboselect=="�������") {
	     	sql = "select ������Ϲ�ȣ, ����ID, ������й�ȣ,�����̸�, �������, �����ּ�, ��������ó, ������ð�, �����ο��� from ���� where ������� like ?";
		}
	
     	ArrayList<HospitalDTO> hospital=  new ArrayList<>();
     	
     	try {
     		PreparedStatement pstmt = con.prepareStatement(sql);
     		
     		textget="%"+textget+"%";
     		pstmt.setString(1,textget);
     		ResultSet rs = pstmt.executeQuery();
     		while (rs.next()) {
     			HospitalDTO AD=  new HospitalDTO();
     			AD.setA_num(rs.getInt("������Ϲ�ȣ"));
    			AD.setId(rs.getString("����ID"));
    			AD.setPw(rs.getString("������й�ȣ"));
     			AD.setName(rs.getString("�����̸�"));
     			AD.setSub(rs.getString("�������"));
     			AD.setAdd(rs.getString("�����ּ�"));
     			AD.setNum(rs.getString("��������ó"));
     			AD.setTime(rs.getString("������ð�"));
     			AD.setRes_num(rs.getInt("�����ο���"));


     			hospital.add(AD);
     		}
     		
     		pstmt.close(); rs.close();
     	} catch (Exception e) {
			e.printStackTrace();
		} 
     	return hospital;
     }

}
