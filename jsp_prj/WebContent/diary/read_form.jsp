<%@page import="java.io.IOException"%>
<%@page import="kr.co.sist.diary.vo.DiaryDetailVO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="kr.co.sist.diary.dao.DiaryDAO"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="readFrm">
<%
	DiaryDAO d_dao=DiaryDAO.getInstance();
	try{
		int num=Integer.parseInt(request.getParameter("num"));
		DiaryDetailVO dd_vo=d_dao.selectDetailEvent(num);
%>
<form action="diary.jsp" method="post" name="readFrm">
<input type="hidden" name="pageFlag"/>
<input type="hidden" name="num" value="${param.num}"/><!-- 글삭제시 중요 -->
<input type="hidden" name="param_year" value="${param.param_year}"/><!-- 이동시 년,월 고정 -->
<input type="hidden" name="param_month" value="${param.param_month}"/>
<table id="readTab">
<tr>
	<th colspan="2" style="text-align: center;">
		<span style="font-size: 20px">이벤트 읽기</span>
		<span style="float: right; padding-right: 5px;">
			<a href="#void" id="btnCloseFrm"><img src="images/btn_close.png"/></a>
		</span>
	</th>
</tr>
<tr>
	<td style="width:80px">제목</td>
	<td style="width:400px">
		<div id="subject"><strong><%=dd_vo.getSubject()%></strong></div>
	</td>
</tr>
<tr>
	<td style="width:80px">내용</td>
	<td style="width:400px">
		<textarea name="contents" id="summernote"><%=dd_vo.getContents()%></textarea>
	</td>
</tr>
<tr>
	<td style="width:80px">이벤트 일</td>
	<td style="width:400px">
		<div id="evtDate">"${param.param_year}-${param.param_month}-${param.param_day}"</div>
	</td>
</tr>
<tr>
	<td style="width:80px">작성자</td>
	<td style="width:400px">
		<div id="writer"><strong><%=dd_vo.getWriter()%></strong></div>
	</td>
</tr>
<tr>
	<td style="width:80px">비밀번호</td>
	<td style="width:400px">
		<input type="password" name="pass" class="inputBox" style="width:200px" id="pass"/>
	</td>
</tr>
<tr>
	<td style="width:80px">작성일</td>
	<td style="width:400px">
	<div id="wDate"><strong><%=dd_vo.getW_date()%></strong></div>
	</td>
</tr>
<tr>
	<td style="width:80px">작성ip</td>
	<td style="width:400px">
		<div id="ip"><strong><%=dd_vo.getIp()%></strong></div> (작성시 ip:<%=request.getRemoteAddr()%>)
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="button" value="이벤트 수정" class="btn" id="btnUpdate"/>
		<input type="button" value="이벤트 삭제" class="btn" id="btnRemove"/>
		<input type="button" value="닫기" class="btn" id="btnWriteClose"/>
	</td>
</tr>
</table>
</form>
<%
	}catch(IOException ie){
	%>
		뎨둉!글의 내용을 읽어들이는 도중에 문제가 발생하였습니다.잠시후에 다시 시도하여 주세요.
	<%
	}catch(SQLException se){
		se.printStackTrace();
		%>
		<img src="images/construction.jpg" title="데둉합니다...빠른 복구를 위해 노력중입니다."/>
		<%
	}//end catch
%>
</div>