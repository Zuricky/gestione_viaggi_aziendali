package it.epicode.gestione_viaggi_aziendali.dipendenti.repository;

import it.epicode.gestione_viaggi_aziendali.dipendenti.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    boolean existsByUsername(String username);
}