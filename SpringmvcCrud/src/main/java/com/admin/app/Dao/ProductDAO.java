package com.admin.app.Dao;

import com.admin.app.Model.Product;
import com.admin.app.Model.ProductDetails;

import java.util.List;

public interface ProductDAO {
   Product  addProduct(ProductDetails product);
   Product findProductById(int id);
   Product findByProductDetails(ProductDetails productDetails);
   void deleteById( int id);
   void update(ProductDetails product);
   List<Product> getAllProducts();
   List<Product> findProductByName(String searchName);

}
