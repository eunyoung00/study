<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    info="브라우저 출력을 목적으로 만들어진 EL사용-EL사용법" 
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
	<div>
		<strong>연산자</strong>
		<div>
		<ul>
			<li>산술</li>
			<li>3+14=${3+14}</li>
			<li>14-3=${14-3}</li>
			<li>3*14=${3*14}</li>
			<li>14/3=${14/3}</li>
			<li>14%3=${14%3} / ${14 mod 3}</li>
			<li>관계</li>
			<li>14>3=${14>3} / ${14 gt 3}</li>
			<li>14<3=${14<3} / ${14 lt 3}</li>
			<li>14>=3=${14>=3} / ${14 ge 3}</li>
			<li>14<=3=${14<=3} / ${14 le 3}</li>
			<li>14==3=${14==3} / ${14 eq 3}</li>
			<li>14!=3=${14!=3} / <%-- ${14 ne 3} 되는데 빨간줄-- --%></li>
			<li>논리</li>
			<li>14>3 && 3<14 =${14>3 && 3<14} /	${14 gt 3 and 3 lt 14}</li>
			<li>14<3 || 3>14 =${14<3 || 3>14} /	${14 lt 3 or 3 gt 14}</li>
			<li>삼항</li>
			<li>10은 ${10%2==0?"짝수":"홀수"} / ${10 mod 2 eq 0?"짝수":"홀수" }</li>
			<li>9는 ${9%2==0?"짝수":"홀수"} / ${9 mod 2 eq 0?"짝수":"홀수" }</li>
			<%--산술,관계,논리,삼항 이외에는 사용할 수 없다.Error발생! <li>${1<<2 }</li> --%>
		</ul>
		</div>
		<div>
			<strong>EL을 사용하여 Parameter 처리(param 객체 사용)</strong>
			<ul>
				<li>이름 : <strong>
				<!-- empty : 대상이 null이거나 ''인경우 비교할때 사용.(param.name==null) -->
				<c:out value="${empty param.name ?'아래 링크를 클릭하거나 폼에 입력':param.name}"/></strong></li>
				<li>나이 : <strong>${param.age}</strong></li>
				<!-- EL에는 취약점이 존재하는데 출력은 잘 해주나, cross site script라는 취약점을 가짐.. -->
				<!-- EL을 사용하여 출력하면 XSS취약점을 가지게 된다
				XSS(cross site script) : 상위 취약점으로 악의적인 목적의 사용자가 HTML Form Control이나
				QueryString에 JavaScript Code를 작성하여 서버에서 실행 시키기 위한 취약점. 
				<script type="text/javascript"> alert("zzzzz");</script>으로 탈취가능해버림..
				크롬은 막혀있으나 익스는 뚫린다!  =>  <c:out value=""/>로 막음!
				JSTL의 c:out을 사용하면 XSS가 실행되지 않고 그대로 보여진다.(XSS 취약점 막기)
				-->
			</ul>
			<%
				String name="김희철";
				//scope객체에 할당한 값은 EL에서 사용할 수 있다.
				pageContext.setAttribute("pageName", name);//pageScope
				request.setAttribute("reqName", name);//requestScope
				session.setAttribute("sesName", name);//sessionScope
				application.setAttribute("appName", name);//applicationScope
			%>
			<ul>
				<li><strong>scope객체의 값 사용</strong></li>
				<li>변수의 직접 사용(사용불가) :${name}</li>
				<li>pageScope 사용 :${pageScope.pageName} / ${pageName}</li>
				<li>requestScope 사용 :${requestScope.reqName} / ${reqName}</li>
				<li>sessionScope 사용 :${sessionScope.sesName} / ${sesName}</li>
				<li>applicationScope 사용 :${applicationScope.appName} / ${appName}</li>
			</ul>
		</div>
		
	</div>
	<a href="use_el.jsp?name=jungyun&age=30">파라메터 연습</a><br/>
	<form action ="use_el.jsp" method="get">
		<label>이름 :</label>
		<input type="text" name="name" class="inputBox"><br/>
		<label>나이 :</label>
		<input type="text" name="age" class="inputBox"><br/>
		<input type="submit" value="입력" class="btn">
	</form>
	</div>
	
	<div id="footer">
		<div id="footerTitle">copyright&copy; all right reserved. class 4.</div>
	</div>
</div>

</body>
</html>