
CREATE SEQUENCE book_id_seq START 1;

create table if not exists book (
    id integer NOT NULL DEFAULT nextval('book_id_seq') primary key,
    name varchar(255) not null
);

ALTER SEQUENCE book_id_seq
OWNED BY book.id;