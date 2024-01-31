package net.ent.etrs.geststage.model.dao.impl;

import net.ent.etrs.geststage.model.dao.IDaoStagiaire;
import net.ent.etrs.geststage.model.dao.base.JpaBaseDao;
import net.ent.etrs.geststage.model.dao.exceptions.DaoException;
import net.ent.etrs.geststage.model.entities.Stage;
import net.ent.etrs.geststage.model.entities.Stagiaire;

import javax.persistence.TypedQuery;
import java.util.Set;

public class DaoStagiaireImpl extends JpaBaseDao<Stagiaire> implements IDaoStagiaire {

}