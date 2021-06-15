package book.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import book.access.BookDAO;
import book.model.User;

public class JMemberSignUp extends JFrame implements ActionListener {

	JPanel panel;
	JTextField tfId, tfName, tfBirth, tfPhone;
	JPasswordField pfPass;	//비밀번호
	JButton btnInsert, btnCancel, btnUpdate, btnDelete;	//가입,가입취소,수정,삭제 버튼
	
	GridBagLayout gbl;	//?
	GridBagConstraints gbc;	//?
	ArrayList<User> userList;
	
	
	//가입용 생성자
	public JMemberSignUp() {
		createUI();	//UI작성하는 메소드
		btnUpdate.setEnabled(false);
		btnUpdate.setVisible(false);
		btnDelete.setEnabled(false);
		btnDelete.setEnabled(false);
	}
	
	public JMemberSignUp(ArrayList<User> userList) {
		createUI();	//UI작성하는 메소드
		btnUpdate.setEnabled(false);
		btnUpdate.setVisible(false);
		btnDelete.setEnabled(false);
		btnDelete.setEnabled(false);
		this.userList = userList;
	}
	//Member DTO = Book  , MemberDAO = bookDAO
	//수정,삭제용 생성자
	public JMemberSignUp(String user_id, ArrayList<User> userList) {
		createUI();
		btnInsert.setEnabled(false);
		btnInsert.setVisible(false);
		this.userList = userList;
		
		System.out.println("user_id=" + user_id);
		
		BookDAO dao = new BookDAO();
		User user = new User();
		viewDate(user);
	} //id로 생성
	
	
	//BookDAO의 회원정보를 가지고 화면에 셋팅해주는 메소드
	private void viewDate(User user) {
		
		String user_id = user.getUser_id();
		String user_pass = user.getUser_pass();
		String user_name = user.getUser_name();
		String user_birth = user.getUser_birth();
		String user_phone = user.getUser_phone();
		
		//화면에 셋팅
		tfId.setText(user_id);
		tfId.setEditable(false); //편집 안 되게
		pfPass.setText(user_pass);
		tfName.setText(user_name);
		tfBirth.setText(user_birth);
		tfPhone.setText(user_phone);
	
	}
	
	private void createUI() {
		this.setTitle("회원정보");
		gbl = new GridBagLayout();
		setLayout(gbl);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		//아이디
		JLabel lbId = new JLabel("아이디: ");
		tfId = new JTextField(20);
		//그리드백에 붙이기
		gblAdd(lbId, 0, 0, 1, 1);
		gblAdd(tfId, 1, 0, 3, 1);
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
