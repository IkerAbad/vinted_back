package com.example.ad_vinted_api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contraoferta {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    private double precio;
    @ManyToOne
    @JoinColumn(name = "usuario_ID")
    private Usuario usuario;

}