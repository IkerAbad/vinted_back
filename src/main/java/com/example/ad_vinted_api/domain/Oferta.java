package com.example.ad_vinted_api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = true)
    private Long id;
    @NotNull
    private double precio_oferta;
    @NotNull
    private Date fecha;
    @NotNull
    private String mensaje;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;
    @Override
    public String toString() {
        return "Oferta{" +
                "id_oferta=" + id +
                ", precio_oferta=" + precio_oferta +
                ", fecha=" + fecha +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }


}