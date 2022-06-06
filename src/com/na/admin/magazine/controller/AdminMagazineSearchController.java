package com.na.admin.magazine.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.na.admin.magazine.model.service.MagazineService;
import com.na.template.model.vo.PageInfo;
import com.na.user.magazine.model.vo.Magazine;

/**
 * Servlet implementation class AdminMagazineSearchController
 */
@WebServlet("/magSearch.mg")
public class AdminMagazineSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMagazineSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("keyword");
		
				// 페이징처리 
				// 기본적으로 구해야하는 변수들 셋팅
				int listCount; // 현재 총 게시글 갯수
				int currentPage; // 현재 보여질 페이지(사용자가 요청한 페이지) getParameter로 뽑을 것임
				int pageLimit; // 페이지 하단에 보여질 페이징바의  최대 페이지 갯수
				int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수 (한 페이지당 게시글이 몇개 단위씩 보여질것인지)
				
				// 위의 변수들을 이용해 구해야 하는 변수들
				int maxPage; // 가장 마지막 페이지가 몇번째 페이지인지 (총 페이지 수)
				int startPage; // 페이지 하단에 보여질 페이징바의 시작수
				int endPage; // 페이지 하단에 보여질 페이징바의 끝 수 
				
				//  *listCount : 현제 게시글 총 갯수 (단, STATUS 값이 'Y'일 경우에만)
				listCount = new MagazineService().selectListCount(keyword);
				
				//System.out.println(listCount);
				
				// *currentPage : 현재페이지 (즉, 사용자가 요청한 페이지)
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
				
				// * pageLimit : 페이지 하단에 보여질 페이징 바의 최대 페이지 갯수
				//				 (페이지 목록들이 몇개 단위씩 보여질 것인지)
				pageLimit = 10;
				
				// * boardLimit : 한 페이지에 보여질 게시글의 최대 갯수
				// 				  (게시글을 몇개 단위씩 보여줄 것인지)
				boardLimit = 10;
				maxPage = (int)Math.ceil((double)listCount / boardLimit);
			
				
				startPage =   (currentPage - 1) / pageLimit  * pageLimit + 1;
				endPage = startPage + pageLimit -1;
				
			
				if(endPage > maxPage) {endPage = maxPage;}
				
				
				// 1. 페이징 바 만들 때 필요한 객체 셋팅
				PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit,
											maxPage, startPage, endPage);	
				

				
				// 2. 현재 사용자가 요청한 페이지(currentPage)에 보여질 게시글 리스트 요청하기 
				ArrayList<Magazine> list = new MagazineService().selectSearch(pi, keyword);
				
				
				request.setAttribute("list", list);
				request.setAttribute("pi", pi);
				request.setAttribute("keyword", keyword);
			
				
				request.getRequestDispatcher("views/admin/magazine/adminMagazineListView2.jsp").forward(request, response);
			
			}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
