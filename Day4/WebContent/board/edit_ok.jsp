<%@page import="com.koreait.db.Dbconn"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("UTF-8");
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String b_idx = request.getParameter("b_idx");
	String b_title 	= request.getParameter("b_title");
	String b_content 	= request.getParameter("b_content");
	String userid = (String)session.getAttribute("userid");
	String name = (String)session.getAttribute("name");

	String sql = "";
	
try {
	conn = Dbconn.getConnection();
	if(conn != null) {
		// System.out.println("DB연결 성공");

		/* sql = "select b_title, b_content from tb_board where b_idx=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, b_idx);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			sql = "update tb_board set b_title=?, b_content=? where b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_title);
			pstmt.setString(2, b_content);
			pstmt.setString(3, b_idx);
			pstmt.executeUpdate(); */
			
			sql = "update tb_board set b_title=?, b_content=?, b_userid=?, b_name=? where b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_title);
			pstmt.setString(2, b_content);
			pstmt.setString(3, userid);
			pstmt.setString(4, name);		
			pstmt.setString(5, b_idx);
			pstmt.executeUpdate();
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
%>
<script>
	alert('변경되었습니다');
	location.href='view.jsp?b_idx=<%=b_idx%>'
</script>