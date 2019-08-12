-- MySQL Script generated by MySQL Workbench
-- Mon Aug 12 09:21:44 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ocr_projet06
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ocr_projet06` ;

-- -----------------------------------------------------
-- Schema ocr_projet06
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ocr_projet06` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `ocr_projet06` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL DEFAULT '\"ocr.projet06@grimpeur.fr',
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `topo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `topo` ;

CREATE TABLE IF NOT EXISTS `topo` (
  `idtopo` INT NOT NULL AUTO_INCREMENT,
  `user_iduser` INT NOT NULL,
  `nom` VARCHAR(256) NOT NULL DEFAULT 'topo_t',
  `est_publie` TINYINT NOT NULL DEFAULT 0,
  `est_disponible` TINYINT NOT NULL DEFAULT 0,
  `resume` VARCHAR(256) NULL,
  PRIMARY KEY (`idtopo`),
  CONSTRAINT `fk_topo_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_topo_user1_idx` ON `topo` (`user_iduser` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `cpte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cpte` ;

CREATE TABLE IF NOT EXISTS `cpte` (
  `user_iduser` INT NOT NULL,
  `mdp` VARCHAR(45) NOT NULL DEFAULT 'password',
  `login` VARCHAR(64) NOT NULL DEFAULT 'user',
  `droits` VARCHAR(2) NOT NULL DEFAULT 'U',
  PRIMARY KEY (`user_iduser`),
  CONSTRAINT `fk_compte_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spot` ;

CREATE TABLE IF NOT EXISTS `spot` (
  `idspot` INT NOT NULL AUTO_INCREMENT,
  `user_iduser` INT NOT NULL,
  `nom` VARCHAR(45) NOT NULL DEFAULT 'spot_s',
  `localisation` VARCHAR(45) NOT NULL DEFAULT 'localisatiion_spot_s',
  PRIMARY KEY (`idspot`),
  CONSTRAINT `fk_spot_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_spot_user1_idx` ON `spot` (`user_iduser` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `secteur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `secteur` ;

