package org.example.catalogo;


import java.util.*;
import java.util.stream.Collectors;

public class CatalogoServizio {
    private List<CatalogoOggetto> catalogo = new ArrayList<>();


    public void aggiungiElemento(CatalogoOggetto item) {
        catalogo.add(item);
    }


    public void rimuoviElementoPerIsbn(String isbn) {
        catalogo.removeIf(item -> item.getIsbn().equals(isbn));
    }


    public Optional<CatalogoOggetto> cercaElementoPerIsbn(String isbn) {
        return catalogo.stream()
                .filter(item -> item.getIsbn().equals(isbn))
                .findFirst();
    }


    public List<CatalogoOggetto> cercaElementiPerAnnoPubblicazione(int anno) {
        return catalogo.stream()
                .filter(item -> item.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }


    public List<Libro> cercaLibriPerAutore(String autore) {
        return catalogo.stream()
                .filter(item -> item instanceof Libro)
                .map(item -> (Libro) item)
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }
}