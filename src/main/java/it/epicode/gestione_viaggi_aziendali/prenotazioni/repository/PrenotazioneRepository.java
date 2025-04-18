package it.epicode.gestione_viaggi_aziendali.prenotazioni.repository;

import it.epicode.gestione_viaggi_aziendali.prenotazioni.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByDipendenteIdAndDataPrenotazione(Long dipendenteId, LocalDate data);
}