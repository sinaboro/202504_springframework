<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h1>Upload with Ajax</h1>
	
	<div class='uploadDiv'>
		<input type="file" name="uploadFile" multiple="multiple">
	</div>
	
	<button id="uploadBtn">Upload</button>
	
<script type="text/javascript">
	$(document).ready(function(){
		$("#uploadBtn").on("click", function(e){
			let formData = new FormData();
			let inputFile = $("input[name='uploadFile']");
			
			let files = inputFile[0].files;
			console.log(inputFile)
			console.log("-------------------")
			console.log(files)
			
			for(let i=0; i<files.length; i++){
				formData.append("uploadFile", files[i]);
			}
			console.log("formData");
			console.log(formData);
			
			$.ajax({
				url: "/uploadAjaxAction",
				type: "post",
				processData: false,  //필수 - > 데이터를 문자열로 변환하지말라!
				contentType: false,  //contentType 설정하지않음( 자동으로 enctype="multipart/form-data")
				data: formData,
				
				success: function(result){
					alert(result);
				}
			});
		});	 
	});
</script>

</body>
</html>