package it.epicode.gestione_viaggi_aziendali.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}