# Makefile
.PHONY: up down logs ps clean help psql-local psql-test up-local up-test down-local down-test

# Variables
DC=docker-compose

help:
	@echo "Available commands:"
	@echo "  make up           - Start both PostgreSQL containers"
	@echo "  make up-local     - Start only local development database"
	@echo "  make up-test      - Start only test database"
	@echo "  make down         - Stop all containers"
	@echo "  make down-local   - Stop local development database"
	@echo "  make down-test    - Stop test database"
	@echo "  make logs-local   - View local database logs"
	@echo "  make logs-test    - View test database logs"
	@echo "  make ps           - List running containers"
	@echo "  make clean        - Remove all containers and volumes"
	@echo "  make psql-local   - Connect to local database"
	@echo "  make psql-test    - Connect to test database"

up:
	@echo "Starting docker containers..."
	@$(DC) up -d

up-local:
	@echo "Starting local PostgreSQL..."
	@$(DC) up -d orm-d-local-postgres

up-test:
	@echo "Starting test PostgreSQL..."
	@$(DC) up -d orm-d-local-test-postgres

down:
	@echo "Stopping all containers..."
	@$(DC) down

down-local:
	@echo "Stopping local PostgreSQL..."
	@$(DC) stop orm-d-local-postgres

down-test:
	@echo "Stopping test PostgreSQL..."
	@$(DC) stop orm-d-local-test-postgres

logs-local:
	@$(DC) logs -f orm-d-local-postgres

logs-test:
	@$(DC) logs -f orm-d-local-test-postgres

ps:
	@$(DC) ps

clean:
	@echo "Removing all containers and volumes..."
	@$(DC) down -v

psql-local:
	@docker exec -it orm-d-local-postgres psql -U postgres -d orm_d

psql-test:
	@docker exec -it orm-d-local-test-postgres psql -U postgres -d orm_d
