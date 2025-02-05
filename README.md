# API para controle do estoque de produtos

## Diagrama de classes
``` mermaid
classDiagram
    class Product {
        +Long id
        +String name
        +String description
        +BigDecimal price
        +Integer quantity
        +Category category
        +Supplier supplier
        +User user
    }

    class Category {
        +Long id
        +String name
        +String description
    }

    class Supplier {
        +Long id
        +String name
        +String phone
        +String email
        +String address
    }

    class StockMovement {
        +Long id
        +Product product
        +String movement_type
        +Integer quantity
        +LocalDateTime movement_date
        +String observation
    }

    class User {
        +Long id
        +String name
        +String email
        +String password
        +String profile
    }

    Product "n" --> "1" Category 
    Product "n" --> "1" Supplier
    Product "n" --> "1" User
    StockMovement "n" --> "1" Product
    StockMovement "n" --> "1" User
```
