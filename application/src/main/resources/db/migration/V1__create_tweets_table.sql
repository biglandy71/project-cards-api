CREATE TABLE `authentications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('BASIC_AUTH') CHARACTER SET latin1 DEFAULT NULL,
  `key` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cardCollection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `cardTypeId` int(11) DEFAULT NULL,
  `notes` varchar(2555) DEFAULT NULL,
  `collectionTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cardTypeId` (`cardTypeId`),
  KEY `collectionTypeId` (`collectionTypeId`),
  CONSTRAINT `cardcollection_ibfk_1` FOREIGN KEY (`cardTypeId`) REFERENCES `cardTypes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cardcollection_ibfk_2` FOREIGN KEY (`collectionTypeId`) REFERENCES `collectionTypes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `cards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardTypeId` int(11) DEFAULT NULL,
  `thirdPartyId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cardTypeId` (`cardTypeId`),
  KEY `thirdPartyId` (`thirdPartyId`),
  CONSTRAINT `cards_ibfk_1` FOREIGN KEY (`cardTypeId`) REFERENCES `cardType` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cards_ibfk_2` FOREIGN KEY (`thirdPartyId`) REFERENCES `thirdParties` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cardTypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `collectionTags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardCollectionId` int(11) DEFAULT NULL,
  `tagId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cardCollectionId` (`cardCollectionId`),
  KEY `tagId` (`tagId`),
  CONSTRAINT `collectiontags_ibfk_1` FOREIGN KEY (`cardCollectionId`) REFERENCES `cardCollection` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `collectiontags_ibfk_2` FOREIGN KEY (`tagId`) REFERENCES `tags` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `collectionTypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `mtgCards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardId` int(11) DEFAULT NULL,
  `name` int(11) DEFAULT NULL,
  `manaType` enum('SWAMP') CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cardId` (`cardId`),
  CONSTRAINT `mtgcards_ibfk_1` FOREIGN KEY (`cardId`) REFERENCES `cards` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` int(11) DEFAULT NULL,
  `type` enum('COLLECTION','CARD','USER') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `thirdParties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `authId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `authId` (`authId`),
  CONSTRAINT `thirdparties_ibfk_1` FOREIGN KEY (`authId`) REFERENCES `authentications` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `userCardCollection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userCardId` int(11) DEFAULT NULL,
  `cardCollectionId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userCardId` (`userCardId`),
  KEY `cardCollectionId` (`cardCollectionId`),
  CONSTRAINT `usercardcollection_ibfk_1` FOREIGN KEY (`userCardId`) REFERENCES `userCards` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usercardcollection_ibfk_2` FOREIGN KEY (`cardCollectionId`) REFERENCES `cardCollection` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `userCards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `cardId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `cardId` (`cardId`),
  CONSTRAINT `usercards_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usercards_ibfk_2` FOREIGN KEY (`cardId`) REFERENCES `cards` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) NOT NULL DEFAULT '',
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

