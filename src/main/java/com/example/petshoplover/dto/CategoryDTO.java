package com.example.petshoplover.dto; // Define o pacote de objetos de transferência de dados

import com.example.petshoplover.model.Category; // Importa a entidade original para conversão
import lombok.*; // Importa anotações do Lombok para reduzir código

@Getter // Gera os métodos de leitura (get)
@Setter // Gera os métodos de escrita (set)
@AllArgsConstructor // Gera construtor com todos os atributos
@NoArgsConstructor // Gera construtor padrão (vazio)
@Builder // Permite criar o DTO de forma fluida (ex: .name("Dog").build())
public class CategoryDTO {

    private Integer id; // Identificador único da categoria
    private String name; // Nome da categoria
    private String description; // Descrição detalhada
    private Integer displayOrder; // Ordem de exibição na interface
    private Boolean isActive; // Status de ativação

    // Método estático que converte uma Entidade (Banco) para um DTO (API)
    public static CategoryDTO fromEntity(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .displayOrder(category.getDisplayOrder())
                .isActive(category.getIsActive())
                .build();
    }

    // Método que converte este DTO de volta para uma Entidade (Banco)
    public Category toEntity() {
        return Category.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .displayOrder(this.displayOrder)
                .isActive(this.isActive)
                .build();
    }
}
