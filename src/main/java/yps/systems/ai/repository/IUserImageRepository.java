package yps.systems.ai.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import yps.systems.ai.model.UserImageCollection;

@Repository
public interface IUserImageRepository extends ReactiveMongoRepository<UserImageCollection, String> {

    Mono<UserImageCollection> findUserImageCollectionByIdUser(Long idUser);

    Mono<Boolean> existsUserImageCollectionByIdUser(Long idUser);

    Mono<Void> deleteUserImageCollectionByIdUser(Long idUser);

}
