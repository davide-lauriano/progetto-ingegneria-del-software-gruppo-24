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
        
    }

    /**
     * Rimuove un contatto dalla rubrica.
     *
     */
    public void eliminaContact(Contact contact) {
        
    }

    /**
     * Restituisce la lista dei contatti presenti nella rubrica.
     * 
     * @return La lista dei contatti
     */
    public List<Contact> getContatti() {
       
    }

    /**
     * Imposta una nuova lista di contatti nella rubrica.
     * 
     * @param contatti La nuova lista di contatti
     */
    public void setContatti(List<Contact> contatti) {
        
    }

    /**
     * Visualizza i dettagli di tutti i contatti presenti nella rubrica.
     * I dettagli dei contatti vengono stampati in console.
     */
    public void visualizzaContacts() {
        
    }

    /**
     * Ordina i contatti per nome in ordine alfabetico crescente (A-Z).
     */
    public void ordinaPerNomeAsc() {
        
    }

    /**
     * Ordina i contatti per cognome in ordine alfabetico crescente (A-Z).
     */
    public void ordinaPerCognomeAsc() {
        
    }

    /**
     * Ordina i contatti per nome in ordine alfabetico decrescente (Z-A).
     */
    public void ordinaPerNomeDesc() {
        
    }

    /**
     * Ordina i contatti per cognome in ordine alfabetico decrescente (Z-A).
     */
    public void ordinaPerCognomeDesc() {
       
    }
}
