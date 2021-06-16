package book.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import book.access.BookAccess;
import book.access.BookDAO;
import book.model.Book;

public class BookGUIApp extends JFrame {

	JTextField txtTitle, txtAuthor, txtPublisher;
	JButton btnSearchAll, btnSearchTitle, btnSearchAuthor, btnSearchPublisher, btnRental, btnReturn, btnMyPage;
	TextArea txtList;
	JLabel lbTitle, lbAuthor, lbPublisher;
	
    GridBagLayout gbl;
    GridBagConstraints gbc;
	
	BookAccess bookList = new BookDAO();

	public BookGUIApp() {
		setTitle("도서관리");
		setSize(420, 600);
		init();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);

	}

		
	

	public void init() {

		txtTitle = new JTextField(35);
		txtAuthor = new JTextField(35);
		txtPublisher = new JTextField(35);
		
		btnSearchAll = new JButton("전체조회");
		btnSearchTitle = new JButton("제목조회");
		btnSearchAuthor = new JButton("작가조회");
		btnSearchPublisher = new JButton("출판사조회");
		btnRental = new JButton("대여");
		btnReturn = new JButton("반납");
		btnMyPage = new JButton("마이페이지");
		

		txtList = new TextArea(22, 55);
		

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
		btnSearchTitle.addActionListener(e -> searchTitle());
		btnSearchAuthor.addActionListener(e -> searchAuthor());
		btnSearchPublisher.addActionListener(e -> searchPublisher());
		btnRental.addActionListener(null);
		btnReturn.addActionListener(null);
		btnMyPage.addActionListener(null);
	}

	public void searchAll() {
		ArrayList<Book> list = bookList.searchAll();
		StringBuffer sb = new StringBuffer();
		for (Book book : list) {
			sb.append(book);
			sb.append("\n");
		}
		txtList.setText(sb.toString());
	}
	
	public void searchTitle() {
		ArrayList<Book> list = bookList.searchTitle(txtTitle.getText());
		StringBuffer sb = new StringBuffer();
		for (Book book : list) {
			sb.append(book);
			sb.append("\n");
		}
		txtList.setText(sb.toString());
	}
	
	public void searchAuthor() {
		ArrayList<Book> list = bookList.searchAuthor(txtAuthor.getText());
		StringBuffer sb = new StringBuffer();
		for (Book book : list) {
			sb.append(book);
			sb.append("\n");
		}
		txtList.setText(sb.toString());
	}
	
	public void searchPublisher() {
		ArrayList<Book> list = bookList.searchPublisher(txtPublisher.getText());
		StringBuffer sb = new StringBuffer();
		for (Book book : list) {
			sb.append(book);
			sb.append("\n");
		}
		txtList.setText(sb.toString());
	}

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

}