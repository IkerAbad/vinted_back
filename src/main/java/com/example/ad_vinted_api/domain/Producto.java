package com.example.ad_vinted_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @NotNull
    private String nombre;
    @NotNull
    private String categoria;
    @NotNull
    private String talla;
    @NotNull
    private String estado;
    @NotNull
    private String marca;
    @NotNull
    private String detalles;
    @NotNull
    private double precio;

    @OneToOne(mappedBy = "producto",optional = true,cascade = CascadeType.ALL)
    @JsonIgnore
    private Pedido pedido;

    @ManyToOne(optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @JsonIgnore
    private Usuario usuario;

    @Override
    public String toString() {
        return "Producto{" +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", talla='" + talla + '\'' +
                ", estado='" + estado + '\'' +
                ", marca='" + marca + '\'' +
                ", detalles='" + detalles + '\'' +
                ", precio=" + precio +
                '}';
    }
}
