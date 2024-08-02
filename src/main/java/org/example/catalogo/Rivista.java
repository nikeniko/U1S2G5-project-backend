package org.example.catalogo;

import java.io.Serializable;

public class Rivista extends CatalogoOggetto implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Periodicita {
        SETTIMANALE,
        MENSILE,
        SEMESTRALE
    }

    private Periodicita periodicita;

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    @Override
    public String toString() {
        return String.format("Rivista [ISBN=%s, Titolo=%s, Anno=%d, Pagine=%d, Periodicità=%s]",
                getIsbn(), getTitolo(), getAnnoPubblicazione(), getNumeroPagine(), periodicita);
    }
}
