package gp.taskcrud.model;

import gp.taskcrud.validator.StartsWithAlph;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity of the developer")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Schema(description = "ID of the developer")
    private Long id;

    @Size(min = 2, max = 50)
    @Column
    @NotNull
    @StartsWithAlph
    @Schema(description = "Name of the developer")
    private String name;

    @Email
    @Column(unique = true)
    @NotNull
    @Schema(description = "Unique email of the developer")
    private String email;

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Developer developer = (Developer) obj;
        if((!developer.getEmail().equals(email)||(!developer.getName().equals(name)))){
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
