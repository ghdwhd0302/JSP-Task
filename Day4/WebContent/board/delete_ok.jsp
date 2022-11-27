<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.koreait.db.Dbconn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String b_idx = request.getParameter("b_idx");
	String userid = (String)session.getAttribute("userid");
	
	String re_idx = request.getParameter("re_idx");
	String re_userid = request.getParameter("re_userid");

	String sql = "";
	
try {
	conn = Dbconn.getConnection();
	if(conn != null) {
			
			sql = "delete from tb_board where b_idx = ? and b_userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_idx);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		}
	
			sql = "delete from tb_reply where re_idx = ? and re_userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, re_idx);
			pstmt.setString(2, re_userid);
			pstmt.executeUpdate();
			
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
%>
<script>
	alert('삭제되었습니다');
	location.href='list.jsp'
</script>