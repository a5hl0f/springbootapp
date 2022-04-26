package org.mik.spring.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true) //!!
@NoArgsConstructor
@Entity(name = Person.TBL_NAME)
@DiscriminatorValue("person")
@SuperBuilder
public class Person extends Client {

    public static final String TBL_NAME="person";
    public static final String FLD_ID_NUMBER ="id_number";

    @NotNull
    @Size(min = 13, max = 13, message = "idNumber exactly 13 characters long")
    @Column(name = FLD_ID_NUMBER, nullable = false, unique = true)
    private String idNumber;

}
