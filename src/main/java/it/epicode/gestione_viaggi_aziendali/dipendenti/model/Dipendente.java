package it.epicode.gestione_viaggi_aziendali.dipendenti.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dipendenti")
@Data
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String profileImageUrl;
}