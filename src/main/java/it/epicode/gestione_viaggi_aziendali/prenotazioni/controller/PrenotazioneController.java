package it.epicode.gestione_viaggi_aziendali.prenotazioni.controller;

import it.epicode.gestione_viaggi_aziendali.prenotazioni.model.Prenotazione;
import it.epicode.gestione_viaggi_aziendali.prenotazioni.service.PrenotazioneService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {
    private final PrenotazioneService service;
    public PrenotazioneController(PrenotazioneService service) { this.service = service; }

    @PostMapping
    public Prenotazione create(@RequestParam Long dipendenteId,
                               @RequestParam Long viaggioId,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                               @RequestParam(required = false) String note) {
        return service.create(dipendenteId, viaggioId, data, note);
    }
    @GetMapping
    public List<Prenotazione> list() { return service.list(); }
    @GetMapping("/{id}")
    public Prenotazione get(@PathVariable Long id) { return service.get(id); }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}