<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<body>
	<!-- 부트스트랩 사용-->
	<!-- 전체를 감싼다. container-->
	<div class="container">
		<!-- 한개의 가로줄 영역을 차지한다. row, 자식 요소들을 중앙으로 배치하겠다.-->
		<div class="row justify-content-center">
			<!-- 12칸 중에서 반인 6칸을 차지한다.-->
			<div class="col-md-6">
				<!-- action 안넣어주면 현재위치가 url로 들어간다-->
				<form:form method="post" modelAttribute="memberVO">
					<!--아이디-->
				  	<div class="mb-3">
				    	<label for="username" class="form-label">ID</label>
				   		<!--  <input type="text"classs="form-control" id="username" name="username" aria-describedby="emailHelp"> -->
				  		<form:input cssClass="form-control" id="username" path="username"></form:input>
				  		<div>
				  			<form:errors path="username"></form:errors>
				  		</div>
				  	</div>
				  	<!--이름-->
				  	<div class="mb-3">
				    	<label for="name" class="form-label">NAME</label>
				    	<!-- <input type="text" class="form-control" id="name" name="name"> -->
				    	<form:input cssClass="form-control" id="name" path="name"></form:input>
					  	<div>
					  		<form:errors path="name"></form:errors>
					  	</div>
				  	</div>
				  	<!--이메일-->
				  	<div class="mb-3">
				    	<label for="email" class="form-label">EMAIL</label>
				    	<!-- <input type="text" class="form-control" id="email" name="email"> -->
				    	<form:input cssClass="form-control" id="email" path="email" aria-describedby="emailHelp"></form:input>
					  	<div>
					  		<form:errors path="email"></form:errors>
					  	</div>
				  	</div>
				  	<!--생일-->
				  	<div class="mb-3">
				    	<label for="birth" class="form-label">BIRTH</label>
				   		<!--<input type="date" class="form-control" id="birth" name="birth"> -->
				   		<form:input cssClass="form-control" id="birth" path="birth"></form:input>
					  	<div>
					  		<form:errors path="birth"></form:errors>
					  	</div>
				  	</div>
				  	<div class="mb-3 form-check">
				    	<input type="checkbox" class="form-check-input" id="exampleCheck1">
				    	<label class="form-check-label" for="exampleCheck1">Check me out</label>
				  	</div>
				  	<button type="submit" class="btn btn-primary">Submit</button>
				</form:form>
			</div>
		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>