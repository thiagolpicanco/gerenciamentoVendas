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
	  
	  CREATE SEQUENCE sq_nota_fiscal
	  INCREMENT 1
	  MINVALUE 00001
	  MAXVALUE 9223372036854775807
	  START 00001
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
	
	
	
	

	
	CREATE TABLE categoria_produto  ( id integer NOT NULL PRIMARY KEY, no_categoria varchar(2)
	)
	
	WITH ( OIDS=FALSE );
	
	
	

	CREATE TABLE CLIENTE
	(
	  id integer NOT NULL DEFAULT nextval('sq_cliente'::regclass),
	  nome varchar(2) NOT NULL,
	  cpf_Cnpj varchar(2) NOT NULL,
	  telefone varchar(2),
	  celular varchar(2),
	  email varchar(2),
	  endereco varchar(2),
	  bairro varchar(2),
	  cidade varchar(2),
	  CONSTRAINT pk_cliente PRIMARY KEY (id)
	  )
	WITH (
	  OIDS=FALSE
	);
	ALTER TABLE CLIENTE OWNER TO postgres;




	CREATE TABLE FUNCIONARIO
	(
	  id integer NOT NULL DEFAULT nextval('sq_funcionario'::regclass),
	  nome varchar(2) NOT NULL,
	  cpf_Cnpj varchar(2) unique not null,
	  telefone varchar(2),
	  celular varchar(2),
	  email varchar(2),
	  endereco varchar(2),
	  cargo varchar(2),
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
	  usuario varchar(2) not null,
	  senha varchar(2) not null,
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
	  nome varchar(2) NOT NULL,
	  cpf_Cnpj varchar(2),
	  telefone varchar(2),
	  celular varchar(2),
	  email varchar(2),
	  endereco varchar(2),
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
	  nota_fiscal varchar(2),
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
	no_tipo_pagamento varchar(2) not null,

	constraint pk_tipo_pagamento primary key (id)
	)WITH (
	  OIDS=FALSE
	);
	
	
	
	
	CREATE TABLE VENDA
	(
	  nu_venda integer NOT NULL DEFAULT nextval('sq_venda'::regclass),
	  id_funcionario integer NOT NULL,
	  nota_fiscal varchar(2),
	  data_venda date,
	  status varchar(2),
	  valor_total numeric not null,
	  tipo_pagamento integer not null,
	  id_cliente integer not null,
	  CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) references
	  CLIENTE (id) MATCH SIMPLE
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
	
	

	CREATE TABLE PRODUTO
	(
	  cod_produto integer NOT NULL,
	  tamanho varchar(2) not null,
	  nome varchar(2) NOT NULL,
	  id_fornecedor integer NOT NULL,
	  descricao varchar(2),
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
	tamanho varchar(2) not null,
	nu_venda integer,
	data_saida timestamp not null,
	quantidade integer not null,
	observacao varchar(2),
	
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
	tamanho varchar(2) not null,
	nu_compra integer,
	data_entrada timestamp not null,
	quantidade integer not null,
	observacao varchar(2),
	valor_untario numeric,
	
	constraint fk_produto_entrada foreign key (cod_produto, tamanho) references
	PRODUTO (cod_produto, tamanho) MATCH SIMPLE ON update restrict on DELETE restrict,
	  CONSTRAINT fk_compra_entrada FOREIGN KEY (nu_compra)
		  REFERENCES compra (nu_compra) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT
	
	)WITH (
	  OIDS=FALSE
	);


	ALTER TABLE ENTRADA_PRODUTO OWNER TO postgres;


	
	
	
	insert into cliente values (0, 'Jorge Carlos Souza', '05788847116', '2126202191', '21970014019', 'joao@gmail.com', 'Rua Carlos Monteiro 90', 'Centro', 'Cabo Frio');


		CREATE TABLE NOTA_FISCAL(nu_nota_fiscal integer not null, id_cliente integer, id_funcionario integer,
		CONSTRAINT fk_cliente_nf FOREIGN KEY (id_cliente) references
	  		CLIENTE (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT,
		  CONSTRAINT fk_funcionario_nf FOREIGN KEY (id_funcionario) references
	  		FUNCIONARIO (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT)
		  
		  
	
	
	

	insert into tipo_pagamento values(1, 'Cheque');
	insert into tipo_pagamento values(2, 'Credito');
	insert into tipo_pagamento values(3, 'Debito');
	insert into tipo_pagamento values(4, 'Dinheiro');
	
	insert into categoria_produto values(1, 'Camisas');
	insert into categoria_produto values(2, 'Shorts');
	insert into categoria_produto values(3, 'Saias');
	insert into categoria_produto values(4, 'Calçados');
	insert into categoria_produto values(5, 'Vestidos');
	
	
	insert into tipo_funcionario values(1, 'Gerente');
	insert into tipo_funcionario values(2, 'Vendedor');
	insert into tipo_funcionario values(3, 'Estoquista');
	
	ALTER TABLE categoria_produto OWNER TO postgres;
	ALTER TABLE tipo_funcionario OWNER TO postgres;
	
	insert into fornecedor values (0, 'Aeropostale', '212121212212', '2126212901','21970014000', 'aero@gmail.com', 'Rua Brasil 54, Centro, Rio de Janeiro');
	insert into funcionario values (0, 'Thiago Picanço', '05782955716', '21970014018','21970014018', 'thiagolpicanco@gmail.com', 'RUa danta tal', 1);
	insert into login values ('admin', '202cb962ac59075b964b07152d234b70', 0);
	
	
	

	