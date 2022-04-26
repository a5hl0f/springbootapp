package org.mik.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = Country.TBL_NAME)
public class Country extends AbstractEntity<Long> {
    public static final String TBL_NAME="country";
    public static final String FLD_NAME="name";
    public static final String FLD_SIGN="sign";

    @Column(name = FLD_NAME, nullable = false, unique = true)
    @NotNull(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "name length must be between 2 and 20")
    private String name;

    @Column(name = FLD_SIGN, nullable = false, unique = true)
    @NotNull(message = "Sign cannot be empty")
    @Size(min = 1, max = 20, message = "Sign length must be between 1 and 20")  //!!
    private String sign;

}
