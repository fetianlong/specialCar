<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="countmenu">
	<ul class="ce">
		<c:forEach items="${menuList}" var="fmlist" varStatus="status">
			<li>
				<c:choose>
					<c:when test="${fmlist.id eq fid}">
						<a id="${fmlist.id}" href="#" class="xz" onclick="cleckFirst('${fmlist.id}');">
						<i class="navpic${fmlist.id}"></i>${fmlist.name}</a>
						<c:forEach items="${fmlist.childrens}" var="smList">
							<c:choose>
								<c:when test="${smList.id eq cid}">
								<ul class="er" style="display: block;">
									<li>
									<a id="${smList.id}" href="#" class="sen_x" onclick="checkSecond('${smList.id}','${smList.url}');">${smList.name}</a>
									</li>
								</ul>
								</c:when>
								<c:otherwise>
								<ul class="er" style="display: block;">
									<li>
									<a id="${smList.id}" href="#" onclick="checkSecond('${smList.id}','${smList.url}');">${smList.name}</a>
									</li>
								</ul>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<a id="${fmlist.id}" href="#" onclick="cleckFirst('${fmlist.id}');">
						<i class="navpic${fmlist.id}"></i>${fmlist.name}</a>
						<c:forEach items="${fmlist.childrens}" var="smList">
								<c:choose>
									<c:when test="${fmlist.id eq cid}">
									<ul class="er" style="display: block;">
										<li>
										<a id="${smList.id}" href="#" onclick="checkSecond('${smList.id}','${smList.url}');">${smList.name}</a>
										</li>
									</ul>
									</c:when>
									<c:otherwise>
									<ul class="er" style="display: none;">
										<li>
										<a id="${smList.id}" href="#" onclick="checkSecond('${smList.id}','${smList.url}');">${smList.name}</a>
										</li>
									</ul>
									</c:otherwise>
								</c:choose>
						</c:forEach>	
					</c:otherwise>
				</c:choose>
				</li>
		</c:forEach>
		<div class="clear"></div>
	</ul>
<%-- 	<input type="hidden" id="fid" name="fid" value="${fid}"> --%>
<%-- 	<input type="hidden" id="cid" name="cid" value="${cid}"> --%>
</div>
<script>
var fid = null;
var cid = null;
function cleckFirst(id){
	fid = id;
	$("#fid").val(fid);
}
function checkSecond(id,url){
	cid = id;
	if(fid == null){
		fid = '${param.fid}';
	}
	var newUrl = '${ctx}/' + url + "?fid="+fid+"&cid="+cid
	$(this).attr("href",newUrl);
	window.location.href = newUrl;
}
</script>