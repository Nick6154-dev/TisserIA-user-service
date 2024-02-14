package yps.systems.ai.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "user-image")
public record UserImageCollection(@MongoId String idUserImage, @Field("id_user") Long idUser,
                                  @Field("image") byte[] imageByte) {
    public UserImageCollection(Long idUser, byte[] imageByte) {
        this(null, idUser, imageByte);
    }
}
