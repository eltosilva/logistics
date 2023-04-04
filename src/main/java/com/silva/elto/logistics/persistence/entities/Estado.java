package com.silva.elto.logistics.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "sigla", unique = true, length = 2)
    private String sigla;
    @ManyToOne
    private Regiao regiao;
}
