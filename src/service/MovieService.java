package service;

import java.util.List;
import java.util.Map;

public interface MovieService {
	public List<Map<String,String>> selectMovieList();
	public int insertMoive(Map<String,String> movie);
	public Map<String,String> selectMovieByMiNum(int miNum);
	public int deleteMove(int miNum);
}
