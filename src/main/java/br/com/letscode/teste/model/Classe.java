package br.com.letscode.teste.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="classe")
@Data
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String uid;

    private String nome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "classe")
    private Collection<Carro> carros = new ArrayList<>();
}
