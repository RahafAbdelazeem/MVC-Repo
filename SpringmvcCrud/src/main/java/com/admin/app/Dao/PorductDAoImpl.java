package com.admin.app.Dao;

import com.admin.app.Model.Product;
import com.admin.app.Model.ProductDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PorductDAoImpl implements  ProductDAO {
    @Autowired
    public SessionFactory sessionFactory;


    @Override
    public Product addProduct(ProductDetails productDetails) {
        try {
            Session session = sessionFactory.getCurrentSession();
            productDetails.setExpirationDate(productDetails.getExpirationDate());

            Product product = new Product(productDetails.getName());
            product.setProductDetails(productDetails);
            session.persist(product);
            return product;
        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return null;

    }

    @Override
    public Product findProductById(int id) {
        try{
             Session session= sessionFactory.getCurrentSession();
             return session.get(Product.class, id);
        } catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Product findByProductDetails(ProductDetails productDetails) {
         try {
             Session session = sessionFactory.getCurrentSession();
             ProductDetails productDetails1 = session.get(ProductDetails.class, productDetails.getId());
             return productDetails1.getProduct();
         }catch(Exception exception){
             exception.printStackTrace();
         }
        return null;
    }

    @Override
    public void deleteById(int theId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Product product = session.get(Product.class, theId);
            Query query = session.createQuery("delete FROM Product where id =:productId ");
            query.setParameter("productId", theId);
            query.executeUpdate();

            query = session.createQuery("delete  FROM   ProductDetails where id=:productDetailsId");
            query.setParameter("productDetailsId",  product.getProductDetails().getId());
            query.executeUpdate();
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void update(ProductDetails newProduct) {
         try{
             Session session= sessionFactory.getCurrentSession();
              ProductDetails oldProductDetails= session.get(ProductDetails.class,newProduct.getId());
              Product oldProduct= oldProductDetails.getProduct();
               oldProduct.setProductName(newProduct.getName());
               oldProductDetails.setName(newProduct.getName());
               oldProductDetails.setExpirationDate(newProduct.getExpirationDate());
               oldProductDetails.setManufacturer(newProduct.getManufacturer());
               oldProductDetails.setPrice(newProduct.getPrice());
               oldProductDetails.setAvailable(newProduct.getAvailable());
               oldProductDetails.setProduct(newProduct.getProduct());
                oldProduct.setProductDetails(oldProductDetails);
                 session.update(oldProduct);
         } catch(Exception exception) {
              exception.printStackTrace();
         }
    }

    @Override
    public List<Product> getAllProducts() {
         try{
             Session session= sessionFactory.getCurrentSession();
             Query query= session.createQuery("From Product ");
             return (List<Product> ) query.list();
         } catch(Exception exception){
             exception.printStackTrace();
         }
        return null;
    }

    @Override
    public List<Product > findProductByName(String searchName) {
        Session session=sessionFactory.getCurrentSession();
        Query query= session.createQuery("From  Product  where productName=:productName");
        query.setParameter("productName",searchName);
        return query.list();
    }
}
