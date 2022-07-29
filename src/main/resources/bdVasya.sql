drop table if exists  "cinema","client","movie","place","seance","order";

CREATE TABLE "cinema" (
   "id" SERIAL  PRIMARY KEY,
   "name" TEXT DEFAULT '',
   "city" TEXT DEFAULT '',
   "adress" TEXT DEFAULT '');

CREATE TABLE "client" (
   "id" SERIAL  PRIMARY KEY,
   "login" TEXT DEFAULT '',
   "password" TEXT DEFAULT '',
   "first_name" TEXT DEFAULT '',
   "last_name" TEXT DEFAULT '',
   "mail" TEXT DEFAULT '',
                      );

CREATE TABLE "movie" (
  "id" SERIAL  PRIMARY KEY,
  "duration" INTEGER NOT NULL,
  "description" TEXT DEFAULT '',
  "start_ticket_sales" DATE DEFAULT '',
  "end_ticket_sales" DATE DEFAULT '',
  "tittle" TEXT DEFAULT ''
 );

CREATE TABLE "place" (
     "id" SERIAL  PRIMARY KEY,
     "row" INTEGER NOT NULL,
     "place_stroka" INTEGER NOT NULL,
     "status" TEXT DEFAULT '',
     "cinema_id" integer REFERENCES "cinema"("id"),
     "cost" FLOAT NOT NULL
);


CREATE TABLE "seance" (
    "id" SERIAL  PRIMARY KEY,
    "cinema_id" integer REFERENCES "cinema"("id"),
    "movie_id" integer REFERENCES "movie"("id"),
    "date" DATE NOT NULL,
    "time" TIME NOT NULL
);

CREATE TABLE "order" (
      "id" SERIAL  PRIMARY KEY,
      "client_id" integer REFERENCES "cinema"("id"),
      "movie_id" integer REFERENCES "movie"("id"),
      "date" DATE NOT NULL,
      "time" TIME NOT NULL
);


