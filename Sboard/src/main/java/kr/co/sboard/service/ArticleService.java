package kr.co.sboard.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.sboard.dao.ArticleDAO;
import kr.co.sboard.repository.ArticleRepo;
import kr.co.sboard.vo.ArticleVO;
import kr.co.sboard.vo.FileVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleService {
	
	@Autowired
	private ArticleDAO dao;
	
	@Autowired
	private ArticleRepo repo;
	
	@Transactional
	public int insertArticle(ArticleVO vo) {
		
		// 글 등록
		int result = dao.insertArticle(vo);
		
		// 파일 업로드
		FileVO fvo = fileUpload(vo);
		
		if(fvo != null) {
			// 파일 등록
			dao.insertFile(fvo);
		}
		
		return result;
	}
	
	@Transactional
	public FileVO selectFile(int fno) {
		FileVO vo = dao.selectFile(fno);
		dao.updateDownload(fno);
		return vo;
	}
	
	@Transactional
	public ArticleVO selectArticle(int no) {
		ArticleVO vo = dao.selectArticle(no);
		dao.updateHit(no);
		return vo;
	}
	
	public List<ArticleVO> selectArticles(int start) {
		return dao.selectArticles(start);
	}
	
	public int updateArticle(ArticleVO vo) {
		return dao.updateArticle(vo);
	}
	
	@Transactional
	public int deleteArticle(int no) {
		
		// 파일 정보가져오기
		ArticleVO vo = dao.selectArticle(no);
		String filename = vo.getFileVO().getNewName();
		
		// 글 삭제
		int result = dao.deleteArticle(no);
		
		// 파일이 존재하면 파일삭제
		if(filename != null) {
			
			// 파일 삭제(DB)
			dao.deleteFile(no);
			
			// 파일삭제(디렉터리)
			deleteFile(filename);
			
		}
				
		return result;
	}
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	public FileVO fileUpload(ArticleVO vo) {
		// 첨부 파일
		MultipartFile file = vo.getFname();
		FileVO fvo = null;
		
		if(!file.isEmpty()) {
			// 시스템 경로
			String path = new File(uploadPath).getAbsolutePath();
			
			// 새 파일명 생성
			String oName = file.getOriginalFilename();
			String ext = oName.substring(oName.lastIndexOf("."));
			String nName = UUID.randomUUID().toString()+ext;
			
			// 파일 저장
			try {
				file.transferTo(new File(path, nName));
			} catch (IllegalStateException e) {
				log.error(e.getMessage());
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			
			fvo = FileVO.builder()
					.parent(vo.getNo())
					.oriName(oName)
					.newName(nName)
					.build();
		}
		
		return fvo;
	}
	
	public ResponseEntity<Resource> fileDownload(FileVO vo) throws IOException {
		
		Path path = Paths.get(uploadPath+vo.getNewName());
		String contentType = Files.probeContentType(path);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition
										.builder("attachment")
										.filename(vo.getOriName(), StandardCharsets.UTF_8)
										.build());;
		
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
	public void deleteFile(String filename) {

		//파일 경로 지정
		String path = new File(uploadPath).getAbsolutePath();
				
		//현재 게시판에 존재하는 파일객체를 만듬
		File file = new File(path, filename);
				
		if(file.exists()) { 
			file.delete();
		}
	}
	
	
	// 페이지 시작값
	public int getLimitStart(int currentPage) {
		return (currentPage - 1) * 10;
	}
	
	// 현재 페이지
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	// 전체 게시물 갯수
	public int getTotalCount() {
		return dao.selectCountTotal();
	}

	// 마지막 페이지 번호
	public int getLastPageNum(int total) {
		
		int lastPage = 0;
		
		if(total % 10 == 0) {
			lastPage = (total / 10);
		}else {
			lastPage = (total / 10) + 1;
		}
		
		return lastPage;
	}
	
	// 페이지 시작 번호
	public int getPageStartNum(int total, int start) {
		return total - start;
	}
	
	// 페이지 그룹
	public int[] getPageGroup(int currentPage, int lastPage) {
		
		int groupCurrent = (int) Math.ceil(currentPage / 10.0);
		int groupStart = (groupCurrent - 1) * 10 + 1;
		int groupEnd = groupCurrent * 10;
		
		if(groupEnd > lastPage) {
			groupEnd = lastPage;
		}
		
		int[] group = {groupStart, groupEnd};
		
		return group;
	}
}
