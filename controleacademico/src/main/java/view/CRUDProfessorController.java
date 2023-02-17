package view;

import static Config.Config.*;
import static Utility.Validate.*;
import static DAO.DAO.professorRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.data.domain.Sort;

public class CRUDProfessorController implements Initializable {

    private ProfessorController controllerPai;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField txtfldCPF;

    @FXML
    private TextField txtfldNome;

    @FXML
    private TextField txtfldEmail;

    @FXML
    private Button btnConfirma;

    @FXML
    private Button btnCancela;

    @FXML
    private void btnCancelaClick() {
        anchorPane.getScene().getWindow().hide();
        controllerPai.tblvwProfessores.requestFocus();
    }

    @FXML
    private void btnConfirmaClick() {
        controllerPai.professor.setCpf(txtfldCPF.getText());
        controllerPai.professor.setNome(txtfldNome.getText());
        controllerPai.professor.setEmail(txtfldEmail.getText());
        try {
            switch (controllerPai.acao) {
                case INCLUIR:
                    professorRepository.insert(controllerPai.professor);
                    break;
                case ALTERAR:
                    professorRepository.save(controllerPai.professor);
                    break;
                case EXCLUIR:
                    professorRepository.delete(controllerPai.professor);
                    break;
            }
            controllerPai.tblvwProfessores.setItems(FXCollections.observableList(professorRepository.findAll(new Sort(new Sort.Order("nome")))));
            controllerPai.tblvwProfessores.refresh();
            anchorPane.getScene().getWindow().hide();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Cadastro de Professor");
            if (e.getMessage().contains("duplicate key")) {
                alert.setContentText("CPF já cadastrado");
            } else {
                alert.setContentText(e.getMessage());
            }
            alert.showAndWait();
        }
    }

    public void setCadastroController(ProfessorController controllerPai) {
        this.controllerPai = controllerPai;
        txtfldCPF.setText(controllerPai.professor.getCpf());
        txtfldNome.setText(controllerPai.professor.getNome());
        txtfldEmail.setText(controllerPai.professor.getEmail());

        txtfldCPF.setDisable(controllerPai.acao == EXCLUIR);
        txtfldNome.setDisable(controllerPai.acao == EXCLUIR);
        txtfldEmail.setDisable(controllerPai.acao == EXCLUIR);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnConfirma.disableProperty().
                bind((txtfldCPF.textProperty().isEmpty()).
                        or(txtfldNome.textProperty().isEmpty()).
                        or(txtfldEmail.textProperty().isEmpty()));

        txtfldCPF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d{0,11}?") && !newValue.isEmpty()) {
                txtfldCPF.setText(oldValue);
            } else {
                txtfldCPF.setText(newValue);
            }
        });

        txtfldCPF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue,
                    Boolean newValue) {
                txtfldCPF.getStyleClass().remove("error");
                if (oldValue) {
                    if (!isCpf(txtfldCPF.getText())) {
                        txtfldCPF.getStyleClass().add("error");
                    }
                }
            }
        });
    }
}
