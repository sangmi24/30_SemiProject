package com.na.admin.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.na.admin.member.model.service.MemberService;
import com.na.template.model.vo.PageInfo;
import com.na.user.member.model.vo.Member;
import com.na.user.product.model.service.ProductService;
import com.na.user.product.model.vo.Product;

/**
 * Servlet implementation class MemberManageSearchController
 */
@WebServlet("/memManageSearch.me")
public class MemberManageSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberManageSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cate = Integer.parseInt(request.getParameter("cate"));
		String keyword = request.getParameter("keyword");

		
		if(cate==1) {
			
			// ---- 페이징 처리 ----
			// 기본적으로 구해야 하는 변수들
			int listCount; // 현재 총 게시글 갯수
			int currentPage; // 현재 보여질 페이지 (즉, 사용자가 요청한 페이지)
			int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
			int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수
			
			// 위의 변수들을 이용해서 계산해서 구해야 하는 변수들
			int maxPage; // 마지막이 몇 페이지인지
			int startPage; // 페이지 하단에 보여질 페이징바의 시작수
			int endPage; // 페이지 하단에 보여질 페이징바의 끝 수
			
			listCount = new MemberService().searchListCount(keyword);
			
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			pageLimit = 10;
			boardLimit = 6;
			
			maxPage = (int)Math.ceil((double)listCount / boardLimit);
			startPage = (int)Math.floor((double)(currentPage - 1)/pageLimit)*pageLimit + 1;
			endPage = startPage + pageLimit - 1;
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
			
			ArrayList<Member> list = new MemberService().memSearchList(pi, keyword);
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			request.setAttribute("keyword", keyword);
			request.setAttribute("cate", cate);
			request.getRequestDispatcher("views/admin/member/memberManagement2.jsp").forward(request, response);
			
			
		}
		
		if(cate==2) {
			
			// ---- 페이징 처리 ----
			// 기본적으로 구해야 하는 변수들
			int listCount; // 현재 총 게시글 갯수
			int currentPage; // 현재 보여질 페이지 (즉, 사용자가 요청한 페이지)
			int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
			int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수
			
			// 위의 변수들을 이용해서 계산해서 구해야 하는 변수들
			int maxPage; // 마지막이 몇 페이지인지
			int startPage; // 페이지 하단에 보여질 페이징바의 시작수
			int endPage; // 페이지 하단에 보여질 페이징바의 끝 수
			
			listCount = new MemberService().searchListCount2(keyword);
			
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			pageLimit = 10;
			boardLimit = 6;
			
			maxPage = (int)Math.ceil((double)listCount / boardLimit);
			startPage = (int)Math.floor((double)(currentPage - 1)/pageLimit)*pageLimit + 1;
			endPage = startPage + pageLimit - 1;
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
			
			ArrayList<Member> list = new MemberService().memSearchList2(pi, keyword);
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			request.setAttribute("keyword", keyword);
			request.setAttribute("cate", cate);
			request.getRequestDispatcher("views/admin/member/memberManagement2.jsp").forward(request, response);
			
		}
		
		if(cate==3) {
			
			// ---- 페이징 처리 ----
			// 기본적으로 구해야 하는 변수들
			int listCount; // 현재 총 게시글 갯수
			int currentPage; // 현재 보여질 페이지 (즉, 사용자가 요청한 페이지)
			int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
			int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수
			
			// 위의 변수들을 이용해서 계산해서 구해야 하는 변수들
			int maxPage; // 마지막이 몇 페이지인지
			int startPage; // 페이지 하단에 보여질 페이징바의 시작수
			int endPage; // 페이지 하단에 보여질 페이징바의 끝 수
			
			listCount = new MemberService().searchListCount3(keyword);
			
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			pageLimit = 10;
			boardLimit = 6;
			
			maxPage = (int)Math.ceil((double)listCount / boardLimit);
			startPage = (int)Math.floor((double)(currentPage - 1)/pageLimit)*pageLimit + 1;
			endPage = startPage + pageLimit - 1;
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
			
			ArrayList<Member> list = new MemberService().memSearchList3(pi, keyword);
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			request.setAttribute("keyword", keyword);
			request.setAttribute("cate", cate);
			request.getRequestDispatcher("views/admin/member/memberManagement2.jsp").forward(request, response);
			
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
