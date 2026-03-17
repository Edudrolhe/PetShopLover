package com.example.petshoplover.model; // Localização da classe no projeto

import jakarta.persistence.*; // Biblioteca para mapear a classe ao banco de dados
import lombok.*; // Biblioteca para automatizar getters, setters e construtores

@Getter // Gera automaticamente todos os métodos get (ex: getName)
@Setter // Gera automaticamente todos os métodos set (ex: setName)
@AllArgsConstructor // Gera um construtor que recebe todos os campos como parâmetro
@NoArgsConstructor // Gera o construtor vazio exigido pelo Hibernate/JPA
@Builder // Ativa o padrão de projeto Builder (ex: Category.builder().name("Ração").build())
@Table(name = "category") // Define que o nome da tabela no banco de dados será "category"
@Entity // Identifica que esta classe representa uma tabela no banco
public class Category {

    @Id // Define o campo 'id' como a chave primária
    @GeneratedValue(strategy = GenerationType.AUTO) // O banco de dados gera o ID automaticamente
    private Integer id;

    @Column(name = "name") // Mapeia o atributo para a coluna "name" (nome da categoria)
    private String name;

    @Column(name = "description") // Mapeia para a coluna "description" (detalhes da categoria)
    private String description;

    @Column(name = "display_order") // Define a ordem de exibição no menu ou lista (ex: 1, 2, 3)
    private Integer displayOrder;

    @Column(name = "is_active") // Flag para ativar ou desativar a categoria sem excluí-la
    private Boolean isActive;
}
