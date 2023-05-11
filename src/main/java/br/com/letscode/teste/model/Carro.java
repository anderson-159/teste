package br.com.letscode.teste.model;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name="carro")
@Data
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String uid;

    private String nome;

    private int placa;

    @ManyToOne(fetch = FetchType.LAZY)
    private Classe classe;
}