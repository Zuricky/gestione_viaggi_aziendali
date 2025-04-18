package it.epicode.gestione_viaggi_aziendali.viaggi.controller;

import it.epicode.gestione_viaggi_aziendali.viaggi.model.StatoViaggio;
import it.epicode.gestione_viaggi_aziendali.viaggi.model.Viaggio;
import it.epicode.gestione_viaggi_aziendali.viaggi.service.ViaggioService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
public class ViaggioController {
    private final ViaggioService service;
    public ViaggioController(ViaggioService service) { this.service = service; }

    @PostMapping
    public Viaggio create(@Validated @RequestBody Viaggio v) { return service.create(v); }
    @GetMapping
    public List<Viaggio> list() { return service.list(); }
    @GetMapping("/{id}")
    public Viaggio get(@PathVariable Long id) { return service.get(id); }
    @PutMapping("/{id}")
    public Viaggio update(@PathVariable Long id, @Validated @RequestBody Viaggio v) {
        return service.update(id, v);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }

    @PatchMapping("/{id}/status")
    public Viaggio changeStatus(@PathVariable Long id, @RequestParam StatoViaggio stato) {
        return service.changeStatus(id, stato);
    }
}