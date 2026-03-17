package com.example.petshoplover.model; // Define o pacote onde a classe está localizada

import jakarta.persistence.*; // Importa anotações da JPA para mapeamento banco de dados
import lombok.*; // Importa anotações do Lombok para reduzir código repetitivo (boilerplate)
import java.math.BigDecimal; // Importa classe para lidar com valores monetários com precisão

@Getter // Lombok: Gera automaticamente os métodos get para todos os campos
@Setter // Lombok: Gera automaticamente os métodos set para todos os campos
@AllArgsConstructor // Lombok: Cria um construtor com todos os atributos como argumentos
@NoArgsConstructor // Lombok: Cria um construtor vazio (obrigatório para a JPA)
@Builder // Lombok: Permite criar objetos usando o padrão de projeto Builder
@Table(name = "product") // JPA: Especifica que esta classe mapeia a tabela "product" no banco
@Entity // JPA: Indica que esta classe é uma entidade persistente
public class Product {

    @Id // Define o campo abaixo como a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.AUTO) // Delega ao banco a geração automática do ID
    private Integer id;

    @Column(name = "name") // Mapeia o atributo para a coluna "name"
    private String name;

    @Column(name = "description") // Mapeia o atributo para a coluna "description"
    private String description;

    @Column(name = "price") // Mapeia o atributo para a coluna "price"
    private BigDecimal price; // BigDecimal é ideal para dinheiro

    @Column(name = "image_url") // Mapeia o link da imagem para a coluna "image_url"
    private String imageUrl;

    @ManyToOne // Define relacionamento: muitos produtos podem pertencer a uma categoria
    @JoinColumn(name = "category_id") // Define o nome da coluna de chave estrangeira (FK)
    private Category category;

    @Column(name = "is_promotion") // Flag para identificar se o item está em promoção
    private Boolean isPromotion;

    @Column(name = "promotion_price") // Guarda o valor promocional, se houver
    private BigDecimal promotionPrice;

    @Column(name = "is_available") // Flag para controle de estoque ou visibilidade (ativo/inativo)
    private Boolean isAvailable;

    @Column(name = "is_highlight") // Flag para destacar o produto na vitrine/home do site
    private Boolean isHighlight;
}
