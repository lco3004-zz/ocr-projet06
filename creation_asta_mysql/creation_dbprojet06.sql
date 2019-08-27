
-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS grimpeur ;

CREATE TABLE grimpeur (
  iduser serial primary key ,
  nom VARCHAR(1024) NULL,
  email VARCHAR(256) NULL,
  mdp VARCHAR(64) NULL,
  profil VARCHAR(16) NULL);


-- -----------------------------------------------------
-- Table `topo`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `topo` (
  `idtopo` INT NOT NULL AUTO_INCREMENT,
  `user_iduser` INT NOT NULL,
  `nom` VARCHAR(64) NULL,
  `est_publie` INT NULL,
  `etat_reservation` VARCHAR(64) NULL,
  `resume` VARCHAR(1024) NULL,
  `lieu` VARCHAR(64) NULL,
  `date_de_parution` DATE NULL,
  PRIMARY KEY (`idtopo`),
  CONSTRAINT `fk_topo_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_topo_user1_idx` ON `topo` (`user_iduser` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `spot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spot` ;

CREATE TABLE IF NOT EXISTS `spot` (
  `idspot` INT NOT NULL AUTO_INCREMENT,
  `user_iduser` INT NOT NULL,
  `nom` VARCHAR(64) NULL DEFAULT 'spot_s',
  `localisation` VARCHAR(64) NULL DEFAULT 'localisatiion_spot_s',
  `classification` VARCHAR(64) NULL DEFAULT 'standard',
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
  `nom` VARCHAR(64) NULL DEFAULT 'secteur_s',
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
  `nom` VARCHAR(64) NULL DEFAULT 'voie_v',
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
  `nom` VARCHAR(64) NULL,
  `cotation` VARCHAR(64) NULL,
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
  `texte` VARCHAR(1024) NULL DEFAULT 'donnes ton avis sur c espot',
  `est_visible` INT NULL DEFAULT 1,
  PRIMARY KEY (`idcommentaire`),
  CONSTRAINT `fk_commentaire_spot`
    FOREIGN KEY (`spot_idspot`)
    REFERENCES `spot` (`idspot`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_commentaire_spot_idx` ON `commentaire` (`spot_idspot` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
