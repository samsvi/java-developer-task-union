-- Vloženie testovacích dát
INSERT INTO providers (ico, name, provider_type, status) VALUES 
('12345678', 'Nemocnica sv. Cyrila a Metoda', 'HOSPITAL', 'ACTIVE'),
('87654321', 'Ambulancia Dr. Novák', 'AMBULANCE', 'ACTIVE'),
('11111111', 'Lekáreň Plus', 'PHARMACY', 'ACTIVE');

INSERT INTO services (code, name, unit_price) VALUES 
('VYSETR01', 'Základné vyšetrenie', 25.50),
('LABOR01', 'Laboratórne vyšetrenie krvi', 45.00),
('RTG01', 'RTG hrudníka', 85.00),
('UZ01', 'UZ vyšetrenie brucha', 120.00),
('KONSUL01', 'Odborná konzultácia', 60.00);

-- Vloženie ukážkových zmlúv
INSERT INTO contracts (provider_id, service_id, quantity, total_price, status) VALUES 
(1, 1, 1000, 25500.00, 'ACTIVE'),
(1, 3, 500, 42500.00, 'ACTIVE'),
(2, 1, 200, 5100.00, 'ACTIVE'),
(2, 5, 100, 6000.00, 'ACTIVE');