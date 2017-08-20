<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
$(document).ready(function(){

		$("#names").focus();
		$("#linksForm").validate({
			 rules: { 
				names: { 
        			required: true, 
        			maxlength:10
    			},
    			frontNum:{
        	    	maxlength:20
        	    },
        	    orderColumn:{
        	    	required: true ,
        	    	number:true
        	    },
            	intro: {
                    required: true ,
			        maxlength:50
        	    },
        	    address:
        	    {
        	    	required: true ,
        	    	url:true,
			        maxlength:100
        	    }
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }	
		});
	});	
</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 互动管理 - 友情链接管理 - 添加友情链接信息
			</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>			
			<div class="clear"></div>
		</div>
		<form action="enterpriseLinksAction!add.action" method="post" id="linksForm">
			<table class="table table-bordered inner-table">
				<tr>
					<th>
						链接名称
					</th>
					<td>
						<input type="text" name="names" size="100" />
					</td>
				</tr>

				<tr>
					<th>
						链接标识
					</th>
					<td>
						<input type="text" name="frontNum" />
					</td>
				</tr>
				<tr>
					<th>
						链接排序
					</th>
					<td>
						<input type="text" name="orderColumn" />
					</td>
				</tr>

				<tr>
					<th>
						链接地址
					</th>
					<td>
						<input type="text" name="address" size="100" />
					</td>
				</tr>
				<tr>
					<th>
						链接简介
					</th>
					<td>
						<textarea rows="10" cols="100" name="intro"></textarea>
					</td>
				</tr>
				<tr style="display:none;">
					<th>
						显示时间
					</th>
					<td>
						<input type="text" name="initTime" onchange="checkDate()"
							id="initTime" readonly />
						<input id="starttimeBt" type="button" class="default_button"
							value='选择' />
						<script type="text/javascript">
						Calendar.setup({
							inputField     : "initTime",
							button	  	   : "starttimeBt",
							/*ifFormat       :    "%Y-%m-%d %H:%M",							
					        showsTime      :    true,
					        timeFormat     :    "24"*/
							ifFormat       :    "%Y-%m-%d"
						});
					</script>
						至
						<input type="text" name="endTime" onchange="checkDate()"
							id="endTime" readonly />
						<input id="endtimeBt" type="button" class="default_button"
							value='选择' />
						<script type="text/javascript">
						Calendar.setup({
							inputField     : "endTime",
							button	  	   : "endtimeBt",
							/*ifFormat       :    "%Y-%m-%d %H:%M",							
					        showsTime      :    true,
					        timeFormat     :    "24"*/
							ifFormat       :    "%Y-%m-%d"
						});
					</script>
					</td>
				</tr>
				<tr>
					<th>
						状态
					</th>
					<td>
						<select name="state">
							<option value="1">
								已启用
							</option>
							<option value="0">
								已停用
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" class="btn" value="提 交 "/>
					</td>
				</tr>
			</table>
			<s:token></s:token>
		</form>
	</body>
</html>

