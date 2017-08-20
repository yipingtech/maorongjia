<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="mtag" uri="http://www.messcat.com/taglib/mtag" %>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="imagePath" value="${ctx}/jsps/theme/default/image" />
<c:set var="jsPath" value="${ctx}/jsps/theme/default/js" />
<c:set var="uploadPath" value="${ctx}/upload/enterprice" />