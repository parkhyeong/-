package Fast;

import Fast.menu_gui;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import Fast.DB_Conn_Query;

public class login extends JFrame{
	  ImageIcon icon;
	  private JTextField txtlogid;
	  private JPasswordField txtlogpw;
	  private JTextField textField;
	  private JTextField textField_1;
	  private JTextField textField_2;
	  private JTextField textField_3;
	  private JTextField textField_4;
	  private JTextField textField_5;
	  private JTextField textField_6;
	  private JTextField textField_7;
	  private JTextField textField_8;
	  private JTextField textField_9;
	  private JTextField textField_10;
	  private JTextField textField_11;
	  private JTextField textField_12;
	  private JTextField textField_13;
	UserDAO DAO = new UserDAO();
	AdminDAO DAO2 = new AdminDAO();
	public login(String str)
	{
		
	super(str);

	setAutoRequestFocus(false);
	this.setLocationRelativeTo(null);
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(850,650);
	
	getContentPane().setLayout(null);
	
	JPanel joinadminpanel = new JPanel();
	joinadminpanel.setBounds(334, 0, 500, 611);
	joinadminpanel.setVisible(false); 
	getContentPane().add(joinadminpanel);
	joinadminpanel.setLayout(null);
	
	JLabel lblNewLabel_1_1 = new JLabel("병원 회원가입");
	lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	lblNewLabel_1_1.setBounds(159, 55, 181, 96);
	joinadminpanel.add(lblNewLabel_1_1);
	
	JLabel lblNewLabel_2_6 = new JLabel("아이디");
	lblNewLabel_2_6.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2_6.setBounds(45, 188, 57, 15);
	joinadminpanel.add(lblNewLabel_2_6);
	
	JLabel lblNewLabel_2_1_1 = new JLabel("비밀번호");
	lblNewLabel_2_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2_1_1.setBounds(45, 238, 57, 15);
	joinadminpanel.add(lblNewLabel_2_1_1);
	
	JLabel lblNewLabel_2_2_1 = new JLabel("병원 이름");
	lblNewLabel_2_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2_2_1.setBounds(45, 288, 83, 15);
	joinadminpanel.add(lblNewLabel_2_2_1);
	
	JLabel lblNewLabel_2_3_1 = new JLabel("진료과목");
	lblNewLabel_2_3_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2_3_1.setBounds(45, 338, 102, 15);
	joinadminpanel.add(lblNewLabel_2_3_1);
	
	JLabel lblNewLabel_2_4_1 = new JLabel("병원 주소");
	lblNewLabel_2_4_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2_4_1.setBounds(45, 388, 102, 15);
	joinadminpanel.add(lblNewLabel_2_4_1);
	
	JLabel lblNewLabel_2_5_1 = new JLabel("병원 연락처");
	lblNewLabel_2_5_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2_5_1.setBounds(45, 438, 102, 15);
	joinadminpanel.add(lblNewLabel_2_5_1);
	
	JLabel lblNewLabel_2_6_1 = new JLabel("병원 운영시간");
	lblNewLabel_2_6_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2_6_1.setBounds(45, 488, 102, 15);
	joinadminpanel.add(lblNewLabel_2_6_1);
	
	JLabel lblNewLabel_2_7_1 = new JLabel("병원 등록번호");
	lblNewLabel_2_7_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2_7_1.setBounds(45, 538, 102, 15);
	joinadminpanel.add(lblNewLabel_2_7_1);
	

	JButton btnjoinadmin = new JButton("병원 회원가입");
	JButton btnjoinuser = new JButton("환자 회원가입");
	JButton btnloginadmin = new JButton("병원 로그인");
	JButton btnloginuser = new JButton("환자 로그인");
	
	textField_6 = new JTextField();
	textField_6.setColumns(10);
	textField_6.setBounds(159, 187, 181, 21);
	joinadminpanel.add(textField_6);
	
	textField_7 = new JTextField();
	textField_7.setColumns(10);
	textField_7.setBounds(159, 237, 181, 21);
	joinadminpanel.add(textField_7);
	
	textField_8 = new JTextField();
	textField_8.setColumns(10);
	textField_8.setBounds(159, 287, 181, 21);
	joinadminpanel.add(textField_8);
	
	textField_9 = new JTextField();
	textField_9.setColumns(10);
	textField_9.setBounds(159, 337, 181, 21);
	joinadminpanel.add(textField_9);
	
	textField_10 = new JTextField();
	textField_10.setColumns(10);
	textField_10.setBounds(159, 387, 181, 21);
	joinadminpanel.add(textField_10);
	
	textField_11 = new JTextField();
	textField_11.setColumns(10);
	textField_11.setBounds(159, 437, 181, 21);
	joinadminpanel.add(textField_11);
	
	textField_12 = new JTextField();
	textField_12.setColumns(10);
	textField_12.setBounds(159, 487, 181, 21);
	joinadminpanel.add(textField_12);
	
	textField_13 = new JTextField();
	textField_13.setColumns(10);
	textField_13.setBounds(159, 537, 181, 21);
	joinadminpanel.add(textField_13);
	
	JButton btnsingupuser_1 = new JButton("가입하기");
	btnsingupuser_1.setBounds(204, 577, 97, 23);
	joinadminpanel.add(btnsingupuser_1);
	btnsingupuser_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			var Connection = new DB_Conn_Query2();
			var con = Connection.DB_Connect();
			var admin = new AdminDTO();
			admin.set병원ID(textField_6.getText());
			admin.set병원비밀번호(textField_7.getText());
			admin.set병원이름(textField_8.getText());
			admin.set진료과목(textField_9.getText());
			admin.set병원주소(textField_10.getText());
			admin.set병원연락처(textField_11.getText());
			admin.set병원운영시간(textField_12.getText());
			admin.set병원등록번호(textField_13.getText());			
			if (AdminDAO.join(con, admin)) {
				JOptionPane.showMessageDialog(null, "회원가입이 완료 되었습니다!");				
				// 회원가입 끝났으면 정보 다 날림
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				textField_9.setText("");
				textField_10.setText("");
				textField_11.setText("");
				textField_12.setText("");
				textField_13.setText("");				
				// 패널을 닫고, 기존에 비활성화된 버튼을 활성화함
				joinadminpanel.setVisible(false);
				btnjoinuser.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "회원가입이 완료되지 않았습니다.");
			}
		}
	});
	
	
	
	
	JPanel joinuserpanel = new JPanel();
	joinuserpanel.setBounds(334, 0, 500, 611);
	joinuserpanel.setVisible(false);
	getContentPane().add(joinuserpanel);
	joinuserpanel.setLayout(null);
	
	
	
	JLabel lblNewLabel_1 = new JLabel("환자 회원가입");
	lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	lblNewLabel_1.setBounds(159, 55, 181, 96);
	joinuserpanel.add(lblNewLabel_1);
	
	JLabel lblNewLabel_2 = new JLabel("아이디");
	lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2.setBounds(45, 188, 57, 15);
	joinuserpanel.add(lblNewLabel_2);
	
	JLabel lblNewLabel_2_1 = new JLabel("비밀번호");
	lblNewLabel_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2_1.setBounds(45, 248, 57, 15);
	joinuserpanel.add(lblNewLabel_2_1);
	
	JLabel lblNewLabel_2_2 = new JLabel("이름");
	lblNewLabel_2_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2_2.setBounds(45, 308, 57, 15);
	joinuserpanel.add(lblNewLabel_2_2);
	
	JLabel lblNewLabel_2_3 = new JLabel("주민번호");
	lblNewLabel_2_3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2_3.setBounds(45, 368, 57, 15);
	joinuserpanel.add(lblNewLabel_2_3);
	
	JLabel lblNewLabel_2_4 = new JLabel("전화번호");
	lblNewLabel_2_4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2_4.setBounds(45, 428, 57, 15);
	joinuserpanel.add(lblNewLabel_2_4);
	
	JLabel lblNewLabel_2_5 = new JLabel("주소");
	lblNewLabel_2_5.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel_2_5.setBounds(45, 488, 57, 15);
	joinuserpanel.add(lblNewLabel_2_5);
	
	textField = new JTextField();	//id
	textField.setBounds(159, 187, 181, 21);
	joinuserpanel.add(textField);
	textField.setColumns(10);
	
	textField_1 = new JTextField();	//pw
	textField_1.setColumns(10);
	textField_1.setBounds(159, 247, 181, 21);
	joinuserpanel.add(textField_1);
	
	textField_2 = new JTextField();	//name
	textField_2.setColumns(10);
	textField_2.setBounds(159, 307, 181, 21);
	joinuserpanel.add(textField_2);
	
	textField_3 = new JTextField();	//human_id
	textField_3.setColumns(10);
	textField_3.setBounds(159, 367, 181, 21);
	joinuserpanel.add(textField_3);
	
	textField_4 = new JTextField();	//phone
	textField_4.setColumns(10);
	textField_4.setBounds(159, 427, 181, 21);
	joinuserpanel.add(textField_4);
	
	textField_5 = new JTextField(); //address
	textField_5.setColumns(10);
	textField_5.setBounds(159, 487, 181, 21);
	joinuserpanel.add(textField_5);
	
	JButton btnsingupuser = new JButton("가입하기");
	btnsingupuser.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//joinadminpanel.setVisible(true);
			//btnsingupuser_1.setEnabled(false);
			var Connection = new DB_Conn_Query2();
			var con = Connection.DB_Connect();
			var user = new UserDTO();
			user.set환자ID(textField.getText());
			user.set환자비밀번호(textField_1.getText());
			user.set환자이름(textField_2.getText());
			user.set환자주민등록번호(textField_3.getText());
			user.set환자연락처(textField_4.getText());
			user.set환자주소(textField_5.getText());
			
			if (UserDAO.join(con, user)) {
				JOptionPane.showMessageDialog(null, "회원가입이 완료 되었습니다!");
				
				// 회원가입 끝났으면 정보 다 날림
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				
				// 패널을 닫고, 기존에 비활성화된 버튼을 활성화함
				joinuserpanel.setVisible(false);
				btnjoinuser.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "회원가입이 완료되지 않았습니다.");
			}
			
		}
	});
	btnsingupuser.setBounds(204, 544, 97, 23);
	joinuserpanel.add(btnsingupuser);
	
	
	
	
	
	JPanel loginpanel = new JPanel();
	loginpanel.setBounds(0, 0, 500, 611);
	loginpanel.setLayout(null);
	getContentPane().add(loginpanel);
	
	JLabel lblNewLabel = new JLabel("아이디");
	lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	lblNewLabel.setBounds(23, 430, 57, 15);
	loginpanel.add(lblNewLabel);
	
	JLabel label = new JLabel("비밀번호");
	label.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	label.setBounds(23, 471, 57, 15);
	loginpanel.add(label);
	
	txtlogid = new JTextField();
	txtlogid.setBounds(106, 427, 175, 21);
	loginpanel.add(txtlogid);
	txtlogid.setColumns(10);
	
	txtlogpw = new JPasswordField();
	txtlogpw.setBounds(106, 468, 175, 21);
	loginpanel.add(txtlogpw);
	
	btnjoinadmin.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			joinadminpanel.setVisible(true);
			btnjoinuser.setEnabled(false);
		}
	});
	btnjoinadmin.setBounds(23, 566, 122, 23);
	loginpanel.add(btnjoinadmin);
	
	
	btnjoinuser.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			joinuserpanel.setVisible(true);
			btnjoinadmin.setEnabled(false);
			
		}
	});
	btnjoinuser.setBounds(165, 566, 116, 23);
	loginpanel.add(btnjoinuser);
	
	
	btnloginadmin.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	         var Connection = new DB_Conn_Query2();
	         var con = Connection.DB_Connect();
	         int result = AdminDAO.login2(con, txtlogid.getText(), String.valueOf(txtlogpw.getPassword()));
	         String admin_id = txtlogid.getText();
	         
	         // 로그인 성공?
	         if (result == 1) {
	            setVisible(false);
	            new admin_gui(admin_id);
	         }
	         if (result == -1) {
	            JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 틀렸습니다.");
	            }
	         if (result == 0) {
	            JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 틀렸습니다.");
	         }
	         }
	   });
	btnloginadmin.setBounds(23, 524, 122, 23);
	loginpanel.add(btnloginadmin);
	   
	   btnloginuser.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		         var Connection = new DB_Conn_Query2();
		         var con = Connection.DB_Connect();
		         int result = UserDAO.login(con, txtlogid.getText(), String.valueOf(txtlogpw.getPassword()));
		         String 환자id = txtlogid.getText();
		         
		         // 로그인 성공?
		         if (result == 1) {
		            setVisible(false);
		            new menu_gui(환자id);
		         }
		         if(result == -1) {
		            JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 틀렸습니다.");
		            }
		         if (result == 0)
		         {
		            JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 틀렸습니다.");
		         }
		         }
	   });
	   
	btnloginuser.setBounds(165, 524, 116, 23);
	loginpanel.add(btnloginuser);
	
	JLabel lblNewLabel_3 = new JLabel("");
	lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
	lblNewLabel_3.setIcon(new ImageIcon("D:\\자바학습\\Fast_Hospital\\src\\lmage\\병원 일러스트3.jpg"));
	lblNewLabel_3.setBounds(0, 0, 500, 611);
	loginpanel.add(lblNewLabel_3);
	
	
	
	JPanel panel_1  = new JPanel();
	panel_1.setBounds(334, 0, 500, 611);
	panel_1.setLayout(null);
	getContentPane().add(panel_1);
	
	JLabel lblNewLabel_4_1 = new JLabel("");
	lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNewLabel_4_1.setIcon(new ImageIcon("D:\\자바학습\\Fast_Hospital\\src\\lmage\\병원 일러스트3.jpg"));
	lblNewLabel_4_1.setBounds(0, 0, 500, 611);
	panel_1.add(lblNewLabel_4_1);
	
	JButton btngoback_1 = new JButton("");
	btngoback_1.setIcon(new ImageIcon("D:\\자바학습\\Fast_Hospital\\src\\lmage\\back22.jpg"));
	btngoback_1.setFont(new Font("맑은 고딕", Font.PLAIN, 5));
	btngoback_1.setBounds(475, 0, 25, 25);
	joinadminpanel.add(btngoback_1);
	btngoback_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			joinadminpanel.setVisible(false);
			joinuserpanel.setVisible(false);
	           loginpanel.setVisible(true);
	           btnjoinuser.setEnabled(true);
	 
	        
		}
	});
	
	JButton btngoback = new JButton("");
	btngoback.setIcon(new ImageIcon("C:\\Users\\soo\\eclipse-workspace\\Fast_Hospital2\\src\\Image\\back22.jpg"));
	btngoback.setFont(new Font("맑은 고딕", Font.PLAIN, 5));
	btngoback.setBounds(475, 0, 25, 25);
	btngoback.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           joinuserpanel.setVisible(false);
           joinadminpanel.setVisible(false);
           loginpanel.setVisible(true);
           btnjoinadmin.setEnabled(true);
        // 창 안보이게 하기 
        }
    });
	joinuserpanel.add(btngoback);
	
	
	
	this.setVisible(true); 
	this.setLocationRelativeTo(null);
	}
}
