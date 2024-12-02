Oi professora, o projeto conceitual e logico, assim como o video pode ser acesso no arquivo trabalho.pdf

O nosso projeto é separado em 2 pastas:

o gymesc_core é a parte da Api, para subi-la você precisa ter o docker instalado, daí basta você acessar essa pasta pelo terminal e rodar o comando:

```sh
docker compose up --build
```

assim ele irá subir a aplicação no seu localhost na porta 8080 e seu banco de dados mysql na porta 3306 cujos dados de autenticação é "root" e a senha "master"


no gymesc_web é onde fica o front-end do projeto, ele é feito em angular 17 e para roda-lo você também precisa acessar essa pasta pelo terminal e rodar o comando:

```sh
docker compose up --build
```

assim ele irá subir o front-end no seu localhost na porta 4200 que você poderá estar acessando em http://localhost:4200/login para iniciar o processo de teste do projeto

dump mysql:
```sql
INSERT INTO gymesc.USER (ID, CREATE_DATE, DELETED, LAST_EDIT, EMAIL, FEDERAL_IDENTIFICATION, HEIGHT, NAME, PASSWORD, PHONE_NUMBER, WEIGHT, WORKOUT_LEVEL) VALUES (1, '2024-09-29 21:07:54.896430', false, '2024-09-29 21:07:54.896430', 'admin@admin.com', null, 100, 'admin', 'X/JbuxPCmeY=', null, 100, 'BEGINNER');

INSERT INTO gymesc.WORKOUT (ID, CREATE_DATE, DELETED, LAST_EDIT, DESCRIPTION, BASE_DURATION, NAME, SCALING_RULE, WORKOUT_LEVEL, USER_ID) VALUES (1, '2024-09-29 21:20:27.310991', false, '2024-09-29 21:20:27.310991', 'Um treino de alta intensidade com exercícios aeróbicos como burpees, polichinelos e corrida estacionária para aumentar a frequência cardíaca e queimar calorias.', 35, 'Cardio Explosivo', null, 'BEGINNER', 1);
INSERT INTO gymesc.WORKOUT (ID, CREATE_DATE, DELETED, LAST_EDIT, DESCRIPTION, BASE_DURATION, NAME, SCALING_RULE, WORKOUT_LEVEL, USER_ID) VALUES (2, '2024-09-29 21:21:08.832083', false, '2024-09-29 21:21:08.832083', 'Focado em exercícios de musculação utilizando pesos livres ou peso corporal, como agachamentos, levantamento terra e flexões, para aumentar a força e a resistência muscular.', 15, 'Força Total', null, 'BEGINNER', 1);
INSERT INTO gymesc.WORKOUT (ID, CREATE_DATE, DELETED, LAST_EDIT, DESCRIPTION, BASE_DURATION, NAME, SCALING_RULE, WORKOUT_LEVEL, USER_ID) VALUES (3, '2024-09-29 21:22:12.826881', false, '2024-09-29 21:22:12.826881', 'Série de exercícios que simulam movimentos do dia a dia, como levantamento, puxada e empurrão, para melhorar a funcionalidade, equilíbrio e coordenação do corpo.', 25, 'Treino Funcional', null, 'BEGINNER', 1);

INSERT INTO gymesc.CATEGORY (ID, NAME, USER_ID) VALUES (1, 'Burpee', 1);
INSERT INTO gymesc.CATEGORY (ID, NAME, USER_ID) VALUES (2, 'Agachamento', 1);
INSERT INTO gymesc.CATEGORY (ID, NAME, USER_ID) VALUES (3, 'Prancha', 1);
INSERT INTO gymesc.CATEGORY (ID, NAME, USER_ID) VALUES (4, 'Flexão', 1);
INSERT INTO gymesc.CATEGORY (ID, NAME, USER_ID) VALUES (5, 'Perna', 1);
INSERT INTO gymesc.CATEGORY (ID, NAME, USER_ID) VALUES (6, 'Remada', 1);
INSERT INTO gymesc.CATEGORY (ID, NAME, USER_ID) VALUES (7, 'Ombro', 1);
INSERT INTO gymesc.CATEGORY (ID, NAME, USER_ID) VALUES (8, 'Abdominal', 1);

INSERT INTO gymesc.EXERCISE (ID, CREATE_DATE, DELETED, LAST_EDIT, DESCRIPTION, EXERCISE_TYPE, NAME, AUTHOR_ID, CATEGORY_ID, USER_ID) VALUES (1, '2024-09-29 21:14:23.230320', false, '2024-09-29 21:14:23.230320', 'Um exercício de corpo inteiro que combina agachamento, salto e flexão de braço em um movimento contínuo. Ótimo para condicionamento cardiovascular e força.', 'REPETITION', 'Burpee Infernal', 1, 1, 1);
INSERT INTO gymesc.EXERCISE (ID, CREATE_DATE, DELETED, LAST_EDIT, DESCRIPTION, EXERCISE_TYPE, NAME, AUTHOR_ID, CATEGORY_ID, USER_ID) VALUES (2, '2024-09-29 21:14:42.184116', false, '2024-09-29 21:14:42.184116', 'Um agachamento tradicional com ênfase em descer até o ponto máximo da flexão do joelho, ativando os músculos das coxas e glúteos.', 'REPETITION', 'Agachamento Profundo', 1, 2, 1);
INSERT INTO gymesc.EXERCISE (ID, CREATE_DATE, DELETED, LAST_EDIT, DESCRIPTION, EXERCISE_TYPE, NAME, AUTHOR_ID, CATEGORY_ID, USER_ID) VALUES (3, '2024-09-29 21:16:09.923014', false, '2024-09-29 21:16:09.923014', 'Manter-se na posição de prancha, com antebraços e pontas dos pés no chão, para trabalhar o core, ombros e estabilidade corporal.', 'DURATION', 'Prancha Estática', 1, 3, 1);
INSERT INTO gymesc.EXERCISE (ID, CREATE_DATE, DELETED, LAST_EDIT, DESCRIPTION, EXERCISE_TYPE, NAME, AUTHOR_ID, CATEGORY_ID, USER_ID) VALUES (4, '2024-09-29 21:16:27.239680', false, '2024-09-29 21:16:27.239680', 'Variação de flexão de braço com as mãos próximas, formando um diamante. Trabalha principalmente os tríceps e o peito.', 'REPETITION', 'Flexão de Diamante', 1, 4, 1);
INSERT INTO gymesc.EXERCISE (ID, CREATE_DATE, DELETED, LAST_EDIT, DESCRIPTION, EXERCISE_TYPE, NAME, AUTHOR_ID, CATEGORY_ID, USER_ID) VALUES (5, '2024-09-29 21:17:40.069436', false, '2024-09-29 21:17:40.069436', 'Movimento de avanço com uma explosão vertical no topo, alternando as pernas no ar. Excelente para trabalhar pernas, glúteos e coordenação.', 'REPETITION', 'Avanço com Salto', 1, 5, 1);
INSERT INTO gymesc.EXERCISE (ID, CREATE_DATE, DELETED, LAST_EDIT, DESCRIPTION, EXERCISE_TYPE, NAME, AUTHOR_ID, CATEGORY_ID, USER_ID) VALUES (6, '2024-09-29 21:18:09.098061', false, '2024-09-29 21:18:09.098061', 'Com barra ou halteres, inclinar o tronco para frente e puxar os pesos em direção ao abdômen, trabalhando a musculatura das costas e bíceps.', 'REPETITION', 'Remada Curvada', 1, 6, 1);
INSERT INTO gymesc.EXERCISE (ID, CREATE_DATE, DELETED, LAST_EDIT, DESCRIPTION, EXERCISE_TYPE, NAME, AUTHOR_ID, CATEGORY_ID, USER_ID) VALUES (7, '2024-09-29 21:18:29.390771', false, '2024-09-29 21:18:29.390771', 'Em posição de prancha, alternar os joelhos em direção ao peito rapidamente. Trabalha o core, ombros e aumenta a frequência cardíaca.', 'DURATION', 'Mountain Climber', 1, 3, 1);
INSERT INTO gymesc.EXERCISE (ID, CREATE_DATE, DELETED, LAST_EDIT, DESCRIPTION, EXERCISE_TYPE, NAME, AUTHOR_ID, CATEGORY_ID, USER_ID) VALUES (8, '2024-09-29 21:18:53.261745', false, '2024-09-29 21:18:53.261745', 'Deitado com os pés apoiados no chão, elevar o quadril até formar uma linha reta dos joelhos aos ombros. Trabalha glúteos e posterior de coxa.', 'REPETITION', 'Elevação de Quadril', 1, 5, 1);
INSERT INTO gymesc.EXERCISE (ID, CREATE_DATE, DELETED, LAST_EDIT, DESCRIPTION, EXERCISE_TYPE, NAME, AUTHOR_ID, CATEGORY_ID, USER_ID) VALUES (9, '2024-09-29 21:19:09.160321', false, '2024-09-29 21:19:09.160321', 'Com halteres, elevar os braços lateralmente até a altura dos ombros, mantendo-os retos. Focado nos músculos dos ombros.', 'REPETITION', 'Elevação Lateral', 1, 7, 1);
INSERT INTO gymesc.EXERCISE (ID, CREATE_DATE, DELETED, LAST_EDIT, DESCRIPTION, EXERCISE_TYPE, NAME, AUTHOR_ID, CATEGORY_ID, USER_ID) VALUES (10, '2024-09-29 21:19:28.683200', false, '2024-09-29 21:19:28.683200', 'Sentado com o tronco levemente inclinado para trás e pés elevados, girar o tronco de um lado para o outro segurando um peso. Trabalha o core, especialmente os oblíquos.', 'REPETITION', 'Twist Russo', 1, 8, 1);

INSERT INTO gymesc.WORKOUT_EXERCISE (WORKOUT_ID, EXERCISE_ID) VALUES (1, 1);
INSERT INTO gymesc.WORKOUT_EXERCISE (WORKOUT_ID, EXERCISE_ID) VALUES (1, 2);
INSERT INTO gymesc.WORKOUT_EXERCISE (WORKOUT_ID, EXERCISE_ID) VALUES (1, 5);
INSERT INTO gymesc.WORKOUT_EXERCISE (WORKOUT_ID, EXERCISE_ID) VALUES (1, 7);
INSERT INTO gymesc.WORKOUT_EXERCISE (WORKOUT_ID, EXERCISE_ID) VALUES (2, 4);
INSERT INTO gymesc.WORKOUT_EXERCISE (WORKOUT_ID, EXERCISE_ID) VALUES (2, 6);
INSERT INTO gymesc.WORKOUT_EXERCISE (WORKOUT_ID, EXERCISE_ID) VALUES (2, 9);
INSERT INTO gymesc.WORKOUT_EXERCISE (WORKOUT_ID, EXERCISE_ID) VALUES (3, 3);
INSERT INTO gymesc.WORKOUT_EXERCISE (WORKOUT_ID, EXERCISE_ID) VALUES (3, 8);
INSERT INTO gymesc.WORKOUT_EXERCISE (WORKOUT_ID, EXERCISE_ID) VALUES (3, 10);
```

pra fazer login no site:
email: admin@admin.com
senha: admin
