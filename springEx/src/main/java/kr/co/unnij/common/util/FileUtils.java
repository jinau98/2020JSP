package kr.co.unnij.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.unnij.board.model.BoardVO;
import kr.co.unnij.file.model.FileItem;

@Component("fileUtils")
public class FileUtils {
	  public static final String filePath = "C:\\Users\\HIT\\Desktop\\SpringUpload\\upload"; //서버가 분리되어 있다면 서버경로도 입력
	   
	   private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	   
	   //파일 업로드 메소드
	   public List<FileItem> uploadFiles(BoardVO board, MultipartHttpServletRequest mReq) throws Exception{
	      
	      List<FileItem> fileList = new ArrayList<FileItem>();
	      
	      List<MultipartFile> mfList = mReq.getFiles("uploadFiles");
	      
	      File file = null;
	      
	      for(MultipartFile parts : mfList) {
	         if(parts.isEmpty() == false) {
	            FileItem fileItem = new FileItem();
	            fileItem.setRef_seq_no(board.getBo_seq_no()); //Ref_seq_no = bo_seq_no
	            fileItem.setReg_user(board.getBo_writer()); //Ref_seq_no = bo_seq_no
	            fileItem.setBiz_type(board.getBo_type()); //Ref_seq_no = bo_seq_no
	            fileItem.setFile_fancy_size(getFancySize(parts.getSize())); //파일사이즈 지정
	            
	            fileItem.setFile_name(parts.getOriginalFilename());//파일의 실제 이름
	            fileItem.setFile_save_name(UUID.randomUUID().toString() + "_" + parts.getOriginalFilename());//실제 저장된 파일이름
	            fileItem.setFile_path(board.getBo_type() + "/" + dateFormat.format(new Date()));
	            
	            
	            try {
	            	file = new File(filePath + "/" + fileItem.getFile_path() + "/" + fileItem.getFile_save_name());
	            	if(file.exists() == false) {
	            		file.mkdirs();
	                     }
	            	parts.transferTo(file);
	            	
	            	String ext=parts.getOriginalFilename().substring(parts.getOriginalFilename().lastIndexOf(".")+1);
	            	
	            	if(MediaUtils.getMediaType(ext) != null && "GALLERY".equals(fileItem.getBiz_type())) {
	                    String thumbSaveName = createThumbnail(fileItem.getFile_path(), fileItem.getFile_save_name(), ext);
	                    fileItem.setThumb_save_name(thumbSaveName);
	                 }
	            	 
	            }catch(Exception e) {
	            	e.printStackTrace();
	            }
	            
	            fileList.add(fileItem);
	            
	         }
	      }
	      
	      return fileList;
	   }
	   //파일 사이즈 연산 메소드
	   public static String getFancySize(long size) {
	      String fancy = "";
	      DecimalFormat decimalFormat = new DecimalFormat();
	      if(size < 1024) { 
	         fancy = decimalFormat.format(size) + "bytes";
	      }else if(size < (1024*1024)) {
	         fancy = decimalFormat.format(size / 1024.0) + "Kb";
	      }else {
	         fancy = decimalFormat.format(size / (1024.0 * 1024.0)) + "Mb";
	      }
	      return fancy;
	   }
	   
	   private String createThumbnail(String path, String fileName, String ext) {
		   BufferedImage sourceImg;
		   String thumbnailName="";
		   
		   try {
			   sourceImg = ImageIO.read(new File(filePath + File.separator + path, fileName));
			   
			   BufferedImage destImg = Scalr.resize(sourceImg,  Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 150);
			   thumbnailName = filePath + File.separator + path + File.separator + "thumb_" + fileName;
			   
			   File file = new File(thumbnailName);
			   ImageIO.write(destImg, ext, file);
		   }catch(IOException e) {
			   e.printStackTrace();
		   }
		   return "thumb_" + fileName;
	   }
	   

}
