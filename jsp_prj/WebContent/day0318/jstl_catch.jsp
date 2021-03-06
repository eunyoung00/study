<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="예외 처리"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/jsp_prj/common/main_v190130.css"/>
<style type="text/css">
#wrap{margin:0px auto;width:800px; height:860px;}
#header{width:800px; height:140px; background:#FFFFFF url(http://localhost:8080/jsp_prj/common/images/header_bg.png) repeat-x;
		position: relative; }
#headerTitle{font-family: HY견고딕,고딕; font-size:35px; font-weight:bold; text-align:center; 
			/* padding-top: 35px */ position:absolute; top:40px; left:290px; }
#container{width:800px; height:600px; }
#footer{width:800px; height:120px; }
#footerTitle{float: right; font-size:15px; padding-top:20px; padding-right:20px; }
</style>
</head>
<body>
<div id="wrap">
	<div id="header">
		<div id="headerTitle">SIST Class4</div>
	</div>
	<div id="container">
	
	<c:catch var="e">
	<%
	/* try{ */
	//코드 생성
	Class.forName("Test");
	/* forName은 컴파일예외로 try catch를 해주어야 하는데,, 이미 해주고 있기 때문에 별도의 try~안해주어도 되는것. */
	//예외를 throws하는 method를 호출하더라도 _jspService()가 코드를 try~catch로 감싸고 있기 때문에 코드 에러가 발생하지 않는다.
/* 	}catch(ClassNotFoundException cnfe){
		out.print("죄송합니다");
	}//end catch -하지 않으면 500이 떠버린다. */
	%>
	<!-- jsp는 자바가 아니다.....훨신 편함.. -->
	</c:catch>
	<c:if test="${not empty e}"><%-- e ne null --%>
	서비스 도중 문제가 발생하였습니다.<br/>
	${e}
	</c:if>
	<%--
 	<%! 
	public void test(){	
		 Class.forName(""); //여기서는 예외처리하라고 빨간줄이뜬다.//try~catch 필수
	}
	%> --%>


	</div>
	<div id="footer">
		<div id="footerTitle">copyright&copy; all right reserved. class 4.</div>
	</div>
</div>
</body>
</html>