package com.example.petshoplover.dto; // Define o pacote dos DTOs

import lombok.*; // Importa as anotações para gerar código repetitivo (getters, setters, etc)
import java.math.BigDecimal; // Importa o tipo correto para lidar com dinheiro

import com.example.petshoplover.model.Category; // Importa a entidade Category
import com.example.petshoplover.model.Product; // Importa a entidade Product

@Getter // Gera métodos get automaticamente
@Setter // Gera métodos set automaticamente
@AllArgsConstructor // Gera construtor com todos os campos
@NoArgsConstructor // Gera construtor sem argumentos
@Builder // Ativa o padrão Builder para criar o objeto de forma fluida
public class ProductDTO {

    private Integer id; // ID único do produto
    private String name; // Nome do produto
    private String description; // Descrição detalhada
    private BigDecimal price; // Preço original
    private String imageUrl; // URL da imagem para exibição
    private Integer categoryId; // ID da categoria (simplifica o JSON para o Front)
    private String categoryName; // Nome da categoria (facilita exibição sem nova busca)
    private Boolean isPromotion; // Flag se está em oferta
    private BigDecimal promotionPrice; // Preço com desconto
    private Boolean isAvailable; // Flag se há estoque/disponibilidade
    private Boolean isHighlight; // Flag para produtos em destaque (ex: banners)

    // Método que transforma um Produto do banco (Entity) para o formato da API (DTO)
    public static ProductDTO fromEntity(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                // Verifica se a categoria existe para evitar erro de ponteiro nulo (NullPointerException)
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .isPromotion(product.getIsPromotion())
                .promotionPrice(product.getPromotionPrice())
                .isAvailable(product.getIsAvailable())
                .isHighlight(product.getIsHighlight())
                .build();
    }

    // Método que transforma o DTO em uma Entidade para ser salva no banco
    // Recebe a 'category' como parâmetro pois o DTO só tem o ID dela
    public Product toEntity(Category category) {
        return Product.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .imageUrl(this.imageUrl)
                .category(category) // Associa o objeto Category completo
                .isPromotion(this.isPromotion)
                .promotionPrice(this.promotionPrice)
                .isAvailable(this.isAvailable)
                .isHighlight(this.isHighlight)
                .build();
    }
}
