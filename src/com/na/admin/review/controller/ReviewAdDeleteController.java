package com.na.admin.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.na.admin.review.model.service.ReviewService;

/**
 * Servlet implementation class ReviewAdDeleteController
 */
@WebServlet("/deletead.re")
public class ReviewAdDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewAdDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//2) 요청 시 전달값 뽑아서 변수에 기록 및 VO객체로 가공
				int revNo = Integer.parseInt(request.getParameter("rno"));
				
				//3) Service 로 전달값 넘기면서 결과값 받기
				int result = new ReviewService().deleteReview(revNo);
				
				//4) 결과에 따른 응답화면 지정
				if(result>0) { //성공 
					// 공지사항 리스트 페이지로 재요청
					request.getSession().setAttribute("alertMsg", "성공적으로 공지사항이 삭제되었습니다.");
					
					response.sendRedirect(request.getContextPath() + "/reviewadlist.re?currentPage=1");			
				}
				else { //실패=> 에러페이지 포워딩
					
					request.setAttribute("errorMsg", "공지사항 삭제 실패");
					
					request.getRequestDispatcher("views/user/common/errorPage.jsp").forward(request, response);
					
	 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
