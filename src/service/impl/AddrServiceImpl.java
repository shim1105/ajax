package service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.AddrDAO;
import dao.impl.AddrDAOImpl;
import service.AddrSerice;
import utils.Command;

public class AddrServiceImpl implements AddrSerice {
	private AddrDAO adao = new AddrDAOImpl();
 

	@Override
	public List<Map<String, String>> selectAddrList(HttpServletRequest request) {
		Map<String, String> paramMap = Command.getSingleMap(request);
		int page = 1;
		int pageCount = 10;
		int blockCount = 10;
		if (paramMap.get("page") != null) {
			page = Integer.parseInt(paramMap.get("page"));
		}
		if(paramMap.get("pageCount")!=null) {
			pageCount=Integer.parseInt(paramMap.get("pageCount"));
		}
		if(paramMap.get("blockCount")!=null) {
			blockCount=Integer.parseInt(paramMap.get("blockCount"));
		}
		request.setAttribute("ad_dong", paramMap.get("ad_dong"));
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("blockCount", blockCount);
		int lNum = page * pageCount;
		int sNum = lNum - (pageCount - 1);
		paramMap.put("sNum", sNum + "");
		paramMap.put("lNum", lNum + "");

		List<Map<String, String>> addrList = adao.selectAddrList(paramMap);
		request.setAttribute("list", addrList);
		int totalCnt = adao.selectTotalAddrCount(paramMap);
		request.setAttribute("totalCnt", totalCnt);
		int totalPageCnt = totalCnt / pageCount;
		if (totalCnt % pageCount > 0) {
			totalPageCnt++;
		}
		int fBlock;
		int lBlock;
		fBlock = (((page - 1) / blockCount) * blockCount) + 1; // 현제 페이지에서 -1하고 /카운터 하고 *카운터 +1 하면 초기값이 고정
		lBlock = fBlock + blockCount - 1; // 초기값에서 카운터를 더하고 -1 하면 끝값.
		if (lBlock > totalPageCnt) {
			lBlock = totalPageCnt;
		}
		request.setAttribute("fBlock", fBlock);
		request.setAttribute("lBlock", lBlock);
		request.setAttribute("totalPageCnt", totalPageCnt);
		return addrList;
	}

	@Override
	public int selectTotalAddrCount() {

		return 0;
//				adao.selectTotalAddrCount();
	}

}
