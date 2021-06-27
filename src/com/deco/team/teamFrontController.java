package com.deco.team;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.ActionForward;
import com.deco.Controller;
import com.deco.share.shareWriteAction;

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
		}else if(command.equals("/createTeam.te")){
			System.out.println("C : createTeam.te 호출");
			forward = new ActionForward("./team/createTeam.jsp", false);
		}else if(command.equals("/createTeamAction.te")){
			System.out.println("C : /team/createTeamAction.te 호출");
			action = new createTeamAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/teamList.te")){
			System.out.println("C : teamList.te 호출");
			action = new teamListAction();
			try {
				action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			forward = new ActionForward("./team/teamList.jsp", false);
		}else if(command.equals("/teamView.te")){
			System.out.println("C : teamView.te 호출");
			forward = new ActionForward("./team/teamView.jsp", false);
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
