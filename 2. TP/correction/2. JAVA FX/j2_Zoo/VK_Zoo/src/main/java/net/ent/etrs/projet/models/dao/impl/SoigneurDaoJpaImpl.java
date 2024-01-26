package net.ent.etrs.projet.models.dao.impl;

import net.ent.etrs.projet.models.dao.IDaoSoigneur;
import net.ent.etrs.projet.models.dao.exceptions.DaoException;
import net.ent.etrs.projet.models.entities.Soigneur;
import net.ent.etrs.projet.models.entities.references.Type;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.util.Map;
import java.util.stream.Collectors;

public class SoigneurDaoJpaImpl extends JpaBaseDao<Soigneur> implements IDaoSoigneur {

}