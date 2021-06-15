package book;

import java.util.Scanner;

import book.util.ScannerUtil;
import book.view.BookApp;
import book.view.UserApp;

public class AppMain {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		String str1 = ScannerUtil.readStr("회원가입/로그인");
		if(str1.equals("회원가입")) {
			new UserApp().signUp();
		}
		else {
			String str2 = ScannerUtil.readStr("user/manager");
			if(str2.equals("user")) {				
				new UserApp().userLogin();
			}
			else {
//				new UserApp().managerLogin();
			}
		}
		
	}
	
}
