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
    // Carica i contatti dal file all'avvio
    List<Contact> contattiCaricati = ContactIO.caricaContacts("rubrica.txt");
    observableContacts = FXCollections.observableArrayList(contattiCaricati);
    rubrica.setContatti(contattiCaricati);

    // Crea la TableView per visualizzare i contatti
    tableView = new TableView<>();
    tableView.setItems(observableContacts);

    // Colonna Nome
    TableColumn<Contact, String> nomeColumn = new TableColumn<>("Nome");
    nomeColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getNome()));
    nomeColumn.setSortable(false);
    
    // Colonna Cognome
    TableColumn<Contact, String> cognomeColumn = new TableColumn<>("Cognome");
    cognomeColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getCognome()));
    cognomeColumn.setSortable(false);
    
    // Colonna Stato
    TableColumn<Contact, String> statoColumn = new TableColumn<>("Stato");
    statoColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getTipoContatto()));
    statoColumn.setSortable(false);
    
    tableView.getColumns().addAll(nomeColumn, cognomeColumn, statoColumn);
    
    // Personalizza le righe in base al tipo di contatto
        tableView.setRowFactory(tv -> new TableRow<Contact>() {
    @Override
    protected void updateItem(Contact contact, boolean empty) {
        super.updateItem(contact, empty);
        if (contact == null || empty) {
            setStyle(""); // Resetta lo stile per righe vuote
        } else {
            // Imposta il colore in base al tipo di contatto
            if ("Emergenza".equals(contact.getTipoContatto())) {
                setStyle("-fx-background-color: lightcoral;"); // Rosso chiaro
            } else if ("Preferito".equals(contact.getTipoContatto())) {
                setStyle("-fx-background-color: lightgreen;"); // Verde chiaro
            } else {
                setStyle(""); // Nessuno stile speciale
            }
        }
    }
});



    // Gestione doppio clic sui contatti
    tableView.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2) {  // Se è un doppio clic
            Contact contactSelezionato = tableView.getSelectionModel().getSelectedItem();
            if (contactSelezionato != null) {
                mostraDettagliContatto(contactSelezionato);  // Mostra i dettagli del contatto
            } else {
                mostraErrore("Errore", "Seleziona un contatto da visualizzare.");
            }
        }
    });

    // Barra di ricerca
    TextField ricercaField = new TextField();
    ricercaField.setPromptText("Cerca contatto...");
    ricercaField.textProperty().addListener((observable, oldValue, newValue) -> filtraContatti(newValue));

    // Layout principale
    VBox layout = new VBox(10, ricercaField, tableView);
    layout.setPadding(new Insets(10));

    // Pulsanti
    Button aggiungiButton = new Button("Aggiungi Contatto");
    aggiungiButton.setOnAction(e -> mostraFinestraAggiungiContatto(null));  // Passiamo null per aggiungere un nuovo contatto

    Button modificaButton = new Button("Modifica Contatto");
    modificaButton.setOnAction(e -> modificaContatto());

    Button eliminaButton = new Button("Elimina Contatto");
    eliminaButton.setOnAction(e -> eliminaContatto());

    Button ordinamentoButton = new Button("Ordinamento");
    ordinamentoButton.setOnAction(e -> mostraFinestraOrdinamento(primaryStage));

    HBox pulsantiLayout = new HBox(10, aggiungiButton, modificaButton, eliminaButton, ordinamentoButton);
    pulsantiLayout.setPadding(new Insets(10));

    // Layout finale
    BorderPane mainLayout = new BorderPane();
    mainLayout.setCenter(layout);
    mainLayout.setBottom(pulsantiLayout);

    // Scene e Stage
    Scene scene = new Scene(mainLayout, 600, 400);
    primaryStage.setTitle("Rubrica");
    primaryStage.setScene(scene);
    
    
    // Aggiungi azione per salvare i contatti quando il programma si chiude
    primaryStage.setOnCloseRequest(e -> ContactIO.salvaContacts(observableContacts, "rubrica.txt"));


    primaryStage.show();
}


    /**
     * @brief Filtra i contatti in base al testo di ricerca.
     * @param searchText Il testo inserito nella barra di ricerca.
     */
    private void filtraContatti(String searchText) {
        ObservableList<Contact> filtraContatti = FXCollections.observableArrayList();
        for (Contact contact : rubrica.getContatti()) {
            if (contact.getNome().toLowerCase().contains(searchText.toLowerCase()) ||
                contact.getCognome().toLowerCase().contains(searchText.toLowerCase())) {
                filtraContatti.add(contact);
            }
        }
        tableView.setItems(filtraContatti);
    }

    /**
     * @brief Mostra i dettagli del contatto selezionato.
     * @param contact Il contatto di cui visualizzare i dettagli.
     */
    private void mostraDettagliContatto(Contact contact) {
    Stage stage = new Stage();
    stage.setTitle("Dettagli del Contatto");

    // TextArea per visualizzare i dettagli del contatto
    TextArea dettagliArea = new TextArea(contact.toString());
    dettagliArea.setEditable(false); // Impedisce la modifica del testo
    dettagliArea.setWrapText(true); // Consente di andare a capo automaticamente

    // Bottone per chiudere la finestra
    Button chiudiButton = new Button("Chiudi");
    chiudiButton.setOnAction(e -> stage.close());

    // Layout principale
    VBox layout = new VBox(10, dettagliArea, chiudiButton);
    layout.setPadding(new Insets(10));

    // Creazione della scena e visualizzazione
    Scene scene = new Scene(layout, 400, 300); // Dimensioni personalizzate
    stage.setScene(scene);
    stage.show();
}



    /**
    * Mostra una finestra di errore con un messaggio specifico.
    * @param titolo Il titolo della finestra di errore.
    * @param messaggio Il messaggio di errore da visualizzare.
    */
    private void mostraErrore(String titolo, String messaggio) {
       Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }

    /**
    * Mostra una finestra per aggiungere un nuovo contatto o modificare uno esistente.
    * @param contatto Il contatto da modificare o null se si sta aggiungendo un nuovo contatto.
    */
   private void mostraFinestraAggiungiContatto(Contact contatto) {
    Stage stage = new Stage();
    stage.setTitle(contatto == null ? "Aggiungi Contatto" : "Modifica Contatto");

    // Form per aggiungere o modificare un contatto
    TextField nomeField = new TextField();
    nomeField.setPromptText("Nome");
    if (contatto != null) nomeField.setText(contatto.getNome());

    TextField cognomeField = new TextField();
    cognomeField.setPromptText("Cognome");
    if (contatto != null) cognomeField.setText(contatto.getCognome());

    // 3 campi per le email
    TextField emailField1 = new TextField();
    emailField1.setPromptText("Email 1");
    if (contatto != null && contatto.getEmail()[0] != null) emailField1.setText(contatto.getEmail()[0]);

    TextField emailField2 = new TextField();
    emailField2.setPromptText("Email 2");
    if (contatto != null && contatto.getEmail()[1] != null) emailField2.setText(contatto.getEmail()[1]);

    TextField emailField3 = new TextField();
    emailField3.setPromptText("Email 3");
    if (contatto != null && contatto.getEmail()[2] != null) emailField3.setText(contatto.getEmail()[2]);

    // 3 campi per i numeri di telefono
    TextField telefonoField1 = new TextField();
    telefonoField1.setPromptText("Telefono 1");
    if (contatto != null && contatto.getTelefono()[0] != null) telefonoField1.setText(contatto.getTelefono()[0]);

    TextField telefonoField2 = new TextField();
    telefonoField2.setPromptText("Telefono 2");
    if (contatto != null && contatto.getTelefono()[1] != null) telefonoField2.setText(contatto.getTelefono()[1]);

    TextField telefonoField3 = new TextField();
    telefonoField3.setPromptText("Telefono 3");
    if (contatto != null && contatto.getTelefono()[2] != null) telefonoField3.setText(contatto.getTelefono()[2]);

    // ComboBox per il tipo di contatto
    ComboBox<String> tipoCombo = new ComboBox<>();
    tipoCombo.getItems().addAll("Preferito", "Emergenza", "Nessuno");
    if (contatto != null) {
        tipoCombo.setValue(contatto.getTipoContatto());  // Imposta il tipo del contatto esistente
    } else {
        tipoCombo.setValue("Nessuno");  // Imposta il valore di default su "Nessuno" per i nuovi contatti
    }

    Button salvaButton = new Button(contatto == null ? "Salva" : "Modifica");
    salvaButton.setOnAction(e -> {
        // Verifica che almeno uno tra nome e cognome sia stato inserito
        if (nomeField.getText().isEmpty() && cognomeField.getText().isEmpty()) {
            mostraErrore("Errore", "Devi inserire almeno un nome o un cognome.");
            return; // Non proseguire con il salvataggio
        }

        if (contatto == null) {
            // Aggiungi nuovo contatto
            Contact newContact = new Contact(nomeField.getText(), cognomeField.getText());
            newContact.setEmail(0, emailField1.getText());
            newContact.setEmail(1, emailField2.getText());
            newContact.setEmail(2, emailField3.getText());
            newContact.setTelefono(0, telefonoField1.getText());
            newContact.setTelefono(1, telefonoField2.getText());
            newContact.setTelefono(2, telefonoField3.getText());
            newContact.setTipoContatto(tipoCombo.getValue());

            rubrica.aggiungiContact(newContact);
            observableContacts.add(newContact);
        } else {
            // Modifica contatto esistente
            contatto.setNome(nomeField.getText());
            contatto.setCognome(cognomeField.getText());
            contatto.setEmail(0, emailField1.getText());
            contatto.setEmail(1, emailField2.getText());
            contatto.setEmail(2, emailField3.getText());
            contatto.setTelefono(0, telefonoField1.getText());
            contatto.setTelefono(1, telefonoField2.getText());
            contatto.setTelefono(2, telefonoField3.getText());
            contatto.setTipoContatto(tipoCombo.getValue());
            
            tableView.refresh();
        }

        stage.close();
    });

    VBox layout = new VBox(10, nomeField, cognomeField, emailField1, emailField2, emailField3, telefonoField1, telefonoField2, telefonoField3, tipoCombo, salvaButton);
    layout.setPadding(new Insets(10));

    Scene scene = new Scene(layout, 300, 400);
    stage.setScene(scene);
    stage.show();
}

    /**
    * Modifica il contatto selezionato nella lista.
    */
    private void modificaContatto() {
        Contact contactSelezionato = tableView.getSelectionModel().getSelectedItem();
        if (contactSelezionato != null) {
            mostraFinestraAggiungiContatto(contactSelezionato);
        } else {
            mostraErrore("Errore", "Seleziona un contatto da modificare.");
        }
    }

    /**
    * Elimina il contatto selezionato dalla lista.
    */
    private void eliminaContatto() {
        Contact contactSelezionato = tableView.getSelectionModel().getSelectedItem();
        if (contactSelezionato != null) {
            rubrica.eliminaContact(contactSelezionato);
            observableContacts.remove(contactSelezionato);
        } else {
            mostraErrore("Errore", "Seleziona un contatto da eliminare.");
        }
    }

    /**
    * Mostra una finestra per applicare un ordinamento ai contatti.
    * @param stage Il principale stage dell'applicazione.
    */
    private void mostraFinestraOrdinamento(Stage stage) {
    // Finestra per ordinare i contatti
    Stage ordinamentoStage = new Stage();
    ordinamentoStage.setTitle("Ordinamento");

    // Crea un ComboBox per scegliere il tipo di ordinamento
    ComboBox<String> ordinamentoComboBox = new ComboBox<>();
    ordinamentoComboBox.getItems().addAll(
            "Ordina per Nome (A-Z)",
            "Ordina per Nome (Z-A)",
            "Ordina per Cognome (A-Z)",
            "Ordina per Cognome (Z-A)"
    );
    ordinamentoComboBox.setValue("Ordina per Nome (A-Z)");  // Imposta il valore predefinito

    // Bottone per applicare l'ordinamento
    Button applicaOrdinamentoButton = new Button("Applica Ordinamento");
    applicaOrdinamentoButton.setOnAction(e -> {
        String selezione = ordinamentoComboBox.getValue();
        switch (selezione) {
            case "Ordina per Nome (A-Z)":
                rubrica.ordinaPerNomeAsc();  // Usa il metodo di ordinamento per nome (A-Z)
                break;
            case "Ordina per Nome (Z-A)":
                rubrica.ordinaPerNomeDesc();  // Usa il metodo di ordinamento per nome (Z-A)
                break;
            case "Ordina per Cognome (A-Z)":
                rubrica.ordinaPerCognomeAsc();  // Usa il metodo di ordinamento per cognome (A-Z)
                break;
            case "Ordina per Cognome (Z-A)":
                rubrica.ordinaPerCognomeDesc();  // Usa il metodo di ordinamento per cognome (Z-A)
                break;
        }
        // Aggiorna la lista osservata con i contatti ordinati
        observableContacts.setAll(rubrica.getContatti());  // Aggiorna la TableView con la lista ordinata
        ordinamentoStage.close();
    });

    VBox ordinamentoLayout = new VBox(10, ordinamentoComboBox, applicaOrdinamentoButton);
    ordinamentoLayout.setPadding(new Insets(10));

    Scene scene = new Scene(ordinamentoLayout, 250, 200);
    ordinamentoStage.setScene(scene);
    ordinamentoStage.show();
}
}
