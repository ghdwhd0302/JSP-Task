<%@page import="com.koreait.db.Dbconn"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../include/sessioncheck.jsp" %>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	
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
%>

<script>
	alert('등록되었습니다');
	location.href="list.jsp";
</script>