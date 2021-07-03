package com.deco.team.comment;

import java.io.PrintWriter;

import javax.mail.search.IntegerComparisonTerm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deco.Action;
import com.deco.ActionForward;

public class Team_commentdeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 댓글번호와 닉네임 비교
		Team_commentDTO tcdto = new Team_commentDTO();
		tcdto.setIdx(Integer.parseInt(req.getParameter("idx")));
		tcdto.setNickname(req.getParameter("nickname"));
		Team_commentDAO tcdao = new Team_commentDAO();
		int check = tcdao.commentDelete(tcdto);
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(check);
		out.close();
		
		
		return null;
	}

	
	
}
