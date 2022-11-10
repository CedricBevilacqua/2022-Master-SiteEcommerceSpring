CREATE TABLE IF NOT EXISTS Clients
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    firstname VARCHAR(64) NOT NULL,
    lastname VARCHAR(64) NOT NULL,
    mail VARCHAR(64) NOT NULL,
    password VARCHAR(128) NOT NULL,
    is_valid BOOLEAN DEFAULT "FALSE" NOT NULL,
    number_house INTEGER NOT NULL,
    street VARCHAR(128) NOT NULL,
    zip_code INTEGER NOT NULL,
    town VARCHAR(64) NOT NULL,
    country VARCHAR(64) NOT NULL,
    role VARCHAR(16) DEFAULT "CLIENT" NOT NULL,
    
    CONSTRAINT unique_mail UNIQUE (mail)
);

CREATE TABLE IF NOT EXISTS Orders
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  id_client  INTEGER NOT NULL,
  commentary VARCHAR(512),
  date_time DATETIME NOT NULL,
  total_price FLOAT NOT NULL,

  CONSTRAINT id_client_exists FOREIGN KEY (id_client) REFERENCES Clients(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Order_Lines
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  id_order INTEGER NOT NULL,
  id_article INTEGER NOT NULL,
  quantity INTEGER NOT NULL,
  delivery_status VARCHAR(16) NOT NULL,

  CONSTRAINT id_article_exists FOREIGN KEY (id_article) REFERENCES Articles(id) ON DELETE CASCADE,
  CONSTRAINT id_order_exists FOREIGN KEY (id_order) REFERENCES Orders(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Articles
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(32) NOT NULL,
  description VARCHAR(128) NOT NULL,
  price FLOAT NOT NULL,
  img VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS Banished
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  mail VARCHAR(64) NOT NULL,

  CONSTRAINT mail_client_exists FOREIGN KEY (mail) REFERENCES Clients(mail) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Reclamations
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  id_client INTEGER NOT NULL,
  id_order INTEGER,
  title VARCHAR(128) NOT NULL,
  description VARCHAR(1024) NOT NULL,
  date_time DATETIME NOT NULL,
  status VARCHAR(16) NOT NULL,

  CONSTRAINT id_order_exists FOREIGN KEY (id_order) REFERENCES Orders(id) ON DELETE CASCADE,
  CONSTRAINT id_client_exists FOREIGN KEY (id_client) REFERENCES Clients(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Reclamation_messages
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  id_client INTEGER NOT NULL,
  id_reclamation INTEGER NOT NULL,
  content VARCHAR(1024) NOT NULL,
  date_time DATETIME NOT NULL,

  CONSTRAINT id_client_exists FOREIGN KEY (id_client) REFERENCES Clients(id) ON DELETE CASCADE,
  CONSTRAINT id_reclamation_exists FOREIGN KEY (id_reclamation) REFERENCES Reclamations(id) ON DELETE CASCADE
);
