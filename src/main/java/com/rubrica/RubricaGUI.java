package com.rubrica;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class RubricaGUI extends Application {
    private Rubrica rubrica = new Rubrica();
    private ObservableList<Contact> observableContacts;
    private TableView<Contact> tableView;

    /**
     * @brief Metodo principale per avviare l'applicazione.
     * @param args Argomenti da linea di comando.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @brief Inizializza l'interfaccia grafica e carica i contatti salvati.
     * @param primaryStage Lo stage principale dell'applicazione.
     */
    @Override
public void start(Stage primaryStage) {
    
}


    /**
     * @brief Filtra i contatti in base al testo di ricerca.
     * @param searchText Il testo inserito nella barra di ricerca.
     */
    private void filtraContatti(String searchText) {
        
    }

    /**
     * @brief Mostra i dettagli del contatto selezionato.
     * @param contact Il contatto di cui visualizzare i dettagli.
     */
    private void mostraDettagliContatto(Contact contact) {
    
}


    /**
    * Mostra una finestra di errore con un messaggio specifico.
    * @param titolo Il titolo della finestra di errore.
    * @param messaggio Il messaggio di errore da visualizzare.
    */
    private void mostraErrore(String titolo, String messaggio) {
       
    }

    /**
    * Mostra una finestra per aggiungere un nuovo contatto o modificare uno esistente.
    * @param contatto Il contatto da modificare o null se si sta aggiungendo un nuovo contatto.
    */
    private void mostraFinestraAggiungiContatto(Contact contatto) {
    
}

    /**
    * Modifica il contatto selezionato nella lista.
    */
    private void modificaContatto() {
        
    }

    /**
    * Elimina il contatto selezionato dalla lista.
    */
    private void eliminaContatto() {
        
    }

    /**
    * Mostra una finestra per applicare un ordinamento ai contatti.
    * @param stage Il principale stage dell'applicazione.
    */
    private void mostraFinestraOrdinamento(Stage stage) {
    
}
}
