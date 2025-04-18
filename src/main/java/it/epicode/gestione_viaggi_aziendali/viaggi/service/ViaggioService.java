package it.epicode.gestione_viaggi_aziendali.viaggi.service;

import it.epicode.gestione_viaggi_aziendali.exception.ResourceNotFoundException;
import it.epicode.gestione_viaggi_aziendali.viaggi.model.StatoViaggio;
import it.epicode.gestione_viaggi_aziendali.viaggi.model.Viaggio;
import it.epicode.gestione_viaggi_aziendali.viaggi.repository.ViaggioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {
    private final ViaggioRepository viRepo;

    public ViaggioService(ViaggioRepository viRepo) { this.viRepo = viRepo; }

    public Viaggio create(Viaggio v) { v.setStato(StatoViaggio.IN_PROGRAMMA); return viRepo.save(v); }
    public List<Viaggio> list() { return viRepo.findAll(); }
    public Viaggio get(Long id) { return viRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato")); }
    public Viaggio update(Long id, Viaggio data) {
        Viaggio v = get(id);
        v.setDestinazione(data.getDestinazione());
        v.setData(data.getData());
        return viRepo.save(v);
    }
    public void delete(Long id) { viRepo.delete(get(id)); }

    public Viaggio changeStatus(Long id, StatoViaggio newStatus) {
        Viaggio v = get(id);
        v.setStato(newStatus);
        return viRepo.save(v);
    }
}