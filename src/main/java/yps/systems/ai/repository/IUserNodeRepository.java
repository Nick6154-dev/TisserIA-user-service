package yps.systems.ai.repository;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import yps.systems.ai.model.UserNode;

@Repository
public interface IUserNodeRepository extends ReactiveNeo4jRepository<UserNode, Long> {

    Mono<Void> deleteUserNodeByIdUser(Long idUser);

}
