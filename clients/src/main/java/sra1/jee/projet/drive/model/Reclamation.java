package sra1.jee.projet.drive.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Reclamations")
public class Reclamation {

	@Id
	@GeneratedValue
	private Integer id;
	
//	TODO: Une fois que le model Client aura été fait, remplacer idClient par un truc du style :
//	@ManyToOne
//	private Customer customer;
	private long idClient;
	
	private String title;
	private String description;
	
	private Integer orderId;
	
	@Temporal(TemporalType.DATE)
	private Date dateTime;
	
	@Enumerated(EnumType.STRING)
	private ReclamationStatus status;
	
	@OneToMany(targetEntity=ReclamationMessage.class, mappedBy="reclamation")
	private List<ReclamationMessage> messages;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public ReclamationStatus getStatus() {
		return status;
	}

	public void setStatus(ReclamationStatus status) {
		this.status = status;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<ReclamationMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ReclamationMessage> messages) {
		this.messages = messages;
	}
}
