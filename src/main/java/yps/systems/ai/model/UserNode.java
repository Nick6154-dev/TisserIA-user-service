package yps.systems.ai.model;

import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("user")
public class UserNode {

    @Id
    @Property("id_person")
    private Long idPerson;

    @Property("id_user")
    private Long idUser;

    @Version
    private Long version;

    public UserNode() {
    }

    public UserNode(Long idPerson, Long idUser) {
        this.idPerson = idPerson;
        this.idUser = idUser;
    }

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
