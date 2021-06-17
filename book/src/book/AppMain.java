package book;

import book.view.BookApp;
/*
 * 2021-06-17 도서관리프로그램

사용자는 도서를 조회하고 대여/반납할 수 있다.
관리자는 도서를 추가하고 수정 및 삭제할 수 있다.

추가하고 싶은 기능... to.미래의 나에게
1.회원가입할 때 이메일까지 입력받아서 -> 비밀번호 잊어버렸을 때 이메일으로 임시비밀번호 보내주는 기능
--임시비밀번호를 입력받는 방법?
2.user마다 대여한 책 목록을 다르게 보여주고 싶음
3.동일 user인데 목록으로 나갔다가 다시 대여하면 추가로 3권씩 더 대여되는 현상 막는 방법?
 */

public class AppMain {
	public static void main(String[] args) {
		
		new BookApp().executeApp();
		
//		new BookApp().user();
				
	}
	
}
