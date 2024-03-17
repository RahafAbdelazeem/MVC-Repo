package com.admin.app.Controller;

import com.admin.app.Model.Product;
import com.admin.app.Model.ProductDetails;
import com.admin.app.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller

public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/")
    public String displayHomePage(Model productModel) throws ParseException {
        List<Product> productList = productService.getAllProduct();
        productModel.addAttribute("productsList", productList);
        return "homePage";
    }

    @GetMapping("/addProduct")
    public String dispalyAddnewProduct(Model model) {
        model.addAttribute("productModel", new ProductDetails());
        return "addProductForm";
    }
    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productId") int id) {
        productService.deleteById(id);
        return "redirect:/";

    }
    @GetMapping("/updateProductForm")
    public String displayUpdateProductForm(@RequestParam("productId") int id, Model Pmodel) {
        Product product = productService.findProductById(id);
        ProductDetails productDetails = product.getProductDetails();
        productDetails.setProduct(product);
        Pmodel.addAttribute("productModel", productDetails);
        return "updateDetailsForm";
    }


    @GetMapping("/showDetails")
    public String displayProductDetailsForm(@RequestParam("productId") int id, Model productmodel) {
        Product product = productService.findProductById(id);
        ProductDetails productDetails = product.getProductDetails();
        productDetails.setProduct(product);
        productmodel.addAttribute("productModel", productDetails);
        return "showDetailsForm";
    }

    @PostMapping("/processAddProduct")
    public String AddProduct(@ModelAttribute("productModel") ProductDetails productDetails, BindingResult bindingResult, Model productModel) {
        if (bindingResult.hasErrors())
            return "addProductForm";
        productService.Add(productDetails);
        List<Product> products = productService.getAllProduct();
        productModel.addAttribute("productList", products);
        return "redirect:/";
    }



    @PostMapping("/UpdateProduct")
    public String updateProduct(@ModelAttribute("productModel") ProductDetails productDetails, Model productModel) {
        productService.Update(productDetails);
        List<Product> products = productService.getAllProduct();
        productModel.addAttribute("productsList", products);
        return "redirect:/";
    }


    @GetMapping("/searchProduct")
    public String searchProduct(@RequestParam("searchName") String searchName, Model productModel) {
        List<Product> products = productService.findProductByName(searchName);
        productModel.addAttribute("product", products);

        //return products.toString();
        return "homePage";
    }


    @InitBinder
    public void initBinder(WebDataBinder  webBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        webBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}

