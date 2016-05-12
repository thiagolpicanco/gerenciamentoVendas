	--CRIACAO TABELA E SEQUENCE - CLIENTE
	CREATE SEQUENCE sq_cliente
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_cliente OWNER TO postgres;


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
	  CONSTRAINT pk_cliente PRIMARY KEY (id)
	  )
	WITH (
	  OIDS=FALSE
	);
	ALTER TABLE CLIENTE OWNER TO postgres;



	--CRIACAO TABELA E SEQUENCE - FUNCIONARIO
	CREATE SEQUENCE sq_funcionario
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_funcionario OWNER TO postgres;


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
	  salario numeric,
	  percentualComissao text,
	  CONSTRAINT pf_funcionario PRIMARY KEY (id),
	  CONSTRAINT fk_tipo_funcionario FOREIGN KEY (tipo_funcionario)
	  REFERENCES tipo_funcionario (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT
	  )
	WITH (
	  OIDS=FALSE
	);
	ALTER TABLE FUNCIONARIO OWNER TO postgres;



	



	--CRIACAO TABELA E SEQUENCE - FORNECEDOR
	CREATE SEQUENCE sq_fornecedor
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_fornecedor OWNER TO postgres;

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



	--CRIACAO TABELA E SEQUENCE - PRODUTO
	CREATE SEQUENCE sq_produto
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_produto OWNER TO postgres;

	CREATE TABLE PRODUTO
	(
	  id integer NOT NULL DEFAULT nextval('sq_produto'::regclass),
	  nome text NOT NULL,
	  id_fornecedor integer NOT NULL,
	  descricao text,
	  categoria integer,
	  
	  palavras_chaves text,
	  CONSTRAINT pk_produto PRIMARY KEY (id),
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



	--CRIACAO TABELA E SEQUENCE - COMPRA
	CREATE SEQUENCE sq_compra
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_compra OWNER TO postgres;

	CREATE TABLE COMPRA
	(
	  id integer NOT NULL DEFAULT nextval('sq_compra'::regclass),
	  id_produto integer NOT NULL,
	  id_fornecedor integer NOT NULL,
	  id_funcionario integer NOT NULL,
	  valor_venda numeric,
	  nota_fiscal text,
	  data_compra date,
	  status text,
	  valor_total numeric,
	  CONSTRAINT pk_compra PRIMARY KEY (id),
	  CONSTRAINT fk_compra_produto FOREIGN KEY (id_produto)
		  REFERENCES PRODUTO (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT,
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









	--CRIACAO TABELA E SEQUENCE - VENDA
	CREATE SEQUENCE sq_venda
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_venda OWNER TO postgres;

	CREATE TABLE VENDA
	(
	  id integer NOT NULL DEFAULT nextval('sq_venda'::regclass),
	  id_produto integer NOT NULL,
	  id_funcionario integer NOT NULL,
	  nota_fiscal text,
	  data_venda date,
	  status text,
	  valor_total numeric,
	  CONSTRAINT pk_venda PRIMARY KEY (id),
	  CONSTRAINT fk_venda_produto FOREIGN KEY (id_produto)
		  REFERENCES PRODUTO (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT,
	  CONSTRAINT fk_venda_funcionario FOREIGN KEY (id_funcionario)
		  REFERENCES FUNCIONARIO (id) MATCH SIMPLE
		  ON UPDATE RESTRICT ON DELETE RESTRICT
	  )
	WITH (
	  OIDS=FALSE
	);
	
	ALTER TABLE VENDA OWNER TO postgres;
	
	CREATE TABLE compra_produto ( compra_id integer NOT NULL
	, produto_id integer NOT NULL, 
	CONSTRAINT fk_compra_id FOREIGN KEY (compra_id) REFERENCES compra (id) 
	MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION, 
	CONSTRAINT produto_id_fk FOREIGN KEY (produto_id) REFERENCES produto (id) 
	MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION)
	WITH ( OIDS=FALSE );
	
	CREATE TABLE venda_produto ( venda_id integer NOT NULL
	, produto_id integer NOT NULL, 
	CONSTRAINT fk_venda_id FOREIGN KEY (venda_id) REFERENCES venda (id) 
	MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION, 
	CONSTRAINT produto_id_fk_venda FOREIGN KEY (produto_id) REFERENCES ProdutoEstoque (id) 
	MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION)
	WITH ( OIDS=FALSE );
	
	
	
	
	--CRIACAO TABELA E SEQUENCE - ESTOQUE
	CREATE SEQUENCE sq_estoque
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_estoque OWNER TO postgres;

	CREATE TABLE PRODUTO_ESTOQUE
	(
	  id integer NOT NULL DEFAULT nextval('sq_estoque'::regclass) PRIMARY KEY,
	   id_produto integer NOT NULL,
		tamanho text,
			valor_compra numeric,
	  valor_venda numeric,
	    qtd_minima integer,
	  qtd_atual integer,
	
	  CONSTRAINT fk_produto_estoque FOREIGN KEY (id_produto)
		  REFERENCES produto (id)
	 
	  )
	WITH (
	  OIDS=FALSE
	);
	ALTER TABLE PRODUTO_ESTOQUE OWNER TO postgres;
	
	
	
	insert into categoria_produto values(1, 'Camisas');
	insert into categoria_produto values(2, 'Shorts');
	
	insert into tipo_funcionario values(1, 'Gerente');
	insert into tipo_funcionario values(2, 'Vendedor');
	

	
	
	
	ALTER TABLE categoria_produto OWNER TO postgres;
	ALTER TABLE tipo_funcionario OWNER TO postgres;
		ALTER TABLE compra_produto OWNER TO postgres;
		ALTER TABLE venda_produto OWNER TO postgres;
	
	
			--CRIACAO TABELA E SEQUENCE - FORNECEDOR
	CREATE SEQUENCE sq_login
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;

	ALTER TABLE sq_login OWNER TO postgres;

	CREATE TABLE LOGIN
	(
	  id integer NOT NULL DEFAULT nextval('sq_login'::regclass),
	  usuario text not null,
	  senha text not null,
	  id_funcionario integer not null,
		  CONSTRAINT pk_login PRIMARY KEY (id),
		  CONSTRAINT fk_func FOREIGN KEY (id_funcionario) references FUNCIONARIO (id)
	  )
	WITH (
	  OIDS=FALSE
	);

	ALTER TABLE LOGIN OWNER TO postgres;