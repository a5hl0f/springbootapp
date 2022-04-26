package org.mik.spring.entity.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.mik.spring.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = false)
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = MikUser.TBL_NAME)
public class MikUser extends AbstractEntity<Long> {
    public static final String TBL_NAME="users";
    public static final String FLD_USERNAME="username";
    public static final String FLD_PASSWORD="password";
    public static final String FLD_FIRST_NAME="first_name";
    public static final String FLD_LAST_NAME="last_name";
    public static final String FLD_MAIL="mail";
    public static final String FLD_AUTHORITY="authority";
    public static final String FLD_ACTIVE="active";

    @NotNull
    @Column(name = FLD_USERNAME, unique = true, updatable = false, nullable = false)
    private String userName;

    @NotNull
    @Column(name = FLD_PASSWORD, nullable = false)
    private String password;

    @NotNull
    @Size(min = 3, message = "min l is 3")
    @Column(name = FLD_FIRST_NAME, nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 3, message = "min l is 3")
    @Column(name = FLD_LAST_NAME, nullable = false)
    private String lastName;

    @NotNull
    @Email
    @Column(name = FLD_MAIL, nullable = false)
    private String mail;

    @Transient
    private String fullName;

    @ElementCollection
    @JoinTable(name = FLD_AUTHORITY,
            joinColumns = {@JoinColumn(name = FLD_USERNAME)})
    @Column(name = FLD_AUTHORITY)
    private Set<String> roles;

    @Column(name = FLD_ACTIVE)
    private Boolean active;

    public void setFullName(String fn) {
        if (fn==null || fn.isEmpty())
            return;

        String parts[] = fn.split(" ");
        setFirstName(parts[0]);
        setLastName(parts[1]);
    }

    public  String getFullName() {
        return firstName + " " + lastName;
    }

}
