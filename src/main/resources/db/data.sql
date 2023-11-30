INSERT INTO player (username, password, email, date_of_birth, gender, country)
VALUES ('admin', 'admin', 'admin@admin.com', '1990-12-07', 'MALE', 'CRO');

INSERT INTO player_base (player_id, x_coordinate, y_coordinate)
VALUES (1, 0, 0);

INSERT INTO resource_production_settings (name, resource_type, formula)
VALUES ('x1 Ore production', 'ORE', '60*log(LEVEL*1.5)^BONUS*(WORKERS*1.25)');

INSERT INTO resource_production (player_base_id, resource_production_settings_id, upgraded_at, computed_at, level, workers_assigned, bonus)
VALUES (1, 1, NOW(), NOW(), 1, 1, 1);

