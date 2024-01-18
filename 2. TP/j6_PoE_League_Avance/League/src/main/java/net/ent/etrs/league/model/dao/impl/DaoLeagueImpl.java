package net.ent.etrs.league.model.dao.impl;

import lombok.*;
import net.ent.etrs.league.model.dao.IDaoLeague;
import net.ent.etrs.league.model.dao.base.JpaBaseDao;
import net.ent.etrs.league.model.entities.League;

import javax.persistence.*;
import javax.validation.constraints.*;

public class DaoLeagueImpl extends JpaBaseDao<League> implements IDaoLeague {
}