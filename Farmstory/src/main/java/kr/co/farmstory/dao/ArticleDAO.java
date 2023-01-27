package kr.co.farmstory.dao;

import java.util.List;

import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ArticleDAO {
	
	public int insertArticle(ArticleVO vo);
	public int insertFile(FileVO vo);
	public FileVO selectFile(int fno);
	public int selectCountTotal(String cate);
	public ArticleVO selectArticle(int no);
	public List<ArticleVO> selectArticles(@Param("start") int start, @Param("cate") String cate);
	public int updateArticle(ArticleVO vo);
	public int updateDownload(int fno);
	public int updateHit(int no);
	public int deleteArticle(int no);
	public int deleteFile(int no);

}
