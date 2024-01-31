package fr.fms.entities;

public class Article {
	
	private int IdArticle;
	private String Description;
	private String Brand;
	private double UnitaryPrice;
	private int IdCategory;
	
	public Article(int idArticle, String description, String brand, double unitaryPrice, int idCategory) {
		IdArticle = idArticle;
		Description = description;
		Brand = brand;
		UnitaryPrice = unitaryPrice;
		IdCategory = idCategory;
	}

	public int getIdArticle() {
		return IdArticle;
	}

	public void setIdArticle(int idArticle) {
		IdArticle = idArticle;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public double getUnitaryPrice() {
		return UnitaryPrice;
	}

	public void setUnitaryPrice(double unitaryPrice) {
		UnitaryPrice = unitaryPrice;
	}

	public int getIdCategory() {
		return IdCategory;
	}

	public void setIdCategory(int idCategory) {
		IdCategory = idCategory;
	}

	@Override
	public String toString() {
		return "Article [IdArticle=" + IdArticle + ", Description=" + Description + ", Brand=" + Brand
				+ ", UnitaryPrice=" + UnitaryPrice + ", IdCategory=" + IdCategory + "]";
	}

}
