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
	
	private String colNames[] = { "����", "�������", "����ο�", "�ּ�" };
	private String colNames2[] = { "�ǻ��̸�", /* "����ο�" */"�������" };
	
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
	
	int hoscode=0;       //������Ϲ�ȣ�� �޾ƿ�
	String comboBoxval=null; //�ð�
	int ddocnum=0;           //�ǻ��ȣ�� �޾ƿ�
	
	public menu_gui(String ȯ��id) { //ȯ��id�� �޾ƿ�
		super();
		
		String[] SL = {"�����̸�","�������"};
		String header[] = {"����","����ο�"};
		String header2[] = { "�ǻ��̸�", "����ο�" };

		String header3 [] = {"���ິ��","�����ǻ�","����ð�","����"};
		
		
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
		
		JLabel lblNewLabel_14 = new JLabel("ȯ���̸�");
		lblNewLabel_14.setBounds(12, 27, 57, 15);
		mypagepanel.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("����ó");
		lblNewLabel_15.setBounds(12, 70, 57, 15);
		mypagepanel.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("�ּ�");
		lblNewLabel_16.setBounds(12, 112, 57, 15);
		mypagepanel.add(lblNewLabel_16);
		
		JTextField textField_17 = new JTextField(); //ȯ���̸� text�ʵ�
		textField_17.setBounds(90, 27, 200, 21);
		mypagepanel.add(textField_17);
		
		JTextField textField_18 = new JTextField(); //ȯ�ڿ���ü text�ʵ�
		textField_18.setBounds(90, 70, 200, 21);
		mypagepanel.add(textField_18);
		
		JTextField textField_19 = new JTextField(); //ȯ���ּ� text�ʵ�
		textField_19.setBounds(90, 112, 300, 21);
		mypagepanel.add(textField_19);
		
		JButton btnNewButton_5 = new JButton("�������� ����");
		btnNewButton_5.setBounds(415, 66, 156, 23);
		mypagepanel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("����Ȯ��");
		btnNewButton_6.setBounds(80, 196, 97, 23);
		mypagepanel.add(btnNewButton_6);
		
		JButton btnNewButton_6_2 = new JButton("�������");
		btnNewButton_6_2.setBounds(415, 196, 97, 23);
		mypagepanel.add(btnNewButton_6_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(36, 251, 562, 45);
		mypagepanel.add(scrollPane_2);
		
		model2 = new DefaultTableModel(header3, 0){				//mypage ���̺� 					
			public boolean isCellEditable(int row, int colunm) {				// ���� ���� �Ұ�
				return false;
			}
		};
		
		table_2 = new JTable(model2);  //mypage ���̺� �߰�
		scrollPane_2.setViewportView(table_2);
		
		textField = new JTextField(); //�˻�â
		textField.setBounds(111, 11, 234, 21);
		mainpanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("�˻�");
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
		table.setFont(new Font("���� ���", Font.BOLD, 14));
		table.setRowHeight(22);
		scrollPane.setViewportView(table);
		// �����̸�
		table.getColumnModel().getColumn(0).setMaxWidth(180);
		table.getColumnModel().getColumn(0).setMinWidth(180);
		table.getColumnModel().getColumn(0).setWidth(180);
		// �������
		table.getColumnModel().getColumn(1).setMaxWidth(70);
		table.getColumnModel().getColumn(1).setMinWidth(70);
		table.getColumnModel().getColumn(1).setWidth(70);
		// ����ο�
		table.getColumnModel().getColumn(2).setMaxWidth(70);
		table.getColumnModel().getColumn(2).setMinWidth(70);
		table.getColumnModel().getColumn(2).setWidth(70);
		// �ּ�
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(3).setWidth(100);
		
		model.setNumRows(0);
		String query = "select *from ����";
		String sql = null;
		try {
			con = DriverManager.getConnection(url, id, password);
			System.out.println("DB ���� ����");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				hos.add(new HospitalDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9)));
				model.addRow(new Object[] { rs.getString("�����̸�"), rs.getString("�������"), rs.getInt("�����ο���"),
						rs.getString("�����ּ�") });
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

		table_1.addMouseListener(new MouseAdapter() { //�ǻ���µ� ���̺� �̺�Ʈ
			public void mouseClicked(MouseEvent e) {
				int row = table_1.getSelectedRow(); //���õ� ��
				ddocnum=Appdocnum.get(row); //������ �ǻ��ȣ�� ������
			}
		});
		
		JPanel panel = new JPanel();            
		panel.setBounds(439, 216, 213, 249); 
		mainpanel.add(panel);         
		panel.setLayout(null); 
		
		JLabel lblNewLabel_4 = new JLabel("�����̸� : ");
		lblNewLabel_4.setFont(new Font("���� ���", Font.BOLD, 14));
		lblNewLabel_4.setBounds(12, 10, 80, 36);
		panel.add(lblNewLabel_4);
		
		lbname = new JLabel();
		lbname.setFont(new Font("���� ���", Font.BOLD, 14));
		lbname.setBounds(90, 10 , 120, 36);
		panel.add(lbname);
		
		JLabel lblNewLabel_5 = new JLabel("������� : ");
		lblNewLabel_5.setBounds(12, 56, 68, 15);
		panel.add(lblNewLabel_5);
		
		lblsub = new JLabel();
		lblsub.setBounds(96, 56, 84, 15);
		panel.add(lblsub);
		
		JLabel lblNewLabel_7 = new JLabel("���� �ּ� :");
		lblNewLabel_7.setBounds(12, 81, 68, 15);
		panel.add(lblNewLabel_7);
		
		lbladd = new JLabel();
		lbladd.setHorizontalAlignment(SwingConstants.LEFT);
		lbladd.setVerticalAlignment(SwingConstants.TOP);
		lbladd.setBounds(12, 107, 201, 50);
		panel.add(lbladd);
		
		JLabel lblNewLabel_9 = new JLabel("������ð�");
		lblNewLabel_9.setBounds(12, 161, 78, 15);
		panel.add(lblNewLabel_9);

		lbltime = new JLabel();
		lbltime.setBounds(12, 175, 157, 36);
		panel.add(lbltime);

		JLabel lblNewLabel_11 = new JLabel("���� ����ó");
		lblNewLabel_11.setBounds(12, 227, 78, 15);
		panel.add(lblNewLabel_11);

		lbltel = new JLabel();
		lbltel.setBounds(91, 227, 122, 15);
		panel.add(lbltel);
		
		JLabel lblNewLabel_17 = new JLabel("����");
		lblNewLabel_17.setBounds(447, 519, 30, 15);
		mainpanel.add(lblNewLabel_17);

		textField_1 = new JTextField(); //���� �ؽ�Ʈ
		textField_1.setBounds(489, 516, 163, 21);
		mainpanel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton_8 = new JButton("����");
		btnNewButton_8.setBounds(592, 578, 60, 23);
		mainpanel.add(btnNewButton_8);

		JLabel lblNewLabel_18 = new JLabel("�ð�");
		lblNewLabel_18.setBounds(446, 548, 35, 15);
		mainpanel.add(lblNewLabel_18);
		
		JComboBox comboBox_1 = new JComboBox(); //�ð� �޺��ڽ�
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

		
		
		JButton btnNewButton_7 = new JButton("�α׾ƿ�");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new login(null);
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() { //�˻��̺�Ʈ
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(""); //�����ؽ�Ʈ �ʱ�ȭ
				comboBox_1.setSelectedIndex(0);
 				ddocnum=0; //�ǻ翹���ȣ �ʱ�ȭ
				hoscode=0; //���������ȣ �ʱ�ȭ
				
				String comboselect=comboBox.getSelectedItem().toString(); //�޺�������
				String textget=textField.getText(); //�����̸� ����
				
				String row1[] = new String[4];
				DB_Conn_Query db =new DB_Conn_Query();
				hos = db.search(comboselect,textget);
				String query = "select *from ����";
				
				if(textget.equals("")) { //���ΰ�ħ
					try {
						textField_1.setText(""); //�����ؽ�Ʈ �ʱ�ȭ
		 				ddocnum=0; //�ǻ翹���ȣ �ʱ�ȭ
						hoscode=0; //���������ȣ �ʱ�ȭ
				        table_1.setVisible(false);
				        
						con = DriverManager.getConnection(url, id, password);
						pstmt = con.prepareStatement(query);
						rs = pstmt.executeQuery();
						hos=new ArrayList<>();
						model.setNumRows(0);
						while (rs.next()) {

							hos.add(new HospitalDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
									rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9)));

							model.addRow(new Object[] { rs.getString("�����̸�"), rs.getString("�������"), rs.getInt("�����ο���"),
									rs.getString("�����ּ�") });
						}

					} catch (SQLException e1) {System.out.println("����");
					} finally {
						try {
							rs.close();
							pstmt.close();
							con.close();
						} catch (Exception e2) {
							System.out.println("����");
						}
					}
				}
				else {
				textField.setText("");
				
		        table_1.setVisible(false);
		        
			    model.setNumRows(0);
			    
				    for (int i=0; i<hos.size(); i++) {     //�ش� �����ҷ�����
				    	row1[0] = String.valueOf(hos.get(i).getName());
						row1[1] = String.valueOf(hos.get(i).getSub());
						row1[2] = String.valueOf(hos.get(i).getRes_num());
						row1[3] = String.valueOf(hos.get(i).getAdd());
	
				        model.addRow(row1);
				    }
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter() { //������� ����Ʈ�� ������ �ǻ� ���̺� ��
			public void mouseClicked(MouseEvent e) {
				ddocnum=0; //�ǻ��Ϲ�ȣ�� ��� �ʱ�ȭ������
				textField_1.setText(""); //�����ؽ�Ʈ �ʱ�ȭ
				comboBox_1.setSelectedIndex(0); //�ð��޺��ڽ��ʱ�ȭ
				
				int row = table.getSelectedRow(); //���õ� ��
				TableModel data = table.getModel();
				hoscode = hos.get(row).getA_num(); //������Ϲ�ȣ
				//String name = (String) data.getValueAt(row, 0); //���õ����� 0��°
				//int code = (int) data.getValueAt(row, 2); //���õ����� �ι�° ����ο���

				
				if (e.getClickCount() == 1) {
					model3.setNumRows(0);
					doc = new ArrayList<DoctorDTO2>();
					String query = "select *from �ǻ�";
					String sql = null;
					try {

						con = DriverManager.getConnection(url, id, password);
						pstmt = con.prepareStatement(query);
						rs = pstmt.executeQuery();

						while (rs.next()) {
							doc.add(new DoctorDTO2(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
									rs.getString(5), rs.getInt(6))); //dto�� ��� �ǻ���������

						}

						for(int i =0; i<hos.size(); i++) {
							if (hoscode == hos.get(i).getA_num()) { //������Ϲ�ȣ�� �������̺� ������ȣ�� ��Ī
								lbname.setText("<html>"+ hos.get(i).getName() +"</html>");
								lblsub.setText(hos.get(i).getSub());
								lbladd.setText("<html>"+hos.get(i).getAdd()+"</html>");
								lbltime.setText(hos.get(i).getTime());
								lbltel.setText(hos.get(i).getNum());
								
							}
						}
						Appdocnum= new ArrayList<>();
						
						for (int i = 0; i < doc.size(); i++) {
							if (hoscode == doc.get(i).getHos_num()) { //������Ϲ�ȣ�� �ǻ����̺� ������Ϲ�ȣ�� ��Ī
								
								Appdocnum.add(doc.get(i).getDocnum()); //�ش�Ǵ� �ǻ��Ϲ�ȣ�� �����
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
		
		btnNewButton_8.addActionListener(new ActionListener() { //�����ư�̺�Ʈ
			public void actionPerformed(ActionEvent e) {
				DB_Conn_Query db = new DB_Conn_Query();
				String comboBoxval=comboBox_1.getSelectedItem().toString();
				if (textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���.");
				}
				
				else {
				int c=db.reservation_insert(ȯ��id,textField_1.getText(), comboBoxval , ddocnum, hoscode);
				
				
				if (c==1) {   //���༺���� ���� �ʱ�ȭ
				textField_1.setText(""); //�����ؽ�Ʈ �ʱ�ȭ
				comboBox_1.setSelectedIndex(0);
 				ddocnum=0; //�ǻ翹���ȣ �ʱ�ȭ
				hoscode=0; //���������ȣ �ʱ�ȭ
		        table_1.setVisible(false);
		        
		        //�ʱ�ȭ�� ���̺� ����
		        try {
					con = DriverManager.getConnection(url, id, password);
					pstmt = con.prepareStatement(query);
					rs = pstmt.executeQuery();
					hos=new ArrayList<>();
					model.setNumRows(0);
					while (rs.next()) {

						hos.add(new HospitalDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9)));

						model.addRow(new Object[] { rs.getString("�����̸�"), rs.getString("�������"), rs.getInt("�����ο���"),
								rs.getString("�����ּ�") });
					}

				} catch (SQLException e1) {System.out.println("����");
				} finally {
					try {
						rs.close();
						pstmt.close();
						con.close();
					} catch (Exception e2) {
						System.out.println("����");
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
		
	
		btnMyPage.addActionListener(new ActionListener() { //mypage Ŭ���̺�Ʈ

			public void actionPerformed(ActionEvent e) {
				DB_Conn_Query db = new DB_Conn_Query();
				mainpanel.setVisible(false);
				
				String[] people_data=db.my_page_people(ȯ��id);
				
				//ȯ�� �������� ���
				textField_17.setText(people_data[0]); //ȯ���̸�
				textField_18.setText(people_data[1]); //ȯ�ڿ���ó
				textField_19.setText(people_data[2]); //ȯ���ּ�
				
				String[] reservation_peopledata = db.my_page_reservation(ȯ��id); //ȯ�� �����������
				model2.setNumRows(0);
				model2.addRow(reservation_peopledata);
				mypagepanel.setVisible(true);
			}
		});
		
		btnNewButton_5.addActionListener(new ActionListener() { //�������������ư

			public void actionPerformed(ActionEvent e) {
				DB_Conn_Query db = new DB_Conn_Query();
				
				String t1= textField_17.getText(); //ȯ���̸� ����
				String t2= textField_18.getText(); //ȯ�ڿ���ó ����
				String t3= textField_19.getText(); //ȯ���ּ� ����
				try {
				db.personal_information_Store(t1,t2,t3,ȯ��id); //ȯ������ insert
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "�߸��Է��Ͽ����ϴ�.");
				}
			}
		});
		
		btnNewButton_6.addActionListener(new ActionListener() { //����Ȯ��(���ΰ�ħ)
			public void actionPerformed(ActionEvent e) {
				DB_Conn_Query db = new DB_Conn_Query();

				model2.setNumRows(0);     
				String [] row=db.my_page_reservation(ȯ��id); //�������� ���ΰ�ħ
				model2.addRow(row);
			}
		});
		btnNewButton_6_2.addActionListener(new ActionListener() { //������ҹ�ư
			public void actionPerformed(ActionEvent e) {
				DB_Conn_Query db = new DB_Conn_Query();
				db.mypage_reservation_cancel(ȯ��id); //������� (** id�� ����)
				
				model2.setNumRows(0);     //model ���̺� ���� �ʱ�ȭ
				String [] row=db.my_page_reservation(ȯ��id); //�������� �ٽ� ��ȸ(���ΰ�ħ)
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
