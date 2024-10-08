<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<!--로그인 실패 시 메세지 -->
				<div>
					<%-- ${param.message} --%>
					${message}
				</div>
				<!-- action 안넣어주면 현재위치가 url로 들어간다-->
				<form method="post">
					<!--아이디-->
					<div class="mb-3">
				    	<label for="username" class="form-label">ID</label>
				    	<input type="text" class="form-control" id="username" name="username" aria-describedby="emailHelp">
				  	</div>
				  	<div class="mb-3">
				    	<label for="password" class="form-label">Password</label>
				    	<input type="password" class="form-control" id="password" name="password">
				 	 </div>
				 	 <!--자동로그인 설정-->
				  	<div class="mb-3 form-check">
				    	<input name="rememberMe" type="checkbox" class="form-check-input" id="exampleCheck1">
				    	<label class="form-check-label" for="exampleCheck1">Check me out</label>
				 	</div>
				  	<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>