<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>

<body class="edit_address">
<header class='clearfix close-bottom'>
    <a href="javascript:history.back();"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
    升级vip会员
</header>
		<section class="e-ad">
		    <form action="" class="e-ad-fome">
		        <p class='clearfix'>
					<label for="name">收货人</label>
					<input type="text" placeholder='名字' id="name">
		        </p>
		        
		        <p class='clearfix'>
					<label for="tel">手机号码</label>
					<input type="tel" placeholder='手机号码' id="tel">
				    <a href="#">发送验证</a>
		        </p>
		        
		        <p class='clearfix'>
					<label for="sex">性别</label>
		            <input type="radio" name="radio01" id="sex-m"><label for="sex-m" class="sex-m">男</label>
		            <input type="radio" name="radio01" id="sex-w"><label for="sex-w" class="sex-w">女</label>
		        </p>
		        
		        <p class='clearfix'>
					<label for="">生日</label>
					<select name="" id="year">
					    <option value="" selected>2000</option>
					    <option value="广东"></option>
					    <option value="广西"></option>
					</select>
					
		            <select name="" id="month">
					    <option value="" selected>05月</option>
					    <option value="广东"></option>
					    <option value="广西"></option>
					</select>
					
		            <select name="" id="day">
					    <option value="" selected>16号</option>
					    <option value="广东"></option>
					    <option value="广西"></option>
					</select>
		        </p>
		        
		        <button id="sure">确认</button>
		    </form>
		</section>
		
<script src="${jsPath}/style.js"></script>
</body>
</html>