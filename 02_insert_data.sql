USE tourguide;

INSERT INTO City (Name) VALUES
    ('Brønshøj');

INSERT INTO Tag (Name) VALUES
    ('Torv'),
    ('Byliv'),
    ('Natur'),
    ('Gratis'),
    ('Bibliotek'),
    ('Børnevenlig'),
    ('Kirke'),
    ('Religion'),
    ('Udendørs'),
    ('Arrangement');


INSERT INTO Attraction (Name, Description, CityID) VALUES
    ('Brønshøj Torv',      'Et torv i Brønshøj',   (SELECT CityID FROM City WHERE Name = 'Brønshøj')),
    ('Utterslev Mose',     'Et naturområde',       (SELECT CityID FROM City WHERE Name = 'Brønshøj')),
    ('Brønshøj Bibliotek', 'Et bibliotek',         (SELECT CityID FROM City WHERE Name = 'Brønshøj')),
    ('Brønshøj Kirke',     'En kirke i Brønshøj',  (SELECT CityID FROM City WHERE Name = 'Brønshøj')),
    ('Friluftsscene',      'Et udendørs scene',    (SELECT CityID FROM City WHERE Name = 'Brønshøj'));

AttractionTag
INSERT INTO AttractionTag (AttractionID, TagID)
SELECT a.AttractionID, t.TagID
FROM (
    SELECT 'Brønshøj Torv' AS A, 'Torv' AS T UNION ALL
    SELECT 'Brønshøj Torv',      'Byliv' UNION ALL
    SELECT 'Utterslev Mose',     'Natur' UNION ALL
    SELECT 'Utterslev Mose',     'Gratis' UNION ALL
    SELECT 'Brønshøj Bibliotek', 'Bibliotek' UNION ALL
    SELECT 'Brønshøj Bibliotek', 'Børnevenlig' UNION ALL
    SELECT 'Brønshøj Kirke',     'Kirke' UNION ALL
    SELECT 'Brønshøj Kirke',     'Religion' UNION ALL
    SELECT 'Friluftsscene',      'Udendørs' UNION ALL
    SELECT 'Friluftsscene',      'Arrangement'
) AS x
JOIN Attraction a ON a.Name = x.A
JOIN Tag t        ON t.Name = x.T;