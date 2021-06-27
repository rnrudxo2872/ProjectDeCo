package com.deco.team;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.ActionForward;
import com.deco.Controller;

@WebServlet("*.te")
public class teamFrontController extends Controller {
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("C : teamFrontControlle_doProcess()호출");
		
		setInit(req, resp);
		System.out.println(command);
		
		/////////////////////////////////////////////////////////
		
		if(command.equals("/teamMain.te")){
			System.out.println("./teamMain.te");
			forward = new ActionForward("./team/teamMain.jsp", false);
		}
		
		
		
		
		/////////////////////////////////////////////////////////
		
		if(forward != null){
			if(forward.isRedirect()){
				resp.sendRedirect(forward.getURL());
				System.out.println("C : sendRedirect() 방식, " + forward.getURL() + "페이지 이동");
			}else{ //false
				System.out.println(forward.getURL());
				RequestDispatcher dis = req.getRequestDispatcher(forward.getURL());
				
				dis.forward(req, resp);
				System.out.println("C : forward() 방식, " + forward.getURL());
			}
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("C : teamFrontControlle_doGet()호출");
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("C : teamFrontControlle_doPost()호출");
		doProcess(req, resp);
	}

	
	
}
