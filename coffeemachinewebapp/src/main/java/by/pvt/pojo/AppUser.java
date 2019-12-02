package by.pvt.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class AppUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "userId", nullable = false, columnDefinition = "char(36)")
    private String userId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToMany
    private Set<AppUserRole> appUserRoles;

}
