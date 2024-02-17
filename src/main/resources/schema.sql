CREATE TABLE IF NOT EXISTS users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  CONSTRAINT pk_user_id PRIMARY KEY (id),
  CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS items (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR NOT null,
  available BOOLEAN not null,
  owner_id BIGINT not null,
  CONSTRAINT pk_item PRIMARY KEY  (id),
  CONSTRAINT fk_item_users FOREIGN KEY (owner_id) REFERENCES users ON DELETE CASCADE,
  );