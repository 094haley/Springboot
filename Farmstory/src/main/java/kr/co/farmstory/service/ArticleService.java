package kr.co.farmstory.service;

import kr.co.farmstory.dao.ArticleDAO;
import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
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

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleDAO dao;

    @Transactional
    public int insertArticle(ArticleVO vo) {
        
        // 글 등록
        int result = dao.insertArticle(vo);
        
        // 파일 업로드
        FileVO fvo = fileUpload(vo);

        if(fvo != null){
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

    public ArticleVO selectArticle(int no) {
        return dao.selectArticle(no);
    }
    public List<ArticleVO> selectArticles(int start, String cate) {
        return dao.selectArticles(start, cate);
    }
    public int updateArticle(ArticleVO vo) {
        return dao.updateArticle(vo);
    }

    @Transactional
    public int deleteArticle(int no){

        // 파일 정보 가져오기
        ArticleVO vo = dao.selectArticle(no);
        String filename = vo.getFileVO().getNewName();

        // 글삭제
        int result = dao.deleteArticle(no);

        // 파일이 존재하면 파일삭제
        if(filename != null){

            // 파일 삭제(DB)
            dao.deleteFile(no);

            // 파일삭제(디렉터리)
            deleteFile(filename);

        }

        return result;
    }

    public int updateHit(int no) {
        return dao.updateHit(no);
    }

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    public FileVO fileUpload(ArticleVO vo) {
        
        // 첨부파일
        MultipartFile file = vo.getFname();
        FileVO fvo = null;

        if(!file.isEmpty()){
            // 시스템 경로
            String path = new File(uploadPath).getAbsolutePath();

            // 새 파일명 생성
            String oName = file.getOriginalFilename();
            String ext = oName.substring(oName.lastIndexOf("."));
            String nName = UUID.randomUUID().toString()+ext;

            // 파일 저장
            try {
                file.transferTo(new File(path, nName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            fvo = FileVO.builder()
                    .parent(vo.getNo())
                    .oriName(oName)
                    .newName(nName)
                    .build();

        }
        return fvo;
    }

    public ResponseEntity<Resource> fileDownload(FileVO vo) throws IOException{

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
    public int getTotalCount(String cate) {
        return dao.selectCountTotal(cate);
    }

    // 마지막 페이지 번호
    public int getLastPageNum(int total) {

        int lastPage = 0;

        if(total % 10 == 0) {
            lastPage = (total / 10);
        }else {
            lastPage = (total / 10) + 1;
        }

        if(lastPage == 0){
            lastPage = 1;
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
