package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import dao.AddrDAO;
import dao.impl.AddrDAOImpl;

public class AddrDAOTest {
	AddrDAO adao = new AddrDAOImpl();
	@Test
	public void test() {
		Map<String,String> addr = new HashMap<>();
		addr.put("sNum", "11");
		addr.put("lNum", "20");
		List<Map<String,String>> addrList=adao.selectAddrList(addr);
		assertEquals(10, addrList.size());   // assertEquals는 <> 같다 '이것은 이것이 거야' 라는 것으로 이해하면 쉬움 .  
	}
//	@Test
//	public void addrCountTest() {
//		int totalCnt = adao.selectTotalAddrCount();
//		assertEquals(358425, totalCnt);
//	}

}