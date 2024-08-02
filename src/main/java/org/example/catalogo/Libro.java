package org.example.catalogo;

import java.io.Serializable;

public class Libro extends CatalogoOggetto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String autore;
    private String genere;

    public Libro(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    @Override
    public String toString() {
        return String.format("Libro [ISBN=%s, Titolo=%s, Anno=%d, Pagine=%d, Autore=%s, Genere=%s]",
                getIsbn(), getTitolo(), getAnnoPubblicazione(), getNumeroPagine(), autore, genere);
    }

}
