package sra1.jee.projet.drive.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sra1.jee.projet.drive.mock.CatalogDaoMock;
import sra1.jee.projet.drive.model.Article;
import sra1.jee.projet.drive.model.Category;

@Controller
@RequestMapping(path="/catalog")
public class CatalogController {
	
	@Autowired
	@Qualifier("mock")
	CatalogDaoMock catalogDao;
	
	@GetMapping(path="/category/{id}.html")
	public String listCategoryArticles(@PathVariable int id, Model model) {
		List<Article> articles = catalogDao.findArticlesByCategoryId(String.valueOf(id));
		model.addAttribute("articles", articles);
		return "catalog/article_display";
	}
	
	@GetMapping(path="/categories.html")
	public String listCategories(Model model) {
		List<Category> categories = catalogDao.getCategories();
		model.addAttribute("categories", categories);
		return "catalog/category_display";
	}
	
}
