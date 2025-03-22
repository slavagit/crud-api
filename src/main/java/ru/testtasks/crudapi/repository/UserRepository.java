package ru.testtasks.crudapi.repository;


import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.testtasks.crudapi.model.entity.UserEnt;

@Repository
public interface UserRepository extends CrudRepository<UserEnt, UUID>,
    PagingAndSortingRepository<UserEnt, UUID>,
    JpaSpecificationExecutor<UserEnt> {

  Optional<UserEnt> findByName(String name);

}
