CREATE TABLE IF NOT EXISTS practice_time_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255),
    music_id VARCHAR(255),
    bpm INT,
    date DATE,
    duration BIGINT,
    updated_at TIMESTAMP
);