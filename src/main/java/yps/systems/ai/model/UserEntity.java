package yps.systems.ai.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "\"user\"")
public class UserEntity {

    @Id
    @Column("id_user")
    private Long idUser;

    @Column("id_person")
    private Long idPerson;

    @Column("username")
    private String username;

    @Column("password")
    private String password;

    public UserEntity() {
    }

    public UserEntity(Long idPerson, String username, String password) {
        this.idPerson = idPerson;
        this.username = username;
        this.password = password;
    }

    public UserEntity(Long idUser, Long idPerson, String username, String password) {
        this.idUser = idUser;
        this.idPerson = idPerson;
        this.username = username;
        this.password = password;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
