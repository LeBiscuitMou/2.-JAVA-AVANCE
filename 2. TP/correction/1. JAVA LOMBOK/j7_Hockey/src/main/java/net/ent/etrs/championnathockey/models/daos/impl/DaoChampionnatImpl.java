package net.ent.etrs.championnathockey.models.daos.impl;

import net.ent.etrs.championnathockey.models.daos.DaoChampionnat;
import net.ent.etrs.championnathockey.models.daos.DaoJoueur;
import net.ent.etrs.championnathockey.models.daos.exception.DaoException;
import net.ent.etrs.championnathockey.models.entities.Championnat;
import net.ent.etrs.championnathockey.models.entities.Equipe;
import net.ent.etrs.championnathockey.models.entities.Joueur;
import net.ent.etrs.championnathockey.models.facades.exceptions.BusinessException;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DaoChampionnatImpl extends AbstractJpaDao<Championnat, Serializable> implements DaoChampionnat {


}
