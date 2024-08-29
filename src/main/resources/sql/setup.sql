CREATE TABLE IF NOT EXISTS player(
    id SERIAL PRIMARY KEY,
    x INT NOT NULL,
    y INT NOT NULL,
    health INT NOT NULL,
    strength INT NOT NULL
);

CREATE TABLE IF NOT EXISTS items (
    id SERIAL PRIMARY KEY,
    player_id INT REFERENCES player(id) ON DELETE CASCADE,
    item_type VARCHAR(50) NOT NULL,
    x INT NOT NULL,
    y INT NOT NULL
);

CREATE TABLE IF NOT EXISTS monsters (
    id SERIAL PRIMARY KEY,
    player_id INT REFERENCES player(id) ON DELETE CASCADE,
    monster_type VARCHAR(50) NOT NULL,
    health INT NOT NULL,
    x INT NOT NULL,
    y INT NOT NULL
);