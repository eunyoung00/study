package day0225;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//1.HttpServlet상속
public class Hello extends HttpServlet {
	//2.요청방식을 처리할 수 있는 추상 method를 Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3.응답방식 설정(MIME-TYPES):접속자에게 보내줄 형식 설정
		//response.setContentType("text/html");//885_1이기본이라..
		response.setContentType("text/html;charset=UTF-8");//885_1이기본이라..
		//4.(응답방식으로 생성될 파일에)접속자에게 보내줄 내용을 전달하기위해 Stream을 얻기.
		PrintWriter out=response.getWriter();
		//5.출력내용을 스트림에 기록
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>안녕?Servlet?</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("요청"+request+"<br/>");
		out.println("응답"+response+"<br/>");
		
		out.println("<Strong>안녕 Servlet!!!</Strong>");
		out.println("난 김정윤이라고 해<br/>");
		out.println("2019년 02월 25일 입니다.");//한글은 나오지 않는다....
		
		out.println("</body>");
		out.println("</html>");
	}//doGet
}//class
