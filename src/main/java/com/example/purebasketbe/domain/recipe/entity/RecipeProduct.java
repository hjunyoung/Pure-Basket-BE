package com.example.purebasketbe.domain.recipe.entity;

import com.example.purebasketbe.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "recipe_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    private RecipeProduct(Recipe recipe, Product product) {
        this.recipe = recipe;
        this.product = product;
    }

    public static RecipeProduct of(Recipe recipe, Product product) {
        return RecipeProduct.builder()
                .recipe(recipe)
                .product(product)
                .build();
    }

   public void addRecipe(Recipe recipe) {
       this.recipe = recipe;
   }
}