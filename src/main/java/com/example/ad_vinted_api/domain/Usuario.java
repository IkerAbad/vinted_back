package com.example.ad_vinted_api.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    private String username;
    @Nullable
    private String nombre_completo;
    @NotNull
    @Email
    private String email;
    @Nullable
    private double valoracion;
    @Nullable
    private String localizacion;
    @NotNull
    private String password;
    //@NotNull
    //private RolUsuario usuario_rol=RolUsuario.ROLE_USER;

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", nombre_completo='" + nombre_completo + '\'' +
                ", correo='" + email + '\'' +
                ", valoracion=" + valoracion +
                ", localizacion='" + localizacion + '\'' +
                ", contrasenia='" + password + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "usuario",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Producto> productoList = new ArrayList<>();
//    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
//    private List<Oferta>ofertaList = new ArrayList<>();
//    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
//    private List<Contraoferta>contraofertas = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    public Usuario(String email, String username, String passwd, String name) {
        this.email = email;
        this.username = username;
        this.password = passwd;
        this.nombre_completo = name;
    }
}
