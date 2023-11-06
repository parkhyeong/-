package Fast;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Doctor_Insert extends JFrame {

	private JPanel contentPane;
	private JLabel lblJoin;
	private JButton joinCompleteBtn;
	private JTextField DCnum;
	private JTextField DCname;
	private JTextField DCyear;
	private JTextField DCday;
	private JTextField DCwork;
	private JTextField Hosnum;

	/**
	 * Launch the application.
	 */

	
	public Doctor_Insert() {
		var Connection = new DB_Conn_Query2();
		var con = Connection.DB_Connect();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(430, 490);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblJoin = new JLabel("의사추가");
		Font f1 = new Font("돋움", Font.BOLD, 20); //궁서 바탕 돋움
		lblJoin.setFont(f1); 
		lblJoin.setBounds(159, 41, 101, 20);
		contentPane.add(lblJoin);
		
		JLabel lblUsername = new JLabel("의사이름");
		lblUsername.setBounds(69, 163, 69, 20);
		contentPane.add(lblUsername);
		
		JLabel label = new JLabel("의사번호");
		label.setBounds(69, 113, 69, 20);
		contentPane.add(label);
		
		JLabel lblName = new JLabel("경력");
		lblName.setBounds(69, 210, 69, 20);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("진료가능요일");
		lblEmail.setBounds(69, 257, 69, 20);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("전문분야");
		lblPhone.setBounds(69, 304, 69, 20);
		contentPane.add(lblPhone);
		
		JLabel lblHosnum = new JLabel("병원등록번호");
		lblHosnum.setBounds(69, 354, 69, 20);
		contentPane.add(lblHosnum);
		
		DCnum = new JTextField();
		DCnum.setColumns(10);
		DCnum.setBounds(159, 106, 186, 35);
		contentPane.add(DCnum);
		
		DCname = new JTextField();
		DCname.setColumns(10);
		DCname.setBounds(159, 156, 186, 35);
		contentPane.add(DCname);
		
		DCyear = new JTextField();
		DCyear.setColumns(10);
		DCyear.setBounds(159, 203, 186, 35);
		contentPane.add(DCyear);
		
		DCday = new JTextField();
		DCday.setColumns(10);
		DCday.setBounds(159, 250, 186, 35);
		contentPane.add(DCday);
		
		DCwork = new JTextField();
		DCwork.setColumns(10);
		DCwork.setBounds(159, 297, 186, 35);
		contentPane.add(DCwork);
		
		Hosnum = new JTextField();
		Hosnum.setColumns(10);
		Hosnum.setBounds(159, 344, 186, 35);
		contentPane.add(Hosnum);
		
		joinCompleteBtn = new JButton("의사 추가하기");
		joinCompleteBtn.setBounds(206, 390, 139, 29);
		contentPane.add(joinCompleteBtn);
		
		setVisible(true);
		//회원가입완료 액션
		
		joinCompleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				var Doc = new DoctorDTO();
				Doc.set의사번호(DCnum.getText());
				Doc.set의사이름(DCname.getText());
				Doc.set경력(DCyear.getText());
				Doc.set진료가능요일(DCday.getText());
				Doc.set전문분야(DCwork.getText());
				Doc.set병원등록번호(Hosnum.getText());
				
				if (AdminDAO.join2(con, Doc)) {
					JOptionPane.showMessageDialog(null, "의사 추가 완료!");				
					// 회원가입 끝났으면 정보 다 날림
					DCnum.setText("");
					DCname.setText("");
					DCyear.setText("");
					DCday.setText("");
					DCwork.setText("");
					Hosnum.setText("");
					// 패널을 닫고, 기존에 비활성화된 버튼을 활성화함
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "의사 추가 실패.");
					}
				}
			});
		}
}