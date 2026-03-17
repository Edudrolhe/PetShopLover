# 🐾 PetShopLover

 Uma API REST para gerenciamento de produtos de petshop com frontend estático integrado.

## 🚀 Tecnologias

![Java](https://img.shields.io/badge/Java-17-blue?style=flat&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.3-green?style=flat&logo=spring)
![JPA](https://img.shields.io/badge/Spring_Data_JPA-green?style=flat)
![Lombok](https://img.shields.io/badge/Lombok-red?style=flat)
![H2 Database](https://img.shields.io/badge/H2-2.1.214-orange?style=flat)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apachemaven)
![HTML/CSS](https://img.shields.io/badge/HTML/CSS-E34F26-blue?style=flat)

| Tecnologia | Versão |
|------------|--------|
| Java | 17 |
| Spring Boot | 4.0.3 |
| Spring Data JPA | - |
| Lombok | - |
| H2 Database | 2.1.214 |
| Maven | 3.9+ |

## 🔌 Endpoints da API

### Produtos
- `GET /api/products` - Listar todos os produtos
- `GET /api/products/{id}` - Buscar produto por ID
- `POST /api/products` - Criar produto
- `PUT /api/products/{id}` - Atualizar produto
- `DELETE /api/products/{id}` - Deletar produto

### Categorias
- `GET /api/categories` - Listar todas as categorias
- `GET /api/categories/{id}` - Buscar categoria por ID
- `POST /api/categories` - Criar categoria
- `PUT /api/categories/{id}` - Atualizar categoria
- `DELETE /api/categories/{id}` - Deletar categoria




## 🗄️ Banco de Dados

Banco de dados em memória **H2** com console disponível em **http://localhost:8080/h2-console**

- JDBC URL: `jdbc:h2:mem:petshoplover`
- Username: `sa`
- Password: (vazio)

## 📝 Licença

MIT License @edudrolhe
