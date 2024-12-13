package com.rubrica;

import org.junit.jupiter.api.*;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class ContactIOTest {

    private static final String TEST_FILE = "test_rubrica.txt";

    @BeforeEach
    public void setUp() throws IOException {
        // Pulisce il file di test prima di ogni esecuzione
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
    }

    @AfterEach
    public void tearDown() {
        // Elimina il file di test dopo ogni esecuzione
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSalvaContacts() {
        
        List<Contact> contacts = new ArrayList<>();

        Contact contact1 = new Contact("Mario", "Rossi");
        contact1.setEmail(0, "mario.rossi@example.com");
        contact1.setTelefono(0, "1234567890");
        contact1.setTipoContatto("Preferito");

        Contact contact2 = new Contact("Luigi", "Verdi");
        contact2.setEmail(0, "luigi.verdi@example.com");
        contact2.setTelefono(0, "0987654321");
        contact2.setTipoContatto("Emergenza");

        contacts.add(contact1);
        contacts.add(contact2);

        
        ContactIO.salvaContacts(contacts, TEST_FILE);

        
        File file = new File(TEST_FILE);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    public void testCaricaContacts() {
        
        List<String> righe = Arrays.asList(
            "Mario,Rossi,mario.rossi@example.com,,,1234567890,,,Preferito",
            "Luigi,Verdi,luigi.verdi@example.com,,,0987654321,,,Emergenza"
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            for (String riga : righe) {
                writer.write(riga);
                writer.newLine();
            }
        } catch (IOException e) {
            fail("Errore durante la scrittura del file di test: " + e.getMessage());
        }

        
        List<Contact> contacts = ContactIO.caricaContacts(TEST_FILE);

        
        assertEquals(2, contacts.size());

        Contact contact1 = contacts.get(0);
        assertEquals("Mario", contact1.getNome());
        assertEquals("Rossi", contact1.getCognome());
        assertEquals("mario.rossi@example.com", contact1.getEmail()[0]);
        assertEquals("1234567890", contact1.getTelefono()[0]);
        assertEquals("Preferito", contact1.getTipoContatto());

        Contact contact2 = contacts.get(1);
        assertEquals("Luigi", contact2.getNome());
        assertEquals("Verdi", contact2.getCognome());
        assertEquals("luigi.verdi@example.com", contact2.getEmail()[0]);
        assertEquals("0987654321", contact2.getTelefono()[0]);
        assertEquals("Emergenza", contact2.getTipoContatto());
    }

    @Test
    public void testSalvaECaricaContacts() {
        
        List<Contact> contacts = new ArrayList<>();

        Contact contact1 = new Contact("Anna", "Bianchi");
        contact1.setEmail(0, "anna.bianchi@example.com");
        contact1.setTelefono(0, "5551234567");
        contact1.setTipoContatto("Nessuno");

        Contact contact2 = new Contact("Giovanni", "Neri");
        contact2.setEmail(0, "giovanni.neri@example.com");
        contact2.setTelefono(0, "5557654321");
        contact2.setTipoContatto("Emergenza");

        contacts.add(contact1);
        contacts.add(contact2);

        
        ContactIO.salvaContacts(contacts, TEST_FILE);
        List<Contact> loadedContacts = ContactIO.caricaContacts(TEST_FILE);

        
        assertEquals(contacts.size(), loadedContacts.size());

        for (int i = 0; i < contacts.size(); i++) {
            Contact original = contacts.get(i);
            Contact loaded = loadedContacts.get(i);

            assertEquals(original.getNome(), loaded.getNome());
            assertEquals(original.getCognome(), loaded.getCognome());
            assertArrayEquals(original.getEmail(), loaded.getEmail());
            assertArrayEquals(original.getTelefono(), loaded.getTelefono());
            assertEquals(original.getTipoContatto(), loaded.getTipoContatto());
        }
    }
}

