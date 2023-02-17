package view;

import static Config.Config.*;
import static DAO.DAO.professorRepository;
import Model.Professor;
import Utility.XPopOver;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.springframework.data.domain.Sort;

public class ProfessorController implements Initializable {

    public char acao;
    public Professor professor;

    @FXML
    public TableView<Professor> tblvwProfessores;

    @FXML
    private MaterialDesignIconView btnInclusao;

    @FXML
    private MaterialDesignIconView btnAlteracao;

    @FXML
    private MaterialDesignIconView btnExclusao;

    @FXML
    private void btnInclusaoClick(Event event) {
        acao = INCLUIR;
        professor = new Professor();
        showCRUD();
    }

    @FXML
    private void btnAlteracaoClick(Event event) {
        acao = ALTERAR;
        professor = tblvwProfessores.getSelectionModel().getSelectedItem();
        showCRUD();
    }

    @FXML
    private void btnExclusaoClick(Event event) {
        acao = EXCLUIR;
        professor = tblvwProfessores.getSelectionModel().getSelectedItem();
        showCRUD();
    }

    private void showCRUD() {
        String cena = "/fxml/CRUDProfessor.fxml";
        XPopOver popOver = null;

        switch (acao) {
            case INCLUIR:
                popOver = new XPopOver(cena, "Inclusão de Professor", null);
                break;
            case ALTERAR:
                popOver = new XPopOver(cena, "Alteração de Professor", null);
                break;
            case EXCLUIR:
                popOver = new XPopOver(cena, "Exclusão de Professor", null);
                break;
        }
        CRUDProfessorController controller = popOver.getLoader().getController();
        controller.setCadastroController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tblvwProfessores.setItems(FXCollections.observableList(professorRepository.findAll(new Sort(new Sort.Order("nome")))));

        btnAlteracao.visibleProperty().bind(Bindings.isEmpty((tblvwProfessores.getSelectionModel().getSelectedItems())).not());
        btnExclusao.visibleProperty().bind(btnAlteracao.visibleProperty());

    }
}
