package br.com.kelvin.persistence.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class ItemEntity {
    private long id;
    private String string;
    private OffsetDateTime date;
    private BigDecimal number;
    
}
