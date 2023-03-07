# insert into cozinha (nome) values ('Brasileira');
# insert into cozinha (nome) values ('Japonesa');
# insert into cozinha (nome) values ('Indiana');
# insert into cozinha (nome) values ('Árabe');
#
# insert into restaurante (nome, taxa_frete, cozinha_id) values ('Zé do caranguejo', 10, 1);
# insert into restaurante (nome, taxa_frete, cozinha_id) values ('Sushi do Zé', 10, 2);
# insert into restaurante (nome, taxa_frete, cozinha_id) values ('Taj Mahal', 10, 3);
# insert into restaurante (nome, taxa_frete, cozinha_id) values ('Al-Kabab', 10, 4);
#
# insert into estados (nome) values ('Santa Catarina');
# insert into estados (nome) values ('Ceará');
# insert into estados (nome) values ('São Paulo');
# insert into estados (nome) values ('Amazonas');
# insert into estados (nome) values ('Sergipe');
#
# insert into cidades (nome, estado_id) values ('Florianópolis', 1);
# insert into cidades (nome, estado_id) values ('Fortaleza', 2);
#
# insert into formas_pagamento (descricao) values ('PIX');
# insert into formas_pagamento (descricao) values ('Cartão de crédito');
# insert into formas_pagamento (descricao) values ('Cartão de débito');
# insert into formas_pagamento (descricao) values ('Dinheiro');
#
# insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (1, 4),
#                                                                                     (2, 1), (2, 3), (2, 4),(3, 1),
#                                                                                     (3, 2), (3, 4),(4, 1);

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into estados (id, nome) values (1, 'Minas Gerais');
insert into estados(id, nome) values (2, 'São Paulo');
insert into estados (id, nome) values (3, 'Ceará');

insert into cidades (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidades (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidades (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidades (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidades (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);

insert into formas_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into formas_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into formas_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissoes (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissoes (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);