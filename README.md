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
        +Long category_id
        +Long supplier_id
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
        +Long product_id
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

    Product "n" --> "1" Category : belongs to
    Product "n" --> "1" Supplier : supplied by
    StockMovement "n" --> "1" Product : refers to
```
