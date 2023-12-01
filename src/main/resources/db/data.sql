INSERT INTO player (username, password, email, date_of_birth, gender, country)
VALUES ('admin', 'admin', 'admin@admin.com', '1990-12-07', 'MALE', 'Croatia');

INSERT INTO resource_production_settings (name, resource_type, formula)
VALUES ('x1 ORE production', 'ORE', '60*log(LEVEL*1.5)^ZONE_BONUS*(WORKERS*1.25)^PLAYER_BONUS'),
       ('x1 WOOD production', 'WOOD', '60*log(LEVEL*1.5)^ZONE_BONUS*(WORKERS*1.25)^PLAYER_BONUS'),
       ('x1 FOOD production', 'FOOD', '60*log(LEVEL*1.5)^ZONE_BONUS*(WORKERS*1.25)^PLAYER_BONUS');
