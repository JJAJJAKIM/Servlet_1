package com.java;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

@WebServlet("/Updateinfo")
public class Updateinfo extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		UserDTO dto = new UserDTO();
		dto.setNo(Integer.parseInt(req.getParameter("no")));
		dto.setName(req.getParameter("name"));
		dto.setEmail(req.getParameter("email"));
		dto.setPwd(req.getParameter("pwd"));
		dto.setGender(req.getParameter("gender"));
		
		SqlSession sql = Dbconn.getFac().openSession();
		int status = sql.update("user.update", dto );
		System.out.println("상태값 : " + status);
		if(status == 1) {
		
			sql.commit();
			int no = dto.getNo();
			System.out.println("Select?no=" +no);
			resp.sendRedirect("Select?no=" + no); // get방식 호출 : 생성된 사용자 번호 전달
		} else {
			sql.rollback();
		}
		
//		System.out.println(name +" : "+ email + " : "+ gender);
	}

	
	
	
}
