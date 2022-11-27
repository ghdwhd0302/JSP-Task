package com.koreait.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		
		try {
			conn = Dbconn.getConnection();
			if(conn != null){
				String sql = "select mem_idx, mem_name from tb_member where mem_userid=? and mem_userpw=sha2(?, 256)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				pstmt.setString(2, userpw);
				rs = pstmt.executeQuery();
				if(rs.next()){
					session.setAttribute("userid", userid);
					session.setAttribute("idx", rs.getString("mem_idx"));
					session.setAttribute("name", rs.getString("mem_name"));
					writer.println("<script>alert('로그인되었습니다'); location.href='login.jsp'; </script>");
		
				} else {
					writer.println("<script>alert('아이디 또는 비밀번호를 확인하세요'); history.back(); </script>");
				}
			}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}