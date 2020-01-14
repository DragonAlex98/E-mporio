CREATE TABLE `privilegio` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `ruolo` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `ruolo_privileges` (
	`roles_id` BIGINT(20) NOT NULL,
	`privileges_id` BIGINT(20) NOT NULL,
	CONSTRAINT `FK72f2gkiuqudptsi4gsmh2pf74` FOREIGN KEY (`privileges_id`) REFERENCES `privilegio` (`id`),
	CONSTRAINT `FKphre9w6um698u8pb9naasyx0m` FOREIGN KEY (`roles_id`) REFERENCES `ruolo` (`id`)
);

CREATE TABLE `categoria_attivita` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`descrizione_categoria_attivita` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `categoria_prodotto` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`descrizione_categoria_prodotto` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `prodotto_descrizione` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(255) NULL DEFAULT NULL,
	`categoria_prodotto` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FKlwb4ina9lddhkjab03y89h0up` FOREIGN KEY (`categoria_prodotto`) REFERENCES `categoria_prodotto` (`id`)
);

CREATE TABLE `prodotto` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`prezzo` DOUBLE NULL DEFAULT NULL,
	`quantita` INT(11) NULL DEFAULT NULL,
	`product_descr_id` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FKkhx0uxc0eiwj4lqoy84kp5010` FOREIGN KEY (`product_descr_id`) REFERENCES `prodotto_descrizione` (`id`)
);

CREATE TABLE `catalogo` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`id`)
);

CREATE TABLE `catalogo_products` (
	`catalogo_id` BIGINT(20) NOT NULL,
	`products_id` BIGINT(20) NOT NULL,
	PRIMARY KEY (`catalogo_id`, `products_id`),
	CONSTRAINT `FK31n8c75m8suvin4wj9j592tjj` FOREIGN KEY (`catalogo_id`) REFERENCES `catalogo` (`id`),
	CONSTRAINT `FK98i63g02ypfrhnrq2qxk4bke4` FOREIGN KEY (`products_id`) REFERENCES `prodotto` (`id`)
);


CREATE TABLE `attivita_descrizione` (
	`partita_iva` VARCHAR(255) NOT NULL,
	`indirizzo` VARCHAR(255) NULL DEFAULT NULL,
	`ragione_sociale` VARCHAR(255) NULL DEFAULT NULL,
	`sede_operativa` VARCHAR(255) NULL DEFAULT NULL,
	`categoria_attivita` INT(11) NOT NULL,
	PRIMARY KEY (`partita_iva`),
	CONSTRAINT `FK7eir1yq6y77r9siy2qos2pwh6` FOREIGN KEY (`categoria_attivita`) REFERENCES `categoria_attivita` (`id`)
);


CREATE TABLE `attivita` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`catalogo_attivita` BIGINT(20) NOT NULL,
	`id_attivita` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK2pydb1bleq5u4nuiv8hvxj5vv` FOREIGN KEY (`id_attivita`) REFERENCES `attivita_descrizione` (`partita_iva`),
	CONSTRAINT `FKdsc9j9p4ypykxvfbuun3y1pbg` FOREIGN KEY (`catalogo_attivita`) REFERENCES `catalogo` (`id`)
);


CREATE TABLE `user` (
	`disc` VARCHAR(31) NOT NULL,
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`password` VARCHAR(255) NULL DEFAULT NULL,
	`username` VARCHAR(255) NULL DEFAULT NULL,
	`ruolo` BIGINT(20) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
	CONSTRAINT `FKg2iokwiwen168e7nxsnvpuk1b` FOREIGN KEY (`ruolo`) REFERENCES `ruolo` (`id`)
);


CREATE TABLE `admin` (
	`id` BIGINT(20) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK1ja8rua032fgnk9jmq7du3b3a` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);

CREATE TABLE `acquirente` (
	`id` BIGINT(20) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FKfab04geg3118xmfw1fojhxran` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);

CREATE TABLE `dipendente` (
	`id` BIGINT(20) NOT NULL,
	`user_shop_employed_id` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK292qtit6txl0hf66s6ogl2ijv` FOREIGN KEY (`user_shop_employed_id`) REFERENCES `attivita` (`id`),
	CONSTRAINT `FKnd61tbtoglt188t5fidrmh4js` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);


CREATE TABLE `fattorino` (
	`id` BIGINT(20) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK1tgj008fgjnpviu9emdhe9to0` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);

CREATE TABLE `gestore_marketing` (
	`id` BIGINT(20) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK7aht9qrdol65c54apqmwyo994` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);

CREATE TABLE `operatore_sistema` (
	`id` BIGINT(20) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FKc5dk6bgk2abgkkykge3db973y` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);

CREATE TABLE `titolare` (
	`id` BIGINT(20) NOT NULL,
	`user_shop_owned_id` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK35ei4m5mkbjbqonr91qnbg3cp` FOREIGN KEY (`id`) REFERENCES `user` (`id`),
	CONSTRAINT `FKe9qh7asus1p6y6wuyd7340cwm` FOREIGN KEY (`user_shop_owned_id`) REFERENCES `attivita` (`id`)
);


CREATE TABLE `locker` (
	`locker_id` INT(11) NOT NULL AUTO_INCREMENT,
	`address` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`locker_id`)
);

CREATE TABLE `consegna` (
	`id_consegna` INT(11) NOT NULL AUTO_INCREMENT,
	`stato_consegna` VARCHAR(255) NULL DEFAULT NULL,
	`fattorino_id` BIGINT(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id_consegna`),
	CONSTRAINT `FKae0uncc9e9xexiyil8t7e45nx` FOREIGN KEY (`fattorino_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `posto` (
	`id_posto` INT(11) NOT NULL AUTO_INCREMENT,
	`nome_posto` VARCHAR(255) NULL DEFAULT NULL,
	`consegna_id` INT(11) NULL DEFAULT NULL,
	`locker` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id_posto`),
	CONSTRAINT `FK3v78yv6bfhgvyyn56in2u4yfn` FOREIGN KEY (`consegna_id`) REFERENCES `consegna` (`id_consegna`),
	CONSTRAINT `FK7pxonsnocap287pstyo4e57oy` FOREIGN KEY (`locker`) REFERENCES `locker` (`locker_id`)
);

CREATE TABLE `ordine` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`parking_address` VARCHAR(255) NULL DEFAULT NULL,
	`consegna_id` INT(11) NULL DEFAULT NULL,
	`user_id` BIGINT(20) NOT NULL,
	`shop_id` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FKelwstw0ilc4tb37muvfo1rux` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
	CONSTRAINT `FKfdr3ioi9f8danb4htkr0la8h7` FOREIGN KEY (`shop_id`) REFERENCES `attivita_descrizione` (`partita_iva`),
	CONSTRAINT `FKg70cm9kfwp8gp3ypfqgujon0h` FOREIGN KEY (`consegna_id`) REFERENCES `consegna` (`id_consegna`)
);


CREATE TABLE `riga_ordine_prodotto` (
	`order_id` BIGINT(20) NOT NULL,
	`product_id` INT(11) NOT NULL,
	`quantity` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`order_id`, `product_id`),
	CONSTRAINT `FK1v3q6twj4fmci991oc1s3dewp` FOREIGN KEY (`order_id`) REFERENCES `ordine` (`id`),
	CONSTRAINT `FKrwuu2rf8mtisgv8u6p8w6ric0` FOREIGN KEY (`product_id`) REFERENCES `prodotto_descrizione` (`id`)
);
