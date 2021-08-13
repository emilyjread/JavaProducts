package com.emilyread.products.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.emilyread.products.models.Category;
import com.emilyread.products.models.Product;
import com.emilyread.products.services.CategoryService;
import com.emilyread.products.services.ProductService;

@Controller
public class MainController {
	private CategoryService categoryService;
	private ProductService productService;
	
	public MainController(CategoryService categoryService, ProductService productService) {
		this.categoryService= categoryService;
		this.productService= productService;
	}

	//create product
	
	@RequestMapping("/products/new")
	public String newProduct(@ModelAttribute("product") Product product, Model model) {
		List <Category> categories = categoryService.allCategories();
		model.addAttribute("categories", categories);
		return "/products/newProduct.jsp";
	}
	
	@RequestMapping(value= "/products", method=RequestMethod.POST)
	public String createProduct(@Valid @ModelAttribute("product") Product p, BindingResult result){
		if(result.hasErrors()) {
			return "/products/new.jsp";
		}
		else {
			productService.createProduct(p);
			return "redirect:/products/new";
		}	
	}
	
	//create category
	
	@RequestMapping("/categories/new")
	public String newCategory(@ModelAttribute("category") Category category, Model model) {
		return "categories/newCategory.jsp";
	}
	
	@RequestMapping(value= "/categories", method=RequestMethod.POST)
	public String createCategory(@Valid @ModelAttribute("category") Category c, BindingResult result){
		if(result.hasErrors()) {
			return "categories/new.jsp";
		}
		else {
			categoryService.createCategory(c);
			return "redirect:/categories/new";
		}	
	}
	
	//show category
	
	@RequestMapping("/categories/{id}")
	public String showCategory(Model model, @PathVariable("id") Long id) {
		Category c= categoryService.findCategory(id);
		model.addAttribute("category", c);
		
		List<Product> productsByCategory = productService.productsInCategory(c);
		model.addAttribute("productsByCategory", productsByCategory);
		List<Product> productsNotInCategory = productService.productsNotInCategory(c);
		model.addAttribute("productsNotInCategory", productsNotInCategory);
		
		return "categories/showCategory.jsp";
		
	}
	
	//show product
	
	@RequestMapping("/products/{id}")
	public String showProducts(Model model, @PathVariable("id") Long id) {
		Product p= productService.findProduct(id);
		model.addAttribute("product", p);
		
		List<Category> categoriesOfProduct = categoryService.categoriesByProduct(p);
		model.addAttribute("categoriesOfProduct", categoriesOfProduct);
		
		List<Category> categoriesNotApplied = categoryService.categoriesByProductNoContained(p);
		model.addAttribute("categoriesNotApplied", categoriesNotApplied);
		
		return "products/showProduct.jsp";
		
	}
	
	//add category to product
	@RequestMapping(value= "/products/{id}", method=RequestMethod.PUT)
	public String addCategory(@Valid @ModelAttribute("product") Product p, BindingResult result, @PathVariable("id") Long id, @RequestParam("categories") Category category){
		if(result.hasErrors()) {
			return "product/showProduct.jsp";
		}
		else {
			productService.updateProduct(p, id, category.getId());
			return "redirect:/products/"+id;
		}	
	}
	
	//add product to category
	@RequestMapping(value= "/categories/{id}", method=RequestMethod.PUT)
	public String addProduct(@Valid @ModelAttribute("category") Category c, BindingResult result, @PathVariable("id") Long id, @RequestParam("products") Product product){
		if(result.hasErrors()) {
			return "categories/showCategory.jsp";
		}
		else {
			categoryService.updateCategory(c, id, product.getId());
			return "redirect:/categories/"+id;
		}	
	}
	
}
