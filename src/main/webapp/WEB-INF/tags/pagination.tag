<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
int current =  page.getNumber();
int begin = Math.max(1, current - paginationSize/2);
int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
%>

<div class="pageline">
   	<% if (page.hasPreviousPage()){%>
	<a href="?fid=${fid}&cid=${cid}&page=1&${searchParams}">首页</a>
	<a href="?fid=${fid}&cid=${cid}&page=${current-1}&${searchParams}">上一页</a>
       <%}else{%>
	<a href="#">首页</a>
	<a href="#">上一页</a>
       <%} %>
       <c:forEach var="i" begin="${begin}" end="${end}">
          <c:choose>
              <c:when test="${i == current}">
               <a class="hover" href="?fid=${fid}&cid=${cid}&page=${i}&${searchParams}">${i}</a>
              </c:when>
              <c:otherwise>
                  <a href="?fid=${fid}&cid=${cid}&page=${i}&${searchParams}">${i}</a>
              </c:otherwise>
          </c:choose>
      </c:forEach>
 
 	 <% if (page.hasNextPage()){%>
<!--  	 	到 <input type="number" id="pnumber" style="width: 50px;" /> <a href="#">Go</a> -->
	<a href="?fid=${fid}&cid=${cid}&page=${current+1}&${searchParams}">下一页</a>
	<a href="?fid=${fid}&cid=${cid}&page=${page.totalPages}&${searchParams}">末页</a>
       <%}else{%>
<!--       	到 <input type="number" id="pnumber" style="width: 50px;" /><a href="#" onclick="gotoNumber()">Go</a> -->
	<a class="next disabled" href="#">下一页</a>
	<a href="#">末页</a>
       <%} %>
</div>