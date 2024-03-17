package com.admin.app.Service;

import com.admin.app.Model.Product;
import com.admin.app.Model.ProductDetails;

import java.util.List;

public interface ProductService {
     ProductDetails Add(ProductDetails product);
     Product findProductById(int Id);
     void deleteById(int id);
     ProductDetails Update(ProductDetails product);
     List<Product>getAllProduct();
     List <Product> findProductByName(String name);
}
