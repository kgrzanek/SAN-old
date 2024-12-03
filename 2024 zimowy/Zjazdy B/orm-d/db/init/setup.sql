DROP SCHEMA public;
CREATE SCHEMA orm_d;

-- Drop users if they exist (useful for resetting)
DROP USER IF EXISTS orm_d_owner;

-- Create users with login capability
CREATE USER orm_d_owner WITH PASSWORD 'orm_d_owner_12345' LOGIN;

ALTER DATABASE orm_d OWNER TO orm_d_owner;

GRANT ALL PRIVILEGES ON DATABASE orm_d TO orm_d_owner;
GRANT ALL PRIVILEGES ON SCHEMA orm_d TO orm_d_owner;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA orm_d TO orm_d_owner;

ALTER USER orm_d_owner SET search_path TO orm_d;
