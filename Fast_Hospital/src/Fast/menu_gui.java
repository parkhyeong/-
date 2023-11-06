package Fast;

import Fast.login;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class menu_gui extends JFrame implements MouseListener, KeyListener {
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	DefaultTableModel model2;
	String url = "jdbc:oracle:thin:@hxlab.co.kr:51521:XE";
	String id = "RESERVATION";
	String password = "1234";
	
	private String colNames[] = { "병원", "진료과목", "대기인원", "주소" };
	private String colNames2[] = { "의사이름", /* "대기인원" */"진료요일" };
	
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);
	private DefaultTableModel model3 = new DefaultTableModel(colNames2, 0);
	
	private JLabel lbname, lblsub, lbladd, lbltime, lbltel = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private JTextField textField_1;
	
	ArrayList<HospitalDTO> hos = new ArrayList<HospitalDTO>();
	ArrayList<DoctorDTO2> doc = new ArrayList<DoctorDTO2>();
	
	ArrayList<Integer> Appdocnum = new ArrayList<>();
	
	int hoscode=0;       //병원등록번호를 받아옴
	String comboBoxval=null; //시간
	int ddocnum=0;           //의사번호를 받아옴
	
	public menu_gui(String 환자id) { //환자id를 받아옴
		super();
		
		String[] SL = {"병원이름","진료과목"};
		String header[] = {"병원","대기인원"};
		String header2[] = { "의사이름", "대기인원" };

		String header3 [] = {"예약병원","예약의사","예약시간","증상"};
		
		
		getContentPane().setLayout(null);
		
		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(170, 0, 664, 611);
		mainpanel.setVisible(true);  
		getContentPane().add(mainpanel);
		mainpanel.setLayout(null);

		JPanel mypagepanel = new JPanel();
		mypagepanel.setBounds(170, 0, 664, 611);
		mypagepanel.setVisible(false);
		getContentPane().add(mypagepanel);
		mypagepanel.setLayout(null);
		
		JLabel lblNewLabel_14 = new JLabel("환자이름");
		lblNewLabel_14.setBounds(12, 27, 57, 15);
		mypagepanel.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("연락처");
		lblNewLabel_15.setBounds(12, 70, 57, 15);
		mypagepanel.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("주소");
		lblNewLabel_16.setBounds(12, 112, 57, 15);
		mypagepanel.add(lblNewLabel_16);
		
		JTextField textField_17 = new JTextField(); //환자이름 text필드
		textField_17.setBounds(90, 27, 200, 21);
		mypagepanel.add(textField_17);
		
		JTextField textField_18 = new JTextField(); //환자연락체 text필드
		textField_18.setBounds(90, 70, 200, 21);
		mypagepanel.add(textField_18);
		
		JTextField textField_19 = new JTextField(); //환자주소 text필드
		textField_19.setBounds(90, 112, 300, 21);
		mypagepanel.add(textField_19);
		
		JButton btnNewButton_5 = new JButton("개인정보 변경");
		btnNewButton_5.setBounds(415, 66, 156, 23);
		mypagepanel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("예약확인");
		btnNewButton_6.setBounds(80, 196, 97, 23);
		mypagepanel.add(btnNewButton_6);
		
		JButton btnNewButton_6_2 = new JButton("예약취소");
		btnNewButton_6_2.setBounds(415, 196, 97, 23);
		mypagepanel.add(btnNewButton_6_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(36, 251, 562, 45);
		mypagepanel.add(scrollPane_2);
		
		model2 = new DefaultTableModel(header3, 0){				//mypage 테이블 					
			public boolean isCellEditable(int row, int colunm) {				// 내용 수정 불가
				return false;
			}
		};
		
		table_2 = new JTable(model2);  //mypage 테이블 추가
		scrollPane_2.setViewportView(table_2);
		
		textField = new JTextField(); //검색창
		textField.setBounds(111, 11, 234, 21);
		mainpanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("검색");
		btnNewButton_1.setBounds(357, 10, 70, 23);
		mainpanel.add(btnNewButton_1);
		
		JComboBox comboBox = new JComboBox(SL);
		comboBox.setBounds(12, 10, 87, 23);
		mainpanel.add(comboBox);
		

		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 43, 415, 558);
		mainpanel.add(scrollPane);
		
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		table.setRowHeight(22);
		scrollPane.setViewportView(table);
		// 병원이름
		table.getColumnModel().getColumn(0).setMaxWidth(180);
		table.getColumnModel().getColumn(0).setMinWidth(180);
		table.getColumnModel().getColumn(0).setWidth(180);
		// 진료과목
		table.getColumnModel().getColumn(1).setMaxWidth(70);
		table.getColumnModel().getColumn(1).setMinWidth(70);
		table.getColumnModel().getColumn(1).setWidth(70);
		// 대기인원
		table.getColumnModel().getColumn(2).setMaxWidth(70);
		table.getColumnModel().getColumn(2).setMinWidth(70);
		table.getColumnModel().getColumn(2).setWidth(70);
		// 주소
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(3).setWidth(100);
		
		model.setNumRows(0);
		String query = "select *from 병원";
		String sql = null;
		try {
			con = DriverManager.getConnection(url, id, password);
			System.out.println("DB 연결 성공");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				hos.add(new HospitalDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9)));
				model.addRow(new Object[] { rs.getString("병원이름"), rs.getString("진료과목"), rs.getInt("예약인원수"),
						rs.getString("병원주소") });
			}
			
			
		} catch (SQLException e1) {
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
			}
		}
		

		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(439, 11, 213, 195);
		mainpanel.add(scrollPane_1);
		
		table_1 = new JTable(model3);
		table_1.setFillsViewportHeight(true);
		table_1.setVisible(false);
		table_1.setRowHeight(30);
		scrollPane_1.setViewportView(table_1);

		table_1.addMouseListener(new MouseAdapter() { //의사출력된 테이블 이벤트
			public void mouseClicked(MouseEvent e) {
				int row = table_1.getSelectedRow(); //선택된 행
				ddocnum=Appdocnum.get(row); //선택한 의사번호를 가져옴
			}
		});
		
		JPanel panel = new JPanel();            
		panel.setBounds(439, 216, 213, 249); 
		mainpanel.add(panel);         
		panel.setLayout(null); 
		
		JLabel lblNewLabel_4 = new JLabel("병원이름 : ");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_4.setBounds(12, 10, 80, 36);
		panel.add(lblNewLabel_4);
		
		lbname = new JLabel();
		lbname.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lbname.setBounds(90, 10 , 120, 36);
		panel.add(lbname);
		
		JLabel lblNewLabel_5 = new JLabel("진료과목 : ");
		lblNewLabel_5.setBounds(12, 56, 68, 15);
		panel.add(lblNewLabel_5);
		
		lblsub = new JLabel();
		lblsub.setBounds(96, 56, 84, 15);
		panel.add(lblsub);
		
		JLabel lblNewLabel_7 = new JLabel("병원 주소 :");
		lblNewLabel_7.setBounds(12, 81, 68, 15);
		panel.add(lblNewLabel_7);
		
		lbladd = new JLabel();
		lbladd.setHorizontalAlignment(SwingConstants.LEFT);
		lbladd.setVerticalAlignment(SwingConstants.TOP);
		lbladd.setBounds(12, 107, 201, 50);
		panel.add(lbladd);
		
		JLabel lblNewLabel_9 = new JLabel("병원운영시간");
		lblNewLabel_9.setBounds(12, 161, 78, 15);
		panel.add(lblNewLabel_9);

		lbltime = new JLabel();
		lbltime.setBounds(12, 175, 157, 36);
		panel.add(lbltime);

		JLabel lblNewLabel_11 = new JLabel("병원 연락처");
		lblNewLabel_11.setBounds(12, 227, 78, 15);
		panel.add(lblNewLabel_11);

		lbltel = new JLabel();
		lbltel.setBounds(91, 227, 122, 15);
		panel.add(lbltel);
		
		JLabel lblNewLabel_17 = new JLabel("증상");
		lblNewLabel_17.setBounds(447, 519, 30, 15);
		mainpanel.add(lblNewLabel_17);

		textField_1 = new JTextField(); //증상 텍스트
		textField_1.setBounds(489, 516, 163, 21);
		mainpanel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton_8 = new JButton("예약");
		btnNewButton_8.setBounds(592, 578, 60, 23);
		mainpanel.add(btnNewButton_8);

		JLabel lblNewLabel_18 = new JLabel("시간");
		lblNewLabel_18.setBounds(446, 548, 35, 15);
		mainpanel.add(lblNewLabel_18);
		
		JComboBox comboBox_1 = new JComboBox(); //시간 콤보박스
		comboBox_1.setBounds(489, 544, 70, 23);
		for (int i= 8; i<21; i++) {
			String s=String.valueOf(i);
			comboBox_1.addItem(s+":00");
		}
		
		mainpanel.add(comboBox_1);
		


		JButton btnNewButton = new JButton("mainmenu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mypagepanel.setVisible(false);
				mainpanel.setVisible(true);
			}
		});
		
		btnNewButton.setBounds(12, 10, 146, 76);
		getContentPane().add(btnNewButton);
		
		
		
		JButton btnMyPage = new JButton("my page");
		

		
		btnMyPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainpanel.setVisible(false);
				mypagepanel.setVisible(true);
			}
		});
		
		btnMyPage.setBounds(12, 96, 146, 76);
		getContentPane().add(btnMyPage);

		
		
		JButton btnNewButton_7 = new JButton("로그아웃");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new login(null);
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() { //검색이벤트
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(""); //증상텍스트 초기화
				comboBox_1.setSelectedIndex(0);
 				ddocnum=0; //의사예약번호 초기화
				hoscode=0; //병원예약번호 초기화
				
				String comboselect=comboBox.getSelectedItem().toString(); //콤보선택자
				String textget=textField.getText(); //병원이름 저장
				
				String row1[] = new String[4];
				DB_Conn_Query db =new DB_Conn_Query();
				hos = db.search(comboselect,textget);
				String query = "select *from 병원";
				
				if(textget.equals("")) { //새로고침
					try {
						textField_1.setText(""); //증상텍스트 초기화
		 				ddocnum=0; //의사예약번호 초기화
						hoscode=0; //병원예약번호 초기화
				        table_1.setVisible(false);
				        
						con = DriverManager.getConnection(url, id, password);
						pstmt = con.prepareStatement(query);
						rs = pstmt.executeQuery();
						hos=new ArrayList<>();
						model.setNumRows(0);
						while (rs.next()) {

							hos.add(new HospitalDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
									rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9)));

							model.addRow(new Object[] { rs.getString("병원이름"), rs.getString("진료과목"), rs.getInt("예약인원수"),
									rs.getString("병원주소") });
						}

					} catch (SQLException e1) {System.out.println("오류");
					} finally {
						try {
							rs.close();
							pstmt.close();
							con.close();
						} catch (Exception e2) {
							System.out.println("오류");
						}
					}
				}
				else {
				textField.setText("");
				
		        table_1.setVisible(false);
		        
			    model.setNumRows(0);
			    
				    for (int i=0; i<hos.size(); i++) {     //해당 정보불러오기
				    	row1[0] = String.valueOf(hos.get(i).getName());
						row1[1] = String.valueOf(hos.get(i).getSub());
						row1[2] = String.valueOf(hos.get(i).getRes_num());
						row1[3] = String.valueOf(hos.get(i).getAdd());
	
				        model.addRow(row1);
				    }
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter() { //병원목록 리스트를 누르면 의사 테이블에 뜸
			public void mouseClicked(MouseEvent e) {
				ddocnum=0; //의사등록번호를 잠시 초기화시켜줌
				textField_1.setText(""); //증상텍스트 초기화
				comboBox_1.setSelectedIndex(0); //시간콤보박스초기화
				
				int row = table.getSelectedRow(); //선택된 행
				TableModel data = table.getModel();
				hoscode = hos.get(row).getA_num(); //병원등록번호
				//String name = (String) data.getValueAt(row, 0); //선택된행의 0번째
				//int code = (int) data.getValueAt(row, 2); //선택된행을 두번째 대기인원수

				
				if (e.getClickCount() == 1) {
					model3.setNumRows(0);
					doc = new ArrayList<DoctorDTO2>();
					String query = "select *from 의사";
					String sql = null;
					try {

						con = DriverManager.getConnection(url, id, password);
						pstmt = con.prepareStatement(query);
						rs = pstmt.executeQuery();

						while (rs.next()) {
							doc.add(new DoctorDTO2(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
									rs.getString(5), rs.getInt(6))); //dto에 모든 의사정보저장

						}

						for(int i =0; i<hos.size(); i++) {
							if (hoscode == hos.get(i).getA_num()) { //병원등록번호와 병원테이블 병원번호와 매칭
								lbname.setText("<html>"+ hos.get(i).getName() +"</html>");
								lblsub.setText(hos.get(i).getSub());
								lbladd.setText("<html>"+hos.get(i).getAdd()+"</html>");
								lbltime.setText(hos.get(i).getTime());
								lbltel.setText(hos.get(i).getNum());
								
							}
						}
						Appdocnum= new ArrayList<>();
						
						for (int i = 0; i < doc.size(); i++) {
							if (hoscode == doc.get(i).getHos_num()) { //병원등록번호와 의사테이블 병원등록번호와 매칭
								
								Appdocnum.add(doc.get(i).getDocnum()); //해당되는 의사등록번호가 저장됨
								model3.addRow(new Object[] { doc.get(i).getDocname(), doc.get(i).getDocday() });
								
							}
						}
						
					} catch (SQLException e1) {
					} finally {
						try {
							rs.close();
							pstmt.close();
							con.close();
						} catch (Exception e2) {
						}
					}

					table_1.setVisible(true);
				}

			}
		});
		
		btnNewButton_8.addActionListener(new ActionListener() { //예약버튼이벤트
			public void actionPerformed(ActionEvent e) {
				DB_Conn_Query db = new DB_Conn_Query();
				String comboBoxval=comboBox_1.getSelectedItem().toString();
				if (textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "증상을 입력해주세요.");
				}
				
				else {
				int c=db.reservation_insert(환자id,textField_1.getText(), comboBoxval , ddocnum, hoscode);
				
				
				if (c==1) {   //예약성공시 전부 초기화
				textField_1.setText(""); //증상텍스트 초기화
				comboBox_1.setSelectedIndex(0);
 				ddocnum=0; //의사예약번호 초기화
				hoscode=0; //병원예약번호 초기화
		        table_1.setVisible(false);
		        
		        //초기화후 테이블 갱신
		        try {
					con = DriverManager.getConnection(url, id, password);
					pstmt = con.prepareStatement(query);
					rs = pstmt.executeQuery();
					hos=new ArrayList<>();
					model.setNumRows(0);
					while (rs.next()) {

						hos.add(new HospitalDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9)));

						model.addRow(new Object[] { rs.getString("병원이름"), rs.getString("진료과목"), rs.getInt("예약인원수"),
								rs.getString("병원주소") });
					}

				} catch (SQLException e1) {System.out.println("오류");
				} finally {
					try {
						rs.close();
						pstmt.close();
						con.close();
					} catch (Exception e2) {
						System.out.println("오류");
					}
				}
				}
				
				}
			}
		}
		);
		
		btnNewButton_7.setBounds(12, 578, 146, 23);
		getContentPane().add(btnNewButton_7);

		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 650);
		setVisible(true);
		setLocationRelativeTo(null);
		
	
		btnMyPage.addActionListener(new ActionListener() { //mypage 클릭이벤트

			public void actionPerformed(ActionEvent e) {
				DB_Conn_Query db = new DB_Conn_Query();
				mainpanel.setVisible(false);
				
				String[] people_data=db.my_page_people(환자id);
				
				//환자 개인정보 출력
				textField_17.setText(people_data[0]); //환자이름
				textField_18.setText(people_data[1]); //환자연락처
				textField_19.setText(people_data[2]); //환자주소
				
				String[] reservation_peopledata = db.my_page_reservation(환자id); //환자 예약정보출력
				model2.setNumRows(0);
				model2.addRow(reservation_peopledata);
				mypagepanel.setVisible(true);
			}
		});
		
		btnNewButton_5.addActionListener(new ActionListener() { //개인정보변경버튼

			public void actionPerformed(ActionEvent e) {
				DB_Conn_Query db = new DB_Conn_Query();
				
				String t1= textField_17.getText(); //환자이름 저장
				String t2= textField_18.getText(); //환자연락처 저장
				String t3= textField_19.getText(); //환자주소 저장
				try {
				db.personal_information_Store(t1,t2,t3,환자id); //환자정보 insert
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "잘못입력하였습니다.");
				}
			}
		});
		
		btnNewButton_6.addActionListener(new ActionListener() { //예약확인(새로고침)
			public void actionPerformed(ActionEvent e) {
				DB_Conn_Query db = new DB_Conn_Query();

				model2.setNumRows(0);     
				String [] row=db.my_page_reservation(환자id); //예약정보 새로고침
				model2.addRow(row);
			}
		});
		btnNewButton_6_2.addActionListener(new ActionListener() { //예약취소버튼
			public void actionPerformed(ActionEvent e) {
				DB_Conn_Query db = new DB_Conn_Query();
				db.mypage_reservation_cancel(환자id); //예약취소 (** id로 삭제)
				
				model2.setNumRows(0);     //model 테이블 전부 초기화
				String [] row=db.my_page_reservation(환자id); //예약정보 다시 조회(새로고침)
				model2.addRow(row);
			}
		});
		
		btnMyPage.setBounds(12, 96, 146, 76);
		getContentPane().add(btnMyPage);
		

		btnNewButton_7.setBounds(12, 578, 146, 23);
		getContentPane().add(btnNewButton_7);
        
		
        
		
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850,650);
		setVisible(true); 
		setLocationRelativeTo(null);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
