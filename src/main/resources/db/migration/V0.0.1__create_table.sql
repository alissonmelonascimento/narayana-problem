CREATE TABLE public.usuario (
	id int8 NOT NULL,
	nome varchar(100) NOT NULL,
	sobrenome varchar(100) NOT NULL,
	ano int8 NOT NULL
);

alter table public.usuario add CONSTRAINT usuario_pkey PRIMARY KEY(id);