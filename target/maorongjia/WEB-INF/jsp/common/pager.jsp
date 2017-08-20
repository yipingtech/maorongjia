<%@ page contentType="text/html;charset=UTF-8"%>
<table class="pager">
	<tr>
		<td width="40%">
			&nbsp;&nbsp;共有 <s:property value="pager.rowCount" /> 条记录，每页行数：
			<s:select name="pageSize" id="pageSize" list="pager.pageSizeIndexs" theme="simple" value="pager.pageSize"
					onchange="$('#pageNo').val('1');$('#form1').submit();" />
			当前第
          	<s:select name="pageNo" id="pageNo" list="pager.pageNoIndexs" theme="simple"
					value="pager.pageNo" onchange="$('#form1').submit();" />
	 		页
		</td>
		<td width="60%">
			<c:if test="${pager.pageNo == '1'}">
				<a class="btn btn-notclick btn-small">首页</a>
				<a class="btn btn-notclick btn-small">上一页</a>
			</c:if>
			<c:if test="${pager.pageNo != '1'}">
				<a class="btn btn-click btn-small" href="javascript:$('#pageNo').val('<s:property value="pager.firstPageNo" />');$('#form1').submit();">首页</a>
				<a class="btn btn-click btn-small" href="javascript:$('#pageNo').val('<s:property value="pager.prePageNo" />');$('#form1').submit();">上一页</a>
            </c:if>
            
            <c:if test="${pager.pageNo == pager.pageCount}">
            	<a class="btn btn-notclick btn-small">下一页</a>
				<a class="btn btn-notclick btn-small">尾页</a>
			</c:if>
			<c:if test="${pager.pageNo != pager.pageCount}">
				<a class="btn btn-click btn-small" href="javascript:$('#pageNo').val('<s:property value="pager.nextPageNo" />');$('#form1').submit();">下一页</a>
				<a class="btn btn-click btn-small" href="javascript:$('#pageNo').val('<s:property value="pager.lastPageNo" />');$('#form1').submit();">尾页</a>
            </c:if>
		</td>
	</tr>
</table>