package com.deco.user.join;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deco.Action;
import com.deco.ActionForward;

public class joinPostAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = null;
		
		req.setCharacterEncoding("utf-8");

		userDTO uDTO = new userDTO();
		uDTO.setReq(req);
		
		if(!isValid(req,uDTO)){
			ValueException(res,"정보입력이 잘못됐습니다! (서로 다른 비밀번호, 정보 입력 등..)");
			return forward; // null 반환
		}

		userDAO uDAO = new userDAO();
		int flag = uDAO.insertUser(uDTO);
		
		//에러 발생 시,
		if(flag == -5){
			ValueException(res,"무언가 잘못됐습니다!");
			return forward; // null 반환
		}
		
		forward = new ActionForward("./emailComfirm", true);
		return forward;
	}
	
	private boolean isValid(HttpServletRequest req, userDTO uDTO){
		int majorLen = uDTO.getMajor().split(",").length;
		
		if(!req.getParameter("pw").equals(req.getParameter("pw2"))){
			return false;
		}
		if(5 < majorLen || majorLen < 1){
			return false;
		}
		
		return true;
	}
	
	public void ValueException(HttpServletResponse res, String msg) throws IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('" + msg + "');");
		out.println("history.back()");
		out.println("</script>");
		
		out.close();
	}
}
