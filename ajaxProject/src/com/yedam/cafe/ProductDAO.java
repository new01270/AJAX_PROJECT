package com.yedam.cafe;

/*
create table product(
item_no varchar2(10) primary key,
item_name varchar2(100),
price number,
item_desc varchar2(1000),
like_it number(3,1),
category varchar2(10),
item_img varchar2(100)
);

SELECT * FROM product;
 */

/*
insert into product values('bean_01', '케냐원두', 4000, '케냐에서 어제 갓 따온 원두', '4.5', 'bean', '케냐.jpg');
insert into product values('bean_02', '브라질원두', 5000, '브라질에서 어제 갓 따온 원두', '4.0', 'bean', '브라질.jpg');
insert into product values('bean_03', '과테말라원두', 6000, '과테말라에서 어제 갓 따온 원두', '3.5', 'bean', '과테말라.jpg');
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedom.common.DBconnect;

public class ProductDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	public void insertProduct(ProductVO prd) {
		conn = DBconnect.getCon();
		sql = "insert into product values(?, ?, ?, ?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, prd.getItemNo());
			psmt.setString(2, prd.getItemName());
			psmt.setInt(3, prd.getPrice());
			psmt.setString(4, prd.getItemDesc());
			psmt.setDouble(5, prd.getLikeIt());
			psmt.setString(6, prd.getCategory());
			psmt.setString(7, prd.getItemImg());
			
			int r = psmt.executeUpdate();
			System.out.println(r + "건이 입력 되었습니다.");
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

	public List<ProductVO> getProductList() {
		conn = DBconnect.getCon();
		sql = "select * from product order by 1";
		
		/*
		 * rs.next()는 한 행을 읽을때, VO객체와 일치하는 컬럼의 값을 가져온다(rs.getString("item_no")).... while만족 할때까지 row를 읽어와 값을 List인  products 에 넣는다.
		 */
		List<ProductVO> products = new ArrayList<>(); //ProductVO의 객체를 리스트에 담는다.
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ProductVO prd = new ProductVO();
				prd.setItemNo(rs.getString("item_no"));
				prd.setItemName(rs.getString("item_name"));
				prd.setPrice(rs.getInt("price"));
				prd.setItemDesc(rs.getString("item_desc"));
				prd.setLikeIt(rs.getDouble("like_it"));
				prd.setCategory(rs.getString("category"));
				prd.setItemImg(rs.getString("item_img"));
				
				products.add(prd);
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

		return products;
	}
}
