package yps.systems.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import yps.systems.ai.model.UserEntity;
import yps.systems.ai.model.UserImageCollection;
import yps.systems.ai.model.UserNode;
import yps.systems.ai.repository.IUserEntityRepository;
import yps.systems.ai.repository.IUserImageRepository;
import yps.systems.ai.repository.IUserNodeRepository;

@RestController
@RequestMapping("/userService")
public class UserController {

    @Autowired
    private IUserImageRepository imageRepository;

    @Autowired
    private IUserNodeRepository nodeRepository;

    @Autowired
    private IUserEntityRepository repository;

    @GetMapping("/findAll")
    public Flux<UserEntity> findAll() {
        return repository.findAll();
    }

    @GetMapping("/findById/{idUser}")
    public Mono<UserEntity> findById(@PathVariable Long idUser) {
        return repository.findById(idUser)
                .onErrorResume(error -> {
                    System.out.println(error.getMessage());
                    return Mono.just(new UserEntity());
                });
    }

    @GetMapping("/getImageUserById/{idUser}")
    public Mono<UserImageCollection> getImageUserById(@PathVariable Long idUser) {
        return imageRepository.findUserImageCollectionByIdUser(idUser)
                .switchIfEmpty(Mono.just(new UserImageCollection(null, null, null)))
                .onErrorResume(error -> {
                    System.out.println(error.getMessage());
                    return Mono.just(new UserImageCollection(null, null, null));
                });
    }

    @GetMapping("/findByUsername/{username}")
    public Mono<UserEntity> findByUsername(@PathVariable String username) {
        return repository.findUserEntityByUsername(username)
                .onErrorResume(error -> {
                    System.out.println(error.getMessage());
                    return Mono.just(new UserEntity());
                });
    }

    @GetMapping("/existsByUsername/{username}")
    public Mono<Boolean> existsByUsername(@PathVariable String username) {
        return repository.existsUserEntityByUsername(username);
    }

    @PostMapping("/save")
    public Mono<UserEntity> save(@RequestBody UserEntity userEntity) {
        return repository.save(userEntity)
                .flatMap(userSaved -> nodeRepository
                        .save(new UserNode(userEntity.getIdPerson(), userSaved.getIdUser()))
                        .thenReturn(userSaved))
                .onErrorResume(error -> {
                    System.out.println(error.getMessage());
                    return Mono.just(new UserEntity());
                });
    }

    @PostMapping("/saveUserImage/{idUser}")
    public Mono<UserImageCollection> saveUserImage(@PathVariable Long idUser, @RequestBody byte[] imageByte) {
        return imageRepository.existsUserImageCollectionByIdUser(idUser)
                .flatMap(exists -> {
                    UserImageCollection userImage = new UserImageCollection(idUser, imageByte);
                    if (exists) {
                        return imageRepository.deleteUserImageCollectionByIdUser(idUser)
                                .then(imageRepository.save(userImage));
                    } else {
                        return imageRepository.save(userImage);
                    }
                })
                .onErrorResume(error -> {
                    System.out.println(error.getMessage());
                    return Mono.just(new UserImageCollection(null, null));
                });
    }

    @DeleteMapping("/delete")
    public Mono<Boolean> delete(@RequestBody UserEntity userEntity) {
        return repository.findById(userEntity.getIdUser())
                .flatMap(userFounded -> repository
                        .delete(userFounded)
                        .then(nodeRepository.deleteUserNodeByIdUser(userFounded.getIdUser()))
                        .thenReturn(true))
                .onErrorResume(error -> {
                    System.out.println(error.getMessage());
                    return Mono.just(false);
                });
    }

    @DeleteMapping("/deleteById/{idUser}")
    public Mono<Boolean> deleteById(@PathVariable Long idUser) {
        return repository.findById(idUser)
                .flatMap(userFounded -> repository
                        .delete(userFounded)
                        .then(nodeRepository.deleteUserNodeByIdUser(userFounded.getIdUser()))
                        .thenReturn(true))
                .onErrorResume(error -> {
                    System.out.println(error.getMessage());
                    return Mono.just(false);
                });
    }

    @PatchMapping("/updateUsername/{idUser},{username}")
    public Mono<UserEntity> updateUsername(@PathVariable Long idUser, @PathVariable String username) {
        return repository.findById(idUser)
                .flatMap(userFounded -> {
                    userFounded.setUsername(username);
                    return repository.save(userFounded);
                })
                .onErrorResume(error -> {
                    System.out.println(error.getMessage());
                    return Mono.just(new UserEntity());
                });
    }

    @PatchMapping("/updatePassword/{idUser},{password}")
    public Mono<UserEntity> updatePassword(@PathVariable Long idUser, @PathVariable String password) {
        return repository.findById(idUser)
                .flatMap(userFounded -> {
                    userFounded.setPassword(password);
                    return repository.save(userFounded);
                })
                .onErrorResume(error -> {
                    System.out.println(error.getMessage());
                    return Mono.just(new UserEntity());
                });
    }

}
