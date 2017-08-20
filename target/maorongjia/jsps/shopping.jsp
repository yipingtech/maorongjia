<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>
<link href="${imagePath}/bootstrap.min.css" rel="stylesheet" type="text/css">

<body class="bg2">
    <div id="container">
        <div class="inner_head clearfix">
            <div class="nav_all"><c:if test="${not empty selectColumn}">${selectColumn.shortName}</c:if><c:if test="${empty selectColumn}">全部宝贝</c:if><span class="triangle"></span>
            </div>
            <div class="result">共搜索到<span>${num}</span>个宝贝</div>
            <div class="nav_list" style="display: none;">
            	<a href="${ctx}/epProductAction!productOneList.action">全部宝贝</a>
            	<c:forEach items="${map.ClassificationPro.enterpriseColumnList}" var="columnList">
            		<a href="${ctx}/epProductAction!productOneList.action?columnId=${columnList.id}">${columnList.shortName }</a>
            	</c:forEach>
                <%--<a href="#" class="active">三件套</a>--%>
            </div>
        </div>
        <div class="product_page">
            <div class="title_img"><img src="${imagePath}/title_img.png"></div>
            
            <c:forEach items="${productList}" var="pro">
            	<a href="${ctx}/epProductAction!tampons.action?id=${pro.id}" class="product_list">
                <div class="pro_pic"><img src="${uploadPath}/${pro.imgurl}""></div>
                <div class="clearfix">
                    <div class="price left">
                        <s>￥${pro.marketPrice}</s>
                        <div>￥${pro.shopPrice}</div>
                    </div>
                    <div class="buy right"><img src="${imagePath}/buy.png"></div>
                </div>
           		</a>
            </c:forEach>
        </div>
    </div>	
     <!--container right end-->
<%@ include file="/jsps/modules/copyRight.jsp"%>