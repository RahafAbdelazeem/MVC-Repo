package com.admin.app.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name="product_details")
@Setter
@Getter
@NoArgsConstructor
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int   id;


    @NotBlank(message = "Product name cannot be blank.")
    @Size(max = 64, message = "Product name cannot exceed 64 characters.")
    @Column(name="name")
    private String  name;

    @NotNull(message = "date cannot be null")
    @Temporal(TemporalType.DATE)
    @Column(name="expiration_date")
    private Date expirationDate;

    @Column(name="manufacturer")
    private  String  manufacturer;

    @NotNull(message = "Product price cannot be null.")
    @Min(value = 0, message = "Product price cannot be negative.")
    @Column(name= "price")
    private  Double price;

   @NotNull(message = "available cannot be null")
    @Column(name=" available")
    private  Boolean available;

    @OneToOne(mappedBy = "productDetails" , cascade = CascadeType.ALL)
    private Product product;




}
