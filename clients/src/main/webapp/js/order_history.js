var ctxPath = $('.ctxPath').attr('id');

$(document).on('click', '.show-order-details', function(){
	show_order_details(this.id);
});

$(document).on('click', '.hide-order-details', function(){
	hide_order_details(this.id);
});

function show_order_details(id){
	
	var data = JSON.stringify({orderId: id});
	
	$.ajax({
		type:'POST',
		url:ctxPath+'/orders/details.json',
		dataType:'json',
		contentType: 'application/json',
		data: data,
		complete: function(responseData){
			$('#order-details-'+id).html(responseData.responseText);
		}
	});
}

function hide_order_details(id){
	JSON.stringify($('#order-details-'+id).html("<!-- nothing to show here... -->"));
}