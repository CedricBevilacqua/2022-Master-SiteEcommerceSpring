package sra1.jee.projet.drive.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ClientEntry {
        

		
		@NotEmpty(message="Le prénom est requis")
		private String firstname;
		
		@NotEmpty(message="Le nom est requis")
		private String lastname;
		
		@NotEmpty(message="L'adresse mail est requise")
        @Email(message="L'email est invalide")
        private String mail;

		@Pattern(regexp = "(?=.*\\d)(?=.*[A-Z]).{8,}",
				message = "Le mot de passe doit contenir au moins un chiffre, une lettre majuscule et au moins 8 caractères.")
		private String password;
		
        @Min(value=1, message="Le numéro de la rue est invalide")
        private int numberHouse;

		@NotEmpty(message="La rue est requise")
		private String street;
		
		@NotEmpty(message="La ville est requise")
		private String town;
		
		@NotEmpty(message="Le pays est requis")
        private String country;
        
        @Size(min=5, max=5, message="Le code postal est invalide")
        private String zipCode;

        private boolean isValid = false;

        private String role = "CLIENT";

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

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
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

}
