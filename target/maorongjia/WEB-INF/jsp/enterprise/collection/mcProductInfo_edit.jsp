<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<link href="${ctx}/js/AsyncBox/skins/ZCMS/asyncbox.css" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
		<script type="text/javascript" src="${ctx}/js/AsyncBox/AsyncBox.v1.4.js"></script>
		<script>
			$(document).ready(function(){
				jQuery.validator.addMethod("decimal", function(value, element) {
					var decimal = /^-?\d+(\.\d{1,2})?$/;
					return this.optional(element) || (decimal.test(value));
					}, $.validator.format("小数位数不能超过两位!"));
				
				$("#mcProductInfoForm").validate({
					rules: {
						"mcProductInfo.title": {
							required: true
						}, 
						"mcProductInfo.marketPrice" :{
							decimal : true
						},
						"mcProductInfo.shopPrice" :{
							decimal : true
						},
						"mcProductInfo.promotePrice" :{
							decimal : true
						},
						
					},
					messages:{  
						"mcProductInfo.title": {
							required: "请输入产品名称"
						}, 
						"mcProductInfo.marketPrice" :{
							decimal : "最多两位小数"
						},
						"mcProductInfo.shopPrice" :{
							decimal : "最多两位小数"
						},
						"mcProductInfo.promotePrice" :{
							decimal : "最多两位小数"
						},
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        }	
				});
				
				$("#productType").change(initAttribute);
				
			});		
		</script>
