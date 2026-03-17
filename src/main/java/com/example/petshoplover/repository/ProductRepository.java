package com.example.petshoplover.repository; // Local onde ficam as interfaces de acesso a dados

import com.example.petshoplover.model.Product; // Importa a classe de modelo Product
import org.springframework.data.jpa.repository.JpaRepository; // Importa o "motor" do Spring Data
import org.springframework.stereotype.Repository; // Indica que esta classe é um componente de acesso ao banco

@Repository // Define esta interface como um Bean do Spring para injeção de dependência
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Ao estender JpaRepository, você ganha de graça métodos como:
    // .save()    -> Para criar ou atualizar produtos
    // .findAll() -> Para listar todos os produtos
    // .delete()  -> Para remover produtos
    // .findById()-> Para buscar um produto pelo seu ID
}
