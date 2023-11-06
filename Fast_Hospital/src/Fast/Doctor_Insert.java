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
		
		lblJoin = new JLabel("�ǻ��߰�");
		Font f1 = new Font("����", Font.BOLD, 20); //�ü� ���� ����
		lblJoin.setFont(f1); 
		lblJoin.setBounds(159, 41, 101, 20);
		contentPane.add(lblJoin);
		
		JLabel lblUsername = new JLabel("�ǻ��̸�");
		lblUsername.setBounds(69, 163, 69, 20);
		contentPane.add(lblUsername);
		
		JLabel label = new JLabel("�ǻ��ȣ");
		label.setBounds(69, 113, 69, 20);
		contentPane.add(label);
		
		JLabel lblName = new JLabel("���");
		lblName.setBounds(69, 210, 69, 20);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("���ᰡ�ɿ���");
		lblEmail.setBounds(69, 257, 69, 20);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("�����о�");
		lblPhone.setBounds(69, 304, 69, 20);
		contentPane.add(lblPhone);
		
		JLabel lblHosnum = new JLabel("������Ϲ�ȣ");
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
		
		joinCompleteBtn = new JButton("�ǻ� �߰��ϱ�");
		joinCompleteBtn.setBounds(206, 390, 139, 29);
		contentPane.add(joinCompleteBtn);
		
		setVisible(true);
		//ȸ�����ԿϷ� �׼�
		
		joinCompleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				var Doc = new DoctorDTO();
				Doc.set�ǻ��ȣ(DCnum.getText());
				Doc.set�ǻ��̸�(DCname.getText());
				Doc.set���(DCyear.getText());
				Doc.set���ᰡ�ɿ���(DCday.getText());
				Doc.set�����о�(DCwork.getText());
				Doc.set������Ϲ�ȣ(Hosnum.getText());
				
				if (AdminDAO.join2(con, Doc)) {
					JOptionPane.showMessageDialog(null, "�ǻ� �߰� �Ϸ�!");				
					// ȸ������ �������� ���� �� ����
					DCnum.setText("");
					DCname.setText("");
					DCyear.setText("");
					DCday.setText("");
					DCwork.setText("");
					Hosnum.setText("");
					// �г��� �ݰ�, ������ ��Ȱ��ȭ�� ��ư�� Ȱ��ȭ��
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "�ǻ� �߰� ����.");
					}
				}
			});
		}
}