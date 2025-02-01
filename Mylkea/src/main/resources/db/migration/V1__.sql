CREATE TABLE carrito
(
    id         INT AUTO_INCREMENT NOT NULL,
    usuario_id INT                NOT NULL,
    CONSTRAINT pk_carrito PRIMARY KEY (id)
);

CREATE TABLE carrito_product
(
    id          INT AUTO_INCREMENT NOT NULL,
    producto_id INT                NULL,
    carrito_id  INT                NULL,
    CONSTRAINT pk_carrito_product PRIMARY KEY (id)
);

CREATE TABLE pedido
(
    id           INT AUTO_INCREMENT NOT NULL,
    fecha        date               NOT NULL,
    precio_total DOUBLE             NULL,
    usuario_id   INT                NOT NULL,
    CONSTRAINT pk_pedido PRIMARY KEY (id)
);

CREATE TABLE pedido_product
(
    id          INT AUTO_INCREMENT NOT NULL,
    producto_id INT                NULL,
    pedido_id   INT                NULL,
    CONSTRAINT pk_pedido_product PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)       NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    role_id INT NOT NULL,
    user_id INT NOT NULL
);

CREATE TABLE usuario
(
    id       INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255)       NOT NULL,
    password VARCHAR(255)       NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

ALTER TABLE carrito
    ADD CONSTRAINT uc_carrito_usuario UNIQUE (usuario_id);

ALTER TABLE `role`
    ADD CONSTRAINT uc_role_name UNIQUE (name);

ALTER TABLE carrito
    ADD CONSTRAINT FK_CARRITO_ON_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE carrito_product
    ADD CONSTRAINT FK_CARRITO_PRODUCT_ON_CARRITO FOREIGN KEY (carrito_id) REFERENCES carrito (id);

ALTER TABLE carrito_product
    ADD CONSTRAINT FK_CARRITO_PRODUCT_ON_PRODUCTO FOREIGN KEY (producto_id) REFERENCES productoffer (product_id);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE pedido_product
    ADD CONSTRAINT FK_PEDIDO_PRODUCT_ON_PEDIDO FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE pedido_product
    ADD CONSTRAINT FK_PEDIDO_PRODUCT_ON_PRODUCTO FOREIGN KEY (producto_id) REFERENCES productoffer (product_id);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_role FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_usuario FOREIGN KEY (user_id) REFERENCES usuario (id);

DROP TABLE customer;