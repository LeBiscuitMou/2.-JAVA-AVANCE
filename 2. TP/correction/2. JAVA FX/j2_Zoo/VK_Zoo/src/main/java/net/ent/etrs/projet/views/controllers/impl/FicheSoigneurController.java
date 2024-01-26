package net.ent.etrs.projet.views.controllers.impl;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.NoArgsConstructor;
import net.ent.etrs.projet.models.entities.EntitiesFactory;
import net.ent.etrs.projet.models.entities.Soigneur;
import net.ent.etrs.projet.models.entities.references.Type;
import net.ent.etrs.projet.models.exceptions.BusinessException;
import net.ent.etrs.projet.models.exceptions.EntitiesFactoryException;
import net.ent.etrs.projet.models.facades.impl.FacadeMetierFactory;
import net.ent.etrs.projet.models.facades.interfaces.IFacadeSoigneur;
import net.ent.etrs.projet.start.Lanceur;
import net.ent.etrs.projet.views.commons.javaFX.ComboBoxItemWrap;

import java.io.IOException;
import java.util.function.Function;

@NoArgsConstructor
public class FicheSoigneurController implements Function<Soigneur, FicheSoigneurController> {
    IFacadeSoigneur fMetSoigneur = FacadeMetierFactory.fabriquerFacadeSoigneur();

    @FXML
    protected TextField frPrenom;
    @FXML
    protected TextField frNom;
    @FXML
    protected DatePicker frDdN;
    @FXML
    protected DatePicker frDA;
    @FXML
    protected ComboBox<ComboBoxItemWrap<Type>> frType;

    private Soigneur soigneur = null;
    public FicheSoigneurController(Soigneur soigneur) {
        this.soigneur = soigneur;
    }

    @FXML
    public void initialize(){
        if(null != soigneur){
            frNom.setText(soigneur.getNom());
        }

        frType.setItems(ComboBoxItemWrap.createComboWrap(frType, Type.values()));
    }

    @FXML
    public void valider() {
        Soigneur newSoigneur = null;

        try {
            newSoigneur = EntitiesFactory.fabriquerSoigneur(frNom.getText(), frPrenom.getText(), frDdN.getValue()
                    , frDA.getValue(), ComboBoxItemWrap.getAllChecked(frType.getItems()));
        } catch (EntitiesFactoryException e) {
            throw new RuntimeException(e);
        }

        if(null != this.soigneur){
            newSoigneur.setId(this.soigneur.getId());
        }

        sauvegarderSoigneur(newSoigneur);

    }

    private void sauvegarderSoigneur(Soigneur newSoigneur) {
        try {
            fMetSoigneur.save(newSoigneur);
        } catch (BusinessException e) {
            try {
                Lanceur.loadFXML("menuPrincipal");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        try {
            Lanceur.loadFXML("listeSoigneur");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Applies this function to the given argument.
     *
     * @param soigneur the function argument
     * @return the function result
     */
    @Override
    public FicheSoigneurController apply(Soigneur soigneur) {
        return new FicheSoigneurController(soigneur);
    }
}
