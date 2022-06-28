<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="admin_menu.jsp" %>
<%@ include file="admin_checklogin.jsp" %>
    <!--/sidebar-->
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"></div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <h1>快捷操作</h1>
            </div>
            <div class="result-content">
                <div class="short-wrap">
                    <a href="toAddProductServlet"><i class="icon-font">&#xe001;</i>新增商品</a>
                    <a href="${pageContext.request.contextPath}/productListServlet"><i class="icon-font">&#xe005;</i>查看商品</a>
                    <a href="${pageContext.request.contextPath}/userListServlet"><i class="icon-font">&#xe048;</i>查看用户</a>
                    <a href="${pageContext.request.contextPath}/toAllOrderListServlet"><i class="icon-font">&#xe041;</i>查看订单</a>

                </div>
            </div>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>