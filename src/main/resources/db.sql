drop table if exists  "company","game","buyer","order","region","genre";

CREATE TABLE "company" (
	"id" SERIAL  PRIMARY KEY,
	"name" TEXT DEFAULT '');




CREATE TABLE "genre" (
	"id" SERIAL  PRIMARY KEY,
	"name" TEXT DEFAULT ''
);



CREATE TABLE "region" (
	"id" SERIAL  PRIMARY KEY,
	"name" TEXT DEFAULT ''
);

	
CREATE TABLE "game" (
	"id" SERIAL  PRIMARY KEY,
	"name" TEXT DEFAULT '',
	"developer_id" integer REFERENCES "company"("id"),
	"publisher_id" integer REFERENCES "company"("id"),
	"genre_id" integer REFERENCES "genre"("id"),
	"price" FLOAT NOT NULL,
	"date_of_publication" DATE NOT NULL
);

CREATE TABLE "buyer" (
    "id" SERIAL  PRIMARY KEY,
	"first_name" TEXT DEFAULT '' NOT NULL,
	"surname" TEXT DEFAULT '',
	"mail" TEXT DEFAULT '',
	"region_id" integer REFERENCES "region"("id") );
	
CREATE TABLE "order" (
	"id" SERIAL  PRIMARY KEY,
	"buyer_id" integer REFERENCES "buyer"("id"),
	"game_id" integer REFERENCES "game"("id"),
	"date_order" DATE NOT NULL);