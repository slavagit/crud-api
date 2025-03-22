package ru.testtasks.crudapi.services;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtasks.crudapi.exception.GeneralException;
import ru.testtasks.crudapi.model.entity.EmailEnt;
import ru.testtasks.crudapi.model.entity.UserEnt;
import ru.testtasks.crudapi.repository.EmailRepository;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final EmailRepository emailRepository;

  @Transactional
  public UUID createEmail(UserEnt userEnt, String email) {
    if (!emailRepository.existsByEmailIgnoreCase(email)) {
      return emailRepository.save(
          EmailEnt.builder().id(UUID.randomUUID()).email(email).user(userEnt).build()
      ).getId();
    } else {
      throw new GeneralException("Данный адрес уже используется");
    }
  }

  @Transactional
  public void updateEmail(UserEnt userEnt, UUID email_id, String email) {
    emailRepository.findByIdAndUserId(email_id, userEnt.getId())
        .ifPresent(el -> {
          el.setEmail(email);
          emailRepository.save(el);
        });
  }

  @Transactional
  public void deleteEmail(UserEnt userEnt, UUID email_id) {
    emailRepository.findByIdAndUserId(email_id, userEnt.getId())
        .ifPresentOrElse(emailRepository::delete, () -> {
          throw new GeneralException("Адрес для удаления не найден");
        });
  }

}
