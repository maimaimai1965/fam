DELETE FROM person;

ALTER SEQUENCE seq_person RESTART WITH 100000;
INSERT INTO person
  (id,      surname,       first_name, middle_name,    birth_date,   death_date, gender) VALUES
  (100000, 'Мирошник',     'Андрей',   'Иванович',     '1965-03-23', NULL,       'M'),
  (100001, 'Мирошниченко', 'Виктория', 'Вячеславовна', '1981-02-24', NULL,       'F'),
  (100002, 'Мирошник',     'Вера',     'Андреевна',    '2008-11-19', NULL,       'F'),
  (100003, 'Мирошник',     'Дмитрий',  'Андреевич',    '1989-01-10', NULL,       'M'),
  (100004, 'Мирошник',     'Наталия',  'Андреевна',    '1994-05-10', NULL,       'F')
;



