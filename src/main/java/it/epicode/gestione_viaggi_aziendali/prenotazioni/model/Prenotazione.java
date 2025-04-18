package it.epicode.gestione_viaggi_aziendali.prenotazioni.model;

import it.epicode.gestione_viaggi_aziendali.dipendenti.model.Dipendente;
import it.epicode.gestione_viaggi_aziendali.viaggi.model.Viaggio;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni", uniqueConstraints = @UniqueConstraint(columnNames = {"dipendente_id","data_prenotazione"}))
@Data
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "viaggio_id", nullable = false)
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendente;

    private LocalDate dataPrenotazione;
    private String note;
}