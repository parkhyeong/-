package Fast;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class admin_gui extends JFrame{
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	DefaultTableModel model;
	public String admin_id;
	public String testdata2 [][] = {}; //�ǻ絥�������� 
	
	public admin_gui(String ����id) {
		String SS[] = {"�̸�","�ֹι�ȣ"};
		String header1 [] = {"�̸�","�ֹι�ȣ","����ó","�ּ�","����ð�","����"};
		String header2 [] = {"�ǻ��ȣ", "�ǻ��̸�","�����о�","���(��)","���ᰡ�ɿ���"};
		
		
		admin_id=����id;
		
		getContentPane().setLayout(null);
		
		var Connection = new DB_Conn_Query2();
		var con = Connection.DB_Connect();
		testdata2 = AdminDAO.SELECT_ALL_DOCTOR(con, admin_id);
		String[] hospital=AdminDAO.hospital(con, admin_id);
		
		model = new DefaultTableModel(header1, 0){				//�±� �ޱ�					
			public boolean isCellEditable(int row, int column) {				// ���� ���� �Ұ�
				return false;
			}
		};
		
		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(170, 0, 664, 611);
		mainpanel.setVisible(true);  // �۾��ϴ� ���� �ӽ÷� �Ⱥ��̰�
		getContentPane().add(mainpanel);
		mainpanel.setLayout(null);
		
		JPanel adminpage = new JPanel();
		adminpage.setBounds(170, 0, 664, 611);
		adminpage.setVisible(false);  //  �۾��ϴ� ���� �ӽ÷� ���̰�
		getContentPane().add(adminpage);
		adminpage.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�����̸�");
		lblNewLabel.setBounds(26, 30, 57, 15);
		adminpage.add(lblNewLabel);
		
		JTextField hospitalname = new JTextField(); //�����̸� text�ʵ�
		hospitalname.setBounds(100, 27, 200, 21);
		adminpage.add(hospitalname);
		hospitalname.setText(hospital[0]);
		
		JLabel lblNewLabel_1 = new JLabel("�����ּ�");
		lblNewLabel_1.setBounds(26, 64, 57, 15);
		adminpage.add(lblNewLabel_1);
		
		JTextField hospitaladdress = new JTextField(); //�����ּ� text�ʵ�
		hospitaladdress.setBounds(100, 64, 280, 21);
		adminpage.add(hospitaladdress);
		hospitaladdress.setText(hospital[1]);
		
		JLabel lblNewLabel_2 = new JLabel("���� ����ó");
		lblNewLabel_2.setBounds(26, 100, 79, 15);
		adminpage.add(lblNewLabel_2);
		
		JTextField hospitalnumber = new JTextField(); //������ȣ text�ʵ�
		hospitalnumber.setBounds(100, 100, 200, 21);
		adminpage.add(hospitalnumber);
		hospitalnumber.setText(hospital[2]);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(42, 206, 574, 366);
		adminpage.add(scrollPane_1);
		
		table_1 = new JTable(testdata2,header2);
		
		
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton_4 = new JButton("���� ���� ���� �ϱ�");
		btnNewButton_4.setBounds(410, 51, 150, 40);
		adminpage.add(btnNewButton_4);
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ŭ��");
				AdminDAO.rectify(con, hospitalname.getText(), hospitaladdress.getText(), hospitalnumber.getText(), admin_id);
				JOptionPane.showMessageDialog(null, "�����Ϸ�");
				mainpanel.setVisible(false);
				adminpage.setVisible(true);
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("�ǻ���");
		lblNewLabel_3.setBounds(42, 181, 57, 15);
		adminpage.add(lblNewLabel_3);
		
		JButton btnNewButton_8 = new JButton("�ǻ��߰�");
		btnNewButton_8.setBounds(519, 177, 97, 23);
		adminpage.add(btnNewButton_8);
		

		
		JButton btnNeww= new JButton("���ΰ�ħ"); //������������ ���ΰ�ħ��ư
		btnNeww.setBounds(295, 177, 97, 23);
		adminpage.add(btnNeww);
		
		btnNeww.addActionListener(new ActionListener() { //���ΰ�ħ �̺�Ʈ
			public void actionPerformed(ActionEvent e) {
				testdata2 = AdminDAO.SELECT_ALL_DOCTOR(con, admin_id); //����
				table_1 = new JTable(testdata2,header2); //���̺����
				scrollPane_1.setViewportView(table_1);
			}
		});
		
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Doctor_Insert c=new Doctor_Insert();
				
				testdata2 = AdminDAO.SELECT_ALL_DOCTOR(con, admin_id); //����
				table_1 = new JTable(testdata2,header2); //���̺����
				scrollPane_1.setViewportView(table_1);
			}
		});
		
		JButton btnNewButton_9 = new JButton("�ǻ����");
		btnNewButton_9.setBounds(407, 177, 97, 23);
		adminpage.add(btnNewButton_9);
		
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table_1.getSelectedRow();
				AdminDAO.DELETE_DOCTOR(con, testdata2[row][0].toString());
				testdata2 = AdminDAO.SELECT_ALL_DOCTOR(con, admin_id); //����
				table_1 = new JTable(testdata2,header2); //���̺����
				scrollPane_1.setViewportView(table_1);
			}
		});
		
	
		
		textField = new JTextField();
		textField.setBounds(116, 33, 393, 21);
		mainpanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("�˻�");
		btnNewButton_1.setBounds(528, 32, 97, 23);
		mainpanel.add(btnNewButton_1);
		
		JComboBox comboBox = new JComboBox(SS);
		comboBox.setBounds(34, 32, 70, 23);
		mainpanel.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 146, 591, 426);
		mainpanel.add(scrollPane);
		
		table = new JTable(model);          //��ºκ�
		table.setFont(new Font("���� ���", Font.BOLD, 12));
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false); //���̺� �̵��Ұ�
		
		scrollPane.setViewportView(table);
	
		DB_Conn_Query DB = new DB_Conn_Query();
		
		
		JButton btnNewButton_2 = new JButton("����");
		btnNewButton_2.setBounds(528, 113, 97, 23);
		mainpanel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("���ΰ�ħ");
		btnNewButton_3.setBounds(34, 113, 97, 23);
		mainpanel.add(btnNewButton_3);
		
		JButton btnNewButton_6 = new JButton("����");
		btnNewButton_6.setBounds(412, 113, 97, 23);
		mainpanel.add(btnNewButton_6);
		
		
		
		JPanel mypagepanel = new JPanel();
		mypagepanel.setBounds(170, 0, 664, 611);
		mypagepanel.setVisible(false);
		getContentPane().add(mypagepanel);
		mypagepanel.setLayout(null);
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminpage.setVisible(false);
				mainpanel.setVisible(true);
			}
		});
		btnNewButton.setBounds(12, 10, 138, 79);
		getContentPane().add(btnNewButton);
		
		
		btnNewButton_1.addActionListener(new ActionListener() { //�˻��̺�Ʈ
			public void actionPerformed(ActionEvent e) {
				String comboselect=comboBox.getSelectedItem().toString(); //�޺�������
				String textget=textField.getText();
				textField.setText("");
				String row1[] = new String[6];
				DB_Conn_Query db =new DB_Conn_Query();
				
				ArrayList<Reservation_People_DTO> replay = db.hospital_search(comboselect,textget,����id);
			    model.setNumRows(0);
			    for (int i=0; i<replay.size(); i++) {
			    	row1[0] = String.valueOf(replay.get(i).getname());
					row1[1] = String.valueOf(replay.get(i).getrrn());
					row1[2] = String.valueOf(replay.get(i).getphone());
					row1[3] = String.valueOf(replay.get(i).getaddress());
					row1[4] = String.valueOf(replay.get(i).getreservation_time());
		            row1[5] = String.valueOf(replay.get(i).getsymptom());
			        model.addRow(row1);
			    }
			}
		});
		
		btnNewButton_3.addActionListener(new ActionListener() { //���ΰ�ħ�̺�Ʈ
			public void actionPerformed(ActionEvent e) {
				hospital_reservation_TableAddRow(����id);  
			}
		});
		
		JButton btnNewButton_5 = new JButton("���� ���� ����");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainpanel.setVisible(false);
				adminpage.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(12, 99, 138, 79);
		getContentPane().add(btnNewButton_5);
		
		btnNewButton_6.addActionListener(new ActionListener() { //�����̺�Ʈ
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = table.getSelectedRow();	
				
				try {String rrn=table.getValueAt(selectedIndex, 1).toString();
				DB_Conn_Query db =new DB_Conn_Query();
				db.hospital_storage(rrn);
				hospital_reservation_TableAddRow(����id);
				}catch(Exception e1){}
			}
		});
		
		JButton btnNewButton_7 = new JButton("�α׾ƿ�");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new login(null);
			}
		});
		btnNewButton_7.setBounds(12, 567, 146, 23);
		getContentPane().add(btnNewButton_7);
		
		
        
		hospital_reservation_TableAddRow(����id);
		
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850,650);
		setVisible(true); 
		setLocationRelativeTo(null);
		
	}
	
	public void hospital_reservation_TableAddRow(String ����id) { ;//�����������̺� ����
		String row1[] = new String[6];
		DB_Conn_Query db =new DB_Conn_Query();
        ArrayList<Reservation_People_DTO> replay = db.hospital_reservation_people(����id);
        model.setNumRows(0);
        for (int i=0; i<replay.size(); i++) {
        	row1[0] = String.valueOf(replay.get(i).getname()); 					// id,����,�帣,����,����ð�,�۰�⵵�� row1 �迭�� ����
			row1[1] = String.valueOf(replay.get(i).getrrn());
			row1[2] = String.valueOf(replay.get(i).getphone());
			row1[3] = String.valueOf(replay.get(i).getaddress());
			row1[4] = String.valueOf(replay.get(i).getreservation_time());
			row1[5] = String.valueOf(replay.get(i).getsymptom());
	        model.addRow(row1);
        }

    }
	

}
