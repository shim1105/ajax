package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.AddrDAO;
import db.DBCon;

public class AddrDAOImpl implements AddrDAO {
	private static String selectAddrList= " select * from " + 
			" (select rownum as rown, addr.* from " + 
			" (select * from ADDRESS $where$ ORDER by ad_num) ADDR " + 
			" where rownum <=?) " + 
			" where rown >=?";
	private static String selectAddrCount="select count(1) from address $where$";
	@Override
	public List<Map<String, String>> selectAddrList(Map<String, String> addr) {
		String adDong = addr.get("ad_dong");
		String sql= selectAddrList.replace("$where$", "");
		try {
			if(!"".equals(adDong) && adDong != null) {
				sql = selectAddrList.replace("$where$"," where ad_dong like '%' || ? || '%'" );
			}
			int cnt =1;
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			if(!"".equals(adDong) && adDong != null) {
				ps.setString(cnt++, adDong);
			}
			ps.setString(cnt++, addr.get("lNum"));
			ps.setString(cnt, addr.get("sNum"));
			
			ResultSet rs = ps.executeQuery();
			List<Map<String,String>> addrList = new ArrayList<>();
			while(rs.next()) {
				Map<String,String> address= new HashMap<>();
				address.put("ad_num", rs.getString("ad_num"));
				address.put("ad_sido", rs.getString("ad_sido"));
				address.put("ad_gugun", rs.getString("ad_gugun"));
				address.put("ad_dong", rs.getString("ad_dong"));
				address.put("ad_lee", rs.getString("ad_lee"));
				address.put("ad_bunji", rs.getString("ad_bunji"));
				address.put("ad_ho", rs.getString("ad_ho"));
				addrList.add(address);
			}
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return null;
	}
	@Override
	public int selectTotalAddrCount(Map<String, String> addr) {
		String adDong = addr.get("ad_dong");
		String sql = selectAddrCount.replace("$where$", "");
		try {
			if(!"".equals(adDong) && adDong != null) {
				sql = selectAddrCount.replace("$where$", " where ad_dong like '%' || ? || '%'"); 
			}
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			if(!"".equals(adDong) && adDong != null) {
				ps.setString(1, adDong);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1); // 첫번째거 가져 오라는 것임 .				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		
		return 0;
	}

}
