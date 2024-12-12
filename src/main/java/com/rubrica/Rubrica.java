package com.rubrica;

import java.util.*;

/**
 * Classe che rappresenta una rubrica telefonica.
 * Permette di aggiungere, rimuovere, visualizzare e ordinare i contatti.
 */
public class Rubrica {

    private List<Contact> contatti; 

    /**
     * Costruttore di default.
     * Inizializza una lista vuota di contatti.
     */
    public Rubrica() {
        contatti = new ArrayList<>();
    }

    /**
     * Aggiunge un contatto alla rubrica.
     * Il nome o il cognome del contatto deve essere obbligatoriamente fornito.
     * 
     * @param contact Il contatto da aggiungere
     * @throws IllegalArgumentException Se sia il nome che il cognome del contatto sono vuoti
     */
    public void aggiungiContact(Contact contact) {
    if (contact.getNome().isEmpty() && contact.getCognome().isEmpty()) {
        throw new IllegalArgumentException("Nome e cognome sono obbligatori");
    }
    contatti.add(contact);
}


    /**
     * Rimuove un contatto dalla rubrica.
     *
     */
    public void eliminaContact(Contact contact) {
        contatti.remove(contact);
    }

    /**
     * Restituisce la lista dei contatti presenti nella rubrica.
     * 
     * @return La lista dei contatti
     */
    public List<Contact> getContatti() {
       return contatti;
    }

    /**
     * Imposta una nuova lista di contatti nella rubrica.
     * 
     * @param contatti La nuova lista di contatti
     */
    public void setContatti(List<Contact> contatti) {
        this.contatti = contatti;
    }

    /**
     * Visualizza i dettagli di tutti i contatti presenti nella rubrica.
     * I dettagli dei contatti vengono stampati in console.
     */
    public void visualizzaContacts() {
        for (Contact contact : contatti) {
            System.out.println(contact);
        }
    }

    /**
     * Ordina i contatti per nome in ordine alfabetico crescente (A-Z).
     */
    public void ordinaPerNomeAsc() {
         Collections.sort(contatti, Comparator.comparing(Contact::getNome));
    }

    /**
     * Ordina i contatti per cognome in ordine alfabetico crescente (A-Z).
     */
    public void ordinaPerCognomeAsc() {
        Collections.sort(contatti, Comparator.comparing(Contact::getCognome));
    }

    /**
     * Ordina i contatti per nome in ordine alfabetico decrescente (Z-A).
     */
    public void ordinaPerNomeDesc() {
        Collections.sort(contatti, Comparator.comparing(Contact::getNome).reversed());
    }

    /**
     * Ordina i contatti per cognome in ordine alfabetico decrescente (Z-A).
     */
    public void ordinaPerCognomeDesc() {
       Collections.sort(contatti, Comparator.comparing(Contact::getCognome).reversed());
    }
}
