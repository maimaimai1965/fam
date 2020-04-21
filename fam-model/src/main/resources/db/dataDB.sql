DELETE FROM person;

ALTER SEQUENCE seq_person RESTART WITH 100000;
INSERT INTO person
  (id,    surname,        first_name,  middle_name,   birth_date,   death_date, gender, version) VALUES
  (50000, 'Мирошник',     'Андрей',   'Иванович',     '1965-03-23', NULL,       'M',    0),
  (50001, 'Мирошниченко', 'Виктория', 'Вячеславовна', '1981-02-24', NULL,       'F',    0),
  (50002, 'Мирошник',     'Вера',     'Андреевна',    '2008-11-19', NULL,       'F',    0),
  (50003, 'Мирошник',     'Дмитрий',  'Андреевич',    '1989-01-10', NULL,       'M',    0),
  (50004, 'Мирошник',     'Наталия',  'Андреевна',    '1994-05-10', NULL,       'F',    0),
  (50005, 'Мирошник',     'Иван',     'Андреевич',    '1941-11-06', NULL,       'M',    0),
  (50006, 'Мирошник',     'Тамара',   'Алфеевна',     '1944-02-06', NULL,       'F',    0)
;

INSERT INTO public.parent_child (parent_id, child_id) VALUES (50000, 50002);
INSERT INTO public.parent_child (parent_id, child_id) VALUES (50000, 50003);
INSERT INTO public.parent_child (parent_id, child_id) VALUES (50000, 50004);
INSERT INTO public.parent_child (parent_id, child_id) VALUES (50001, 50002);

INSERT INTO public.together_type (code, name) VALUES ('MARRIAGE', 'Брак');

INSERT INTO together
    (id,    together_type_code, person1_id, person2_id, start_date, finish_date, description, version) VALUES
    (50000, 'MARRIAGE',         50000,      50001,      null,       null,        null,        0),
    (50001, 'MARRIAGE',         50005,      50006,      null,       null,        null,        0)
;

commit;
