package ru.testtasks.crudapi.schedulers;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.testtasks.crudapi.repository.UserRepository;
import ru.testtasks.crudapi.services.TransferService;

@Component
@RequiredArgsConstructor
public class InterestScheduler {

  private final TransferService transferService;
  private final UserRepository userRepository;

  @Scheduled(cron = "*/30 * * * * *")
  public void interestRun() {
    var iterator = userRepository.findAll().iterator();
    iterator.forEachRemaining(transferService::transferInterest);
  }


}
