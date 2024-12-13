package com.rubrica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    private Contact contact;

    @BeforeEach
    public void setUp() {
        contact = new Contact("Mario", "Rossi");
    }

    @Test
    public void testGetNome() {
        assertEquals("Mario", contact.getNome());
    }

    @Test
    public void testSetNome() {
        contact.setNome("Giovanni");
        assertEquals("Giovanni", contact.getNome());
    }

    @Test
    public void testGetCognome() {
        assertEquals("Rossi", contact.getCognome());
    }

    @Test
    public void testSetCognome() {
        contact.setCognome("Bianchi");
        assertEquals("Bianchi", contact.getCognome());
    }

    @Test
    public void testSetEmail() {
        contact.setEmail(0, "mario.rossi@example.com");
        assertEquals("mario.rossi@example.com", contact.getEmail()[0]);
    }

    @Test
    public void testSetTelefono() {
        contact.setTelefono(0, "1234567890");
        assertEquals("1234567890", contact.getTelefono()[0]);
    }

    @Test
    public void testSetTipoContatto() {
        contact.setTipoContatto("Preferito");
        assertEquals("Preferito", contact.getTipoContatto());
    }

    @Test
    public void testToString() {
    Contact contact = new Contact("Mario", "Rossi");
    contact.setEmail(0, "mario.rossi@example.com");
    contact.setTelefono(0, "1234567890");
    contact.setTipoContatto("Preferito");

    String expected = "Contatto: Mario Rossi\n" +
                      "Email: mario.rossi@example.com\n" +
                      "Telefono: 1234567890\n" +
                      "Tipo di contatto: Preferito";
                      
    assertEquals(expected, contact.toString());
}
}