<script>
	//当选择图片时发生
	function doSelectPhotoChanged(fileNameId,upLoadFileId,imageId) {
		        $.ajaxFileUpload({
		            url: '${ctx}/ajaxepUploadImage!ajaxUploadImage.htm', 
		            type: 'post',
		            secureuri: false, //一般设置为false
		            fileElementId: upLoadFileId, // 上传文件的id、name属性名
		            dataType: 'json', //返回值类型，一般设置为json、application/json
		            success: function(data, status){
		                //$('#'+upLoadFileId).val(data);
		                //alert(data);
		            	if(null == data){
							$('#'+fileNameId).val('');
							$('#'+imageId).attr('src','${ctx}/upload/NoExpertPhoto.JPG');
							$('#'+imageId).parent().attr('href','${ctx}/upload/NoExpertPhoto.JPG'); ;
						}else{
			                $('#'+fileNameId).val(data);
							$('#'+imageId).attr('src','${ctx}/upload/enterprice/'+data);
							$('#'+imageId).parent().attr('href','${ctx}/upload/enterprice/'+data); ;
						}
		            },
		            error: function(data, status, e){ 
		                alert(e);
		            }
		        });
		        //return false;
		    }
	//搜索属性（单选）
		function seachAttrValList(singleAttrVal, attrId, choseType) {
			asyncbox.open({
				title: '属性值',
				url : '${ctx}/product/attributeAction!findAttrValueById.action',
				data : {
					choseType : choseType,
					id : $('#'+attrId).val()
				},
				width : 800,
	    		height : 440,
				btnsbar : $.btn.OKCANCEL,
				callback : function(action, value) {
					if(action == 'ok') {
						if(isNull(value.selectValue)){
							asyncbox.tips("请选择属性值!");
							return false;
						}else{
							$("#"+singleAttrVal).val(value.selectValue);
							$("#lineStockAmount").val(value.count);
							return true;
						}
					}
				}
			});
		}
		 function del_unit(e){
			$('.unit'+e).remove();
			$('.stockcheck'+e).remove();
		 }
		 function checkRule(line){
			var amountvalue = $("#amount"+line).val();
			var valueNum = line+"1";
			var attrVal = $("#allSingleAttrVal"+valueNum).val();
			var attrName = $("#allSingleAttrName1").val();
			var valueList = amountvalue.split(",");
			if(attrVal!=''){
				var standardList = attrVal.split(",");
				if(standardList.length!=valueList.length){
					alert("输入错误，"+attrName+"跟库存不对应");
				}
			}else if(valueList.length!=1){
				alert("库存格式错误");
			}
			
		}
		 function addRepertory(){
		 	//新增库存行数
		 	var count=$(".addcont").children("div").length+1;
		 	//属性数量
		 	var attrAmount = $("#attrAmount").val();
		 	for(var i = 1; i < attrAmount; i++){
		 		var attrName = $("#allSingleAttrName"+i).val();
		 		var attrVal = "allSingleAttrVal"+count+i;
		 		var attrId = "allSingleAttrId1"+i;
		 		if(i==1){
		 			$('.addcont').append("<div class='unit"+count+"'>"
						+attrName+" <input type='text' id='"+attrVal+"' name='"+attrVal+"' value='' readonly='readonly' required='true' "
						+"onClick=\"seachAttrValList('"+attrVal+"','"+attrId+"','radio')\";/>"
						+" 库存 <input type='text' id='amount"+count+"' name='amount"+count+"' onblur='checkRule("+count+")' value='' required='true' number='true' />"
						+" 价格 <input type='text' id='price"+count+"' name='price"+count+"' value='' required='true' number='true' />"
						+" <input type='button' name='' value='删除' class='del_unit' onClick='del_unit("+count+")'></div>"
					);
		 		}else{
		 			$(".unit"+count).prepend(attrName+" <input type='text' id='"+attrVal+"' name='"+attrVal+"' value='' readonly='readonly'  required='true' "
		 			+" onClick=\"seachAttrValList('"+attrVal+"','"+attrId+"','radio')\";/> ");
		 		}
		 	}
		 	$('.checkarea:last').append("<input type='checkbox' checked='checked' class='stockcheck"+count+"' name='stocklist' value='"+count+"'/>");
		 }
		 
		 
		 function initAttribute(){
				//删除之前添加的tr内容
					$(".attr_th").parent().remove();
					$(".attr_tr").remove();
			 	$.ajax({ 
				      	url:'${ctx}/ajaxFindAttrByType!findAttrByType.htm', 
				      	type:'post', 
				      	dataType: 'json',
				      	data:{
				      		"attribute.productType.id":$("#productType").val()
				      	},
						success: function(json) {
							//$("#standard-box").empty();
							var attrSigle = 1;
							var allSoleAttr = 1;
							if(json!=""){
					      		$.each(json, function(index, value) {
					      			index++;
					      			console.log(value);
					      			if(value.attrType==0){
					      				$("#attribute").after("<tr class='attr_tr'><th>"+value.attrName+"</th><td class='attr_td'><input type='text' name='allSoleAttrVal"+allSoleAttr+"'><input type='hidden' name='allSoleAttrId"+allSoleAttr+"' value='"+value.id+"'></td></tr>");
					      				allSoleAttr++;
					      			}else if(value.attrType==1){
					      				if(attrSigle==1){
						      				$("#beforeStock").after("<tr><th class='attr_th'>库存</th><td class='attr_td'>"
						      				+"<div class='addcont'><div class='unit1'>"
						      				+value.attrName+"<input type='hidden' id='allSingleAttrName"+attrSigle+"' name='allSingleAttrName"+attrSigle+"' value='"+value.attrName+"' />"
						      				+" <input type='text' id='allSingleAttrVal1"+attrSigle+"' name='allSingleAttrVal1"+attrSigle+"' value='' readonly='readonly'  required='true' "
						      				+"onClick=\"seachAttrValList('allSingleAttrVal1"+attrSigle+"','allSingleAttrId1"+attrSigle+"','radio')\";/>"
											+"<input type='hidden' id='allSingleAttrId1"+attrSigle+"' name='allSingleAttrId1"+attrSigle+"' value='"+value.id+"' />"
											+"库存 <input type='text' id='amount1' name='amount1' value='' onblur='checkRule(1)' required='true' number='true'/>"
											+"价格 <input type='text' id='price1' name='price1' value='' required='true' number='true'/>"
											+"</div></div>"
						      				+"<input type='button' name='' value='添加' onclick=\"addRepertory()\";>"
						      				+"<div class='checkarea' style='display: none;'><input type='checkbox' class='stockcheck1' checked='checked' name='stocklist' value='1'></div>"
						      				+"</td></tr>");
					      				}else{
					      					$('.unit1').prepend(value.attrName+"<input type='hidden' id='allSingleAttrName"+attrSigle+"' name='allSingleAttrName"+attrSigle+"' value='"+value.attrName+"' />"
					      					+" <input type='text' id='allSingleAttrVal1"+attrSigle+"' name='allSingleAttrVal1"+attrSigle+"' value='' readonly='readonly'   required='true' "
					      					+" onClick=\"seachAttrValList('allSingleAttrVal1"+attrSigle+"','allSingleAttrId1"+attrSigle+"','radio')\";/>"
											+"<input type='hidden' id='allSingleAttrId1"+attrSigle+"' name='allSingleAttrId1"+attrSigle+"' value='"+value.id+"' />"
											);
					      				}
					      				attrSigle++;
					      				allSoleAttr++;
					      			}
					      			$(".error checked").remove();
					      			
					      		});
					      		//记录单一属性个数，为添加做准备
					      		$("#attrAmount").val(attrSigle);
					      		$("#attrSoleAmount").val(allSoleAttr);
							}
						 },
						 error: function(XMLHttpRequest, textStatus, errorThrown) {
						      alert("网络出现异常");
						   }
				});
			 }	
		 
