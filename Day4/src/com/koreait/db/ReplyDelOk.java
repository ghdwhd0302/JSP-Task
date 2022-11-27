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
import javax.servlet.http.HttpSession;

@WebServlet("/ReplyDelOk")
public class ReplyDelOk extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String b_idx = request.getParameter("b_idx");
		String userid = (String)session.getAttribute("userid");
		
		String re_idx = request.getParameter("re_idx");

		String sql = "";
		
	try {
		conn = Dbconn.getConnection();
		if(conn != null) {

				sql = "delete from tb_reply where re_idx = ? and re_userid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, re_idx);
				pstmt.setString(2, userid);
				pstmt.executeUpdate();
		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	writer.println("<script>alert('삭제되었습니다!'); location.href='./board/view.jsp?b_idx=" + b_idx + "'</script>");
	}
		
}
