package org.mik.spring.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@DiscriminatorValue("company")
@SuperBuilder
@Table(name = Company.TBL_NAME)
public class Company extends Client {

    public static final String TBL_NAME="company";
    public static final String FLD_TAX_NUMBER="tax_number";

    @Column(name = FLD_TAX_NUMBER, nullable = false, unique = true)
    @NotNull
    @Size(min = 13, max = 13, message = "tax Number exactly 13 characters long")
    private String taxNumber;
}
