package sra1.jee.projet.drive.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {
	
	//Maps article id to quantity
	Map<Article,Integer> cartContent;
	
	public Cart() {
		this.cartContent = new HashMap<Article, Integer>();
	}
	
	public void addArticle(Article article, int quantity) {
		//Check if article already in cart : if so -> update quantity
		if(this.cartContent.containsKey(article)) {
			this.cartContent.put(article, this.cartContent.get(article)+quantity);
		} else {
			this.cartContent.put(article, quantity);
		}
	}
	
	public void deteteArticle(Article article) {
		this.cartContent.remove(article);
	}
	
	public Map<Article,Integer> getCartContent(){
		return this.cartContent;
	}
	
	public double getCartTotalPrice() {
		double totalPrice = 0;
		
		for(Entry<Article,Integer> entry : this.cartContent.entrySet()) {
			totalPrice += Integer.valueOf(entry.getKey().getPrice()) * entry.getValue();
		}
		
		return totalPrice;
	}
	
	public void emptyCart() {
	    this.cartContent.clear();
	}

}
