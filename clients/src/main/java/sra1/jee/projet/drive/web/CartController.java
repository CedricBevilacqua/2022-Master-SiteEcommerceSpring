package sra1.jee.projet.drive.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sra1.jee.projet.drive.dto.CartEntry;
import sra1.jee.projet.drive.dto.Response;
import sra1.jee.projet.drive.dto.Response.Status;
import sra1.jee.projet.drive.mock.CatalogDaoMock;
import sra1.jee.projet.drive.model.Article;
import sra1.jee.projet.drive.model.Cart;

@Controller
@RequestMapping(path="/cart")
public class CartController {
	
	@Autowired
	@Qualifier("mock")
	CatalogDaoMock catalogDao;
	
	@GetMapping(path="/content_header.html")
	public String getCartContentHeader(HttpSession session, Model model) {
		Cart cart = this.getOrCreateSessionCart(session);
		
		model.addAttribute("cartSize", cart.getCartContent().size());
		model.addAttribute("cartContent", cart.getCartContent());
		model.addAttribute("totalPrice", cart.getCartTotalPrice());
		
		return "cart/_cart_header";

	}
	
	@GetMapping("/content.html")
	public String getCartContent(HttpSession session, Model model) {
		Cart cart = this.getOrCreateSessionCart(session);
		
		model.addAttribute("cartSize", cart.getCartContent().size());
		model.addAttribute("cartContent", cart.getCartContent());
		model.addAttribute("totalPrice", cart.getCartTotalPrice());
		
		return "cart/cart_display";
	}
	
	@ResponseBody
	@PostMapping(path="/add.json", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response addArticle(@RequestBody CartEntry entry, HttpSession session) {
		Cart cart = this.getOrCreateSessionCart(session);
		Response resp = new Response();
		
		//Checking quantity
		if(entry.getQuantity() < 1) {
			resp.status = Status.ERROR;
			resp.message = "Cannot add a negative quantity";
			return resp;
		}
		
		//Article article = this.articleDao.find(entry.getId());
		Article article = this.catalogDao.getArticleById(String.valueOf(entry.getId()));
		
		//Checking if article exists
		if(article == null) {
			resp.status = Status.ERROR;
			resp.message = "Article with id "+entry.getId()+" does not exists.";
			return resp;
		}
		
		//Adding the article to the cart
		cart.addArticle(article, entry.getQuantity());
		
		return resp;
	}
	
	@ResponseBody
	@PostMapping(path="/delete.json", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response deleteArticle(@RequestBody CartEntry entry, HttpSession session) {
		Cart cart = this.getOrCreateSessionCart(session);
		Response resp = new Response();
		
		//Article article = this.articleDao.find(entry.getId());
		Article article = this.catalogDao.getArticleById(String.valueOf(entry.getId()));
		
		//Checking if article exists
		if(article == null) {
			resp.status = Status.ERROR;
			resp.message = "Article with id "+entry.getId()+" does not exists.";
			return resp;
		}
		
		cart.deteteArticle(article);
		
		return resp;
	}
	
	private Cart getOrCreateSessionCart(HttpSession session) {
		//Getting the cart stored in the session
		Cart cart = (Cart) session.getAttribute("cart");
		
		//If the cart does not exists, creates a new cart
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		
		return cart;
	}
}
