package com.emilyread.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emilyread.products.models.Category;
import com.emilyread.products.models.Product;
import com.emilyread.products.repositories.CategoryRepo;
import com.emilyread.products.repositories.ProductRepo;

@Service
public class ProductService {
	

	private final ProductRepo productRepo;
	private final CategoryRepo categoryRepo;
	
	public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo){
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
	}
	
	public List<Product> allProducts(){
		return productRepo.findAll();
	}
	public Product createProduct(Product p) {
		return productRepo.save(p);
	}
	
	 public Product findProduct(Long id) {
	        Optional<Product> optionalProduct = productRepo.findById(id);
	        if(optionalProduct.isPresent()) {
	            return optionalProduct.get();
	        } else {
	            return null;
	        }
	    }
	 
	 public Category findCategory(Long id) {
	        Optional<Category> optionalCategory = categoryRepo.findById(id);
	        if(optionalCategory.isPresent()) {
	            return optionalCategory.get();
	        } else {
	            return null;
	        }
	    }
	 
	 public List<Product> productsInCategory(Category c){
			return productRepo.findAllByCategories(c);
		}
	
	 public List<Product> productsNotInCategory(Category c){
		return productRepo.findByCategoriesNotContains(c);
	 }
	 

	 public Product updateProduct(Product p, Long productId, Long categoryId) {
		 Category thisCategory = findCategory(categoryId);
		 Product thisProduct = findProduct(productId);
		 thisProduct.getCategories().add(thisCategory);
		 return productRepo.save(thisProduct);
	 }
}
