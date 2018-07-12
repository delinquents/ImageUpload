package com.velja.product.imageproductupload.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity@Table(name = "product")
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@EqualsAndHashCode@ToString
public class Product {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "price")
    private Float price;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    @Lob
    private String description;

    @Lob@Column(name = "image")
    private Byte[] image;

    public Product(Float price,String name, String description, Byte[] image) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
