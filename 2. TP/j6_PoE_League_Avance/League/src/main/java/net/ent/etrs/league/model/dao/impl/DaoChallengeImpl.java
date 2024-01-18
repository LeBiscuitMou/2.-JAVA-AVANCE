package net.ent.etrs.league.model.dao.impl;

import lombok.*;
import net.ent.etrs.league.model.dao.IDaoChallenge;
import net.ent.etrs.league.model.dao.base.JpaBaseDao;
import net.ent.etrs.league.model.entities.Challenge;

import javax.persistence.*;
import javax.validation.constraints.*;

public class DaoChallengeImpl extends JpaBaseDao<Challenge> implements IDaoChallenge {
}