CREATE TABLE IF NOT EXISTS `secteur` (
  `idsecteur` INT NOT NULL AUTO_INCREMENT,
  `spot_idspot` INT NOT NULL,
  `nom` VARCHAR(45) NOT NULL DEFAULT 'secteur_s',
  PRIMARY KEY (`idsecteur`),
  CONSTRAINT `fk_secteur_spot1`
    FOREIGN KEY (`spot_idspot`)
    REFERENCES `spot` (`idspot`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_secteur_spot1_idx` ON `secteur` (`spot_idspot` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `voie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voie` ;

CREATE TABLE IF NOT EXISTS `voie` (
  `idvoie` INT NOT NULL AUTO_INCREMENT,
  `secteur_idsecteur` INT NOT NULL,
  `nom` VARCHAR(45) NOT NULL DEFAULT 'voie_v',
  PRIMARY KEY (`idvoie`),
  CONSTRAINT `fk_voie_secteur1`
    FOREIGN KEY (`secteur_idsecteur`)
    REFERENCES `secteur` (`idsecteur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_voie_secteur1_idx` ON `voie` (`secteur_idsecteur` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `longueur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `longueur` ;

CREATE TABLE IF NOT EXISTS `longueur` (
  `idlongueur` INT NOT NULL AUTO_INCREMENT,
  `voie_idvoie` INT NOT NULL,
  `nom` VARCHAR(45) NULL,
  `cotation` VARCHAR(45) NULL,
  `nombre_de_spits` INT NULL,
  PRIMARY KEY (`idlongueur`),
  CONSTRAINT `fk_longueur_voie1`
    FOREIGN KEY (`voie_idvoie`)
    REFERENCES `voie` (`idvoie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_longueur_voie1_idx` ON `longueur` (`voie_idvoie` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `commentaire`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `commentaire` ;

CREATE TABLE IF NOT EXISTS `commentaire` (
  `idcommentaire` INT NOT NULL AUTO_INCREMENT,
  `spot_idspot` INT NOT NULL,
  `user_iduser` INT NOT NULL,
  `texte` VARCHAR(256) NULL DEFAULT 'donnes ton avis sur c espot',
  `est_visible` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`idcommentaire`),
  CONSTRAINT `fk_commentaire_spot`
    FOREIGN KEY (`spot_idspot`)
    REFERENCES `spot` (`idspot`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_commentaire_user`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_commentaire_spot_idx` ON `commentaire` (`spot_idspot` ASC) VISIBLE;

CREATE INDEX `fk_commentaire_user1_idx` ON `commentaire` (`user_iduser` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocr_projet06`;
INSERT INTO `user` (`iduser`, `nom`, `email`) VALUES (DEFAULT, 'user_01', 'user_01@mail.fr');
INSERT INTO `user` (`iduser`, `nom`, `email`) VALUES (DEFAULT, 'user_02', 'user_02@mail.fr');

COMMIT;


-- -----------------------------------------------------
-- Data for table `topo`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocr_projet06`;
INSERT INTO `topo` (`idtopo`, `user_iduser`, `nom`, `est_publie`, `est_disponible`, `resume`) VALUES (DEFAULT, 1, 'topo_01', 1, 1, 'resume_topo_01');
INSERT INTO `topo` (`idtopo`, `user_iduser`, `nom`, `est_publie`, `est_disponible`, `resume`) VALUES (DEFAULT, 2, 'topo_02', 1, 0, 'resume_topo_02');
INSERT INTO `topo` (`idtopo`, `user_iduser`, `nom`, `est_publie`, `est_disponible`, `resume`) VALUES (DEFAULT, 2, 'topo_03', 1, 1, 'resume_topo_03');

COMMIT;


-- -----------------------------------------------------
-- Data for table `cpte`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocr_projet06`;
INSERT INTO `cpte` (`user_iduser`, `mdp`, `login`, `droits`) VALUES (1, 'mdp_01', 'login_01', 'U');
INSERT INTO `cpte` (`user_iduser`, `mdp`, `login`, `droits`) VALUES (2, 'mdp_02', 'login_02', 'M');

COMMIT;


-- -----------------------------------------------------
-- Data for table `spot`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocr_projet06`;
INSERT INTO `spot` (`idspot`, `user_iduser`, `nom`, `localisation`) VALUES (DEFAULT, 1, 'spot_01', 'localisation_spot01');
INSERT INTO `spot` (`idspot`, `user_iduser`, `nom`, `localisation`) VALUES (DEFAULT, 2, 'spot_02', 'localisation_spot02');

COMMIT;


-- -----------------------------------------------------
-- Data for table `secteur`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocr_projet06`;
INSERT INTO `secteur` (`idsecteur`, `spot_idspot`, `nom`) VALUES (DEFAULT, 1, 'secteur_01');
INSERT INTO `secteur` (`idsecteur`, `spot_idspot`, `nom`) VALUES (DEFAULT, 2, 'secteur_02');
INSERT INTO `secteur` (`idsecteur`, `spot_idspot`, `nom`) VALUES (DEFAULT, 1, 'secteur_03');
INSERT INTO `secteur` (`idsecteur`, `spot_idspot`, `nom`) VALUES (DEFAULT, 2, 'secteur_04');

COMMIT;


-- -----------------------------------------------------
-- Data for table `voie`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocr_projet06`;
INSERT INTO `voie` (`idvoie`, `secteur_idsecteur`, `nom`) VALUES (DEFAULT, 1, 'voie_01');
INSERT INTO `voie` (`idvoie`, `secteur_idsecteur`, `nom`) VALUES (DEFAULT, 2, 'voie_02');
INSERT INTO `voie` (`idvoie`, `secteur_idsecteur`, `nom`) VALUES (DEFAULT, 3, 'voie-03');
INSERT INTO `voie` (`idvoie`, `secteur_idsecteur`, `nom`) VALUES (DEFAULT, 4, 'voie_04');

COMMIT;


-- -----------------------------------------------------
-- Data for table `longueur`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocr_projet06`;
INSERT INTO `longueur` (`idlongueur`, `voie_idvoie`, `nom`, `cotation`, `nombre_de_spits`) VALUES (DEFAULT, 1, 'longeur_01', '3a', 10);
INSERT INTO `longueur` (`idlongueur`, `voie_idvoie`, `nom`, `cotation`, `nombre_de_spits`) VALUES (DEFAULT, 1, 'longeur_02', '3b', 20);
INSERT INTO `longueur` (`idlongueur`, `voie_idvoie`, `nom`, `cotation`, `nombre_de_spits`) VALUES (DEFAULT, 1, 'longeur_03', '3c', 30);
INSERT INTO `longueur` (`idlongueur`, `voie_idvoie`, `nom`, `cotation`, `nombre_de_spits`) VALUES (DEFAULT, 2, 'longeur_04', '4a', 40);
INSERT INTO `longueur` (`idlongueur`, `voie_idvoie`, `nom`, `cotation`, `nombre_de_spits`) VALUES (DEFAULT, 2, 'longeur_05', '5a', 50);
INSERT INTO `longueur` (`idlongueur`, `voie_idvoie`, `nom`, `cotation`, `nombre_de_spits`) VALUES (DEFAULT, 3, 'longeur_06', '6a', 60);
INSERT INTO `longueur` (`idlongueur`, `voie_idvoie`, `nom`, `cotation`, `nombre_de_spits`) VALUES (DEFAULT, 3, 'longeur_07', '7a', 70);
INSERT INTO `longueur` (`idlongueur`, `voie_idvoie`, `nom`, `cotation`, `nombre_de_spits`) VALUES (DEFAULT, 3, 'longeur_08', '8a', 80);
INSERT INTO `longueur` (`idlongueur`, `voie_idvoie`, `nom`, `cotation`, `nombre_de_spits`) VALUES (DEFAULT, 4, 'longeur_09', '9a', 90);
INSERT INTO `longueur` (`idlongueur`, `voie_idvoie`, `nom`, `cotation`, `nombre_de_spits`) VALUES (DEFAULT, 4, 'longeur_10', '9b', 100);

COMMIT;


-- -----------------------------------------------------
-- Data for table `commentaire`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocr_projet06`;
INSERT INTO `commentaire` (`idcommentaire`, `spot_idspot`, `user_iduser`, `texte`, `est_visible`) VALUES (DEFAULT, 1, 1, 'comment_0101', 1);
INSERT INTO `commentaire` (`idcommentaire`, `spot_idspot`, `user_iduser`, `texte`, `est_visible`) VALUES (DEFAULT, 2, 2, 'comment_0102', 1);
INSERT INTO `commentaire` (`idcommentaire`, `spot_idspot`, `user_iduser`, `texte`, `est_visible`) VALUES (DEFAULT, 1, 2, 'comment_0201', 1);

COMMIT;

