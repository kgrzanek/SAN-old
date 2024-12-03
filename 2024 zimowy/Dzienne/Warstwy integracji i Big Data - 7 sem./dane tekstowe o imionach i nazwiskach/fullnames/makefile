# DOCKER
DC=docker-compose

docker-up:
	@echo "Starting all containers..."
	@$(DC) up -d

docker-down:
	@echo "Stopping all containers..."
	@$(DC) down

docker-ps:
	@$(DC) ps

docker-clean:
	@echo "Removing all containers and volumes..."
	@$(DC) down -v

# POSTGRES FOR LOCAL DEV
local-postgres-up:
	@echo "Starting local PostgreSQL..."
	@$(DC) up -d telsos-local-postgres

local-postgres-down:
	@echo "Stopping local PostgreSQL..."
	@$(DC) stop telsos-local-postgres

local-postgres-logs:
	@$(DC) logs -f telsos-local-postgres

local-psql:
	@docker exec -it telsos-local-postgres psql -U postgres -d telsos

# POSTGRES FOR LOCAL TESTS
local-test-postgres-up:
	@echo "Starting test PostgreSQL..."
	@$(DC) up -d telsos-local-test-postgres

local-test-postgres-down:
	@echo "Stopping test PostgreSQL..."
	@$(DC) stop telsos-local-test-postgres

local-test-postgres-logs:
	@$(DC) logs -f telsos-local-test-postgres

local-test-psql:
	@docker exec -it telsos-local-test-postgres psql -U postgres -d telsos

# CLOJURE
.PHONY: clean compile jar install

ccjl:                 clean compile jar install
clean:              ; @clojure -T:build clean ; rm -rf ./.cpcache/
compile:            ; @clojure -T:build compile-clj
install:            ; @clojure -T:build install
jar:                ; @clojure -T:build jar
javac:              ; @clojure -T:build javac
kaocha-it:          ; @clojure -M:kaocha --skip acceptance-test
kaocha:             ; @clojure -M:kaocha --skip acceptance-test --skip integration-test
uberjar:            ; @clojure -T:build uberjar

clj-kondo:
	@clj-kondo --config .clj-kondo/config.edn --lint  src/
	@clj-kondo --config .clj-kondo/config.edn --lint test/
cloc:
	@cloc . --exclude-list-file=cloc.excluded
