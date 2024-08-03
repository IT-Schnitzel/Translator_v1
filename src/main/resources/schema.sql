CREATE TABLE translation_requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ip_address VARCHAR(255) NOT NULL,
    input_text VARCHAR(255) NOT NULL,
    translated_text VARCHAR(255) NOT NULL
);