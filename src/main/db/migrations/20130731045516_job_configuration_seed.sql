insert into jobs(name, description, strategy, initial_import_completed, journal_mode_activated, interval_in_seconds)
values ('gross_cost','gross cost job','SOURCE_TO_DESTINATION', false , false , 3600);
insert into jobs(name, description, strategy, initial_import_completed, journal_mode_activated, interval_in_seconds)
values ('price','price job','SOURCE_TO_DESTINATION', false , false , 3600);
insert into jobs(name, description, strategy, initial_import_completed, journal_mode_activated, interval_in_seconds)
values ('central_replenishments','warehouse replenishment','SOURCE_TO_DESTINATION', false , false , 3600);
