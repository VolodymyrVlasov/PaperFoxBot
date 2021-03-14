-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE customer
(
    customerId  uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    firstName   VARCHAR(50) NOT NULL,
    chatId      BIGINT,
    lastName    VARCHAR(50),
    phoneNumber VARCHAR(12),
    email       VARCHAR(50)
);

CREATE TABLE productMaterial
(
    materialId uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name       VARCHAR(20) NOT NULL
);

CREATE TYPE DELIVERY_METHOD as ENUM ('PickUp', 'NovaPoshta', 'Uklon');

CREATE TYPE PAYMENT_METHOD as ENUM ('LiqPay', 'Cash', 'IBAN');

CREATE TYPE ORDER_STATUS as ENUM ('NEW', 'InProgress', 'NeedChange', 'Completed','Canceled');

CREATE TABLE orderList
(
    orderId        uuid PRIMARY KEY   DEFAULT uuid_generate_v4(),
    deliveryMethod DELIVERY_METHOD,
    paymentMethod  PAYMENT_METHOD,
    orderStatus    ORDER_STATUS,
    pathURL        TEXT      NOT NULL,
    createdAt      TIMESTAMP NOT NULL DEFAULT now(),
    customerId     uuid REFERENCES customer (customerId)
);

CREATE TYPE ORDER_CLIENT_TYPE as ENUM ('Telegram', 'WebClient');

CREATE TABLE orderItem
(
    orderItemId uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    orderClient ORDER_CLIENT_TYPE,
    width       SMALLINT,
    height      SMALLINT,
    productName VARCHAR(50),
    price       SMALLINT,
    quantity    SMALLINT,
    orderId     uuid REFERENCES orderList (orderId),
    materialId  uuid REFERENCES productMaterial (materialId)


);

CREATE TABLE orderItemFile
(
    orderItemField uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    fileURL        TEXT NOT NULL,
    orderItemId    uuid REFERENCES orderItem (orderItemId)

);