INSERT INTO Clients (firstname, lastname, mail, password, number_house, street, zip_code, town, country) VALUES
    ('Jean-Jacques', 'Debuchon', 'jjdebuchon@gmail.com', 'republiquecestmoi', 22, 'rue des prés', 75000, 'Paris', 'France'),
    ('Manu', 'Mitron', 'projeeeeet@elysee.fr', 'carabistouilles', 1, 'Elysee', 75000, 'Paris', 'France'),
    ('Virginie', 'Paprasse', 'debout@gmail.com', '5millions', 42, 'rue des dettes', 75000, 'Paris', 'France');

INSERT INTO Articles (name, description, price, img) VALUES 
    ('Chou', 'un chou', 1.99, 'img'),
    ('Lasagnes', 'des lasagnes', 3.54, 'imglasagne');

INSERT INTO Banished (mail) VALUES ('debout@gmail.com');

INSERT INTO Orders (id_client, commentary, date_time, total_price) VALUES 
    (1, 'nothing', '01-01-2022 10:00', 1.81);

INSERT INTO Order_Lines (id_order, id_article, quantity, delivery_status) VALUES
    (1, 1, 1, 'WAITING');

INSERT INTO Reclamations (id_client, title, description, date_time, status) VALUES
    (1, 'Republique', 'Ou est ma République ?', '24-04-2022 22:00', 'WAITING');

INSERT INTO Reclamation_messages (id_client, id_reclamation, content, date_time) VALUES
    (2, 1, 'Pas là.', '25-04-2022 09:00');