package org.example.catalogo;

import org.example.catalogo.Libro;
import org.example.catalogo.Rivista;
import org.example.catalogo.CatalogoServizio;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AppCatalogo {
    private static CatalogoServizio catalogoService = new CatalogoServizio();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        boolean eseguendo = true;
        while (eseguendo) {
            System.out.println("1. Aggiungi Elemento");
            System.out.println("2. Rimuovi Elemento");
            System.out.println("3. Cerca Elemento per ISBN");
            System.out.println("4. Cerca Elementi per Anno di Pubblicazione");
            System.out.println("5. Cerca Libri per Autore");
            System.out.println("6. Salva Catalogo");
            System.out.println("7. Carica Catalogo");
            System.out.println("8. Visualizza Tutti gli Elementi");
            System.out.println("9. Esci");
            System.out.print("Scegli un'opzione: ");

            int scelta;
            try {
                scelta = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opzione non valida. Inserisci un numero.");
                scanner.nextLine();
                continue;
            }

            switch (scelta) {
                case 1:
                    aggiungiElemento();
                    break;
                case 2:
                    rimuoviElemento();
                    break;
                case 3:
                    cercaElementoPerIsbn();
                    break;
                case 4:
                    cercaElementiPerAnnoPubblicazione();
                    break;
                case 5:
                    cercaLibriPerAutore();
                    break;
                case 6:
                    salvaCatalogo();
                    break;
                case 7:
                    caricaCatalogo();
                    break;
                case 8:
                    visualizzaTuttiElementi();
                    break;
                case 9:
                    eseguendo = false;
                    break;
                default:
                    System.out.println("Opzione non valida.");

            }
        }

    }
    private static void visualizzaTuttiElementi() {
        List<CatalogoOggetto> elementi = catalogoService.getTuttiElementi();
        if (elementi.isEmpty()) {
            System.out.println("Il catalogo è vuoto.");
        } else {
            System.out.println("Elenco degli elementi nel catalogo:");
            elementi.forEach(elemento -> System.out.println(elemento));
        }
    }

    private static void aggiungiElemento() {
        System.out.print("Inserisci il tipo (Libro/Rivista): ");
        String tipo = scanner.nextLine();

        if (!tipo.equalsIgnoreCase("Libro") && !tipo.equalsIgnoreCase("Rivista")) {
            System.out.println("Tipo non valido. Inserisci 'Libro' o 'Rivista'.");
            return;
        }

        String isbn;
        String titolo;
        int anno;
        int pagine;

        System.out.print("Inserisci ISBN: ");
        isbn = scanner.nextLine();

        System.out.print("Inserisci Titolo: ");
        titolo = scanner.nextLine();

        System.out.print("Inserisci Anno di Pubblicazione: ");
        try {
            anno = scanner.nextInt();
            if (anno <= 0) {
                System.out.println("Anno di pubblicazione non valido. Deve essere un numero positivo.");
                scanner.nextLine();
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Anno di pubblicazione non valido. Inserisci un numero.");
            scanner.nextLine();
            return;
        }

        System.out.print("Inserisci Numero di Pagine: ");
        try {
            pagine = scanner.nextInt();
            if (pagine <= 0) {
                System.out.println("Numero di pagine non valido. Deve essere un numero positivo.");
                scanner.nextLine();
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Numero di pagine non valido. Inserisci un numero.");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();

        if (tipo.equalsIgnoreCase("Libro")) {
            System.out.print("Inserisci Autore: ");
            String autore = scanner.nextLine();
            System.out.print("Inserisci Genere: ");
            String genere = scanner.nextLine();
            catalogoService.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
        } else if (tipo.equalsIgnoreCase("Rivista")) {
            System.out.print("Inserisci Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
            String periodicitaStr = scanner.nextLine();
            Rivista.Periodicita periodicita;
            try {
                periodicita = Rivista.Periodicita.valueOf(periodicitaStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Periodicità non valida. Inserisci 'SETTIMANALE', 'MENSILE' o 'SEMESTRALE'.");
                return;
            }
            catalogoService.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, periodicita));
        }

        System.out.println("Elemento aggiunto.");
    }

    private static void rimuoviElemento() {
        System.out.print("Inserisci ISBN dell'elemento da rimuovere: ");
        String isbn = scanner.nextLine();
        catalogoService.rimuoviElementoPerIsbn(isbn);
        System.out.println("Elemento rimosso.");
    }
    private static void cercaElementoPerIsbn() {
        System.out.print("Inserisci ISBN da cercare: ");
        String isbn = scanner.nextLine();
        Optional<CatalogoOggetto> elementoOpt = catalogoService.cercaElementoPerIsbn(isbn);

        if (elementoOpt.isPresent()) {
            CatalogoOggetto elemento = elementoOpt.get();
            System.out.println("Elemento trovato: " + elemento);
        } else {
            System.out.println("Elemento non trovato.");
        }
    }

    private static void cercaElementiPerAnnoPubblicazione() {
        System.out.print("Inserisci Anno di Pubblicazione: ");
        int anno;
        try {
            anno = scanner.nextInt();
            if (anno <= 0) {
                System.out.println("Anno di pubblicazione non valido. Deve essere un numero positivo.");
                scanner.nextLine();
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Anno di pubblicazione non valido. Inserisci un numero.");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();

        List<CatalogoOggetto> elementi = catalogoService.cercaElementiPerAnnoPubblicazione(anno);
        if (elementi.isEmpty()) {
            System.out.println("Nessun elemento trovato per l'anno " + anno);
        } else {
            elementi.forEach(elemento -> System.out.println("Elemento: " + elemento));
        }
    }

    private static void cercaLibriPerAutore() {
        System.out.print("Inserisci Autore da cercare: ");
        String autore = scanner.nextLine();
        List<Libro> libri = catalogoService.cercaLibriPerAutore(autore);
        if (libri.isEmpty()) {
            System.out.println("Nessun libro trovato per l'autore " + autore);
        } else {
            libri.forEach(libro -> System.out.println("Libro: " + libro));
        }
    }


    private static void salvaCatalogo() {
        System.out.print("Inserisci nome file per salvare il catalogo: ");
        String nomeFile = scanner.nextLine();

        try {
            List<CatalogoOggetto> catalogo = catalogoService.getCatalogo();
            catalogoService.salvaCatalogo(nomeFile, catalogo);
            System.out.println("Catalogo salvato.");
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    private static void caricaCatalogo() {
        System.out.print("Inserisci nome file per caricare il catalogo: ");
        String nomeFile = scanner.nextLine();
        try {
            catalogoService.caricaCatalogo(nomeFile);
            System.out.println("Catalogo caricato.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errore durante il caricamento: " + e.getMessage());
        }
    }
}