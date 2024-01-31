alter table doctors add active tinyint;
update doctors set active = 1;
alter table patients add active tinyint;
update patients set active = 1;