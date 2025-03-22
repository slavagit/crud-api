package ru.testtasks.crudapi.repository;


import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.testtasks.crudapi.model.entity.EmailEnt;

@Repository
public interface EmailRepository extends CrudRepository<EmailEnt, UUID>,
    JpaSpecificationExecutor<EmailEnt> {


  boolean existsByEmailIgnoreCase(String email);

  Optional<EmailEnt> findByIdAndUserId(UUID email_id, UUID user_id);


}
