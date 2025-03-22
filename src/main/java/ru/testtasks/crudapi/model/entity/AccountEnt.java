package ru.testtasks.crudapi.model.entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "account")
@Table(name = "accounts")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class AccountEnt extends AbstractAccount {

}
