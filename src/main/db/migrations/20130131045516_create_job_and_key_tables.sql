create table jobs(
   id serial primary key,
   name varchar(64),
   description varchar(512),
   strategy varchar(64),
   initial_import_completed boolean default false,
   journal_mode_activated boolean default false,
   interval_in_seconds integer,
   last_run_time timestamp without time zone
);


CREATE TABLE job_runs
(
  run_id serial primary key,
  job_id integer references  jobs(id),
  start_time timestamp without time zone,
  end_time timestamp without time zone,
  mode varchar(30),
  journal_count integer,
  insert_count integer,
  update_count integer,
  delete_count integer,
  status varchar(30),
  error text
);

