
function showList(result) {
	let data = result;
	console.log(data);
	let ul = $('<ul />');		
	data.forEach(function(item, idx) {	//item: {...}, item.id: {...}안의 {id..}값.
		let li = $('<li />').html(item.id + " ::: ")
						.attr('id', item.id);
						
	//li.mouseover(function() { }) 이렇게 해도되고,
		$(li).on({	// key,value 형식으로
		'mouseover': mouseOverCallback,	// callback 함수 생성.
		'mouseout':function() {
		$(this).css('background','');
		}
		}); 
		let aTag = $('<a />').html(item.firstName)
						.attr('href','jquery_empInfo.jsp?id='+item.id);
		li.append(aTag);
		ul.append(li);
	
		localStorage.setItem('eid'+item.id, item.id);	
		localStorage.setItem('fNm'+item.id, item.firstName); // name+data.id 로 식별하기 위해.
		localStorage.setItem('lNm'+item.id, item.lastName);
		localStorage.setItem('eml'+item.id, item.email);
		localStorage.setItem('sal'+item.id, item.salary);
		localStorage.setItem('jid'+item.id, item.jobId);//######jid100 = JobId
		
	});	
	$('#result').append(ul);
	// data 건수 만큼 반복해서 function()실행.
	// data.forEach((item, idx)=>{	});	람다식
	
}

function mouseOverCallback(event) {
	// localStorage: 웹 스토리지를 사용하면 웹 애플리케이션이 사용자의 브라우저 내에 로컬로 데이터를 저장.
	// window.localStorage -만료일없이 데이터 저장
	// event의 대성: currentTarget: li#101. -> 스토리지에서 가져올 때 식별 할 것.
	//console.log(event.currentTarget.id);
	let eid = localStorage.getItem('eid'+event.currentTarget.id);	// localStorage.setItem('name'+data.id,item.firstName); 으로 setting 해줌.
	let fNm = localStorage.getItem('fNm'+event.currentTarget.id);	//currentTarget.id 의 firstName.
	let lNm = localStorage.getItem('lNm'+event.currentTarget.id);
	let eml= localStorage.getItem('eml'+event.currentTarget.id);
	let sal= localStorage.getItem('sal'+event.currentTarget.id);
	let jid= localStorage.getItem('jid'+event.currentTarget.id);//######담은 jobId를 currentTarget.id로 식별해, jid로 불러온뒤, let에 담음.
	
	$(this).css('background','lightblue');	//this: liTag
	$('#eid').val(eid);
	$('#fName').val(fNm);
	$('#lName').val(lNm);
	$('#email').val(eml);
	$('#salary').val(sal);
	$('#jobId').val(jid); // storage에서 가져온jid값을 value에 넣어준다.
}

function mouseoverFunc() {
	let id = $(this).attr('id');
	
	$.ajax({
		url: 'GetEmpInfoServlet',	
		data: {id: id},	// {parameter id: 값 id} 
		success: function(result) {
			console.log(result);				
		},
		dataType: 'json'
	});
		
};