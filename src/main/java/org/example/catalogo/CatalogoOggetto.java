package org.example.catalogo;

import java.io.Serializable;

public abstract class CatalogoOggetto implements Serializable {
    private static final long serialVersionUID = 1L; // Versione di serializzazione

    private String isbn;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;

    public CatalogoOggetto(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    @Override
    public String toString() {
        return "CatalogoItem{" +
                "ISBN='" + isbn + '\'' +
                ", Titolo='" + titolo + '\'' +
                ", Anno di Pubblicazione=" + annoPubblicazione +
                ", Numero di Pagine=" + numeroPagine +
                '}';
    }
}
