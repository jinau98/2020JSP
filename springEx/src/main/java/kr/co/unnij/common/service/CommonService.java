package kr.co.unnij.common.service;

import java.util.Map;

import kr.co.unnij.file.model.FileItem;

public interface CommonService {
	public FileItem getFileItem(Map<String, Object> paramMap) throws Exception;
}
