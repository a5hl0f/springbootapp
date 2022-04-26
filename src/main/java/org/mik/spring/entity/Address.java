package org.mik.spring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Embeddable
@ToString(callSuper = false)
@NoArgsConstructor
public class Address {

    public static final String FLD_ZIP="zip";
    public static final String FLD_CITY="city";
    public static final String FLD_STREET="street";
    public static final String FLD_NUMBER="number";

    @NotNull
    @Size(min = 4, message = "ml n 4")
    @Column(name = FLD_ZIP, nullable = false)
    private String zip;

    @NotNull
    @Size(min = 4, message = "ml n 4")
    @Column(name = FLD_CITY, nullable = false)
    private String city;

    @NotNull
    @Size(min = 4, message = "ml n 4")
    @Column(name = FLD_STREET, nullable = false)
    private String street;

    @NotNull
    @Size(min = 4, message = "ml n 4")
    @Column(name = FLD_NUMBER, nullable = false)
    private String number;
}
