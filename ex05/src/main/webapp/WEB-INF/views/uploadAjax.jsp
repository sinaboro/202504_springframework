<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<style>
	.uploadResult{
		width: 100%;
		background-color: gray;
	}	
	
	.uploadResult ul{
		display: flex;
		flex-flow: row;
		justify-content: center;
		align-items: center;
	}
	
	.uploadResult ul li{
		list-style: none;
		padding: 10px;
	}
	
	.uploadResult ul li img{
		width: 20px;
	}
</style>

</head>
<body>
	<h1>Upload with Ajax</h1>
	
	<div class='uploadDiv'>
		<input type="file" name="uploadFile" multiple="multiple">
	</div>
	
	<div class="uploadResult">
		<ul>
		</ul>
	</div>
	
	<button id="uploadBtn">Upload</button>



	
<script type="text/javascript">
	$(document).ready(function(){
		
		let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$")
		let maxSize = 5242880; //5MB
		
		function checkExtension(fileName, fileSize){
			if(fileSize >= maxSize){
				alert("파일 사이즈 초과");
				return false;
			}
			
			if(regex.test(fileName)){
				alert("해당 종류의 파일은 업로드 할 수 없습니다.");
				return false;
			}
			
			return true;
		}
		
		
		let uploadResult = $(".uploadResult ul");
		
		function showUploadFile(uploadResultArr){
			
			let str = "";
			
			$(uploadResultArr).each(function(i, obj){
				
				if(!obj.image){
					str += "<li><img src='/resources/img/attach.png'>" 
							+ obj.fileName + "</li>";
				}else{
					str += "<li>" + obj.fileName + "</li>"
				}
			});
			
			uploadResult.append(str);
			
		}; //end showUploadFile
		
		
		let cloneObj = $(".uploadDiv").clone();
		
		$("#uploadBtn").on("click", function(e){
			let formData = new FormData();
			let inputFile = $("input[name='uploadFile']");
			
			let files = inputFile[0].files;
			/* console.log(inputFile)
			console.log("-------------------")
			console.log(files) */
			
			for(let i=0; i<files.length; i++){
				
				if(!checkExtension(files[i].name, files[i].size)){
					return false;
				}
					
				formData.append("uploadFile", files[i]);
			}
			
			$.ajax({
				url: "/uploadAjaxAction",
				type: "post",
				processData: false,  //필수 - > 데이터를 문자열로 변환하지말라!
				contentType: false,  //contentType 설정하지않음( 자동으로 enctype="multipart/form-data")
				data: formData,
				dataType: 'json',  //전달데이타가 형식이 json
				
				success: function(result){
					console.log(result);
					
					showUploadFile(result);
					$(".uploadDiv").html(cloneObj.html());
				}
			});
		});	 
	});
</script>

</body>
</html>