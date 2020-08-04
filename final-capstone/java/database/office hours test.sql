BEGIN TRANSACTION;

INSERT INTO offices(address, phone, cost_per_hour)
VALUES ('555 Main St +++ Lansing, MI', '517-555-5678', 90.99);

 ROLLBACK;