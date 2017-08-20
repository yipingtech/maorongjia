<div id="foot">
	<div id="friendship_link">
		<div id="flink_title">友情链接</div>
		<div id="flink_1">
			<div class="linktitle">政府链接</div>
			<div class="linkselect">
				<select onchange="if(this.options[this.selectedIndex].value!='')window.open(this.options[this.selectedIndex].value)">
						<#if link_1?exists>
	                    	<#list link_1 as link>
			                	<option value='${link.address}'>${link.names}</option>
			            	</#list>
			         	</#if>
				</select>
			</div>
		</div>
		<div id="flink_2">
			<div class="linktitle">企业链接</div>
			<div class="linkselect">
				<select onchange="if(this.options[this.selectedIndex].value!='')window.open(this.options[this.selectedIndex].value)">
					<#if link_2?exists>
	                	<#list link_2 as link>
			            	<option value='${link.address}'>${link.names}</option>
			        	</#list>
			    	</#if>
				</select>
			</div>
		</div>
		<div id="flink_3">
			<div class="linktitle">媒体链接</div>
			<div class="linkselect">
				<select onchange="if(this.options[this.selectedIndex].value!='')window.open(this.options[this.selectedIndex].value)">
					<#if link_3?exists>
	                	<#list link_3 as link>
			            	<option value='${link.address}'>${link.names}</option>
			        	</#list>
			    	</#if>
				</select>
			</div>
		</div>
	</div>
	<div id="foot_info">
		<#if webSite?exists>
			<#if webSite.copyright?exists>
				${webSite.copyright}
			</#if>
		</#if>
	</div>
</div>