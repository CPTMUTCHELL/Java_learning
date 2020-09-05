package example.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "roles")
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String role;
    @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    private List<User> users;
}
