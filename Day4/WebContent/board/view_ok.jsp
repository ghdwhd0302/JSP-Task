<%@page import="com.koreait.db.Dbconn"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String b_idx = request.getParameter("b_idx");
	
	try {
		conn = Dbconn.getConnection();
		if(conn != null) {
			// System.out.println("DB연결 성공");

			String sql = "update tb_board set b_like = b_like+1 where b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_idx);
			pstmt.executeUpdate();
			
			String sql2 = "select b_like from tb_board where b_idx=?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, b_idx);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String b_like = rs.getString("b_like");
				out.print(b_like);
			}
		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
%>