package it.epicode.gestione_viaggi_aziendali.dipendenti.service;

import it.epicode.gestione_viaggi_aziendali.cloudinary.CloudinaryService;
import it.epicode.gestione_viaggi_aziendali.dipendenti.model.Dipendente;
import it.epicode.gestione_viaggi_aziendali.dipendenti.repository.DipendenteRepository;
import it.epicode.gestione_viaggi_aziendali.exception.BadRequestException;
import it.epicode.gestione_viaggi_aziendali.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DipendenteService {
    private final DipendenteRepository dipRepo;
    private final CloudinaryService cloudService;

    public DipendenteService(DipendenteRepository dipRepo, CloudinaryService cloudService) {
        this.dipRepo = dipRepo;
        this.cloudService = cloudService;
    }

    public Dipendente create(Dipendente d) {
        if (dipRepo.existsByUsername(d.getUsername()))
            throw new BadRequestException("Username già esistente");
        return dipRepo.save(d);
    }
    public List<Dipendente> list() { return dipRepo.findAll(); }
    public Dipendente get(Long id) {
        return dipRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato"));
    }
    public Dipendente update(Long id, Dipendente data) {
        Dipendente d = get(id);
        d.setNome(data.getNome()); d.setCognome(data.getCognome()); d.setEmail(data.getEmail());
        return dipRepo.save(d);
    }
    public void delete(Long id) { dipRepo.delete(get(id)); }

    public Dipendente uploadImage(Long id, MultipartFile file) throws IOException {
        Dipendente d = get(id);
        String url = cloudService.upload(file);
        d.setProfileImageUrl(url);
        return dipRepo.save(d);
    }
}