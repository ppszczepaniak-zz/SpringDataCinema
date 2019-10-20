INSERT INTO room (id,name,capacity,description) VALUES (1, 'Sala 1',130,'10 rzędów po 13 miejsc');
INSERT INTO room (id,name,capacity,description) VALUES (2, 'Sala 2',121,'11 rzędów po 11 miejsc');
INSERT INTO room (id,name,capacity,description) VALUES (3, 'Sala 3',150,'10 rzędów po 15 miejsc');
INSERT INTO room (id,name,capacity,description) VALUES (4, 'Sala 4',144,'12 rzędów po 12 miejsc');

INSERT INTO movie (id,title,category,length,description,required_Age) VALUES (5, 'After', 'ROMANTIC',106, 'Ekranizacja bestsellerowej powieści... ',18);
INSERT INTO movie (id,title,category,length,description,required_Age) VALUES (6, 'Avengers: Koniec gry', 'ACTION',182, 'Połowa życia w kosmosie...',15);
INSERT INTO movie (id,title,category,length,description, required_Age) VALUES (7, 'Green Book ', 'COMEDY',130, 'Tony, drobny cwaniaczek z Bronxu...',15);
INSERT INTO movie (id,title,category,length,description, required_Age) VALUES (8, 'Hellboy', 'ACTION',121, 'Kultowy superbohater z piekła rodem...',18);
INSERT INTO movie (id,title,category,length,description,required_Age) VALUES (9,' Impostor ', 'HORROR',90, 'Sarah, chcąc uciec przed przeszłością...',18);
INSERT INTO movie (id,title,category,length,description, required_Age) VALUES (10, 'Przemytnik', 'DRAMA',116, 'Eastwood wciela się w rolę Earla Stona...',15);
INSERT INTO movie (id,title,category, length,description, required Age) VALUES (11, 'Niedobrani', 'COMEDY',121, 'Fred nie jest typem amanta... ',15);

INSERT INTO poster (id,movie_id,file_path) VALUES (12,8, '/images/posterHellboy.png');
INSERT INTO poster (id,movie_id,file_path) VALUES (13,9, '/images/posterInmpostor.png');
INSERT INTO poster (id,movie_id,file_path) VALUES (14,10, '/images/posterPrzemytnik.png');

INSERT INTO session (id,movie_id,room id,start_time) VALUES (15,8,2, TIMESTAMP '2019-05-01 12:00:00');
INSERT INTO session (id,movie_id,room id,start_time) VALUES (16,8,1,TIMESTAMP '2019-05-02 18:00:00');
INSERT INTO session (id,movie_id,room id,start_time) VALUES (17,10,3,TIMESTAMP '2019-05-02 21:15:00');

INSERT INTO ticket (id,session_id,seat,price) VALUES (18,15, 'r10m2',21.00);
INSERT INTO ticket (id,session_id,seat,price) VALUES (19,15, 'r8m1',21.00);
INSERT INTO ticket (id,session_id,seat,price) VALUES (20,15, 'r8m2',18.00);
INSERT INTO ticket (id,session_id,seat,price) VALUES (21,15, 'r5m11',18.00);

SELECT setval('public.hibernate_sequence', 21, true);