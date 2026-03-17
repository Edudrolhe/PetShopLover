package com.example.petshoplover.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.petshoplover.model.Category;
import com.example.petshoplover.model.Product;
import com.example.petshoplover.repository.CategoryRepository;
import com.example.petshoplover.repository.ProductRepository;

import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
@Profile("!test")
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            loadCategories();
            loadProducts();
        }
    }

    private void loadCategories() {
        Category racao = Category.builder()
                .name("Ração")
                .description("Rações para cães e gatos de todas as idades")
                .displayOrder(1)
                .isActive(true)
                .build();

        Category petiscos = Category.builder()
                .name("Petiscos")
                .description("Snacks e petiscos saborosos para seu pet")
                .displayOrder(2)
                .isActive(true)
                .build();

        Category acessorios = Category.builder()
                .name("Acessórios")
                .description("Coleiras, guias, brinquedos e muito mais")
                .displayOrder(3)
                .isActive(true)
                .build();

        Category higiene = Category.builder()
                .name("Higiene")
                .description("Produtos de banho, limpeza e cuidados")
                .displayOrder(4)
                .isActive(true)
                .build();

        Category medicamentos = Category.builder()
                .name("Medicamentos")
                .description("Remédios e suplementos para saúde do pet")
                .displayOrder(5)
                .isActive(true)
                .build();

        categoryRepository.save(racao);
        categoryRepository.save(petiscos);
        categoryRepository.save(acessorios);
        categoryRepository.save(higiene);
        categoryRepository.save(medicamentos);
    }

    private void loadProducts() {
        var categorias = categoryRepository.findAll();

        Category racao = categorias.stream()
                .filter(c -> c.getName().equals("Ração"))
                .findFirst()
                .orElse(null);

        Category petiscos = categorias.stream()
                .filter(c -> c.getName().equals("Petiscos"))
                .findFirst()
                .orElse(null);

        Category acessorios = categorias.stream()
                .filter(c -> c.getName().equals("Acessórios"))
                .findFirst()
                .orElse(null);

        Category higiene = categorias.stream()
                .filter(c -> c.getName().equals("Higiene"))
                .findFirst()
                .orElse(null);

        if (racao != null) {
            productRepository.save(Product.builder()
                    .name("Ração Premium Cães Adultos")
                    .description("Ração super premium para cães adultos de todas as raças")
                    .price(new BigDecimal("189.90"))
                    .imageUrl("images/products/racao-caes.jpg")
                    .category(racao)
                    .isPromotion(true)
                    .promotionPrice(new BigDecimal("159.90"))
                    .isAvailable(true)
                    .isHighlight(true)
                    .build());

            productRepository.save(Product.builder()
                    .name("Ração Premium Gatos Filhotes")
                    .description("Ração especialmente formulada para gatos filhotes")
                    .price(new BigDecimal("159.90"))
                    .imageUrl("images/products/racao-gatos.jpg")
                    .category(racao)
                    .isPromotion(false)
                    .isAvailable(true)
                    .isHighlight(false)
                    .build());

            productRepository.save(Product.builder()
                    .name("Ração Diet para Cães")
                    .description("Ração para cães com necessidade de controle de peso")
                    .price(new BigDecimal("199.90"))
                    .imageUrl("images/products/racao-diet.jpg")
                    .category(racao)
                    .isPromotion(true)
                    .promotionPrice(new BigDecimal("179.90"))
                    .isAvailable(true)
                    .isHighlight(false)
                    .build());
        }

        if (petiscos != null) {
            productRepository.save(Product.builder()
                    .name("Biscoitos para Cães")
                    .description("Biscoitos saborosos e nutritivos para cães")
                    .price(new BigDecimal("29.90"))
                    .imageUrl("images/products/biscoitos.jpg")
                    .category(petiscos)
                    .isPromotion(false)
                    .isAvailable(true)
                    .isHighlight(true)
                    .build());

            productRepository.save(Product.builder()
                    .name("Snacks para Gatos")
                    .description("Petiscos irresistíveis para gatos")
                    .price(new BigDecimal("24.90"))
                    .imageUrl("images/products/snacks-gatos.jpg")
                    .category(petiscos)
                    .isPromotion(false)
                    .isAvailable(true)
                    .isHighlight(false)
                    .build());
        }

        if (acessorios != null) {
            productRepository.save(Product.builder()
                    .name("Coleira de Couro")
                    .description("Coleira resistente e elegante para cães")
                    .price(new BigDecimal("89.90"))
                    .imageUrl("images/products/coleira.jpg")
                    .category(acessorios)
                    .isPromotion(true)
                    .promotionPrice(new BigDecimal("69.90"))
                    .isAvailable(true)
                    .isHighlight(true)
                    .build());

            productRepository.save(Product.builder()
                    .name("Brinquedo Interativo")
                    .description("Brinquedo que estimula o desenvolvimento do pet")
                    .price(new BigDecimal("49.90"))
                    .imageUrl("images/products/brinquedo.jpg")
                    .category(acessorios)
                    .isPromotion(false)
                    .isAvailable(true)
                    .isHighlight(false)
                    .build());

            productRepository.save(Product.builder()
                    .name("Cama para Pets")
                    .description("Cama macia e confortável para Dogs e Gatos")
                    .price(new BigDecimal("129.90"))
                    .imageUrl("images/products/cama.jpg")
                    .category(acessorios)
                    .isPromotion(false)
                    .isAvailable(true)
                    .isHighlight(true)
                    .build());
        }

        if (higiene != null) {
            productRepository.save(Product.builder()
                    .name("Shampoo Pet Premium")
                    .description("Shampoo hipoalergênico para cães e gatos")
                    .price(new BigDecimal("39.90"))
                    .imageUrl("images/products/shampoo.jpg")
                    .category(higiene)
                    .isPromotion(false)
                    .isAvailable(true)
                    .isHighlight(false)
                    .build());

            productRepository.save(Product.builder()
                    .name("Areia Higiênica")
                    .description("Areia agglomerante perfumada para gatos")
                    .price(new BigDecimal("34.90"))
                    .imageUrl("images/products/areia.jpg")
                    .category(higiene)
                    .isPromotion(true)
                    .promotionPrice(new BigDecimal("29.90"))
                    .isAvailable(true)
                    .isHighlight(false)
                    .build());
        }
    }
}
