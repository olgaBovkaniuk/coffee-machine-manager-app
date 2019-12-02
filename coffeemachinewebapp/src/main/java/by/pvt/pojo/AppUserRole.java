package by.pvt.pojo;

import by.pvt.type.RoleName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class AppUserRole {

    @Id
    @Column(name = "roleId", nullable = false, columnDefinition = "char(36)")
    private String roleId;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
