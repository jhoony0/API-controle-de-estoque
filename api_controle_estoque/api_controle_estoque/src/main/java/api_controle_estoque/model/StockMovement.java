package api_controle_estoque.model;

import java.time.LocalDateTime;

public class StockMovement {
    private Long id;
    private String movement_type;
    private Integer quantity;
    private LocalDateTime movement_date;
    private String observation;
}
