package br.com.kelvin.persistence.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class ItemAuditEntity {
    private long id;
    private long itemId;
    private String string;
    private String oldString;
    private OffsetDateTime itemDate;
    private OffsetDateTime oldItemDate;
    private BigDecimal number;
    private BigDecimal oldNumber;
    private OperationType operation; // <--- aqui
    private OffsetDateTime createAt;
}
