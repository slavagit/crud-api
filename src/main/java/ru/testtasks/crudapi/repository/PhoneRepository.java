package ru.testtasks.crudapi.repository;


import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.testtasks.crudapi.model.entity.PhoneEnt;

@Repository
public interface PhoneRepository extends CrudRepository<PhoneEnt, UUID>, JpaSpecificationExecutor<PhoneEnt> {


  boolean existsByPhoneIgnoreCase(String email);
  Optional<PhoneEnt> findByIdAndUserId(UUID phone_id, UUID user_id);

}
