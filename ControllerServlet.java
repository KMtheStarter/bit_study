package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionFactory;
import action.ActionForward;
@WebServlet("*.bit")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10, // 10MB
maxRequestSize=1024*1024*50) // 50MB

public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}

	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("euc-kr");
		// 사용자로부터 요청을 받는다.
		String cmd = req.getParameter("cmd");
		// cmd가 있을 경우 (사용자로부터 요청이 있는 경우)
		if (cmd != null) {
			ActionFactory factory = ActionFactory.getFactory();
			Action action = factory.getAction(cmd);
			ActionForward af = action.execute(req, resp);
			if (af.isMethod()) {
				resp.sendRedirect("/WEB-INF/jsp/" + af.getUrl());
			} else {
				String path = "/WEB-INF/jsp/" + af.getUrl();
				RequestDispatcher rd = req.getRequestDispatcher(path);
				rd.forward(req, resp);
			}
		} else {
			// 요청이 없다면 처리할 로직...
		}
	}
}
