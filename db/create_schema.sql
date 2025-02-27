CREATE TABLE business_details (id SERIAL PRIMARY KEY, name VARCHAR(255), contact_details VARCHAR(255), address VARCHAR(255), industry VARCHAR(255), financial_information TEXT);
CREATE TABLE application_status (id SERIAL PRIMARY KEY, status VARCHAR(255), created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, business_id INT, FOREIGN KEY (business_id) REFERENCES business_details(id));
CREATE TABLE amlyc_check (id SERIAL PRIMARY KEY, result VARCHAR(255), created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, application_id INT, FOREIGN KEY (application_id) REFERENCES application_status(id));
CREATE TABLE credit_check (id SERIAL PRIMARY KEY, result VARCHAR(255), created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, application_id INT, FOREIGN KEY (application_id) REFERENCES application_status(id));
CREATE TABLE event_log (id SERIAL PRIMARY KEY, event_type VARCHAR(255), event_data TEXT, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);