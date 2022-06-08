<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--
<%@ include file=” ”%>
假如 在B.jsp 中，使用
<%@ include file=”A.jsp”%>  ，
那么就是把 A.jsp 的内容 原封不动  引入到 B.jsp 中。
--%>
<%@include file="/WEB-INF/common/head.jsp"%>
<div class="right">
    <img class="wColck" src="${pageContext.request.contextPath }/images/clock.jpg" alt=""/>
    <div class="wFont">
        <h2>${user.userName }</h2>
        <p>欢迎来到水库管理系统!</p>
    </div>
</div>
</section>
<%@include file="/WEB-INF/common/foot.jsp" %>
