package kr.co.unnij.common.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.unnij.common.service.CommonService;
import kr.co.unnij.file.dao.FileItemDAO;
import kr.co.unnij.file.model.FileItem;

@Service
public class CommonServiceImpl implements CommonService{
	@Autowired
	FileItemDAO fileItemDao;

	@Override
	public FileItem getFileItem(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return fileItemDao.selectFileItem(paramMap);
	}
}
