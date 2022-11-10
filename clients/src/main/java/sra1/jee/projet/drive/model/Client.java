package sra1.jee.projet.drive.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Clients")
public class Client {

    @Id
    @GeneratedValue
    private long id;
    
    @Column(unique=true)
    private String mail; 
    
	@OneToMany(targetEntity=ReclamationMessage.class, mappedBy="reclamation")
	private List<ReclamationMessage> messages;

    private String 
            firstname,
            lastname,
            password,
            street,
            town,
            country;


    
    private int numberHouse;
    
    private String zipCode;


    private boolean isValid = false;

    private String role = "CLIENT";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String string) {
		this.zipCode = string;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getNumberHouse() {
		return numberHouse;
	}

	public void setNumberHouse(int number_house) {
		this.numberHouse = number_house;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setIs_valid(boolean is_valid) {
		this.isValid = is_valid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<ReclamationMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ReclamationMessage> messages) {
		this.messages = messages;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
