<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />


</head>

<body class="main">

	<jsp:include page="head.jsp" />

	<jsp:include page="menu_search.jsp" />


	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td><div style="text-align: right; margin: 5px 10px 5px 0px">
						<a href="index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车
					</div>

					<table cellspacing="0" class="infocontent">
						<tr>
							<td><img src="ad/page_ad.jpg" width="666" height="89" />
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td><img src="images/buy1.gif" width="635" height="38" />
										</td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="10%">序号</td>
													<td width="30%">商品名称</td>
													<td width="10%">价格</td>
													<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量</td>
													<td width="10%">库存</td>
													<td width="10%">小计</td>
													<td width="10%">取消</td>
												</tr>
											</table>
											<table width="100%" border="0" cellspacing="0">
											<!-- 存一个总价格的变量 -->
											< <c:set var="totalPrice" value="0"></c:set>
											<c:forEach items="${cart }" var="entry" varStatus="vs">
												<tr>
													<td width="10%">${vs.count }</td>
													<td width="30%">${entry.key.name}</td>

													<td width="10%">${entry.key.price }</td>
													<td width="20%">
													<input type="button" value='-' style="width: 20px" onclick="changeNum(${entry.key.id},${entry.value - 1},${entry.key.pnum })">
													<input name="text" type="text" value=${entry.value } style="width: 40px; text-align: center" /> 
													<input type="button" value='+' style="width: 20px" onclick="changeNum(${entry.key.id},${entry.value + 1},${entry.key.pnum })">
													<script type="text/javascript">
														//id---商品的id
														//num---更改后的购物数量
														//pnum---库存
														
														function changeNum(id,num,pnum){
															//alert(id + "-" + num + "-" + pnum);
															//1.购买数量不能大于库存
															if(num > pnum){
																alert('购买数量不能大于库存');
																return;
															}
															//2.如果购买数量为0，从购物车移除，提示用户
															if(num == 0){
																var b = confirm('您确定要移除该商品吗？');
																if(b == false){
																	return;
																}
															}
															
															//3.如果可以进行加减，对session的购物车数据进行更新
															//alert('更新session的购物车数据')；
															location.href = '${pageContext.request.contextPath}/changeNum?id='+id+'&num='+num;
														}
													</script>
													</td>
													<td width="10%">${entry.key.pnum }</td>
													<td width="10%">${entry.key.price * entry.value}</td>

													<td width="10%"><a href="#"
														style="color: #FF0000; font-weight: bold">X</a></td>
												</tr>
												 <c:set var="totalPrice" value="${totalPrice + entry.key.price * entry.value }"></c:set>
											</c:forEach>
											</table>



											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align: right; padding-right: 40px;"><font
														style="color: #FF6600; font-weight: bold">合计：&nbsp;&nbsp;${totalPrice }元</font>
													</td>
												</tr>
											</table>
											<div style="text-align: right; margin-top: 10px">
												<a href="product_list.jsp"><img src="images/gwc_jx.gif"
													border="0" /> </a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/settleAccount"><img
													src="images/gwc_buy.gif" border="0" /> </a>
											</div>
										</td>
									</tr>
								</table></td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
