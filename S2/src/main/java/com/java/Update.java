package com.java;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.java.Utils;

@WebServlet("/Update")
public class Update extends HttpServlet {
	
	private final String page = "user/Update.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String no = request.getParameter("no");
		SqlSession sql = Dbconn.getFac().openSession();
		UserDTO dto = sql.selectOne("user.findNo",no);
		
		request.setAttribute("user", dto);
		Utils.print(request, response, page);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utils.print(request, response, page);
	}

}
