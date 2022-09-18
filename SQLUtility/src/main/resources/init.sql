create table if not exists movies(
 movieId int not null primary key,
 name varchar not null,
 released varchar not null,
 income bigint not null
 );
 create table if not exists actors(
 actorId int not null primary key,
 fullName varchar not null);
 create table if not exists movie_actors(
  actorId int not null,
  movieId int not null,
  foreign key (actorId) references actors (actorId),
  foreign key (movieId) references movies (movieId),
  primary key (actorId,movieId)
 );


delete from movies;
delete from actors;
delete from movie_actors;



