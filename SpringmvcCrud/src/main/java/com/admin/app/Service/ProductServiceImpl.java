package com.admin.app.Service;

import com.admin.app.Dao.ProductDAO;
import com.admin.app.Model.Product;
import com.admin.app.Model.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;


    @Override
    public ProductDetails Add(ProductDetails productDetails) {
        if(!productDetails.getName().isEmpty()){
            productDAO.addProduct(productDetails);
            return productDetails;
        }
        else
         throw  new NullPointerException();
    }

    @Override
    public Product findProductById(int Id) {return productDAO.findProductById(Id);}

    @Override
    public void deleteById(int id) {productDAO.deleteById(id);
    }

    @Override
    public ProductDetails Update(ProductDetails productDetails) {
         Product product = productDAO.findByProductDetails(productDetails);
          if(product!=null)
          {
              productDAO.update(productDetails);
          }
          else {
              throw new NullPointerException();
          }
        return productDetails;
    }

    @Override
    @Transactional
    public List<Product> getAllProduct() {
         try{
             return productDAO.getAllProducts();
         }catch(Exception exception)
         {
             exception.printStackTrace();
         }
        return null;
    }

    @Override
    @Transactional
    public List<Product> findProductByName(String name) {return productDAO.findProductByName(name);}
}
