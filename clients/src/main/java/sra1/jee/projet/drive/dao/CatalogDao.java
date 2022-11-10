package sra1.jee.projet.drive.dao;

import java.util.List;

import sra1.jee.projet.drive.model.Article;
import sra1.jee.projet.drive.model.Category;

public interface CatalogDao {

	List<Category> getCategories();
	
	List<Article> findArticlesByCategoryId(String categoryId);
	
	Article getArticleById(String articleId);
}
