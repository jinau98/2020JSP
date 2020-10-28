<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">

   $(document).ready(function(){
      
      $('#bo_title').focus();
      
      $('#btnGoList').click(function(){
         location.href="noticeList?bo_type=NOTICE"
      });
      
      $('#noticeForm').validate({
         rules : {
            bo_title : { required : true },
            bo_content :  "required" 
         },
         messages : {
            bo_title : "제목을 입력해주세요",
            bo_content : "내용을 입력해주세요"
         },
         submitHandler : function(frm){
       		 console.log(checkFiles());
        	 if(!checkFiles()){
        		 return false;
        	 }else{
        	 //유효성체크 후 실행되는 부분
            doSubmit();
         }
      }
   });
      
      $frm = $("#noticeForm");
      //파일 추가
      $(".btn-new-file", $frm).click(function(){
    	  $("#fileList").append(
    		'<div>' + 
    		'<input type="file" name="uploadFiles" id="uploadFiles" multiple="multiple" style="display:inline-block;">'+
    		'<button type="button" class="btn btn-primary btn-xs btn-delete-file" >x</button>'+
    		'</div>'
    	  );
      });
      
      $('#fileList').on("click", ".btn-delete-file", function(){
    	 $(this).parent().remove();
      });
      
      $(".btn-delete-exist").click(function(){
    	 $(this).parent().html('<input type="hidden" name="delFileSeq" value="' + $(this).data("file_seq_no") + '">'); 
      });
   
    function checkFiles(){
    	var regex = new RegExp("(.*?)\.(exe|sh|alz|zip)$");
    	var maxSize = 10485760;
    	var files = $("input[name='uploadFiles']")[0].files;
    	
    	for(var i=0; i<files.length; i++){
    		var fileName = files[i].name;
    		var fileSize = files[i].size;
    		var ext = fileName.split(".").pop().toLowerCase();
    		
    		console.log("fileName : " + fileName);
    		console.log("fileSize : " + fileSize);
    		console.log("ext : " + ext);
    		
    		if(fileSize >= maxSize){
    			alert("파일은 10mb를 초과할 수 없습니다.");
    			return false;
    		}
    		
    		if(regex.test(fileName)){
    			alert("해당 확장자는 업로드할 수 없습니다.");
    			return false;
    		}
    		
    		if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1){
    			alert("gif, png, jpg, jpeg만 업로드할 수 있습니다.");
    			return false;
    		}
    	}
    	return true;
    	}
    });
      
   function doSubmit(){
      var frm = document.noticeForm;
      <c:if test = "${board.bo_seq_no == 0 }">
         frm.action = "noticeInsert";
      </c:if>
      <c:if test = "${board.bo_seq_no != 0 }">
         frm.action = "noticeUpdate";
      </c:if>
      frm.submit();
   }
</script>


<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h2 align="center">공지사항 등록</h2>
		 <form action="" method="post" name="noticeForm" id="noticeForm" enctype="multipart/form-data">
         <input type="hidden" name="bo_type" value="NOTICE">
         <input type="hidden" name="bo_seq_no" id="bo_seq_no" value="${board.bo_seq_no}">
         
			<table>
				<tr>
					<td>제목</td>
					<td><input type="text" name="bo_title" id="bo_title" size="100"  value="${board.bo_title}"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="hidden" name="bo_writer" id="bo_writer" size="20" value="${board.bo_writer }"/> 
					<input type="text" name="bo_writer_name" id="bo_writer_name"  value="${board.bo_writer_name}" readonly="readonly" />
					</td>
				</tr>
				<tr>
				<td>첨부파일</td>
				<td><p><button type = "button" class="btn btn-primary btn-xs btn-new-file">추가</button></p>
				<div id="fileList">
				<c:forEach var="fileItem" items="${board.fileList }">
					<div>
						<a href="${pageContext.request.contextPath}/common/downnload?file_seq_no=${fileItem.file_seq_no}">${fileItem.file_name }</a>${fileItem.file_fancy_size }
						<button type="button" class="btn btn-danger btn-xs btn-delete-exist" data-file_seq_no="${fileItem.file_seq_no }">x</button>
					</div>
				</c:forEach>
					<div>
						<input type="file" name="uploadFiles" id="uploadFiles" multiple="multiple" style="display:inline-block;" accept=".gif, .jpg, .png">
						<button type="button" class="btn btn-primary btn-xs btn-delete-file">x</button>
					</div>
				</div>
				</td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="bo_content" id="bo_content" rows="15" cols="100">${board.bo_content}</textarea>
					</td>
				</tr>
			</table>
			<p align="center">
				<input type="submit" value="저장" class="btn btn-primary"/>
				<input type="reset" value="취소" class="btn btn-primary"/>
				<input type="button" id="btnGoList" value="목록" class="btn btn-primary"/>
			</p>
		</form>
	</div>
</body>
</html>