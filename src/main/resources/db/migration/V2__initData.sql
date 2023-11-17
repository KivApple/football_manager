INSERT INTO teams (name, acronym, budget) VALUES
('AS MONACO', 'MCM', 240), -- 1
('CLERMONT FOOT 63', 'CFE', 25), -- 2
('FC LORIENT', 'LRT', 50), -- 3
('FC NANTES', 'NTE', 75), -- 4
('LOSC LILLE', 'LIL', 100), -- 5
('MONTPELLIER HÉRAULT SC', 'MPL', 52), -- 6
('OGC NICE', 'NCE', 100), -- 7
('OLYMPIQUE DE MARSEILLE', 'MRS', 250), -- 8
('OLYMPIQUE LYONNAIS', 'LYS', 250), -- 9
('PARIS SAINT-GERMAIN', 'CDG', 700), -- 10
('RC LENS', 'LFQL', 62), -- 11
('RC STRASBOURG ALSACE', 'SXB', 45), -- 12
('STADE BRESTOIS 29', 'BES', 48), -- 13
('STADE DE REIMS', 'RPK', 70), -- 14
('STADE RENNAIS FC', 'RNS', 90), -- 15
('TOULOUSE FC', 'TLS', 40); -- 16
UPDATE teams SET budget = budget * 1000000;

INSERT INTO players (name, position, team_id) VALUES
-- Nice
('Marcin Bulka', 'GOALKEEPER', 7),
('Salvatore Sirigu', 'GOALKEEPER', 7),
('Teddy Boulhendi', 'GOALKEEPER', 7),
('Jean-Clair Todibo', 'DEFENDER', 7),
('Dante', 'DEFENDER', 7),
('Amidou Doumbouya', 'DEFENDER', 7),
('Yannis Nahounou', 'DEFENDER', 7),
('Romain Perraud', 'DEFENDER', 7),
('Melvin Bard', 'DEFENDER', 7),
('Ayoub Amraoui', 'DEFENDER', 7),
('Jordan Lotomba', 'DEFENDER', 7),
('Youcef Atal', 'DEFENDER', 7),
('Antoine Mendy', 'DEFENDER', 7),
('Youssouf Ndayishimiye', 'MIDFIELD', 7),
('Pablo Rosario', 'MIDFIELD', 7),
('Alexis Beka Beka', 'MIDFIELD', 7),
('Reda Belahyane', 'MIDFIELD', 7),
('Khéphren Thuram', 'MIDFIELD', 7),
('Hicham Boudaoui', 'MIDFIELD', 7),
('Morgan Sanson', 'MIDFIELD', 7),
('Daouda Traoré', 'MIDFIELD', 7),
('Alexis Claude-Maurice', 'MIDFIELD', 7),
('Sofiane Diop', 'MIDFIELD', 7),
('Jérémie Boga', 'MIDFIELD', 7),
('Aliou Baldé', 'MIDFIELD', 7),
('Badredine Bouanani', 'FORWARD', 7),
('Terem Moffi', 'FORWARD', 7),
('Gaëtan Laborde', 'FORWARD', 7),
('Evann Guessand', 'FORWARD', 7),
-- Toulouse
('Guillaume Restes', 'GOALKEEPER', 16),
('Álex Domínguez', 'GOALKEEPER', 16),
('Thomas Himeur', 'GOALKEEPER', 16),
('Justin Lacombe', 'GOALKEEPER', 16),
('Rasmus Nicolaisen', 'DEFENDER', 16),
('Logan Costa', 'DEFENDER', 16),
('Moussa Diarra', 'DEFENDER', 16),
('Kévin Keben', 'DEFENDER', 16),
('Christian Mawissa Elebi', 'DEFENDER', 16),
('Gabriel Suazo', 'DEFENDER', 16),
('Oliver Zandén', 'DEFENDER', 16),
('Ylies Aradj', 'DEFENDER', 16),
('Mikkel Desler', 'DEFENDER', 16),
('Warren Kamanzi', 'DEFENDER', 16),
('Stijn Spierings', 'MIDFIELD', 16),
('Cristian Cásseres Jr.', 'MIDFIELD', 16),
('Vincent Sierro', 'MIDFIELD', 16),
('Kléri Serber', 'MIDFIELD', 16),
('Mamady Bangré', 'MIDFIELD', 16),
('Niklas Schmidt', 'MIDFIELD', 16),
('Denis Genreau', 'MIDFIELD', 16),
('César Gelabert', 'MIDFIELD', 16),
('Naatan Skyttä', 'MIDFIELD', 16),
('Ibrahim Cissoko', 'MIDFIELD', 16),
('Zakaria Aboukhlal', 'MIDFIELD', 16),
('Aron Dønnum', 'MIDFIELD', 16),
('Thijs Dallinga', 'FORWARD', 16),
('Frank Magri', 'FORWARD', 16),
('Yanis Begraoui', 'FORWARD', 16),
('Bonota Traoré', 'FORWARD', 16);
