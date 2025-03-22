package ru.testtasks.crudapi.specification;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.testtasks.crudapi.controllers.dto.UserFilterDto;
import ru.testtasks.crudapi.model.entity.EmailEnt;
import ru.testtasks.crudapi.model.entity.EmailEnt_;
import ru.testtasks.crudapi.model.entity.PhoneEnt;
import ru.testtasks.crudapi.model.entity.PhoneEnt_;
import ru.testtasks.crudapi.model.entity.UserEnt;
import ru.testtasks.crudapi.model.entity.UserEnt_;

import javax.persistence.criteria.Join;
import java.time.LocalDate;
import java.util.Objects;

@Component
public class UserSpecification {

    public Specification<UserEnt> getByFilter(UserFilterDto filter) {
        Specification<UserEnt> specification = Specification.where(null);
        if (StringUtils.isNotBlank(filter.getName())) {
            specification = specification.and(
                    findByLikeName(filter.getName())
            );
        }
        if (StringUtils.isNotBlank(filter.getPhone())) {
            specification = specification.and(
                    findByPhone(filter.getPhone())
            );
        }
        if (StringUtils.isNotBlank(filter.getEmail())) {
            specification = specification.and(
                    findByEmail(filter.getEmail())
            );
        }
        if (Objects.nonNull(filter.getDateOfBirth())) {
            specification = specification.and(
                    findByDateOfBirth(filter.getDateOfBirth())
            );
        }
        return specification;
    }

    public Specification<UserEnt> findByLikeName(String name) {
        return (root, query, cb) -> cb.like(root.get(UserEnt_.NAME), name + "%");
    }

    public Specification<UserEnt> findByDateOfBirth(LocalDate dateOfBirth) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(UserEnt_.DATE_OF_BIRTH), dateOfBirth);
    }


    public Specification<UserEnt> findByEmail(String email) {
        return (root, query, cb) -> {
            Join<UserEnt, EmailEnt> userEntEmailEntJoin = root.join(UserEnt_.emails);
            return cb.equal(userEntEmailEntJoin.get(EmailEnt_.EMAIL), email);
        };
    }

    public Specification<UserEnt> findByPhone(String phone) {
        return (root, query, cb) -> {
            Join<UserEnt, PhoneEnt> userEntPhoneEntJoin = root.join(UserEnt_.phones);
            return cb.equal(userEntPhoneEntJoin.get(PhoneEnt_.PHONE), phone);
        };
    }
}
