CREATE KEYSPACE IF NOT EXISTS encurtador_url
WITH replication = {
    'class': 'SimpleStrategy',
    'replication_factor': 1
};

USE encurtador_url;

CREATE TABLE IF NOT EXISTS urls (
    short_code text PRIMARY KEY,
    id uuid,
    original_url text,
    created_at timestamp,
    updated_at timestamp
);

CREATE INDEX IF NOT EXISTS urls_original_url_idx
    ON urls(original_url);