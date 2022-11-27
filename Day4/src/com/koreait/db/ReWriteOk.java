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

@WebServlet("/ReWriteOk")
public class ReWriteOk extends HttpServlet {
	private static final long serialVersionUID = 1L;     

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		request.setCharacterEncoding("UTF-8");
		
		String userid	= (String)session.getAttribute("userid");
		String name	= (String)session.getAttribute("name");
		
		String b_idx = request.getParameter("b_idx");
		String re_content = request.getParameter("re_content");
		
		try {
			conn = Dbconn.getConnection();
			if(conn != null) {
				String sql = "insert into tb_reply(re_userid, re_name, re_content, re_boardidx) values (?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				pstmt.setString(2, name);
				pstmt.setString(3, re_content);
				pstmt.setString(4, b_idx);
				pstmt.executeUpdate();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		writer.println("<script>alert('등록되었습니다!'); location.href='./board/view.jsp?b_idx=" + b_idx +"'</script>");
	}

}
