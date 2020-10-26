package kr.co.unnij.file.dao;

import java.util.List;
import java.util.Map;

import kr.co.unnij.file.model.FileItem;

public interface FileItemDAO {
	
	//첨부파일에 등록(테이블에 저장)
	public int insertFileItem(FileItem fileItem) throws Exception;
	
	//첨부파일 목록 조회
	public List<FileItem> selectFileItemList(Map<String, Object> paramMap) throws Exception;
	
	//첨부파일 1건 조회(다운로드)
	
	//첨부파일 삭제
	
}
