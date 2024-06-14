CREATE TABLE IF NOT EXISTS bookelse.users
(
    user_id       VARCHAR(50),
    pwd           VARCHAR(255)                NOT NULL,
    salt          VARCHAR(255)                NOT NULL,
    first_name    VARCHAR(25)                 NOT NULL,
    last_name     VARCHAR(25)                 NOT NULL,
    email_id      VARCHAR(100) UNIQUE         NOT NULL,
    contact       VARCHAR(10)                 NOT NULL,
    country_code  VARCHAR(5)                  NOT NULL,
    address_1     VARCHAR(100),
    address_2     VARCHAR(100),
    postal_code   VARCHAR(6),
    city          VARCHAR(15),
    state         VARCHAR(15),
    country       VARCHAR(15),
    bnk_acc_name  VARCHAR(25),
    bnk_acc_no    VARCHAR(50),
    bnk_ifsc_code VARCHAR(20),
    created_on    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    lst_update_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS bookelse.product
(
    product_id        VARCHAR(50),
    product_name      VARCHAR(50)                 NOT NULL,
    product_mrp       NUMERIC(10, 2)              NOT NULL,
    product_discount  NUMERIC(5, 2)               NOT NULL DEFAULT 0.00,
    product_net_price NUMERIC(10, 2)              NOT NULL,
    product_features  JSONB,
    created_on        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    lst_update_on     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (product_id)
);

CREATE TABLE IF NOT EXISTS bookelse.product_img
(
    img_id        VARCHAR(50),
    img_url       VARCHAR(255)                NOT NULL,
    img_lbl       VARCHAR(50)                 NOT NULL,
    alt_txt       VARCHAR(255)                NOT NULL,
    product_id    VARCHAR(50)                 NOT NULL,
    created_on    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    lst_update_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT product_img_product_id_fk FOREIGN KEY (product_id) REFERENCES bookelse.product (product_id),
    CONSTRAINT product_img_pkey PRIMARY KEY (img_id)
);

CREATE TABLE IF NOT EXISTS bookelse.book
(
    book_id       VARCHAR(50),
    book_name     VARCHAR(255)                NOT NULL,
    book_author   VARCHAR(255)                NOT NULL,
    publisher     VARCHAR(255)                NOT NULL,
    published_on  TIMESTAMP WITHOUT TIME ZONE,
    product_id    VARCHAR(50)                 NOT NULL,
    created_on    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    lst_update_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT book_product_id_fk FOREIGN KEY (product_id) REFERENCES bookelse.product (product_id),
    CONSTRAINT book_pkey PRIMARY KEY (book_id)

);

CREATE TABLE IF NOT EXISTS bookelse.rental_book
(
    rental_book_id VARCHAR(50),
    book_name      VARCHAR(255)                NOT NULL,
    book_author    VARCHAR(255)                NOT NULL,
    publisher      VARCHAR(255)                NOT NULL,
    published_on   TIMESTAMP WITHOUT TIME ZONE,
    depreciation   NUMERIC(5, 2)               NOT NULL,
    product_id     VARCHAR(50)                 NOT NULL,
    created_on     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    lst_update_on  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT rental_book_product_id_fk FOREIGN KEY (product_id) REFERENCES bookelse.product (product_id),
    CONSTRAINT rental_book_pkey PRIMARY KEY (rental_book_id)
);

CREATE TABLE IF NOT EXISTS bookelse.vendor
(
    vendor_id      VARCHAR(50),
    first_name     VARCHAR(25),
    last_name      VARCHAR(25),
    ctr_code       VARCHAR(8),
    vendor_contact VARCHAR(10),
    email_id       VARCHAR(100) UNIQUE,
    is_active      BOOLEAN                     NOT NULL,
    created_on     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    lst_update_on  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT vendor_pkey PRIMARY KEY (vendor_id)
);

CREATE TABLE IF NOT EXISTS bookelse.inventory
(
    inventory_id         VARCHAR(50),
    product_id           VARCHAR(50)                 NOT NULL,
    vendor_id            VARCHAR(50)                 NOT NULL,
    is_sold              BOOLEAN                     NOT NULL DEFAULT FALSE,
    is_present_inventory BOOLEAN                     NOT NULL DEFAULT TRUE,
    purchase_gross_amt   NUMERIC(10, 2)              NOT NULL,
    purchase_net_amt     NUMERIC(10, 2)              NOT NULL,
    discount             NUMERIC(5, 2)               NOT NULL,
    created_on           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    lst_update_on        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT inventory_vendor_id_fk FOREIGN KEY (vendor_id) REFERENCES bookelse.vendor (vendor_id),
    CONSTRAINT inventory_product_id_fk FOREIGN KEY (product_id) REFERENCES bookelse.product (product_id),
    CONSTRAINT inventory_pkey PRIMARY KEY (inventory_id)
);

CREATE TABLE IF NOT EXISTS bookelse.order
(
    order_id        VARCHAR(50),
    ordered_on      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_id         VARCHAR(50)                 NOT NULL,
    order_gross_amt NUMERIC(10, 2)              NOT NULL,
    order_net_amt   NUMERIC(10, 2)              NOT NULL,
    discount        NUMERIC(5, 2)               NOT NULL,
    is_trans_online BOOLEAN                     NOT NULL,
    is_amt_paid     BOOLEAN                     NOT NULL,
    pymt_mode       VARCHAR(25)                 NOT NULL,
    created_on      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    lst_update_on   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT order_user_id_fk FOREIGN KEY (user_id) REFERENCES bookelse.users (user_id),
    CONSTRAINT order_pkey PRIMARY KEY (order_id)
);

CREATE TABLE IF NOT EXISTS bookelse.sale_order_items
(
    sle_ord_itm_id VARCHAR(50),
    gross_amt      NUMERIC(10, 2)              NOT NULL,
    net_amt        NUMERIC(10, 2)              NOT NULL,
    discount       NUMERIC(5, 2)               NOT NULL,
    order_id       VARCHAR(50)                 NOT NULL,
    user_id        VARCHAR(50)                 NOT NULL,
    inventory_id   VARCHAR(50)                 NOT NULL,
    created_on     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    lst_update_on  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT sale_order_items_order_id_fk FOREIGN KEY (order_id) REFERENCES bookelse.order (order_id),
    CONSTRAINT sale_order_items_user_id_fk FOREIGN KEY (user_id) REFERENCES bookelse.users (user_id),
    CONSTRAINT sale_order_items_inventory_id_fk FOREIGN KEY (inventory_id) REFERENCES bookelse.inventory (inventory_id),
    CONSTRAINT sale_order_items_pkey PRIMARY KEY (sle_ord_itm_id)
);

CREATE TABLE IF NOT EXISTS bookelse.rent_order_items
(
    rnt_ord_itm_id       VARCHAR(50),
    gross_amt            NUMERIC(10, 2)              NOT NULL,
    net_amt              NUMERIC(10, 2)              NOT NULL,
    discount             NUMERIC(5, 2)               NOT NULL,
    receive_deposit      NUMERIC(10, 2)              NOT NULL,
    receive_depreciation NUMERIC(10, 2)              NOT NULL,
    rented_from          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    rented_to            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    lst_dte_to_return    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    order_id             VARCHAR(50)                 NOT NULL,
    user_id              VARCHAR(50)                 NOT NULL,
    inventory_id         VARCHAR(50)                 NOT NULL,
    created_on           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    lst_update_on        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT rent_order_items_items_order_id_fk FOREIGN KEY (order_id) REFERENCES bookelse.order (order_id),
    CONSTRAINT rent_order_items_items_user_id_fk FOREIGN KEY (user_id) REFERENCES bookelse.users (user_id),
    CONSTRAINT rent_order_items_inventory_id_fk FOREIGN KEY (inventory_id) REFERENCES bookelse.inventory (inventory_id),
    CONSTRAINT rent_order_items_pkey PRIMARY KEY (rnt_ord_itm_id)
);

CREATE TABLE IF NOT EXISTS bookelse.rent_itm_trns
(
    rent_itm_dep_trns_id VARCHAR(50),
    rent_start_dt        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    rent_end_dt          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    depreciation         NUMERIC(10, 2)              NOT NULL,
    rem_deposit          NUMERIC(10, 2)              NOT NULL,
    rnt_ord_itm_id       VARCHAR(50)                 NOT NULL,
    created_on           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    lst_update_on        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT rent_itm_dep_trns_rnt_ord_itm_id_fk FOREIGN KEY (rnt_ord_itm_id) REFERENCES bookelse.rent_order_items (rnt_ord_itm_id),
    CONSTRAINT rent_itm_dep_trns_pkey PRIMARY KEY (rent_itm_dep_trns_id)
);

CREATE TABLE IF NOT EXISTS bookelse.db_tbl_audit
(
    db_tbl_audit_id VARCHAR(50),
    operation       VARCHAR(25)                 NOT NULL,
    tbl_name        VARCHAR(50)                 NOT NULL,
    txt_cmt         TEXT                        NOT NULL,
    update_by       VARCHAR(100)                NOT NULL,
    created_on      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT db_tbl_audit_pkey PRIMARY KEY (db_tbl_audit_id)
);

CREATE TABLE IF NOT EXISTS bookelse.exception_audit
(
    id                  VARCHAR(50),
    user_id             VARCHAR(50)                 NOT NULL,
    exception_timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    exception_type      TEXT                        NOT NULL,
    stack_trace         TEXT                        NOT NULL,
    detailed_msg        TEXT                        NOT NULL,
    environment         VARCHAR(25)                 NOT NULL,
    severity            VARCHAR(10)                 NOT NULL,
    correlation_id      VARCHAR(50)                 NOT NULL,
    additional_data     JSONB,
    web_request         JSONB
);