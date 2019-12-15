package api.motolife.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_location")
public class UserLocation implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "last_location_update")
    @NotNull
    private Timestamp last_location_update;

    @Column(name = "latitude")
    @NotNull
    private Double latitude;

    @Column(name = "longitude")
    @NotNull
    private Double longitude;

    @Override
    public String toString() {
        return "UserLocation:\n"+
                "Id:"+getId()+
                ", User_id: "+getUser().getId()+
                ", Last location update: "+getLast_location_update()+
                ", Latitude: "+getLatitude() +
                ", Longitude: "+getLongitude()+"\n";
    }
}
