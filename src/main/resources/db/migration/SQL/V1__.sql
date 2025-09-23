CREATE TABLE atributo
(
    id           bigint IDENTITY (1, 1) NOT NULL,
    nome         varchar(255),
    valor_atual  int NOT NULL,
    valor_minimo int NOT NULL,
    valor_maximo int NOT NULL,
    sistema      varchar(255),
    ficha_id     bigint,
    CONSTRAINT pk_atributo PRIMARY KEY (id)
)
    GO

CREATE TABLE campanha
(
    id          bigint IDENTITY (1, 1) NOT NULL,
    name        varchar(255),
    master      varchar(255),
    qtd_players int    NOT NULL,
    sistema_id  bigint NOT NULL,
    user_id     bigint,
    CONSTRAINT pk_campanha PRIMARY KEY (id)
)
    GO

CREATE TABLE ficha
(
    id          bigint IDENTITY (1, 1) NOT NULL,
    nome        varchar(255),
    raca        varchar(255),
    classe      varchar(255),
    nivel       int NOT NULL,
    vida        int NOT NULL,
    mana        int NOT NULL,
    campanha_id bigint,
    CONSTRAINT pk_ficha PRIMARY KEY (id)
)
    GO

CREATE TABLE ficha_habilidades
(
    ficha_id      bigint NOT NULL,
    habilidade_id bigint NOT NULL
)
    GO

CREATE TABLE ficha_inventario
(
    ficha_id bigint NOT NULL,
    item_id  bigint NOT NULL
)
    GO

CREATE TABLE habilidade
(
    id        bigint IDENTITY (1, 1) NOT NULL,
    nome      varchar(255),
    descricao varchar(255),
    custo     int NOT NULL,
    sistema   varchar(255),
    CONSTRAINT pk_habilidade PRIMARY KEY (id)
)
    GO

CREATE TABLE herouser
(
    id         bigint IDENTITY (1, 1) NOT NULL,
    name       varchar(255),
    email      varchar(255),
    avatar_url varchar(255),
    CONSTRAINT pk_herouser PRIMARY KEY (id)
)
    GO

CREATE TABLE item
(
    id        bigint IDENTITY (1, 1) NOT NULL,
    nome      varchar(255),
    descricao varchar(255),
    poder     int NOT NULL,
    sistema   varchar(255),
    CONSTRAINT pk_item PRIMARY KEY (id)
)
    GO

CREATE TABLE sistema
(
    id       bigint IDENTITY (1, 1) NOT NULL,
    nome     varchar(255),
    vida_min int NOT NULL,
    vida_max int NOT NULL,
    mana_min int NOT NULL,
    mana_max int NOT NULL,
    CONSTRAINT pk_sistema PRIMARY KEY (id)
)
    GO

CREATE TABLE sistema_classes
(
    sistema_id bigint NOT NULL,
    classes    varchar(255)
)
    GO

CREATE TABLE sistema_racas
(
    sistema_id bigint NOT NULL,
    racas      varchar(255)
)
    GO

ALTER TABLE herouser
    ADD CONSTRAINT uc_herouser_email UNIQUE (email)
    GO

ALTER TABLE atributo
    ADD CONSTRAINT FK_ATRIBUTO_ON_FICHA FOREIGN KEY (ficha_id) REFERENCES ficha (id)
    GO

ALTER TABLE campanha
    ADD CONSTRAINT FK_CAMPANHA_ON_SISTEMA FOREIGN KEY (sistema_id) REFERENCES sistema (id)
    GO

ALTER TABLE campanha
    ADD CONSTRAINT FK_CAMPANHA_ON_USER FOREIGN KEY (user_id) REFERENCES herouser (id)
    GO

ALTER TABLE ficha
    ADD CONSTRAINT FK_FICHA_ON_CAMPANHA FOREIGN KEY (campanha_id) REFERENCES campanha (id)
    GO

ALTER TABLE ficha_habilidades
    ADD CONSTRAINT fk_fichab_on_ficha FOREIGN KEY (ficha_id) REFERENCES ficha (id)
    GO

ALTER TABLE ficha_habilidades
    ADD CONSTRAINT fk_fichab_on_habilidade FOREIGN KEY (habilidade_id) REFERENCES habilidade (id)
    GO

ALTER TABLE ficha_inventario
    ADD CONSTRAINT fk_ficinv_on_ficha FOREIGN KEY (ficha_id) REFERENCES ficha (id)
    GO

ALTER TABLE ficha_inventario
    ADD CONSTRAINT fk_ficinv_on_item FOREIGN KEY (item_id) REFERENCES item (id)
    GO

ALTER TABLE sistema_classes
    ADD CONSTRAINT fk_sistema_classes_on_sistema FOREIGN KEY (sistema_id) REFERENCES sistema (id)
    GO

ALTER TABLE sistema_racas
    ADD CONSTRAINT fk_sistema_racas_on_sistema FOREIGN KEY (sistema_id) REFERENCES sistema (id)
    GO