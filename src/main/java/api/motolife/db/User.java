package api.motolife.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "token")
    @Nullable
    private String token;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private UserLocation userLocation;

    @Override
    public String toString() {
        return "User:\n"+
                "Id:"+getId()+
                ", Username: "+getUsername()+
                ", Email: "+getEmail()+
                ", Token: "+getToken() + "\n";
    }
}
