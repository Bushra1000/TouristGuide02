
CREATE DATABASE tourguide
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_danish_ci;
USE tourguide;


CREATE TABLE City (
    CityID INT          NOT NULL AUTO_INCREMENT,
    Name   VARCHAR(100) NOT NULL,
    CONSTRAINT PK_City      PRIMARY KEY (CityID),
    CONSTRAINT UQ_City_Name UNIQUE (Name)
);


CREATE TABLE Attraction (
    AttractionID INT          NOT NULL AUTO_INCREMENT,
    Name         VARCHAR(150) NOT NULL,
    Description  TEXT         NOT NULL,
    CityID       INT          NOT NULL,
    CONSTRAINT PK_Attraction PRIMARY KEY (AttractionID),
    CONSTRAINT FK_Attraction_City FOREIGN KEY (CityID)
        REFERENCES City (CityID),
    CONSTRAINT UQ_Attraction_Name_City UNIQUE (Name, CityID)
);


CREATE TABLE Tag (
    TagID INT         NOT NULL AUTO_INCREMENT,
    Name  VARCHAR(50) NOT NULL,
    CONSTRAINT PK_Tag      PRIMARY KEY (TagID),
    CONSTRAINT UQ_Tag_Name UNIQUE (Name)
);

CREATE TABLE AttractionTag (
    AttractionID INT NOT NULL,
    TagID        INT NOT NULL,
    CONSTRAINT PK_AttractionTag PRIMARY KEY (AttractionID, TagID),
    CONSTRAINT FK_AttractionTag_Attraction FOREIGN KEY (AttractionID)
        REFERENCES Attraction (AttractionID) ON DELETE CASCADE,
    CONSTRAINT FK_AttractionTag_Tag FOREIGN KEY (TagID)
        REFERENCES Tag (TagID) ON DELETE CASCADE
);

CREATE INDEX IX_AttractionTag_TagID ON AttractionTag (TagID);