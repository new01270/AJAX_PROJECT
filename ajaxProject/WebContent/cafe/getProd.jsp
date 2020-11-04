<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cafe/getProd.jsp</title>
<script src="vendor/jquery/jquery.min.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<%
		String item = request.getParameter("item_no");
	%>
<script>
$.ajax({
	url: '../GetProdSelectServlet',
	data: {item_no:'<%=item%>'},
	dataType: 'json',
	success: function(result) {
		console.log(result[0].itemName)
		selectFunc(result[0]);
				
	}
})

function selectFunc(data) {
	let div1 = $('<div />').attr('class', 'col-lg-4 col-md-6 mb-4');
	let div2 = $('<div />').attr('class', 'card h-100');
	let div2_a = $('<a />').attr('href', 'getProd.jsp?item_no=' + data.itemNo);
	let img = $('<img />').attr({
		'class': 'card-img-top',
		'src': '../images/'+data.itemImg
	}); // attr두번써도됨.
	let div2_div = $('<div />').attr('class', 'card-body');
	let h4 = $('<h4 />').attr('class', 'card-title');
	let h4_a = $('<a />').attr('href', '#').html(data.itemName).attr('style','color:brown');
	let prices = new Intl.NumberFormat('ko-KR', {style: 'currency', currency: 'KRW'}).format(data.price);
	let h5 = $('<h5 />').html(prices);
	let p = $('<p />').attr('class', 'card-text').html(data.itemDesc);
	let div2_div2 = $('<div />').attr('class', 'card-footer');
	let star = "";
	let like = Math.floor(data.likeIt);	//Math.floor(4.5)=4.0 ,(-3.5)=4.0
	// https://www.w3schools.com/icons/icons_reference.asp
	let ic="";	//star
	for(let i = 0; i < like; i++) {
		ic += '<i style="font-size:15px; color:brown" class="fas">&#xf005;</i>';
		star += '&#xf005;';	//&#9733; 꽉찬별 4.0
	}
	if(data.likeIt > like) {
		ic += '<i style="font-size:15px; color:brown"  class="far">&#xf005;</i>';
		star += '&#xf005;';	//&#9734; 빈별 0.5
	}
	let small = $('<small />').attr('class', 'text-muted').html(ic);	//html(star)->주석은 별로 처리됨.

	// 부모관계 생성.
	div1.append(div2);
	div2.append(div2_a, div2_div, div2_div2);
	div2_a.append(img);
	div2_div.append(h4, h5, p);
	h4.append(h4_a);
	div2_div2.append(small);
	$('#bdy').append(div1);
	
	
}
</script>

</head>

<body id="bdy">
	

	
</body>
</html>