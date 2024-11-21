# CRUD em Java - Portfolio Faculdade

Este repositório contém um exemplo de implementação de um sistema CRUD (Create, Read, Update, Delete) utilizando Java com Spring Boot e JPA/Hibernate. O projeto é dividido em três pacotes principais: Entities, Repositories e Resource.

## Estrutura do Projeto

- **Entities:** Contém a classe `User`, que é mapeada para uma tabela no banco de dados.
- **Repositories:** Contém a interface `UserRepository`, que estende `JpaRepository` para fornecer operações CRUD.
- **Resource:** Contém a classe `UserResource`, que é um controlador REST que expõe endpoints para operações CRUD.
- **Service:** Contém a classe `UserService`, que contém a lógica de negócio para as operações com `User`.
- **Resource.exceptions:** Contém classes para manipulação de exceções personalizadas.
- **RepositoryTest:** Contém testes unitários para `UserRepository`.

## Estrutura dos Pacotes

```
com.br.jg.PortfolioFaculdade
├── Entities
│   └── User.java
├── Repositories
│   └── UserRepository.java
├── Resource
│   ├── UserResource.java
│   └── exceptions
│       ├── ResourceExceptionHandler.java
│       ├── ResourceNotFoundException.java
│       └── StandardError.java
├── Service
│   └── UserService.java
└── RepositoryTest
    └── UserRepositoryTest.java
```

## Dependências

- Spring Boot
- Spring Data JPA
- Hibernate
- Lombok
- JUnit 5
- H2 Database (para testes)

## Endpoints

### User Resource

- **GET /users**: Retorna todos os usuários.
- **GET /users/{id}**: Retorna um usuário específico por ID.
- **POST /users**: Cria um novo usuário.
- **PUT /users/{id}**: Atualiza um usuário existente por ID.
- **DELETE /users/{id}**: Remove um usuário específico por ID.

## Exemplo de Classe User

```java
package com.br.jg.PortfolioFaculdade.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Serial
    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;
    private String telefone;
    private String password;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
```

## Testes

Os testes para o repositório são definidos na classe `UserRepositoryTest`, que utiliza `@DataJpaTest` para testar a interação com o banco de dados.

```java
package com.br.jg.PortfolioFaculdade.RepositoryTest;

import com.br.jg.PortfolioFaculdade.Entities.User;
import com.br.jg.PortfolioFaculdade.Repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Should return all users added on the DB")
    void findUsers() {
        saveListUsers();
        List<User> users = userRepository.findAll();
        assertThat(users.isEmpty()).isFalse();
    }

    private void saveListUsers() {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        this.userRepository.saveAll(users);
    }
}
```

## Como Executar

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
    ```
2. Navegue até o diretório do projeto:
    ```bash
    cd seu-repositorio
    ```
3. Execute a aplicação:
    ```bash
    ./mvnw spring-boot:run
    ```
4. A aplicação estará disponível em `http://localhost:8080`.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
