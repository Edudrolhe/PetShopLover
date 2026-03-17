package com.example.petshoplover.repository; // Define o pacote de repositórios do projeto

import com.example.petshoplover.model.Category; // Importa a classe de modelo Category
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface base do Spring Data JPA
import org.springframework.stereotype.Repository; // Importa a anotação de componente do Spring

@Repository // Indica ao Spring que esta interface é um componente de acesso a dados (DAO)
public interface CategoryRepository extends JpaRepository<Category, Integer> { 
    // JpaRepository<Entidade, TipoDoID>: Herda automaticamente métodos como 
    // save(), findAll(), findById(), delete() e muitos outros sem precisar escrever SQL.
}
