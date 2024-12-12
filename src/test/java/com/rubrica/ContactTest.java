package com.rubrica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    private Contact contact; // Istanza condivisa tra i test

    @BeforeEach
    public void setUp() {
        // Inizializza un'istanza di Contact prima di ogni test
        contact = new Contact("Mario", "Rossi");
    }

    @Test
    public void testCostruttore() {
        
        assertEquals("Mario", contact.getNome());
        assertEquals("Rossi", contact.getCognome());
        assertEquals("Nessuno", contact.getTipoContatto());
        assertArrayEquals(new String[3], contact.getEmail());
        assertArrayEquals(new String[3], contact.getTelefono());
    }

    @Test
    public void testSetNome() {
        
        contact.setNome("Luigi");

        
        assertEquals("Luigi", contact.getNome());
    }

    @Test
    public void testSetCognome() {
       
        contact.setCognome("Verdi");

        
        assertEquals("Verdi", contact.getCognome());
    }

    @Test
    public void testSetEmail() {
        
        contact.setEmail(0, "mario.rossi@example.com");
        contact.setEmail(1, "rossimario@test.com");

        
        assertEquals("mario.rossi@example.com", contact.getEmail()[0]);
        assertEquals("rossimario@test.com", contact.getEmail()[1]);
        assertNull(contact.getEmail()[2]); // Deve essere null se non inizializzato
    }

    @Test
    public void testSetTelefono() {
        
        contact.setTelefono(0, "1234567890");
        contact.setTelefono(1, "0987654321");

        
        assertEquals("1234567890", contact.getTelefono()[0]);
        assertEquals("0987654321", contact.getTelefono()[1]);
        assertNull(contact.getTelefono()[2]); // Deve essere null se non inizializzato
    }

    @Test
    public void testSetTipoContatto() {
        
        contact.setTipoContatto("Preferito");
        String tipoContatto1 = contact.getTipoContatto();

        contact.setTipoContatto("Emergenza");
        String tipoContatto2 = contact.getTipoContatto();

        contact.setTipoContatto("Nessuno");
        String tipoContatto3 = contact.getTipoContatto();

        
        assertEquals("Preferito", tipoContatto1);
        assertEquals("Emergenza", tipoContatto2);
        assertEquals("Nessuno", tipoContatto3);
    }

    @Test
    public void testSetTipoContattoInvalido() {
        
        contact.setTipoContatto("Invalido");
        String tipoContatto = contact.getTipoContatto();

        
        assertEquals("Nessuno", tipoContatto); // Non deve cambiare se il valore è invalido
    }

    @Test
    public void testToString() {
        
        contact.setEmail(0, "mario.rossi@example.com");
        contact.setTelefono(0, "1234567890");
        contact.setTipoContatto("Emergenza");

        
        String result = contact.toString();

        
        assertTrue(result.contains("Mario Rossi"));
        assertTrue(result.contains("mario.rossi@example.com"));
        assertTrue(result.contains("1234567890"));
        assertTrue(result.contains("Emergenza"));
    }
}

