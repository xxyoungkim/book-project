package book.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import book.access.BookAccess;
import book.access.BookDAO;
import book.model.Book;

public class BookGUIApp extends JFrame {

	TextField txtTitle, txtAuthor, txtPublisher;
	JButton btnSearchAll, btnSearchTitle, btnSearchAuthor, btnSearchPublisher, btnRental, btnReturn, btnMyPage;
	TextArea txtList;
	BookAccess bookList = new BookDAO();

	public BookGUIApp() {
		setTitle("도서관리");
		setSize(600, 600);
		init();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void setDisplay() {
//		JPanel pnlWest = new JPanel(new GridLayout(0,1,0,10));
//		pnlWest.add(lblName);
//		pnlWest.add(lblNumber);
//		pnlWest.setBorder(new TitledBorder("West"));
		
		JPanel pnlEast = new JPanel(new GridLayout(0,1,0,10));
		pnlEast.add(txtTitle);
		pnlEast.add(txtAuthor);
//		pnlEast.setBorder(new TitledBorder("East"));
		
//		JPanel pnlSouth = new JPanel();
//		pnlSouth.add(btnSave);
//		pnlSouth.setBorder(new TitledBorder("South"));
		
//		add(pnlWest, BorderLayout.WEST);
		add(pnlEast, BorderLayout.EAST);
//		add(pnlSouth, BorderLayout.SOUTH);
	}


	public void init() {
		txtTitle = new TextField(50);
		txtAuthor = new TextField(50);
		txtPublisher = new TextField(50);

		btnSearchAll = new JButton("전체조회");
		btnSearchTitle = new JButton("제목조회");
		btnSearchAuthor = new JButton("작가조회");
		btnSearchPublisher = new JButton("출판사조회");
		btnRental = new JButton("대여");
		btnReturn = new JButton("반납");
		btnMyPage = new JButton("마이페이지");

		txtList = new TextArea(20, 70);

		this.getContentPane().setLayout(new FlowLayout());
		this.getContentPane().add(new JLabel("제목"));
		this.getContentPane().add(txtTitle);
		this.getContentPane().add(new JLabel("작가"));
		this.getContentPane().add(txtAuthor);
		this.getContentPane().add(new JLabel("출판사"));
		this.getContentPane().add(txtPublisher);

		this.getContentPane().add(btnSearchAll);
		this.getContentPane().add(btnSearchTitle);
		this.getContentPane().add(btnSearchAuthor);
		this.getContentPane().add(btnSearchPublisher);
		this.getContentPane().add(btnRental);
		this.getContentPane().add(btnReturn);
		this.getContentPane().add(btnMyPage);

		this.getContentPane().add(txtList);

		
		
		
		btnSearchAll.addActionListener(e -> searchAll());
	}

	public void searchAll() {
		ArrayList<Book> list = bookList.searchAll();
		StringBuffer sb = new StringBuffer();
		for (Book book : list) {
			sb.append(book);
			sb.append("\n");
		}
		txtList.setText(sb.toString());

//
//	// 등록
//	public void insert() {
//		Friend friend = new Friend();
//		friend.setGubun(txtGubun.getText());
//		friend.setName(txtName.getText());
//		friend.setTel(txtTel.getText());
//		friendList.insert(friend);
//	}
//
//	// 수정
//	public void update() {
//		Friend friend = new Friend();
//		friend.setName(txtName.getText());
//		friend.setTel(txtTel.getText());
//		friendList.update(friend);
//	}
//	
//	
//	// 삭제
//	public void delete() {
//		String name = txtName.getText();
//		friendList.delete(name);
//	}
//
//
//	// 이름검색
//	public void findName() {
//		String name = txtName.getText();
//		Friend friend = friendList.selectOne(name);
//		txtGubun.setText(friend.getGubun());
//		txtTel.setText(friend.getTel());
//		txtName.setText(friend.getName());
//	}
//
//	// 전체조회
//	public void selectAll() {
//		List<Friend> list = friendList.selectAll();
//		StringBuffer sb = new StringBuffer();
//		for (Friend friend : list) {
//			sb.append(friend);
//			sb.append("\n");
//		}
//		txtList.setText(sb.toString());
//	}
	}
}