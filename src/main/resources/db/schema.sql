CREATE TABLE product_type
(
    id   SERIAL NOT NULL
        CONSTRAINT product_type_pk
            PRIMARY KEY,
    name VARCHAR(512)
);

CREATE TABLE product_category
(
    id   SERIAL NOT NULL 
        CONSTRAINT product_category_pk
            PRIMARY KEY ,
    name VARCHAR(512)
);

CREATE TABLE product
(
    id          SERIAL NOT NULL
        CONSTRAINT product_pk
            PRIMARY KEY,
    type_id     INTEGER
        CONSTRAINT product_type_id_fk
            REFERENCES product_type,
    category_id INTEGER
        CONSTRAINT product_category_id_fk
            REFERENCES product_category,
    description VARCHAR(512)
);

CREATE TABLE supplier
(
    id      SERIAL NOT NULL
        CONSTRAINT supplier_pk
            PRIMARY KEY,
    name    VARCHAR(512),
    address VARCHAR(2048),
    inn     BIGINT UNIQUE
);

CREATE TABLE supply
(
    id          SERIAL    NOT NULL
        CONSTRAINT supply_pk
            PRIMARY KEY,
    supplier_id INTEGER
        CONSTRAINT supply_supplier_id_fk
            REFERENCES supplier,
    created     TIMESTAMP NOT NULL DEFAULT now(),
    updated     TIMESTAMP
);

CREATE TYPE weight_measure AS ENUM ('GRAM',
    'KG',
    'TONNE');

CREATE TABLE supply_element
(
    id             SERIAL           NOT NULL
        CONSTRAINT supply_element_pk
            PRIMARY KEY,
    supply_id      INTEGER
        CONSTRAINT supply_element_supply_id_fk
            REFERENCES supply       NOT NULL,
    product_id     INTEGER
        CONSTRAINT supply_element_product_id_fk
            REFERENCES product      NOT NULL,
    quantity       INTEGER          NOT NULL,
    price          DOUBLE PRECISION NOT NULL,
    weight_measure weight_measure   NOT NULL
);

CREATE TABLE product_price
(
    id             SERIAL           NOT NULL
        CONSTRAINT product_price_pk
            PRIMARY KEY,
    product_id     INTEGER
        CONSTRAINT product_price_product_id_fk
            REFERENCES product      NOT NULL,

    supplier_id    INTEGER
        CONSTRAINT product_price_supplier_id_fk
            REFERENCES supplier     NOT NULL,
    period_start   DATE             NOT NULL,
    period_end     DATE             NOT NULL,
    price          DOUBLE PRECISION NOT NULL,
    weight_measure weight_measure   NOT NULL
);