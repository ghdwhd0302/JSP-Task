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

@WebServlet("/Write")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		
		String userid	= (String)session.getAttribute("userid");
		String name	= (String)session.getAttribute("name");
		
		String b_title = request.getParameter("b_title");
		String b_content = request.getParameter("b_content");
		
		try {
			conn = Dbconn.getConnection();
			if(conn != null) {
				// System.out.println("DB연결 성공");
				String sql = "insert into tb_board(b_userid, b_name, b_title, b_content) values (?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				pstmt.setString(2, name);
				pstmt.setString(3, b_title);
				pstmt.setString(4, b_content);
		
				pstmt.executeUpdate();
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		writer.println("<script>alert('등록되었습니다!');  location.href='./board/list.jsp'; </script>");
	}

}
