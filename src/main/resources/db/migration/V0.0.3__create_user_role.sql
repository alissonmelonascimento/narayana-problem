CREATE TABLE public.user_role (
	user_id int8 NOT NULL,
	role_id varchar(100) NOT NULL
);

alter table public.user_role add CONSTRAINT user_role_pkey PRIMARY KEY(user_id,role_id);