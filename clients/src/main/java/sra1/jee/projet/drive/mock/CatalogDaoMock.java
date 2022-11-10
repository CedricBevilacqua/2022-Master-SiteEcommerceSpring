package sra1.jee.projet.drive.mock;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import sra1.jee.projet.drive.dao.CatalogDao;
import sra1.jee.projet.drive.model.Article;
import sra1.jee.projet.drive.model.Category;

@Component
@Qualifier("mock")
public class CatalogDaoMock implements CatalogDao{
	
	private static String CATEGORY_FILE = "/data/category.csv";
	private static String ARTICLE_FILE = "/data/article.csv";
	
	private List<Category> categories;
	private List<Article> articles;
	
	public CatalogDaoMock() {
		this.categories = load(Category.class, CATEGORY_FILE);
		this.articles = load(Article.class, ARTICLE_FILE);
		
		// Fix article category
		int i = 0;
		for (Article art : this.articles) {
			i++;
			if ( i < 100 ) art.setCat_id("1");
			else if ( i < 498 ) art.setCat_id("2");
			else if ( i < 598 ) art.setCat_id("3");
			else if ( i < 691 ) art.setCat_id("4");
			else art.setCat_id("5");
		}
	}
	
	//Interface methods implementation

	@Override
	public List<Category> getCategories() {
		return this.categories;
	}

	@Override
	public List<Article> findArticlesByCategoryId(String categoryId) {
		List<Article> res = new ArrayList<>();
		for ( Article art : this.articles )
			if ( art.getCat_id().equals(categoryId)) {
				res.add(art);
			}
		return res;
	}
	
	@Override
	public Article getArticleById(String articleId) {
		for (Article article : this.articles) {
			if(article.getId().equals(articleId)) {
				return article;
			}
		}
		return null;
	}
	
	//Private methods
	
	private static <T> List<T> load(Class<T> type, String dataFile) {
		try {
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
			CsvMapper mapper = new CsvMapper();
			File file = new ClassPathResource(dataFile).getFile();
			MappingIterator<T> readValues = 
					mapper.readerFor(type)
					.with(bootstrapSchema)
					.readValues( new InputStreamReader(new FileInputStream(file),"UTF-8") );
			return readValues.readAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error occurred while loading category list from file " + CATEGORY_FILE);
			return Collections.emptyList();
		}
	}
	
}
