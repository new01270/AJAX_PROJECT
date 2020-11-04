// WebContent/cafe/product.js
// /GetProdListServlet
// /cafe/index.html

$.ajax({
	url: '../GetProdListServlet',
	dataType: 'json',
	data: {'category':categoryss}, // 변수명category:넘거오는 진짜 카테고리명
	success: function (result) {
		console.log(categoryss);	// category.jsp 에서 변수를 만들어 가져옴.

		for (obj of result) {
			createRow(obj);
		}
	}

});

/*
	<div class="col-lg-4 col-md-6 mb-4">
		<div class="card h-100">
			<a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
			<div class="card-body">
				<h4 class="card-title">
					<a href="#">Item One</a>
				</h4>
				<h5>$24.99</h5>
				<p class="card-text">Lorem ipsum dolor sit amet, consectetur
					adipisicing elit. Amet numquam aspernatur!</p>
			</div>
			<div class="card-footer">
				<small class="text-muted">&#9733; &#9733; &#9733; &#9734; &#9734;</small>	
			</div>
		</div>
	</div>
*/

// obj: data 한건한건한건
function createRow(obj) {
	let div1 = $('<div />').attr('class', 'col-lg-4 col-md-6 mb-4');
	let div2 = $('<div />').attr('class', 'card h-100');
	let div2_a = $('<a />').attr('href', 'getProd.jsp?item_no=' + obj.itemNo);
	let img = $('<img />').attr({
		'class': 'card-img-top',
		'src': '../images/'+obj.itemImg
	}); // attr두번써도됨.
	let div2_div = $('<div />').attr('class', 'card-body');
	let h4 = $('<h4 />').attr('class', 'card-title');
	let h4_a = $('<a />').attr('href', '#').html(obj.itemName).attr('style','color:brown');
	let prices = new Intl.NumberFormat('ko-KR', {style: 'currency', currency: 'KRW'}).format(obj.price);
	let h5 = $('<h5 />').html(prices);
	let p = $('<p />').attr('class', 'card-text').html(obj.itemDesc);
	let div2_div2 = $('<div />').attr('class', 'card-footer');
	let star = "";
	let like = Math.floor(obj.likeIt);	//Math.floor(4.5)=4.0 ,(-3.5)=4.0
	// https://www.w3schools.com/icons/icons_reference.asp
	let ic="";	//star
	for(let i = 0; i < like; i++) {
		ic += '<i style="font-size:15px; color:brown" class="fas">&#xf005;</i>';
		star += '&#xf005;';	//&#9733; 꽉찬별 4.0
	}
	if(obj.likeIt > like) {
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
	$('#data').append(div1);
	
	//	$(this).parent().removeClass('active');
}