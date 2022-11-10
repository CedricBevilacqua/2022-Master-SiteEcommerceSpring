package sra1.jee.projet.drive.model;

import java.util.Objects;

public class Article {
	
	private String
		id,
		price,
		name,
		img,
		cat_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public String getCat_id() {
		return cat_id;
	}
	
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cat_id, id, img, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		return Objects.equals(cat_id, other.cat_id) && Objects.equals(id, other.id) && Objects.equals(img, other.img)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price);
	}
}
