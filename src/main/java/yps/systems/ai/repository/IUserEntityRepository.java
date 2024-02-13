package yps.systems.ai.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import yps.systems.ai.model.UserEntity;

@Repository
public interface IUserEntityRepository extends ReactiveCrudRepository<UserEntity, Long> {

    Mono<UserEntity> findUserEntityByUsername(String username);

    Mono<Boolean> existsUserEntityByUsername(String username);

}
