<%@page import="com.koreait.db.Dbconn"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
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
					// db에서 name 가져옴
					session.setAttribute("name", rs.getString("mem_name"));
%>
					 <script>
					 	alert('로그인 되었습니다');
					 	location.href="login.jsp";
					 </script>
<%			
				} else {
				// 로그인 실패!
%>
 				<script>
			 	alert('아이디 또는 비밀번호를 확인하세요');
			 	history.back();
			 	</script>
<% 				
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
%>





