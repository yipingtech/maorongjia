<%@ page contentType="text/html;charset=UTF-8"%>
<table class="listTable2">
	<tr>
		<td width="40%">
			&nbsp;&nbsp;<!-- 共有 -->共有 <s:property value="pager2.rowCount" /> <!-- 条记录，每页行数： -->条记录，每页行数：
			<s:select name="pageSize2" id="pageSize2" list="pager.pageSizeIndexs" theme="simple" value="pager2.pageSize"
					onchange="$('#pageNo2').val('1');$('#pageform2').submit();" />
			<!-- 当前第  -->当前第
          	<s:select name="pageNo2" id="pageNo2" list="pager2.pageNoIndexs" theme="simple"
					value="pager2.pageNo" onchange="$('#pageform2').submit();" />
	 		<!-- 页  -->页  
		</td>
		<td width="60%">
			<c:if test="${pager2.pageNo == '1'}">
				<a class="btn btn-notclick btn-small">首页</a>
				<a class="btn btn-notclick btn-small">上一页</a>
			</c:if>
			<c:if test="${pager2.pageNo != '1'}">
				<a class="btn btn-click btn-small" href="javascript:$('#pageNo2').val('<s:property value="pager2.firstPageNo" />');$('#pageform2').submit();">首页</a>
				<a class="btn btn-click btn-small" href="javascript:$('#pageNo2').val('<s:property value="pager2.prePageNo" />');$('#pageform2').submit();">上一页</a>
            </c:if>
            
            <c:if test="${pager2.pageNo == pager2.pageCount}">
            	<a class="btn btn-notclick btn-small">下一页</a>
				<a class="btn btn-notclick btn-small">尾页</a>
			</c:if>
			<c:if test="${pager2.pageNo != pager2.pageCount}">
				<a class="btn btn-click btn-small" href="javascript:$('#pageNo2').val('<s:property value="pager2.nextPageNo" />');$('#pageform2').submit();">下一页</a>
				<a class="btn btn-click btn-small" href="javascript:$('#pageNo2').val('<s:property value="pager2.lastPageNo" />');$('#pageform2').submit();">尾页</a>
            </c:if>
		</td>
	</tr>
</table>