
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 		<section id="index_footer" class="index_footer" style="width:100%">
		    <c:if test="${empty fatherId }">
			    <section class="bottom-footer-buy clearfix">
			        <section class="buy-img">
			            <section>
			            	<a class="tx-link" href="epMemberCenterAction.action">
			            		<c:if test="${not empty member.imagePath }">
			            			<img src="${member.imagePath}">
			            		</c:if>
			            		<c:if test="${empty member.imagePath}">
			                        <img src="${imagePath}/bottom-touxiang.png">
			            		</c:if>
			                </a>
			            </section>
			            <section>
			                <p>来自【
				                <c:if test="${not empty member.memberFather}">
				                       <c:choose> 
									     <c:when test="${fn:length(member.memberFather.nickname) > 4}"> 
									      <c:out value="${fn:substring(member.memberFather.nickname, 0, 4)}......" /> 
									     </c:when> 
									     <c:otherwise> 
									      <c:out value="${member.memberFather.nickname}" /> 
									     </c:otherwise>
									    </c:choose>       
				                </c:if>
				                <c:if test="${empty member.memberFather}">
				                                       谐达平台
				                </c:if>】的推荐</p>
			                <p>您是东家</p>
			            </section>
			        </section>
			        <section class="buy-btn" ><a href="epProductAction.htm">更多产品</a></section>
			    </section>
		    </c:if>
		     <c:if test="${!empty fatherId }">
			     <section class="bottom-footer-buy clearfix">
			        <section class="buy-img">
			            <section><img src="${member.imagePath}"></section>
			            <section>
			                <p>来自好友【${member.memberFather.nickname}】的推荐</p>
			                <p>您还不是东家，请购买成为东家</p>
			            </section>
			        </section>
			        <section class="buy-btn" ><a href="epProductAction.htm">更多产品</a></section>
			    </section>
		    </c:if>

		 <%-- <section id="index_footer" class="index_footer" style="width:100%">
			<a class="i-f-blue" href="epIndexAction.action">
		        <img src="${imagePath}/index-img.png" class="index_footer_img">
		        <p>首页</p>
		    </a>
		    <a class="i-f" href="epMemberCenterAction!initMemberPrivilege.action">
		        <img src="${imagePath}/vip-particular.png" class="index_footer_img">
		        <p>会员特权</p>
		    </a>
		    <a class="re_c" href="epMemberCenterAction.action">
		        <img src="${member.imagePath}" class="index_footer_img">
		        <p>会员中心-<a href="epProductAction!tampons.action">立即购买</a></p>
		    </a> --%>
		</section>
	</body>
</html>