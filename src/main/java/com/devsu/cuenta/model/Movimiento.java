package com.devsu.cuenta.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "cuenta")
public class Cuenta extends BaseModel {

    @Id
    @GeneratedValue(generator = "cuentaId")
    @GenericGenerator(name = "cuentaId", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "cuentaId", updatable = false, nullable = false)
    private UUID cuentaId;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado")
    private String estado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(this.cuentaId, cuenta.cuentaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cuentaId);
    }
}
