package com.na.user.info.model.dao;
import static com.na.template.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.na.template.model.vo.PageInfo;
import com.na.user.info.model.vo.Faq;
import com.na.user.info.model.vo.Notice;
import com.na.user.info.model.vo.Qna;
import com.na.user.member.model.dao.MemberDao;


public class InfoDao {

	private Properties prop = new Properties();
	
	public InfoDao() {

		String fileName = MemberDao.class.getResource("/sql/info/info-mapper.xml").getPath();
				
		try {
			prop.loadFromXML(new FileInputStream(fileName));
					
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectNoticeCount(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;		
		
	}
	
	public ArrayList<Notice> selectNoticeList(Connection conn, PageInfo pi){
		
		ArrayList<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeList");
		try {
			pstmt = conn.prepareStatement(sql);

			
			int startRow = (pi.getCurrentPage() - 1)* pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
//			NOTI_NO, NOTI_TITLE, NOTI_DESCRIPTION, NOTI_DATE, NOTI_STATUS
			while(rset.next()) {
				list.add(new Notice(rset.getInt("NOTI_NO")
								 , rset.getString("NOTI_TITLE")
						         , rset.getString("NOTI_DESCRIPTION")
						         , rset.getString(4)
						         , rset.getString("NOTI_STATUS")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;	
		
		
	}
	
	public Notice selectNotice(Connection conn, int notiNo) {
		
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNotice9");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notiNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice(
						rset.getInt(1)
					  , rset.getString(2)
					  , rset.getString(3)
					  , rset.getString(4)
					  , rset.getString(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return n;
		
	}
	
	public ArrayList<Faq> selectFaqList(Connection conn){
		
		ArrayList<Faq> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFaqList9");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				list.add(new Faq(rset.getInt("FAQ_NO")
								 , rset.getString("FAQ_TITLE")
						         , rset.getString("FAQ_DESCRIPTION")
						         , rset.getString("FAQ_STATUS")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;	
		
		
	}
	
	public Faq selectFaq(Connection conn, int faqNo) {
		
		Faq f = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFaq9");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, faqNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				f = new Faq(
						rset.getInt(1)
					  , rset.getString(2)
					  , rset.getString(3)
					  , rset.getString(4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return f;
		
	}
	
	public int selectListCount5(Connection conn, int userNo) {
		// SELECT??? => ResultSet
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null; 
		
		// sql???
		String sql = prop.getProperty("selectListCount5");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;		
	}
	
	public ArrayList<Qna> selectQnaList(Connection conn, PageInfo pi, int userNo){
		// ????????? ?????? ??????
		ArrayList<Qna> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// sql??? 
		String sql = prop.getProperty("selectQnaList");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			// ????????? ?????????  = (???????????????-1) * ????????? ???????????? + 1
			// ?????? ??????, ??????????????? ????????? ?????? ????????? 10?????????
			// 1????????? = 1 ~ 10
			// 2????????? = 11 ~ 20
			// 3????????? = 21 ~ 30
			// ...
			
			// ????????? ??? ??? = ????????? + ????????? ???????????? - 1
			int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
		
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Qna(rset.getInt("INQ_NO"),
						 		 rset.getString("INQ_TITLE"),
						 		 rset.getString("MEM_ID"),
						 		 rset.getString("D"),
						 		 rset.getString("????????????")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			close(pstmt);
			close(rset);
		}
	
		return list;
	}
	
	public Qna selectQnaDetail(Connection conn, int inqueryNo) {
		Qna q = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectQnaDetail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inqueryNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				q = new Qna();
				q.setInqNo(rset.getInt("INQ_NO"));
				q.setInqTitle(rset.getString("INQ_TITLE"));
				q.setInqQuery(rset.getString("INQ_QUERY"));
				q.setInqAnswer(rset.getString("????????????"));
				q.setStatus(rset.getString("INQ_STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	return q;
	}
	
	public int insertQna(Connection conn, Qna q) {
		// ????????? ?????? ??????
		int result = 0;
		PreparedStatement pstmt = null;
		
		// sql???
		String sql = prop.getProperty("insertQna");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, q.getInqTitle());
			pstmt.setString(2, q.getInqQuery());
			pstmt.setInt(3, Integer.parseInt(q.getUserNo()));
			System.out.println(q.getUserNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public int deleteQna(Connection conn, int inqueyNo ) {
		
		// ????????? ?????? ??????
		int result = 0;
		PreparedStatement pstmt = null;
		
		// sql???
		String sql = prop.getProperty("deleteQna");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, inqueyNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
				
	}
	
	public int updateQna(Connection conn, Qna q) {
		// ????????? ?????? ??????
		int result = 0;
		PreparedStatement pstmt = null;
		
		// sql??? 
		String sql = prop.getProperty("updateQna");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, q.getInqTitle());
			pstmt.setString(2, q.getInqQuery());
			pstmt.setInt(3,q.getInqNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
}


