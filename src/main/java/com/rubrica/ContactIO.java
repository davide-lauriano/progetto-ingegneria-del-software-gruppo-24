package com.rubrica;

import java.io.*;
import java.util.*;

/**
 * Classe che gestisce il salvataggio e il caricamento dei contatti su un file di testo.
 */
public class ContactIO {

    /**
     * Salva una lista di contatti su un file di testo.
     * I dati vengono salvati in formato CSV, con ogni contatto su una riga separata.
     * 
     * @param contacts La lista dei contatti da salvare
     * @param nomeFile Il nome del file in cui salvare i contatti
     */
    public static void salvaContacts(List<Contact> contacts, String nomeFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile))) {
            for (Contact contact : contacts) {
                // Salvataggio dei dati in formato CSV (nome, cognome, email, telefono, tipoContatto)
                writer.write(contact.getNome() + "," + contact.getCognome() + ",");
                for (String email : contact.getEmail()) {
                    writer.write((email != null ? email : "") + ",");
                }
                for (String telefono : contact.getTelefono()) {
                    writer.write((telefono != null ? telefono : "") + ",");
                }
                writer.write(contact.getTipoContatto());
                writer.newLine();
            }
            System.out.println("Contatti salvati con successo.");
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio dei contatti: " + e.getMessage());
        }
    }

    /**
     * Carica una lista di contatti da un file di testo.
     * Il file deve contenere i dati in formato CSV, con ogni contatto su una riga.
     * 
     * @param nomeFile Il nome del file da cui caricare i contatti
     * @return Una lista di contatti caricati dal file
     */
    public static List<Contact> caricaContacts(String nomeFile) {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] dati = linea.split(",");
                if (dati.length >= 9) { // Verifica che ci siano abbastanza dati per creare un contatto
                    // Crea un nuovo contact a partire dai dati del file
                    Contact contact = new Contact(dati[0], dati[1]); // Nome e cognome
                    for (int i = 2; i < 5; i++) { // Email (fino a 3)
                        if (!dati[i].isEmpty()) {
                            contact.setEmail(i - 2, dati[i]);
                        }
                    }
                    for (int i = 5; i < 8; i++) { // Telefono (fino a 3)
                        if (!dati[i].isEmpty()) {
                            contact.setTelefono(i - 5, dati[i]);
                        }
                    }
                    contact.setTipoContatto(dati[8]); // Tipo contatto
                    contacts.add(contact);
                }
            }
            System.out.println("Contatti caricati con successo.");
        } catch (IOException e) {
            System.out.println("Errore durante il caricamento dei contatti: " + e.getMessage());
        }
        return contacts;
    }
}
