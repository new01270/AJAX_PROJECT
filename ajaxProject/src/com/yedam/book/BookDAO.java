package com.yedam.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DBconnect;

/*
create table book (
book_no number,
book_title varchar2(100),
book_author varchar2(100),
book_price number
);

create sequence book_seq;

insert into book values(book_seq.nextval, '제이쿼리 입문', '정인용', 20000);
insert into book values(book_seq.nextval, '자바스크립트 입문', '정인용', 30000);

select * from book order by 1;
 */

public class BookDAO {
	PreparedStatement psmt;
	ResultSet rs;
	Connection conn;
	String sql;

	// 삭제
	public void deleteBookList(int no) {
		conn = DBconnect.getCon();
		sql = "delete book where book_no=?";
		int r;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			r = psmt.executeUpdate();
			System.out.println(r + "건 삭제 완료했습니당");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 수정
	public void updateBookList(BookVO bo) {
		conn = DBconnect.getCon();
		sql = "update book set book_title=?, book_author=?, book_price=? where book_no=?";
		int r;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, bo.getBookTitle());
			psmt.setString(2, bo.getBookAuthor());
			psmt.setInt(3, bo.getPrice());
			psmt.setInt(4, bo.getBookNo());
			r = psmt.executeUpdate();
			System.out.println(r + "건 수정완료.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 시퀀스로 등록
	public String bookListSeq(BookVO bo) {
		conn = DBconnect.getCon();
		String sqe = null;
		String sql2 = "select book_seq.nextval from dual";

		sql = "insert into book values(?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql2);
			rs = psmt.executeQuery();
			if (rs.next()) {
				sqe = rs.getString(1);
			}

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sqe);
			psmt.setString(2, bo.getBookTitle());
			psmt.setString(3, bo.getBookAuthor());
			psmt.setInt(4, bo.getPrice());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 목록추가 완료.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sqe; // html파일에 반환.
	}

	// 목록조회
	public List<BookVO> getBookList() {
		List<BookVO> books = new ArrayList<>();
		conn = DBconnect.getCon();
		try {
			sql = "select * from book order by 1";
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			while (rs.next()) {
				BookVO bo = new BookVO(rs.getString("book_title"), rs.getString("book_author"),
						rs.getInt("book_price"));
				bo.setBookNo(rs.getInt("book_no"));
				books.add(bo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return books; // 어디로 return??????????
	}
} // end of DAO
