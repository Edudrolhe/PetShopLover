package com.example.petshoplover.controller; // Define o pacote onde o controlador reside

import lombok.RequiredArgsConstructor; // Lombok: Cria construtor para injeção de dependência (campos final)
import org.springframework.http.ResponseEntity; // Classe do Spring para customizar a resposta HTTP (status, corpo)
import org.springframework.web.bind.annotation.*; // Importa anotações para rotas (Get, Post, Put, Delete)

import com.example.petshoplover.dto.CategoryDTO; // Importa o DTO para transferência de dados
import com.example.petshoplover.model.Category; // Importa a entidade de banco de dados
import com.example.petshoplover.repository.CategoryRepository; // Importa o acesso ao banco

import java.util.List; // Interface para coleções de dados
import java.util.stream.Collectors; // Utilitário para transformar listas

@RestController // Define que a classe é um controlador que retorna JSON
@RequestMapping("/api/categories") // Define o prefixo da URL para todos os métodos desta classe
@RequiredArgsConstructor // Injeta o categoryRepository automaticamente via construtor
public class CategoryController {

    private final CategoryRepository categoryRepository; // Dependência do repositório (imutável)

    @GetMapping // Responde a requisições GET em /api/categories (Listagem)
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryRepository.findAll() // Busca todas as categorias do banco
                .stream() // Inicia fluxo de processamento
                .map(CategoryDTO::fromEntity) // Converte cada Entity em DTO
                .collect(Collectors.toList()); // Agrupa de volta em uma lista
        return ResponseEntity.ok(categories); // Retorna 200 OK com a lista no corpo
    }

    @GetMapping("/{id}") // Responde a GET em /api/categories/{id} (Busca única)
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
        return categoryRepository.findById(id) // Busca no banco pelo ID
                .map(category -> ResponseEntity.ok(CategoryDTO.fromEntity(category))) // Se achar, retorna 200 OK
                .orElse(ResponseEntity.notFound().build()); // Se não achar, retorna 404 Not Found
    }

    @PostMapping // Responde a requisições POST (Criação)
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryDTO.toEntity(); // Converte o DTO recebido para Entity
        Category savedCategory = categoryRepository.save(category); // Salva no banco de dados
        return ResponseEntity.ok(CategoryDTO.fromEntity(savedCategory)); // Retorna 200 OK com o objeto salvo
    }

    @PutMapping("/{id}") // Responde a requisições PUT (Atualização completa)
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        return categoryRepository.findById(id) // Busca o registro existente
                .map(existingCategory -> {
                    // Atualiza os dados da entidade existente com os dados do DTO
                    existingCategory.setName(categoryDTO.getName());
                    existingCategory.setDescription(categoryDTO.getDescription());
                    existingCategory.setDisplayOrder(categoryDTO.getDisplayOrder());
                    existingCategory.setIsActive(categoryDTO.getIsActive());
                    Category updatedCategory = categoryRepository.save(existingCategory); // Salva as mudanças
                    return ResponseEntity.ok(CategoryDTO.fromEntity(updatedCategory)); // Retorna o DTO atualizado
                })
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 se o ID não existir
    }

    @DeleteMapping("/{id}") // Responde a requisições DELETE (Remoção)
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        if (categoryRepository.existsById(id)) { // Verifica se o registro existe
            categoryRepository.deleteById(id); // Remove do banco
            return ResponseEntity.ok().build(); // Retorna 200 OK (sem corpo)
        }
        return ResponseEntity.notFound().build(); // Retorna 404 se não houver o ID
    }
}
