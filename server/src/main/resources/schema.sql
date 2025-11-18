CREATE TABLE IF NOT EXISTS practice_time_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255),
    music_id VARCHAR(255),
    bpm INT,
    date DATE,
    duration BIGINT,
    updated_at TIMESTAMP
);
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    createdDate DATE,
    updatedDate DATE
);

CREATE TABLE category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    description TEXT,
    isDeleted BOOLEAN,
    isArchived BOOLEAN,
    isPrivate BOOLEAN,
    createdUserId BIGINT,
    FOREIGN KEY (createdUserId) REFERENCES users(id),
    createdDate DATE,
    updatedDate DATE
);
CREATE TABLE song (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    artist VARCHAR(255),
    categoryId BIGINT,
    FOREIGN KEY (categoryId) REFERENCES category(id),
    description TEXT,
    isDeleted BOOLEAN,
    isArchived BOOLEAN,
    isPrivate BOOLEAN,
    createdUserId BIGINT,
    FOREIGN KEY (createdUserId) REFERENCES users(id),
    createdDate DATE,
    updatedDate DATE
);

