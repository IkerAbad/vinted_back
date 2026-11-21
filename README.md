# vinted_back

API REST en Java con Spring Boot (mayo de 2023, ciclo de DAM) para una práctica de tienda de ropa de segunda mano inspirada en Vinted: usuarios, productos, ofertas y contraofertas, y pedidos. El cliente web es [vinted_front](https://github.com/IkerAbad/vinted_front).

## Qué hay

- Autenticación con JWT y Spring Security: `/api/auth/signup`, `/api/auth/signin` y `/api/auth/logout`, con roles de usuario.
- CRUD de productos (`/producto`), usuarios (`/usuarios`) y pedidos (`/pedido`), más ofertas y contraofertas.
- Entidades JPA con relaciones uno a uno, uno a muchos y muchos a uno sobre MySQL (`domain`, `repository`, `service`, `controller`).
- Compila con Java 17 y Maven. Subí Lombok a la 1.18.30 para poder compilar con un JDK moderno. No la he arrancado ni probado los endpoints desde aquí.

## Cómo probarla

Hace falta MySQL con una base `vinted`. Por defecto se conecta a `192.168.56.101:3306` (la máquina virtual de clase) con el usuario `user`; se puede cambiar con las variables de entorno `DB_URL`, `DB_USER` y `DB_PASS`. El secreto de los tokens es `JWT_SECRET`; si no existe usa un valor de ejemplo, que no debe usarse fuera de pruebas.

```
./mvnw spring-boot:run
```

Escucha en `http://localhost:8080`.

## Licencia

MIT, ver [LICENSE](LICENSE). La parte de seguridad con JWT sigue el esquema de un ejemplo público de autenticación, como delata el nombre `example.app.jwtSecret` de su configuración; la licencia cubre mi código.

---

# vinted_back (English)

A REST API in Java with Spring Boot (May 2023, DAM vocational course) for a second-hand clothing store assignment inspired by Vinted: users, products, offers and counteroffers, and orders. The web client is [vinted_front](https://github.com/IkerAbad/vinted_front).

## What's inside

- JWT authentication with Spring Security: `/api/auth/signup`, `/api/auth/signin` and `/api/auth/logout`, with user roles.
- CRUD for products (`/producto`), users (`/usuarios`) and orders (`/pedido`), plus offers and counteroffers.
- JPA entities with one-to-one, one-to-many and many-to-one relationships over MySQL (`domain`, `repository`, `service`, `controller`).
- It builds with Java 17 and Maven. I bumped Lombok to 1.18.30 so it compiles on a modern JDK. I have not started it or tested the endpoints from here.

## Trying it

You need MySQL with a `vinted` database. By default it connects to `192.168.56.101:3306` (the classroom virtual machine) as user `user`; override it with the `DB_URL`, `DB_USER` and `DB_PASS` environment variables. The token secret is `JWT_SECRET`; if it is missing it uses an example value, which must not be used outside tests.

```
./mvnw spring-boot:run
```

It listens on `http://localhost:8080`.

## License

MIT, see [LICENSE](LICENSE). The JWT security part follows the scheme of a public authentication example, as the `example.app.jwtSecret` name in its configuration shows; the license covers my code.
