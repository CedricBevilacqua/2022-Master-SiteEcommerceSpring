$(document).on('click', ".add-to-cart", function(){
	var quantity = 1
	
	add_article_to_cart(this.id, quantity);
});

$(document).on('click', '.delete-from-cart', function(){
	delete_article_from_cart(this.id);
});

$(document).ready(function(){
	update_cart_snippet();
});

var ctxPath = $('.ctxPath').attr('id');

function add_article_to_cart(id, quantity){
	if(quantity == null){
		quantity = 1
	}
	
	var data = JSON.stringify({id: id, quantity: quantity});
	
	$.ajax({
		type:'POST',
		url:ctxPath+'/cart/add.json',
		dataType:'json',
		contentType: 'application/json',
		data: data,
		success: function(response){
			update_cart_snippet();
		}
	});
}

function delete_article_from_cart(id){	
	var data = JSON.stringify({id: id, quantity: 1});
	
	$.ajax({
		type:'POST',
		url:ctxPath+'/cart/delete.json',
		dataType:'json',
		contentType: 'application/json',
		data: data,
		success: function(response){
			update_cart_snippet();
			location.reload();
		}
	});
}

function update_cart_snippet(){
	$.ajax({
		type:'GET',
		url:ctxPath+'/cart/content_header.html',
		success: function(data){
			JSON.stringify($('#cartSnippet').html(data))	
		}
	});
}