</script>
<style>
	.notview{
		display:none;
	}
</style>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 内容管理  - 编辑产品信息</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
		</div>

		<form action="mcProductInfoAction!editMcProductInfo.action" method="post" id="mcProductInfoForm" name="mcProductInfoForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<input type="hidden" name="colId" value="${colId}"/>
				<input type="hidden" name="id" value="${mcProductInfo.id}" />
				<input type="hidden" name="mcProductInfo.id" value="${mcProductInfo.id}" />
				<tr>
					<th>产品名称</th>
					<td><input type="text" name="mcProductInfo.title" value="${mcProductInfo.title}" size="100" class="{required:true,maxlength:50}"/></td>
				</tr>
				 <%-- <c:if test="${ empty stockVoList}"> --%>
				  <tr>
					<th>产品类型</th>
					<td>
						<select name="typeId" id="productType" required="true" >
							<option value="">请选择</option>
							<c:forEach var="bean" items="${productTypes}">
								<option value="${bean.id}" <c:if test="${bean.id eq mcProductInfo.productType.id}">selected="selected"</c:if>>${bean.name}</option>
							</c:forEach>
						</select>
					</td>
				 </tr>
				<%-- </c:if>  --%>
				
				<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_PRODUCT_KEYWORD"> style="display:table-row;"</security:authorize>>
					<th>分享描述</th>
					<td><input type="text" id="keywords" name="mcProductInfo.keywords" value="${mcProductInfo.keywords}" class="{maxlength:500}" size="100" />(用于该商品被分享时显示的描述)</td>
				</tr>
				<%-- <tr id="attribute" class="notview"<security:authorize ifAnyGranted="COLLECTION_PRODUCT_INTRO"> style="display:table-row;"</security:authorize>>
					<th>简短描述</th>
					<td>
						<textarea id="description" rows="10" cols="77" name="mcProductInfo.description" class="{maxlength:500}">${mcProductInfo.description}</textarea>
					</td>
				</tr> --%>
				<c:forEach var="bean" items="${productAttrList}" varStatus="status">
					<tr>
						<th>${bean.attr.attrName}</th>
						<td>
							<input type="hidden" name="productAttrId${status.count}" value="${bean.id}"  class="{required:true}"/>
							<input type="text" name="allSoleAttrVal${status.count}"  value="${bean.attrValue}" class="{required:true}"/>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<th>上架</th>
					<td>
						<select id="isSale" name="mcProductInfo.isSale" class="{required:true}">
							<option value="1" <c:if test="${mcProductInfo.isSale eq '1'}">selected</c:if>>是	</option>
							<option value="0" <c:if test="${mcProductInfo.isSale eq '0'}">selected</c:if>>否	</option>
						</select>
					</td>
				</tr>
				<!--  <tr>
					<th>赠送积分</th>
					<td><input type="text" name="mcProductInfo.giveIntegral" value="${mcProductInfo.giveIntegral}" required="true" number="true"/></td>
				</tr>-->
				<tr>
					<th>市场售价</th>
					<td><input type="text" name="mcProductInfo.marketPrice" value="${mcProductInfo.marketPrice}"  number="true"/></td>
				</tr>
				 <tr>
					<th>显示售价</th>
					<td><input type="text" name="mcProductInfo.shopPrice" value="${mcProductInfo.shopPrice}" required="true" number="true"/></td>
				</tr>
				<tr>
					<th>促销价格</th>
					<td><input type="text" name="mcProductInfo.promotePrice" value="${mcProductInfo.promotePrice}" required="true" number="true"/></td>
				</tr>
				
				<tr>
					<th>排序</th>
					<td><input type="text" name="mcProductInfo.orderPara"  required="true" number="true" value="${mcProductInfo.orderPara}"/></td>
				</tr>
				
				<tr id="beforeStock">
					<th>商品的详情图</th>
					<td>
						<a href="${ctx}/upload/enterprice/${mcProductInfo.imgurl}" target="_blank"
							name="imgchange" id="imgchange" class="screenshot"> 
							<img id="imgurl"
								name="mcProductInfo.imgurl" border="0" width="96"
								src="<c:choose>
										<c:when test="${empty mcProductInfo.imgurl}">
												${ctx}/upload/NoExpertPhoto.JPG
										</c:when>
										<c:otherwise>
											${ctx}/upload/enterprice/${mcProductInfo.imgurl}
										</c:otherwise>
									</c:choose>" /> 
						</a> 
							<br /> 
							<input type="file" id="upload" name="upload" value="文件"
						onchange="doSelectPhotoChanged('fileName','upload','imgurl');" size="30" />
							<input type="hidden" id="fileName" value="${mcProductInfo.imgurl}" name="mcProductInfo.imgurl"/>
					<input type="hidden" name="attrSoleAmount" id="attrSoleAmount" value="${fn:length(productAttrList)}"/>
					</td>
				</tr>
				<c:if test="${not empty stockVoList}">
				   <tr>
					<th>库存</th>
					<td>
						<div class='addcont'>
						<c:forEach var="stockVo" items="${stockVoList}" varStatus="status">
							<input type='hidden' id="stockId${status.count}" name="oldStockId" value="${stockVo.stockId}" />
							<div class='unit${status.count}'>
							<c:forEach var="bean" items="${stockVo.attMap}" varStatus="attrNum">
								<c:set var="attrCount" value="${fn:length(stockVo.attMap)-attrNum.count+1}"/>
								<%-- <c:if test="${attrNum.count == '1'}">
								</c:if> --%>
								${bean.key.attrName}
								<c:if test="${status.count == '1'}">
									<input type='hidden' id="allSingleAttrId1${attrCount}" name="allSingleAttrId1${attrCount}" value="${bean.key.id}" />
								</c:if>
								<input type='hidden' id='allSingleAttrName${attrCount}' name='allSingleAttrName${attrCount}' value='${bean.key.attrName}' />
								<c:if test="${attrNum.last}">
									<input type='text' id="allSingleAttrVal${status.count}${attrCount}" name="allSingleAttrVal${status.count}${attrCount}" value="${bean.value}" required="true" readonly='readonly' onClick="seachAttrValList('allSingleAttrVal${status.count}${attrCount}','allSingleAttrId1${attrCount}','radio')" />
								</c:if>
								<c:if test="${!attrNum.last}">
									<input type='text' id="allSingleAttrVal${status.count}${attrCount}" name="allSingleAttrVal${status.count}${attrCount}" value="${bean.value}" required="true" readonly='readonly' onClick="seachAttrValList('allSingleAttrVal${status.count}${attrCount}','allSingleAttrId1${attrCount}','radio')" />
								</c:if>
							</c:forEach>
								库存 <input type="text" id="amount${status.count}" name="amount${status.count}" value="${stockVo.repertory}" onblur="checkRule(${status.count})" required="true" number="true"/>
								价格 <input type="text" id="price${status.count}" name="price${status.count}" value="${stockVo.price}" required="true" number="true"/>
								<c:if test="${status.count != '1'}">
								<input type='button' name='' value='删除' class='del_unit' onClick="del_unit(${status.count})"/>
								</c:if>
								<c:if test="${status.count == '1'}">
									<input type="hidden" name="attrAmount" id="attrAmount" value="${fn:length(stockVo.attMap)+1}"/>
								</c:if>
								<div class="checkarea" style="display: none;">
									<input type="checkbox" checked="checked" class="stockcheck1" name="stocklist" value="${status.count}">
								</div>
							</div>
						</c:forEach>
						</div>
						<input type="button" name="" value="添加" onclick="addRepertory()">
					</td>
				  </tr>
				</c:if>
				
				<c:if test="${ empty stockVoList}">
				  <input type="hidden" name="attrAmount" id="attrAmount" value="0"/>
				</c:if>
				
				
				<c:forEach var="parameter" items="${parameterValue}" varStatus="status">
					<tr>
						<th>${parameter.key.name}</th>
						<td>
							<c:choose>
								<c:when test="${parameter.key.type == 0}">
									<input type="text" id="${parameter.key.mark}" name="mcProductInfo.${parameter.key.mark}" value="${parameter.value}" class="{maxlength:500}" size="100" />
								</c:when>
								<c:when test="${parameter.key.type == 1}">
									<textarea id="editor" name="mcProductInfo.${parameter.key.mark}" style="width:870px;height:400px;">${parameter.value}</textarea>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/upload/enterprice/${parameter.value}"
										target="_blank" name="imgchange" id="imgchange"
										class="screenshot">
										<img id="imgurl${status.count}" name="mcProductInfo.${parameter.key.mark}" border="0"
											width="96"
											src="<c:choose>
													<c:when test="${empty parameter.value}">
															${ctx}/upload/NoExpertPhoto.JPG
													</c:when>
													<c:otherwise>
														${ctx}/upload/enterprice/${parameter.value}
													</c:otherwise>
												</c:choose>
												" />
									</a>
									<br />
									<input type="file" id="upload${status.count}" name="upload" 
									onchange="doSelectPhotoChanged('fileName${status.count}','upload${status.count}','imgurl${status.count}');" size="30"/>
									<input type="hidden" id="fileName${status.count}" value="${parameter.value}" name="mcProductInfo.${parameter.key.mark}"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
				<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_PRODUCT_SPECIAL"> style="display:table-row;"</security:authorize>>
					<th>特殊设置</th>
					<td>
						<div class="notview" style="float:left;<security:authorize ifAnyGranted="COLLECTION_PRODUCT_ISNEW">display:block;</security:authorize>">
							是否为最新产品：
							<select id="isNew" name="mcProductInfo.isNew" class="{required:true}">
								<option value="1" <c:if test="${mcProductInfo.isNew eq '1'}">selected</c:if>>是	</option>
								<option value="0" <c:if test="${mcProductInfo.isNew eq '0'}">selected</c:if>>否	</option>
							</select>
						</div>
						<div class="notview" style="float:left;margin-left:10px;<security:authorize ifAnyGranted="COLLECTION_PRODUCT_ISSALE">display:block;</security:authorize>">
							是否为推荐产品：
							<select id="isBest" name="mcProductInfo.isBest" class="{required:true}">
								<option value="1" <c:if test="${mcProductInfo.isBest eq '1'}">selected</c:if>>是</option>
								<option value="0" <c:if test="${mcProductInfo.isBest eq '0'}">selected</c:if>>否</option>
							</select>
						</div>
						提示:热销和最新 不能同时为 “ 是 ”,否则热销或最新的标示图片无法显示
					</td>
				</tr>
				<tr>
					<th>内容</th>
					<td>
						<textarea id="editor" name="mcProductInfo.content" style="width:870px;height:400px;">${mcProductInfo.content}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" class="btn" value="提 交 "/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>