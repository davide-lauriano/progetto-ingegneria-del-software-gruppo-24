package com.rubrica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class RubricaTest {

    private Rubrica rubrica;
    private Contact contact1;
    private Contact contact2;
    private Contact contact3;

    @BeforeEach
    public void setUp() {
        // Inizializzazione della rubrica e dei contatti prima di ogni test
        rubrica = new Rubrica();
        contact1 = new Contact("Mario", "Rossi");
        contact2 = new Contact("Luigi", "Bianchi");
        contact3 = new Contact("Anna", "Verdi");
    }

    @Test
    public void testAggiungiContact() {
        rubrica.aggiungiContact(contact1);
        assertEquals(1, rubrica.getContatti().size(), "Il contatto dovrebbe essere aggiunto alla rubrica");
        assertTrue(rubrica.getContatti().contains(contact1), "La rubrica dovrebbe contenere il contatto aggiunto");
    }

    @Test
    public void testRimuoviContact() {
        rubrica.aggiungiContact(contact1);
        rubrica.eliminaContact(contact1);
        assertEquals(0, rubrica.getContatti().size(), "Il contatto dovrebbe essere rimosso dalla rubrica");
    }

    @Test
    public void testOrdinaPerNomeAsc() {
        rubrica.aggiungiContact(contact3);
        rubrica.aggiungiContact(contact1);
        rubrica.aggiungiContact(contact2);

        rubrica.ordinaPerNomeAsc();

        List<Contact> contattiOrdinati = rubrica.getContatti();
        assertEquals("Anna", contattiOrdinati.get(0).getNome(), "Il primo contatto dovrebbe essere Anna");
        assertEquals("Luigi", contattiOrdinati.get(1).getNome(), "Il secondo contatto dovrebbe essere Luigi");
        assertEquals("Mario", contattiOrdinati.get(2).getNome(), "Il terzo contatto dovrebbe essere Mario");
    }

    @Test
    public void testOrdinaPerCognomeDesc() {
        rubrica.aggiungiContact(contact3);
        rubrica.aggiungiContact(contact1);
        rubrica.aggiungiContact(contact2);

        rubrica.ordinaPerCognomeDesc();

        List<Contact> contattiOrdinati = rubrica.getContatti();
        assertEquals("Verdi", contattiOrdinati.get(0).getCognome(), "Il primo contatto dovrebbe essere Anna Verdi");
        assertEquals("Rossi", contattiOrdinati.get(1).getCognome(), "Il secondo contatto dovrebbe essere Mario Rossi");
        assertEquals("Bianchi", contattiOrdinati.get(2).getCognome(), "Il terzo contatto dovrebbe essere Luigi Bianchi");
    }

    @Test
public void testAggiungiContactConNomeVuoto() {
    // Verifica che NON venga lanciata l'eccezione se solo il nome è vuoto
    Contact contactConNomeVuoto = new Contact("", "Nero");
    rubrica.aggiungiContact(contactConNomeVuoto); // Non deve lanciare eccezioni
    assertTrue(rubrica.getContatti().contains(contactConNomeVuoto), "Il contatto con nome vuoto e cognome non vuoto dovrebbe essere aggiunto.");
}

@Test
public void testAggiungiContactConCognomeVuoto() {
    // Verifica che NON venga lanciata l'eccezione se solo il cognome è vuoto
    Contact contactConCognomeVuoto = new Contact("Giulia", "");
    rubrica.aggiungiContact(contactConCognomeVuoto); // Non deve lanciare eccezioni
    assertTrue(rubrica.getContatti().contains(contactConCognomeVuoto), "Il contatto con nome non vuoto e cognome vuoto dovrebbe essere aggiunto.");
}

@Test
public void testAggiungiContactConNomeECognomeVuoti() {
    // Verifica che venga lanciata l'eccezione solo se sia il nome che il cognome sono vuoti
    Contact contactConNomeECognomeVuoti = new Contact("", "");
    assertThrows(IllegalArgumentException.class, () -> rubrica.aggiungiContact(contactConNomeECognomeVuoti), "Dovrebbe lanciare un'eccezione se sia il nome che il cognome sono vuoti");
}


    @Test
    public void testVisualizzaContacts() {
        // Test solo per la parte di visualizzazione
        rubrica.aggiungiContact(contact1);
        rubrica.aggiungiContact(contact2);
        rubrica.visualizzaContacts();  // Questo stamperà in console i contatti
        assertEquals(2, rubrica.getContatti().size(), "Dovrebbero esserci 2 contatti nella rubrica");
    }
}

