-- Inserir dados na tabela animal
insert into animal(id, tipo, sexo, raca, porte, nome, cor, castrado, adocao, data) values(1, 'Gato', 'femea', 'Siamês', 'Pequeno', 'Luna', 'Branco', 'Sim', 'Não', '2023-01-16');
insert into animal(id, tipo, sexo, raca, porte, nome, cor, castrado, adocao, data) values (2, 'Cachorro', 'macho', 'Labrador', 'Grande', 'Rex', 'Amarelo', 'Não', 'Sim', '2023-02-10');
insert into animal(id, tipo, sexo, raca, porte, nome, cor, castrado, adocao, data) values(3, 'Gato', 'macho', 'Persa', 'Médio', 'Simba', 'Cinza', 'Sim', 'Não', '2023-05-18');
insert into animal(id, tipo, sexo, raca, porte, nome, cor, castrado, adocao, data) values(4, 'Gato', 'femea', 'Bengal', 'Pequeno', 'Nina', 'Dourado', 'Não', 'Não', '2023-10-02');
insert into animal(id, tipo, sexo, raca, porte, nome, cor, castrado, adocao, data) values(5, 'Cachorro', 'macho', 'Dachshund', 'Pequeno', 'Max', 'Preto e Marrom', 'Sim', 'Sim', '2023-09-09');
insert into animal(id, tipo, sexo, raca, porte, nome, cor, castrado, adocao, data) values(6, 'Cachorro', 'femea', 'Poodle', 'Médio', 'Mimi', 'Branco', 'Não', 'Sim', '2023-06-22');

-- Inserir dados na tabela vacina
INSERT INTO vacina (id, nome, fabricante) VALUES (1, 'Antirrábica', 'BioVet');
INSERT INTO vacina (id, nome, fabricante) VALUES(2, 'Vacina V10', 'Zoetis');
INSERT INTO vacina (id, nome, fabricante) VALUES(3, 'Vacina V8', 'Agener União');
INSERT INTO vacina (id, nome, fabricante) VALUES(4, 'Vacina Gripe', 'Virbac');
INSERT INTO vacina (id, nome, fabricante) VALUES(5, 'Vacina Leptospirose', 'MSD Saúde Animal');

-- Inserir dados na tabela animal_vacina
INSERT INTO animal_vacina (id, lote, data_aplicacao, animal_id, vacina_id) VALUES(1, 'L001', '2023-02-15', 1, 1);
INSERT INTO animal_vacina (id, lote, data_aplicacao, animal_id, vacina_id) VALUES(2, 'L002', '2023-03-20', 1, 2);
INSERT INTO animal_vacina (id, lote, data_aplicacao, animal_id, vacina_id) VALUES(3, 'L003', '2023-04-05', 3, 3);
INSERT INTO animal_vacina (id, lote, data_aplicacao, animal_id, vacina_id) VALUES(4, 'L004', '2023-05-18', 4, 4);
INSERT INTO animal_vacina (id, lote, data_aplicacao, animal_id, vacina_id) VALUES(5, 'L005', '2023-06-10', 5, 5);

-- Inserir dados na tabela doenca
INSERT INTO doenca (id, nome, gravidade) VALUES (1, 'Gripe Canina', 'Leve');
INSERT INTO doenca (id, nome, gravidade) VALUES (2, 'Parvovirose', 'Grave');
INSERT INTO doenca (id, nome, gravidade) VALUES (3, 'Cinomose', 'Muito Grave');
INSERT INTO doenca (id, nome, gravidade) VALUES (4, 'Leishmaniose', 'Grave');
INSERT INTO doenca (id, nome, gravidade) VALUES (5, 'Raiva', 'Fatal');

-- Inserir dados na tabela animal_doenca
INSERT INTO animal_doenca (id, status, descricao, animal_id, doenca_id) VALUES (1, 'Tratamento', 'Animal em tratamento para Gripe Canina', 1, 1);
INSERT INTO animal_doenca (id, status, descricao, animal_id, doenca_id) VALUES (2, 'Recuperado', 'Animal se recuperou completamente da Parvovirose', 2, 2);
INSERT INTO animal_doenca (id, status, descricao, animal_id, doenca_id) VALUES (3, 'Crônico', 'Animal com Cinomose em estado crônico', 3, 3);
INSERT INTO animal_doenca (id, status, descricao, animal_id, doenca_id) VALUES (4, 'Tratamento', 'Animal em tratamento para Leishmaniose', 4, 4);
INSERT INTO animal_doenca (id, status, descricao, animal_id, doenca_id) VALUES (5, 'Quarentena', 'Animal em quarentena devido à Raiva', 5, 5);

insert into users(id, role, celular,email, password, nome, sexo, cpf) values(1,0,"2346","kaiki@kaiki.com","$2a$10$zmzxbzXvp2JMhi39QYddwOYquMA9ZUU9cffKh85lY47zym/PWfC0i","kaiki","M","1234");