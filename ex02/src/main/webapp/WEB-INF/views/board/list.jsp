<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Tables</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board List Page
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <!-- <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example"> -->
                <table width="100%" class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>#번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>수정일</th>
                        </tr>
                    </thead>
                    
                    <c:forEach var="board" items="${list}">
                    	<tr>
							<td><c:out value="${board.bno}" /></td>
                    		<td><c:out value="${board.title}" /></td>
                    		<td><c:out value="${board.writer}" /></td>
                    		<td><fmt:formatDate pattern="yyyy-MM-dd"  
                    						value="${board.regDate}"/></td>
                    		<td><fmt:formatDate pattern="yyyy-MM-dd" 
                    						value="${board.updateDate}"/></td>
                    	</tr>
                    </c:forEach>
                </table>
            </div>
            <!-- end panel-body -->
        </div>
        <!-- end panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
    
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal title</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      
      <!-- Modal body -->
      <div class="modal-body">
        처리가 완료되었습니다.
      </div>
      
      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save Changes</button>
      </div>
      
    </div>
  </div>
</div> <!-- end The Modal -->



<%@ include file="../includes/footer.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
	
		let result = "<c:out value='${result}' />";
		
		checkModal(result);
		
		function checkModal(result){
			if(result == '') return ;
			
			if(parseInt(result)>0){
				$(".modal-body").html("게시글 " + result + "번이 등록되었습니다.");
			}
			
			$("#myModal").modal("show");
		}
	});
</script>

    