package com.java;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;



@WebServlet("/Insert")
public class Insert extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO dto = new UserDTO();
		
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setGender(request.getParameter("gender"));
		
		SqlSession sql = Dbconn.getFac().openSession();
		int status = sql.insert("user.add", dto);
		System.out.println("상태값 : " + status);
		if(status == 1) {
			int no = sql.selectOne("user.getNo");
//			System.out.println("no : " + no);
			sql.commit();
			
			response.sendRedirect("Select?no=" + no); // get방식 호출 : 생성된 사용자 번호 전달
		} else {
			sql.rollback();
		}
		
		
	}

}
