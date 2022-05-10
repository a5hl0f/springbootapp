package org.mik.spring.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = false)
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuperBuilder
@Table(name = Client.TBL_NAME)
public abstract class Client extends AbstractEntity<Long> {

    public static final String TBL_NAME="client";
    public static final String FLD_NAME="name";
    public static final String FLD_COUNTRY="country";

    @Column(name = FLD_NAME, nullable = false)
    @NotNull(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "name length must be between 2 and 50")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FLD_COUNTRY)
    private Country country;

    private Address address;
}
