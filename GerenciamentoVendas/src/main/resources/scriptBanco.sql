	--CRIACAO TABELA E SEQUENCE - CLIENTE
	CREATE SEQUENCE sq_cliente
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_cliente OWNER TO postgres;
	
	
	
	--CRIACAO TABELA E SEQUENCE - FUNCIONARIO
	CREATE SEQUENCE sq_funcionario
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_funcionario OWNER TO postgres;
	
	
	
	CREATE SEQUENCE sq_fornecedor
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_fornecedor OWNER TO postgres;
	
		--CRIACAO SEQUENCE - COMPRA
	CREATE SEQUENCE sq_compra
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_compra OWNER TO postgres;
	
	

	
	--CRIACAO TABELA E SEQUENCE - ESTOQUE
	CREATE SEQUENCE sq_saida_produto
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_saida_produto OWNER TO postgres;
	
	
	
	--CRIACAO TABELA E SEQUENCE - ESTOQUE
	CREATE SEQUENCE sq_entrada_produto
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_entrada_produto OWNER TO postgres;
	
	--CRIACAO TABELA E SEQUENCE - VENDA
	CREATE SEQUENCE sq_venda
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_venda OWNER TO postgres;
	
	
	CREATE TABLE categoria_produto  ( id integer NOT NULL PRIMARY KEY, no_categoria text
	)
	
	WITH ( OIDS=FALSE );
	
	
	
	CREATE TABLE tipo_funcionario ( id integer NOT NULL PRIMARY KEY, no_tipo text)
	
	WITH ( OIDS=FALSE );

	CREATE TABLE CLIENTE
	(
	  id integer NOT NULL DEFAULT nextval('sq_cliente'::regclass),
	  nome text NOT NULL,
	  cpf_Cnpj text NOT NULL,
	  telefone text,
	  celular text,
	  email text,
	  endereco text,
	  bairro text,
	  cidade text,
	  CONSTRAINT pk_cliente PRIMARY KEY (id)
	  )
	WITH (
	  OIDS=FALSE
	);
	ALTER TABLE CLIENTE OWNER TO postgres;




	CREATE TABLE FUNCIONARIO
	(
	  id integer NOT NULL DEFAULT nextval('sq_funcionario'::regclass),
	  nome text NOT NULL,
	  cpf_Cnpj text unique not null,
	  telefone text,
	  celular text,
	  email text,
	  endereco text,
	  tipo_funcionario integer,
	  CONSTRAINT pf_funcionario PRIMARY KEY (id),
	  CONSTRAINT fk_tipo_funcionario FOREIGN KEY (tipo_funcionario)
	  REFERENCES tipo_funcionario (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT
	  )
	WITH (
	  OIDS=FALSE
	);
	ALTER TABLE FUNCIONARIO OWNER TO postgres;
	
	CREATE TABLE LOGIN
	(
	  usuario text not null,
	  senha text not null,
	  id_funcionario integer not null,
		  CONSTRAINT pk_login PRIMARY KEY (usuario),
		  CONSTRAINT fk_func FOREIGN KEY (id_funcionario) references FUNCIONARIO (id)
	  )
	WITH (
	  OIDS=FALSE
	);

	ALTER TABLE LOGIN OWNER TO postgres;



	--CRIACAO TABELA E SEQUENCE - FORNECEDOR
	

	CREATE TABLE FORNECEDOR
	(
	  id integer NOT NULL DEFAULT nextval('sq_fornecedor'::regclass),
	  nome text NOT NULL,
	  cpf_Cnpj text,
	  telefone text,
	  celular text,
	  email text,
	  endereco text,
		  CONSTRAINT pk_fornecedor PRIMARY KEY (id)
	  )
	WITH (
	  OIDS=FALSE
	);

	ALTER TABLE FORNECEDOR OWNER TO postgres;
	

	CREATE TABLE COMPRA
	(
	  nu_compra integer NOT NULL DEFAULT nextval('sq_compra'::regclass),
	  id_fornecedor integer NOT NULL,
	  id_funcionario integer NOT NULL,
	  valor_venda numeric,
	  nota_fiscal text,
	  data_compra timestamp,
	valor_total numeric,
	  CONSTRAINT pk_compra PRIMARY KEY (nu_compra),
		 
	  CONSTRAINT fk_compra_fornecedor FOREIGN KEY (id_fornecedor)
		  REFERENCES FORNECEDOR (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT,
	  CONSTRAINT fk_compra_funcionario FOREIGN KEY (id_funcionario)
		  REFERENCES FUNCIONARIO (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT
	  )
	WITH (
	  OIDS=FALSE
	);
	ALTER TABLE COMPRA OWNER TO postgres;
	
	
	
	
	
	
	CREATE TABLE VENDA
	(
	  nu_venda integer NOT NULL DEFAULT nextval('sq_venda'::regclass),
	  id_funcionario integer NOT NULL,
	  nota_fiscal text,
	  data_venda date,
	  status text,
	  valor_total numeric not null,
	  tipo_pagamento integer not null,
	  CONSTRAINT pk_venda PRIMARY KEY (nu_venda),
	  CONSTRAINT fk_pagamento FOREIGN KEY (tipo_pagamento) references
	  TIPO_PAGAMENTO (id),
	  CONSTRAINT fk_venda_funcionario FOREIGN KEY (id_funcionario)
		  REFERENCES FUNCIONARIO (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT
	  )
	WITH (
	  OIDS=FALSE
	);
	
	

	CREATE TABLE PRODUTO
	(
	  cod_produto integer NOT NULL,
	  tamanho text not null,
	  nome text NOT NULL,
	  id_fornecedor integer NOT NULL,
	  descricao text,
	  categoria integer,
	  valor_venda numeric,
	  qtd_minima integer not null,
	  qtd_atual integer not null,
	  CONSTRAINT pk_produto PRIMARY KEY (cod_produto, tamanho),
	  CONSTRAINT fk_categoria FOREIGN KEY (categoria)
	  REFERENCES categoria_produto (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT,
	  CONSTRAINT fk_produto_fornecedor FOREIGN KEY (id_fornecedor)
		  REFERENCES FORNECEDOR (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT
	  )
	WITH (
	  OIDS=FALSE
	);
	ALTER TABLE PRODUTO OWNER TO postgres;
	
	
	CREATE TABLE SAIDA_PRODUTO(
	id integer not null,
	cod_produto integer not null,
	tamanho text not null,
	nu_venda integer,
	data_saida timestamp not null,
	quantidade integer not null,
	observacao text,
	
	constraint fk_produto_saida foreign key (cod_produto, tamanho) references
	PRODUTO (cod_produto, tamanho) MATCH SIMPLE ON update restrict on DELETE restrict,
	  CONSTRAINT fk_venda_saida FOREIGN KEY (nu_venda)
		  REFERENCES venda (nu_venda) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT
	
	)WITH (
	  OIDS=FALSE
	);
	ALTER TABLE SAIDA_PRODUTO OWNER TO postgres;
	
	CREATE TABLE  ENTRADA_PRODUTO(
	id integer not null,
	cod_produto integer not null,
	tamanho text not null,
	nu_compra integer,
	data_entrada timestamp not null,
	quantidade integer not null,
	observacao text,
	
	constraint fk_produto_entrada foreign key (cod_produto, tamanho) references
	PRODUTO (cod_produto, tamanho) MATCH SIMPLE ON update restrict on DELETE restrict,
	  CONSTRAINT fk_compra_entrada FOREIGN KEY (nu_compra)
		  REFERENCES compra (nu_compra) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT
	
	)WITH (
	  OIDS=FALSE
	);


	ALTER TABLE ENTRADA_PRODUTO OWNER TO postgres;

CREATE TABLE  TIPO_PAGAMENTO(
	id integer not null,
	no_tipo_pagamento text not null,

	constraint pk_tipo_pagamento primary key (id)
	)WITH (
	  OIDS=FALSE
	);




	insert into tipo_pagamento values(1, 'Cheque');
	insert into tipo_pagamento values(2, 'Credito');
	insert into tipo_pagamento values(3, 'Debito');
	insert into tipo_pagamento values(4, 'Dinheiro');
	
	insert into categoria_produto values(1, 'Camisas');
	insert into categoria_produto values(2, 'Shorts');
	
	insert into tipo_funcionario values(1, 'Gerente');
	insert into tipo_funcionario values(2, 'Vendedor');
	
	ALTER TABLE categoria_produto OWNER TO postgres;
	ALTER TABLE tipo_funcionario OWNER TO postgres;
		
	
	
	

	