package ru.testtasks.crudapi.services;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtasks.crudapi.controllers.dto.GenericPage;
import ru.testtasks.crudapi.controllers.dto.UserDto;
import ru.testtasks.crudapi.controllers.dto.UserFilterDto;
import ru.testtasks.crudapi.exception.GeneralException;
import ru.testtasks.crudapi.model.entity.UserEnt;
import ru.testtasks.crudapi.repository.UserRepository;
import ru.testtasks.crudapi.specification.UserSpecification;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final UserSpecification userSpecification;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByName(username).orElse(UserEnt.builder().build());
  }

  public UserEnt getCurrentUser() {
    return (UserEnt)
        Optional.ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .map(Authentication::getPrincipal)
            .orElseThrow(() -> new GeneralException("Не найден данные пользователя"));
  }

  public UserEnt getUser(UUID userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new GeneralException("Не найден данные пользователя"));
  }

  @Transactional(readOnly = true)
  public GenericPage<UserDto> getUsersByFilter(UserFilterDto filter, Pageable pageable) {
    Specification<UserEnt> spec = userSpecification.getByFilter(filter);
    Page<UserEnt> userEntPage = userRepository.findAll(spec, pageable);
    return new GenericPage<>(
        userEntPage.map(userEnt -> new UserDto(userEnt.getId(), userEnt.getName())).getContent(),
        userEntPage.getTotalElements());
  }

}
