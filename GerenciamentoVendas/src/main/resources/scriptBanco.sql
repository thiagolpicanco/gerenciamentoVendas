	--CRIACAO TABELA E SEQUENCE - CLIENTE
	CREATE SEQUENCE sq_cliente
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 10
	  CACHE 1;

	ALTER TABLE sq_cliente OWNER TO postgres;
	
	
	
	--CRIACAO TABELA E SEQUENCE - FUNCIONARIO
	CREATE SEQUENCE sq_funcionario
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 10
	  CACHE 1;

	ALTER TABLE sq_funcionario OWNER TO postgres;
	
	
	
	CREATE SEQUENCE sq_fornecedor
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 10
	  CACHE 1;
	  
	  CREATE SEQUENCE sq_nota_fiscal
	  INCREMENT 1
	  MINVALUE 00001
	  MAXVALUE 9223372036854775807
	  START 10
	  CACHE 1;


	ALTER TABLE sq_fornecedor OWNER TO postgres;
	
		--CRIACAO SEQUENCE - COMPRA
	CREATE SEQUENCE sq_compra
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 10
	  CACHE 1;

	ALTER TABLE sq_compra OWNER TO postgres;
	
	

	
	--CRIACAO TABELA E SEQUENCE - ESTOQUE
	CREATE SEQUENCE sq_saida_produto
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 10
	  CACHE 1;

	ALTER TABLE sq_saida_produto OWNER TO postgres;
	
	
	
	--CRIACAO TABELA E SEQUENCE - ESTOQUE
	CREATE SEQUENCE sq_entrada_produto
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 10
	  CACHE 1;

	ALTER TABLE sq_entrada_produto OWNER TO postgres;
	
	--CRIACAO TABELA E SEQUENCE - VENDA
	CREATE SEQUENCE sq_venda
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 10
	  CACHE 1;

	ALTER TABLE sq_venda OWNER TO postgres;
	
	
	
	

	
	CREATE TABLE categoria_produto  ( id integer NOT NULL PRIMARY KEY, no_categoria varchar
	)
	
	WITH ( OIDS=FALSE );
	
	
	

	CREATE TABLE CLIENTE
	(
	  id integer NOT NULL DEFAULT nextval('sq_cliente'::regclass),
	  nome varchar NOT NULL,
	  cpf varchar NOT NULL,
	  telefone varchar,
	  celular varchar,
	  email varchar,
	  endereco varchar,
	  bairro varchar,
	  cidade varchar,
	  CONSTRAINT pk_cliente PRIMARY KEY (id)
	  )
	WITH (
	  OIDS=FALSE
	);
	ALTER TABLE CLIENTE OWNER TO postgres;




	CREATE TABLE FUNCIONARIO
	(
	  id integer NOT NULL DEFAULT nextval('sq_funcionario'::regclass),
	  nome varchar NOT NULL,
	  cpf varchar unique not null,
	  telefone varchar,
	  celular varchar,
	  email varchar,
	  endereco varchar,
	  cargo varchar,
	  CONSTRAINT pf_funcionario PRIMARY KEY (id)
	  )
	WITH (
	  OIDS=FALSE
	);
	ALTER TABLE FUNCIONARIO OWNER TO postgres;
	
	CREATE TABLE LOGIN
	(
	  usuario varchar not null,
	  senha varchar not null,
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
	  nome varchar NOT NULL,
	  cnpj varchar,
	  telefone varchar,
	  celular varchar,
	  email varchar,
	  endereco varchar,
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
	  nota_fiscal varchar,
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
	

	CREATE TABLE  TIPO_PAGAMENTO(
	id integer not null,
	no_tipo_pagamento varchar not null,

	constraint pk_tipo_pagamento primary key (id)
	)WITH (
	  OIDS=FALSE
	);
	
	
	
		
	
	

	CREATE TABLE PRODUTO
	(
	  cod_produto integer NOT NULL,
	  tamanho varchar not null,
	  nome varchar NOT NULL,
	  id_fornecedor integer NOT NULL,
	  descricao varchar,
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
	
	
	
	
	

CREATE TABLE NOTA_FISCAL(nu_nota_fiscal integer primary key not null, id_cliente integer, id_funcionario integer,
		CONSTRAINT fk_cliente_nf FOREIGN KEY (id_cliente) references
	  		CLIENTE (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT,
		  CONSTRAINT fk_funcionario_nf FOREIGN KEY (id_funcionario) references
	  		FUNCIONARIO (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT);
	
	CREATE TABLE VENDA
	(
	  nu_venda integer NOT NULL DEFAULT nextval('sq_venda'::regclass),
	  id_funcionario integer NOT NULL,
	  nota_fiscal integer,
	  data_venda date,
	  status varchar,
	  valor_total numeric not null,
	  tipo_pagamento integer not null,
	  id_cliente integer not null,
	  CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) references
	  CLIENTE (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT,
		   CONSTRAINT fk_nf FOREIGN KEY (nota_fiscal) references
	  nota_fiscal (nu_nota_fiscal) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT,
	  CONSTRAINT pk_venda PRIMARY KEY (nu_venda),
	  CONSTRAINT fk_pagamento FOREIGN KEY (tipo_pagamento) references
	  TIPO_PAGAMENTO (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT,
	  CONSTRAINT fk_venda_funcionario FOREIGN KEY (id_funcionario)
		  REFERENCES FUNCIONARIO (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT
	  )
	WITH (
	  OIDS=FALSE
	);

	CREATE TABLE  ENTRADA_PRODUTO(
	id integer not null,
	cod_produto integer not null,
	tamanho varchar not null,
	nu_compra integer,
	data_entrada timestamp not null,
	quantidade integer not null,
	observacao varchar,
	valor_unitario numeric,
	
	constraint fk_produto_entrada foreign key (cod_produto, tamanho) references
	PRODUTO (cod_produto, tamanho) MATCH SIMPLE ON update restrict on DELETE restrict,
	  CONSTRAINT fk_compra_entrada FOREIGN KEY (nu_compra)
		  REFERENCES compra (nu_compra) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT
	
	)WITH (
	  OIDS=FALSE
	);

	ALTER TABLE ENTRADA_PRODUTO OWNER TO postgres;


	CREATE TABLE SAIDA_PRODUTO(
	id integer not null,
	cod_produto integer not null,
	tamanho varchar not null,
	nu_venda integer,
	data_saida timestamp not null,
	quantidade integer not null,
	observacao varchar,
	
	constraint fk_produto_saida foreign key (cod_produto, tamanho) references
	PRODUTO (cod_produto, tamanho) MATCH SIMPLE ON update restrict on DELETE restrict,
	  CONSTRAINT fk_venda_saida FOREIGN KEY (nu_venda)
		  REFERENCES venda (nu_venda) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT
	
	)WITH (
	  OIDS=FALSE
	);
	ALTER TABLE SAIDA_PRODUTO OWNER TO postgres;
	
	



		  
		  
	
	
	
	insert into cliente values (0, 'Jorge Carlos Souza', '05788847116', '2126202191', '21970014019', 'joao@gmail.com', 'Rua Carlos Monteiro 90', 'Centro', 'Cabo Frio');

	insert into tipo_pagamento values(1, 'Cheque');
	insert into tipo_pagamento values(2, 'Credito');
	insert into tipo_pagamento values(3, 'Debito');
	insert into tipo_pagamento values(4, 'Dinheiro');
	
	insert into categoria_produto values(1, 'Camisas');
	insert into categoria_produto values(2, 'Shorts');
	insert into categoria_produto values(3, 'Saias');
	insert into categoria_produto values(4, 'Calçados');
	insert into categoria_produto values(5, 'Vestidos');
	
		
	ALTER TABLE categoria_produto OWNER TO postgres;

	
	insert into funcionario values (0, 'Thiago Picanço', '057.829.557-16', '(21)97001-4018','(21)97001-4018', 'thiagolpicanco@gmail.com', 'Rua Gomes de Sá, Arraial', 'Gerente');
	insert into login values ('admin', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', 0);
	
	
	

	