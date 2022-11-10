package sra1.jee.projet.drive.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Commandes")
public class Order {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private Client client;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private String description;
	private Double totalPrice = 0.00;
	
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getCreatedOn() {
        return createdOn;
    }
    
    public void setCreatedOn(Date date) {
        this.createdOn = date;
    }
    
    public Double getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}