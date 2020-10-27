package kr.co.unnij.common.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.unnij.common.service.CommonService;
import kr.co.unnij.common.util.FileUtils;
import kr.co.unnij.file.model.FileItem;

@Controller
public class CommonController {
	@Autowired
	CommonService commonService;
	
	@RequestMapping(value="/common/download")
	public void fileDownload(
			@RequestParam(value="file_seq_no", required = true) String fileSeqNo
			, HttpServletResponse response
			) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("file_seq_no", fileSeqNo);
		
		FileItem fileItem = commonService.getFileItem(paramMap);
		
		if(fileItem == null) {
			throw new RuntimeException("첨부파일이 존재하지 않습니다.");
		}
		
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(
				new File(FileUtils.filePath + "/" 
						+ fileItem.getFile_path() + "/"
						+ fileItem.getFile_save_name()));
								
				response.setContentType("application/octet-stream)");
				response.setContentLength(fileByte.length);
				response.setHeader("Content-Disposition", "attachment; fileName = \"" + URLEncoder.encode(fileItem.getFile_name(), "UTF-8"));
				response.getOutputStream().write(fileByte);
			    response.getOutputStream().flush();
			    response.getOutputStream().close();
		
	}
}
