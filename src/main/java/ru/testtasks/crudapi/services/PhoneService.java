package ru.testtasks.crudapi.services;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtasks.crudapi.exception.GeneralException;
import ru.testtasks.crudapi.model.entity.PhoneEnt;
import ru.testtasks.crudapi.model.entity.UserEnt;
import ru.testtasks.crudapi.repository.PhoneRepository;

@Service
@RequiredArgsConstructor
public class PhoneService {

  private final PhoneRepository phoneRepository;

  @Transactional
  public UUID createPhone(UserEnt userEnt, String phone) {
    if (!phoneRepository.existsByPhoneIgnoreCase(phone)) {
      return phoneRepository.save(
          PhoneEnt.builder().id(UUID.randomUUID()).phone(phone).user(userEnt).build()
      ).getId();
    } else {
      throw new GeneralException("Данный номер уже используется");
    }
  }

  @Transactional
  public void updatePhone(UserEnt userEnt, UUID phone_id, String phone) {
    phoneRepository.findByIdAndUserId(phone_id, userEnt.getId())
        .ifPresent(el -> {
          el.setPhone(phone);
          phoneRepository.save(el);
        });
  }

  @Transactional
  public void deletePhone(UserEnt userEnt, UUID phone_id) {
    phoneRepository.findByIdAndUserId(phone_id, userEnt.getId())
        .ifPresentOrElse(phoneRepository::delete, () -> {
          throw new GeneralException("Телефон для удаления не найден");
        });
  }
}

