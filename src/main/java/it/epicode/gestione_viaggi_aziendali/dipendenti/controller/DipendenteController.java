package it.epicode.gestione_viaggi_aziendali.dipendenti.controller;

import it.epicode.gestione_viaggi_aziendali.dipendenti.service.DipendenteService;
import it.epicode.gestione_viaggi_aziendali.dipendenti.model.Dipendente;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {
    private final DipendenteService service;
    public DipendenteController(DipendenteService service) { this.service = service; }

    @PostMapping
    public Dipendente create(@Validated @RequestBody Dipendente d) {
        return service.create(d);
    }
    @GetMapping
    public List<Dipendente> list() { return service.list(); }
    @GetMapping("/{id}")
    public Dipendente get(@PathVariable Long id) { return service.get(id); }
    @PutMapping("/{id}")
    public Dipendente update(@PathVariable Long id, @Validated @RequestBody Dipendente d) {
        return service.update(id, d);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }

    @PostMapping("/{id}/uploadImage")
    public Dipendente uploadImage(@PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        return service.uploadImage(id, file);
    }
}