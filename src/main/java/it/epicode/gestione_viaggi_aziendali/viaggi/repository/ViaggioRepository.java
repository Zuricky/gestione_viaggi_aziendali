package it.epicode.gestione_viaggi_aziendali.viaggi.repository;

import it.epicode.gestione_viaggi_aziendali.viaggi.model.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {}