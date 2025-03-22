package ru.testtasks.crudapi.specification.predicate;

import org.springframework.data.jpa.domain.Specification;
import ru.testtasks.crudapi.controllers.dto.UserFilterDto;
import ru.testtasks.crudapi.model.entity.UserEnt;
import ru.testtasks.crudapi.specification.UserSpecification;

import java.time.LocalDate;
import java.util.Map;
import java.util.function.Function;

public class UserPredicate {

    private final Map<String, Function<Object, Specification<UserEnt>>> fieldSpecs;

    public UserPredicate(UserSpecification specification) {
        this.fieldSpecs = Map.ofEntries(
                Map.entry(UserFilterDto.Fields.phone, phone ->
                        specification.findByPhone((String) phone)),
                Map.entry(UserFilterDto.Fields.email, email ->
                        specification.findByEmail((String) email)),
                Map.entry(UserFilterDto.Fields.dateOfBirth, dateOfBirth ->
                        specification.findByDateOfBirth((LocalDate) dateOfBirth)),
                Map.entry(UserFilterDto.Fields.name, name ->
                        specification.findByLikeName((String) name))
        );
    }

    public Map<String, Function<Object, Specification<UserEnt>>> getFieldSpecs() {
        return fieldSpecs;
    }

}
