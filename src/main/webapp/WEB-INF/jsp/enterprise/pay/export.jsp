<%@ page contentType="application/vnd.ms-excel;charset=UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	
	String file = "订单信息.xls";
	file = new String(file.getBytes("UTF-8"), "ISO_8859_1");
   	response.setHeader("Content-disposition","attachment; filename="+file);
   //以上这行设定传送到前端浏览器时的档名为test1.xls
   //就是靠这一行，让前端浏览器以为接收到一个excel档 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>订单信息</title>
		<style>
			.headbg {
				background: #c9e6f5;
				font-weight: bold;
				padding-left: 15px;
				height: 30px;
			}
			
			.headbg td {
				font-weight: bold;
				text-align: center;
			}
		</style>

		<script>
		</script>
	</head>
	<body>
	<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()" border="1">
					
			<tr class="headbg">
					
					<td>
					        付款时间
					</td>
					<td>
					        收件人姓名
					</td>
					<td>
						收件人手机
					</td>
				<!-- 	<td>
						添加备注
					</td> -->
					
					<td>
						收件人详细地址
					</td>
					<!-- <td>
						内装货品
					</td>
					<td>
						收件人备注
					</td> -->
					<td>
						发件人姓名
					</td>
					
					<td>
						发件人电话
					</td>
					<!-- <td>
						发件人公司
					</td> -->
					<!-- <td>
						发件人详细地址
					</td> -->
					<!-- <td>
						发件人备注1
					</td> -->

			</tr>
		<s:iterator var="bean" value="pv2List" status="status">
				<tr>
					<td><s:date name="#bean.payTimeStr" format="yyyy-MM-dd"/></td>
					<td><s:property value="#bean.receiver"/></td>
					<td><s:property value="#bean.receiverPhone"/></td>
					<!-- <td></td> -->
					<td><s:property value="#bean.receiverAddress"/></td>
					<!-- <td></td>
					<td></td> -->
					<td><s:property value="#bean.sender"/></td>
					<td><s:property value="#bean.senderPhone"/></td>
					<!-- <td></td> -->
					<%-- <td><s:property value="#bean.senderAddress"/></td> --%>
					<!-- <td></td> -->
					
					
				</tr>
			</s:iterator>
		</table>
	</body>
</html>