package com.deco.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.ActionForward;
import com.deco.Controller;

@WebServlet("*.us")
public class userController extends Controller{

	protected void doProcess(HttpServletRequest req, HttpServletResponse res) throws Exception{
		setInit(req, res);
		
		if(command.equals("/join.us")){
			forward = new ActionForward("./user/join/join.jsp", false);
		
		}else if(command.equals("/joinAction.us")){
			action = new joinPostAction();
			forward = action.execute(req, res);
		
		}else if(command.equals("/emailAuthAction.us")){
			action = new EmailAuthAction();
			forward = action.execute(req, res);
		
		}else if(command.equals("/changeEmailCode.us")){
			action = new changeEmailAction();
			forward = action.execute(req, res);
		
		}else if(command.equals("/login.us")){
			forward = new ActionForward("./user/login/login.jsp", false);
		
		}else if(command.equals("/LoginAction.us")){
			action = new LoginAction();
			forward = action.execute(req, res);
		
		}else if(command.equals("/main.us")){
			forward = new ActionForward("./main/main.jsp",false);
		
		}else if(command.equals("/info.us")){
			System.out.println("info.us 호출");
			action = new UserInfoAction();
			forward = action.execute(req, res);
		
		}else if(command.equals("/userlogout.us")){
			action = new userLogoutAction();
			forward = action.execute(req, res);
		
		}else if(command.equals("/update.us")){
			action = new listAction();
			forward = action.execute(req, res);
		
		}else if(command.equals("/UpdateAction.us")){
			action = new UpdateAction();
			forward = action.execute(req, res);
		
		}else if(command.equals("/githubStart.us")){
			GitLogin git = new GitLogin();
			forward = new ActionForward("https://github.com/login/oauth/authorize?client_id="+git.getCLIENT_CODE()+"&scope=read:user user:email", true);
			
		}else if(command.equals("/gitLoginFin.us")){
			action = new GitLoginFin();
			forward = action.execute(req, res);
		
		}else if(command.equals("/SocialJoin.us")){
			action = new SocialJoin();
			forward = action.execute(req, res);
		
		}else if(command.equals("/SocialJoinAction.us")){
			action = new SocialJoinAction();
			forward = action.execute(req, res);
		
		}else if(command.equals("/kakaoLoginAction.us")){
			action = new KakaoLoginAction();
			forward = action.execute(req, res);
		}
		
		render(forward,req,res);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			doProcess(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			doProcess(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
