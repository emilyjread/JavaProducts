package com.emilyread.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emilyread.products.models.Category;
import com.emilyread.products.models.Product;
import com.emilyread.products.repositories.CategoryRepo;
import com.emilyread.products.repositories.ProductRepo;

      
@Service
public class CategoryService {
	
	private final ProductRepo productRepo;
	private final CategoryRepo categoryRepo;
	
	
	public CategoryService(CategoryRepo categoryRepo, ProductRepo productRepo) {
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
	}

	public List<Category> allCategories(){
		return categoryRepo.findAll();
	}
	public Category createCategory(Category c) {
		return categoryRepo.save(c);
	}
	
	 public Category findCategory(Long id) {
	        Optional<Category> optionalCategory = categoryRepo.findById(id);
	        if(optionalCategory.isPresent()) {
	            return optionalCategory.get();
	        } else {
	            return null;
	        }
	    }
	 
	 public Product findProduct(Long id) {
	        Optional<Product> optionalProduct = productRepo.findById(id);
	        if(optionalProduct.isPresent()) {
	            return optionalProduct.get();
	        } else {
	            return null;
	        }
	    }
	 
	public List<Category> categoriesByProduct(Product p){
			return categoryRepo.findAllByProducts(p);
		}
	
	public List<Category> categoriesByProductNoContained(Product p){
		return categoryRepo.findByProductsNotContains(p);
	}
	
	 public Category updateCategory(Category c, Long categoryId, Long productId) {
		 Category thisCategory = findCategory(categoryId);
		 Product thisProduct = findProduct(productId);
		 thisCategory.getProducts().add(thisProduct);
		 return categoryRepo.save(thisCategory);
	 }
}
