package sra1.jee.projet.drive.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sra1.jee.projet.drive.dao.CatalogDao;
import sra1.jee.projet.drive.dto.OrderDto;
import sra1.jee.projet.drive.model.Article;
import sra1.jee.projet.drive.model.Cart;
import sra1.jee.projet.drive.model.Client;
import sra1.jee.projet.drive.model.Order;
import sra1.jee.projet.drive.model.OrderLine;
import sra1.jee.projet.drive.repository.ClientRepository;
import sra1.jee.projet.drive.repository.OrderLineRepository;
import sra1.jee.projet.drive.repository.OrderRepository;

@Controller
@RequestMapping(path="/orders")
public class OrderController {
    
    @Autowired
    OrderRepository orderRepository;
    
    @Autowired
    OrderLineRepository orderLineRepository;
    
    @Autowired
    ClientRepository clientRepository;
    
    @Autowired
    CatalogDao catalogDao;
	
	@GetMapping(path="/validate.html")
	public String createOrder(HttpSession session, Model model) {
	    Cart cart = (Cart) session.getAttribute("cart");
	    
	    if(cart == null) {
	        model.addAttribute("errorMessage", "Erreur : pas de panier trouvé.");
	        return "error";
	    }
	    
	    Long clientId = (Long) session.getAttribute("id_client");
	    
	    if(clientId == null || (boolean) session.getAttribute("authentication") != true) {
	        return "redirect:/auth/login.html";
        }
	    
	    if(!(boolean) session.getAttribute("valid_account")) {
	        model.addAttribute("errorMessage", "Vous devez valider votre compte pour pouvoir commander.");
	        return "error";
	    }
	    
	    //Récupération du client
	    Client client = clientRepository.findOne(clientId);
	    
	    Order order = new Order();
	    
	    order.setClient(client);
	    order.setTotalPrice(cart.getCartTotalPrice());
	    order.setCreatedOn(new Date());
	        
	    //Saving the order into the database
	    orderRepository.save(order);
	    
	    //Creating an order line for each article in the cart and saving it into the database
	    for(Entry<Article, Integer> entry : cart.getCartContent().entrySet()) {
	        OrderLine orderLine = new OrderLine();
	        orderLine.setArticleId(entry.getKey().getId());
	        orderLine.setQuantity(entry.getValue());
	        orderLine.setOrder(order);
	        orderLineRepository.save(orderLine);
	    }
	    
	    //Emptying cart
	    cart.emptyCart();
	    
	    List<OrderLine> orderLines = orderLineRepository.findAllByOrderId(order.getId());
	    
	    model.addAttribute("orderLines", orderLines);
	    model.addAttribute("order", order);
	    
	    return "order/confirm";
	}
	
	@GetMapping(path="/history.html")
	public String getClientOrderHistory(HttpSession session, Model model) {
	    Long clientId = (Long) session.getAttribute("id_client");
	    
	    if(clientId == null || (boolean) session.getAttribute("authentication") != true) {
	        return "redirect:/auth/login.html";
	    }
	    
	    List<Order> clientOrders = (List<Order>) orderRepository.findByClientId(clientId);
	    
	    model.addAttribute("orders", clientOrders);
	    
	    return "order/clientHistory";
	}
	
	@PostMapping(path="/details.json",consumes=MediaType.APPLICATION_JSON_VALUE)   
	public String getOrderDetails(@RequestBody OrderDto order, Model model, HttpSession session) {
	    if((boolean) session.getAttribute("authentication") != true) {
	        model.addAttribute("errorMessage", "Cette opération nécessite votre connection");
            return "invalid_activation_link";
        }
	    
	    List<OrderLine> orderLines = orderLineRepository.findAllByOrderId(order.getOrderId());
	    
	    //Mapping the article of each order line to its quantity
	    Map<Article,Integer> map = new HashMap<>();
	    
	    for(OrderLine line : orderLines) {
	        Article article = catalogDao.getArticleById(line.getArticleId());
	        map.put(article, line.getQuantity());
	    }
	    
	    model.addAttribute("orderLinesMap", map);
	    model.addAttribute("orderId", order.getOrderId());
	    
	    return "/order/_order_details";
	}

}
