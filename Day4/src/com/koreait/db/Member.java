package com.koreait.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Connection conn = null;
		PreparedStatement pstmt = null;
		PrintWriter writer = response.getWriter();
		
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		String name = request.getParameter("name");
		String hp = request.getParameter("hp");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String hobby[] = request.getParameterValues("hobby");
		String ssn1 = request.getParameter("ssn1");
		String ssn2 = request.getParameter("ssn2");
		String zipcode = request.getParameter("zipcode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");
		
		try {
			conn = Dbconn.getConnection();
			if(conn != null) {
				// System.out.println("DB연결 성공");
				String sql = "insert into tb_member(mem_userid, mem_userpw, mem_name, mem_hp, mem_email, mem_hobby, mem_ssn1, mem_ssn2, mem_zipcode, mem_address1, mem_address2, mem_address3, mem_gender) values (?, sha2(?, 256), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				pstmt.setString(2, userpw);
				pstmt.setString(3, name);
				pstmt.setString(4, hp);
				pstmt.setString(5, email);
				
				String hobbystr = "";
				for(int i=0; i<hobby.length; i++) {
					hobbystr = hobbystr + hobby[i] + " ";
				}
				pstmt.setString(6, hobbystr);
				
				pstmt.setString(7, ssn1);
				pstmt.setString(8, ssn2);
				pstmt.setString(9, zipcode);
				pstmt.setString(10, address1);
				pstmt.setString(11, address2);
				pstmt.setString(12, address3);
				pstmt.setString(13, gender);
				
				pstmt.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		writer.println("<html><head></head><body>");
		writer.println("<h2>회원가입 완료</h2>");
		writer.println("<p>" + name + "(" + userid + ")" + "님 회원가입 완료되었습니다!</p>");
		writer.println("<p><a href=\"login.jsp\">로그인 하러가기</a></p>");
		writer.println("</body></html>");
	}
	
}
