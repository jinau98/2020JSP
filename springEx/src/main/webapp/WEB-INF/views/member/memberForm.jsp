<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
<script>
var duplicateCheck = false;				//중복체크 필드

function doSubmit(type){
	if(type ==2 ){
		duplicateCheck = true;
	}
	
	if(!validate()){
		return false;
	}
	
	var frm=document.memberForm;
	
	if(type==1){
		frm.action="memberInsert";
	}else{
		frm.action="memberUpdate";
	}
	
	frm.submit();
}

function validate(){
	var frm =document.memberForm;
	
	if(!duplicateCheck){
		alert("아이디 중복체크를 해주세요");
		return false;
	}
	
	if(frm.mem_name.value ==""){
		alert("이름을 입력하세요.");
		frm.mem_name.focus();
		return false;
	}
	
	if (frm.mem_pwd.value == "") {
		alert("비밀번호를 입력하세요");
		frm.mem_pwd.focus();
		return false;
	} else {
		if (frm.mem_pwd_confirm.value == "") {
			alert("비밀번호를 한 번 더 입력해주세요.");
			frm.mem_pwd_confirm.focus();
			return false;
		} else {
			if (frm.mem_pwd.value != frm.mem_pwd_confirm.value) {
				alert("비밀번호가 맞지 않습니다.");
				return false
			}
		}
	}
	if (frm.mem_birth.value == "") {
		alert("전화번호를 입력하세요");
		frm.mem_birth.focus();
		return false;
	}
	if (frm.mem_phone.value == "") {
		alert("전화번호를 입력하세요");
		frm.mem_phone.focus();
		return false;
	}
	if (frm.mem_email.value == "") {
		alert("이메일을 입력하세요");
		frm.mem_email.focus();
		return false;
	}
	return true;
}
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function execDaumPostcode(){
	new daum.Postcode({
		oncomplete: function(data){
			//팝업에서 검색결과 항목 클릭했을 때 실행할 코드
			
			//각 주소의 노출 규칙에 따라 주소를 조합
			//내려오는 변수가 값이 없는 경우엔 공백('') 값을 가지므로 이를 참고하여 분기.
			var fullAddr = ''; //최종 주소 변수
			var extraAddr='';	//조합형 주소 변수
			
			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져옴
			if(data.userSelectedtype == 'R'){ //사용자가 도로명 주소를 선택했을 경우
				fullAddr = data.roadAddress;
			} else{				//사용자가 지번 주소를 선택했을 때
				fullAddr = data.jibunAddress;
			}
			
			//사용자가 선택한 주소가 도로명 타입일 때 조합
			if(data.userSelectedType ==='R'){
				//법정동명이 있을 경우 추가
				if(data.bname !== ''){
					extraAddr += data.bname;
				}
				if(data.buildingName !== ''){
					extraAddr += (extraAddr !== ''? ',' + data.buildingName : data.buildingName);
				}
				//조합형 주소의 유무에 따라 양쪽 괄호 추가하여 최종 주소 만듦
				fullAddr += (extraAddr !== ''?' ('+ extraAddr + ')' : '');
			}
			
			//우편번호와 주소 정보를 해당 필드에 넣음
			document.getElementById('mem_zipcode').value = data.zonecode;
			document.getElementById('mem_addr_master').value = fullAddr;
			
			//커서를 상세주소 필드로 이동
			document.getElementById('mem_addr_detail').focus();
		}
	}).open();
}
</script>
</head>
<body>
	<div>
		<form name="memberForm" method="post">
			<input type="hidden" value="" name="mem_seq_no" />
			<table class="table table-bordered">
				<tr>
					<td>이름</td>
					<td><input type="text" name="mem_name" size="20"
						value="${member.mem_name }" /></td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="mem_id" size="20"
						value="${member.mem_id == null ? '': 'readonly'}"> <c:if
							test="${member.mem_id ==null }">
							<button type="button" class="btn btn-default" id="btn_idCheck">ID
								중복검사</button>
				8~20자리 숫자와 영문자 조합<br>
							<label id="lbl_result"></label>
						</c:if></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="mem_pwd" size="20">8~20
						자리의 숫자와 영문자 조합</td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" name="mem_pwd_confirm" size="20" />8~20
						자리의 숫자와 영문 조합</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="text" name="mem_birth" size="20"
						value="${member.mem_birth }" /></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="mem_phone" size="20"
						value="${member.mem_phone }" /></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="mem_email" size="20"
						value="${member.mem_email }" /></td>
				</tr>
				<tr>
					<td>주소</td>
					<td>
						<p>
							<input type="text" name="mem_zipcode" id="mem_zipcode" size="5"
								value="${member.mem_zipcode }" readonly="readonly" />
						</p>
						<p>
							<button type="button" class="btn btn-default"
								onclick="execDaumPostcode();">우편번호 검색</button>
						</p>
						<p>
					<td><input type="text" name="mem_addr_master" size="50"
						value="${member.mem_addr_master}" readonly="readonly" />
					</p>
					<td><input type="text" name="mem_addr_detail" size="50"
						value="${member.mem_addr_detail}" /></td>
					</td>
				</tr>
				<tr>
					<td colsapn="2"><c:if test="${empty member.mem_id }">
							<input type="button" value="가입하기" class="btn btn-default"
								onclick="doSubmit(1);">
						</c:if> <c:if test="${not empty member.mem_id }">
							<input type="button" value="수정하기" class="btn btn-default"
								onclick="doSubmit(2);">
						</c:if> <input type="reset" value="취소" class="btn btn-default"> <input
						type="button" value="목록" class="btn btn-default"
						onclick="location.href='memberList'"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>