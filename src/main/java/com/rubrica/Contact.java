package com.rubrica;

/**
 * Rappresenta un contatto telefonico con nome, cognome, email, numeri di telefono e tipo di contatto.
 * Ogni contatto può avere fino a 3 indirizzi email, 3 numeri di telefono e un tipo di contatto
 * che può essere "Preferito", "Emergenza" o "Nessuno".
 */
public class Contact {
    private String nome;          
    private String cognome;       
    private String[] email;       
    private String[] telefono;    
    private String tipoContatto;  

    /**
     * Costruttore per creare un contatto con nome e cognome.
     * Le email e i numeri di telefono sono inizializzati come array vuoti.
     * Il tipo di contatto è impostato su "Nessuno" di default.
     * 
     * @param nome Il nome del contatto
     * @param cognome Il cognome del contatto
     */
    public Contact(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = new String[3];  
        this.telefono = new String[3];  
        this.tipoContatto = "Nessuno";  
    }

    /**
     * Restituisce il nome del contatto.
     * 
     * @return Il nome del contatto
     */
    public String getNome() {
        
    }

    /**
     * Imposta il nome del contatto.
     * 
     * @param nome Il nome da assegnare al contatto
     */
    public void setNome(String nome) {
        
    }

    /**
     * Restituisce il cognome del contatto.
     * 
     * @return Il cognome del contatto
     */
    public String getCognome() {
        
    }

    /**
     * Imposta il cognome del contatto.
     * 
     * @param cognome Il cognome da assegnare al contatto
     */
    public void setCognome(String cognome) {
        
    }

    /**
     * Restituisce l'array di email del contatto.
     * 
     * @return L'array di email
     */
    public String[] getEmail() {
        
    }

    /**
     * Imposta un indirizzo email al contatto in una posizione specificata.
     * 
     * @param index L'indice (0-2) per impostare l'email
     * @param email L'email da assegnare al contatto
     */
    public void setEmail(int index, String email) {
        
    }

    /**
     * Restituisce l'array dei numeri di telefono del contatto.
     * 
     * @return L'array dei numeri di telefono
     */
    public String[] getTelefono() {
        
    }

    /**
     * Imposta un numero di telefono al contatto in una posizione specificata.
     * 
     * @param index L'indice (0-2) per impostare il numero di telefono
     * @param telefono Il numero di telefono da assegnare al contatto
     */
    public void setTelefono(int index, String telefono) {
        
    }

    /**
     * Restituisce il tipo di contatto ("Preferito", "Emergenza", "Nessuno").
     * 
     * @return Il tipo di contatto
     */
    public String getTipoContatto() {
        
    }

    /**
     * Imposta il tipo di contatto.
     * Il tipo di contatto deve essere uno dei seguenti valori: "Preferito", "Emergenza", "Nessuno".
     * 
     * @param tipoContatto Il tipo di contatto da assegnare
     */
    public void setTipoContatto(String tipoContatto) {
        
    }

    /**
     * Restituisce una rappresentazione del contatto sotto forma di stringa.
     * La stringa include nome, cognome, email, telefono e tipo di contatto.
     * 
     * @return La rappresentazione del contatto come stringa
     */
    @Override
    public String toString() {
        String emailStr = String.join(", ", email);
        String telefonoStr = String.join(", ", telefono);
        return "Contatto: " + nome + " " + cognome +
               "\nEmail: " + (emailStr.isEmpty() ? "Nessuna" : emailStr) +
               "\nTelefono: " + (telefonoStr.isEmpty() ? "Nessuno" : telefonoStr) +
               "\nTipo di contatto: " + tipoContatto;
    }
}
