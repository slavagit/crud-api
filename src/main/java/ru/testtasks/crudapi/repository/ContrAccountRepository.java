package ru.testtasks.crudapi.repository;


import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.testtasks.crudapi.model.entity.ContrAccountEnt;
import ru.testtasks.crudapi.model.entity.EmailEnt;

@Repository
public interface ContrAccountRepository extends CrudRepository<ContrAccountEnt, UUID>,
    JpaSpecificationExecutor<EmailEnt> {


  @Query(value = "select ac.* from contr_accounts ac where ac.user_id=:userId for update skip locked", nativeQuery = true)
  Optional<ContrAccountEnt> getUserAccountWithBlock(@Param("userId") UUID userId);


}
