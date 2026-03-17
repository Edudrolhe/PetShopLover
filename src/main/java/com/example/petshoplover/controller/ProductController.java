package com.example.petshoplover.controller; // Pacote que organiza os controladores da API

import lombok.RequiredArgsConstructor; // Lombok: Injeta as dependências via construtor (campos final)
import org.springframework.http.ResponseEntity; // Classe para formatar a resposta HTTP (status, corpo)
import org.springframework.web.bind.annotation.*; // Anotações para mapear as rotas da API

import com.example.petshoplover.dto.ProductDTO; // Objeto de transferência de dados (Produtos)
import com.example.petshoplover.model.Category; // Entidade Categoria (para relacionamentos)
import com.example.petshoplover.model.Product; // Entidade Produto
import com.example.petshoplover.repository.CategoryRepository; // Acesso ao banco de Categorias
import com.example.petshoplover.repository.ProductRepository; // Acesso ao banco de Produtos

import java.util.List; // Interface de lista do Java
import java.util.stream.Collectors; // Utilitário para transformar coleções de dados

@RestController // Define a classe como um controlador REST que retorna JSON
@RequestMapping("/api/products") // Define a rota base da API como /api/products
@RequiredArgsConstructor // Cria automaticamente o construtor com os repositórios injetados
public class ProductController {

    private final ProductRepository productRepository; // Repositório de produtos (imutável)
    private final CategoryRepository categoryRepository; // Repositório de categorias (necessário para o vínculo)

    @GetMapping // GET /api/products - Lista todos os produtos
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productRepository.findAll() // Busca todos no banco
                .stream() // Inicia o fluxo de dados
                .map(ProductDTO::fromEntity) // Converte cada Entidade em DTO
                .collect(Collectors.toList()); // Transforma o fluxo em lista
        return ResponseEntity.ok(products); // Retorna 200 OK com a lista
    }

    @GetMapping("/{id}") // GET /api/products/{id} - Busca um produto específico
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return productRepository.findById(id) // Procura no banco pelo ID
                .map(product -> ResponseEntity.ok(ProductDTO.fromEntity(product))) // Se achar, retorna 200
                .orElse(ResponseEntity.notFound().build()); // Se não achar, retorna 404 Not Found
    }

    @PostMapping // POST /api/products - Cria um novo produto
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        // Busca a categoria no banco usando o ID enviado no DTO
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElse(null); // Se a categoria não existir, define como nulo
        
        Product product = productDTO.toEntity(category); // Converte DTO para Entidade vinculando a categoria
        Product savedProduct = productRepository.save(product); // Salva no banco de dados
        return ResponseEntity.ok(ProductDTO.fromEntity(savedProduct)); // Retorna 200 e o produto criado
    }

    @PutMapping("/{id}") // PUT /api/products/{id} - Atualiza um produto existente
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        return productRepository.findById(id) // Tenta localizar o produto no banco
                .map(existingProduct -> {
                    // Busca a nova categoria ou mantém a atual se a busca falhar
                    Category category = categoryRepository.findById(productDTO.getCategoryId())
                            .orElse(existingProduct.getCategory());
                    
                    // Atualiza manualmente os campos da entidade existente
                    existingProduct.setName(productDTO.getName());
                    existingProduct.setDescription(productDTO.getDescription());
                    existingProduct.setPrice(productDTO.getPrice());
                    existingProduct.setImageUrl(productDTO.getImageUrl());
                    existingProduct.setCategory(category);
                    existingProduct.setIsPromotion(productDTO.getIsPromotion());
                    existingProduct.setPromotionPrice(productDTO.getPromotionPrice());
                    existingProduct.setIsAvailable(productDTO.getIsAvailable());
                    existingProduct.setIsHighlight(productDTO.getIsHighlight());
                    
                    Product updatedProduct = productRepository.save(existingProduct); // Salva mudanças
                    return ResponseEntity.ok(ProductDTO.fromEntity(updatedProduct)); // Retorna o DTO atualizado
                })
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 se o produto não existir
    }

    @DeleteMapping("/{id}") // DELETE /api/products/{id} - Remove um produto
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        if (productRepository.existsById(id)) { // Verifica se existe antes de deletar
            productRepository.deleteById(id); // Exclui do banco
            return ResponseEntity.ok().build(); // Retorna 200 OK (Vazio)
        }
        return ResponseEntity.notFound().build(); // Retorna 404 se não houver o produto
    }

    @GetMapping("/category/{categoryId}") // GET /api/products/category/{id} - Filtro por categoria
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable Integer categoryId) {
        List<ProductDTO> products = productRepository.findAll() // Busca todos (processamento em memória)
                .stream()
                .filter(p -> p.getCategory() != null && p.getCategory().getId().equals(categoryId)) // Filtra pelo ID da categoria
                .map(ProductDTO::fromEntity) // Converte para DTO
                .collect(Collectors.toList());
        return ResponseEntity.ok(products); // Retorna a lista filtrada
    }
}
