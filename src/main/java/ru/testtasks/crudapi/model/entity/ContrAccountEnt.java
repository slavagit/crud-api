package ru.testtasks.crudapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "contrAccount")
@Table(name = "contr_accounts")
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class ContrAccountEnt  extends  AbstractAccount  {
}
