package it.epicode.gestione_viaggi_aziendali.prenotazioni.service;

import it.epicode.gestione_viaggi_aziendali.exception.BadRequestException;
import it.epicode.gestione_viaggi_aziendali.exception.ResourceNotFoundException;
import it.epicode.gestione_viaggi_aziendali.prenotazioni.model.Prenotazione;
import it.epicode.gestione_viaggi_aziendali.dipendenti.repository.DipendenteRepository;
import it.epicode.gestione_viaggi_aziendali.prenotazioni.repository.PrenotazioneRepository;
import it.epicode.gestione_viaggi_aziendali.viaggi.repository.ViaggioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {
    private final PrenotazioneRepository prRepo;
    private final DipendenteRepository dipRepo;
    private final ViaggioRepository viRepo;

    public PrenotazioneService(PrenotazioneRepository prRepo, DipendenteRepository dipRepo, ViaggioRepository viRepo) {
        this.prRepo = prRepo;
        this.dipRepo = dipRepo;
        this.viRepo = viRepo;
    }

    public Prenotazione create(Long idDip, Long idViag, LocalDate date, String note) {
        if (prRepo.existsByDipendenteIdAndDataPrenotazione(idDip, date))
            throw new BadRequestException("Dipendente già prenotato per questa data");
        var dip = dipRepo.findById(idDip)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato"));
        var vi = viRepo.findById(idViag)
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato"));
        Prenotazione p = new Prenotazione();
        p.setDipendente(dip);
        p.setViaggio(vi);
        p.setDataPrenotazione(date);
        p.setNote(note);
        return prRepo.save(p);
    }
    public List<Prenotazione> list() { return prRepo.findAll(); }
    public Prenotazione get(Long id) { return prRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prenotazione non trovata")); }
    public void delete(Long id) { prRepo.delete(get(id)); }
}