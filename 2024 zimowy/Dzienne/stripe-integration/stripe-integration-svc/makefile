# CLOJURE
.PHONY: clean compile jar install

ccjl: clean compile jar install

clean:
	@git rev-parse HEAD > resources/.commit_hash
	@clojure -T:build clean
	@rm -rf ./.cpcache/

compile:
	@clojure -T:build compile-clj

install:
	@clojure -T:build install

jar:
	@clojure -T:build jar

javac:
	@clojure -T:build javac

kaocha-it:
	@clojure -M:kaocha --skip acceptance-test

kaocha:
	@clojure -M:kaocha --skip acceptance-test --skip integration-test

uberjar:
	@git rev-parse HEAD > resources/.commit_hash
	@clojure -T:build uberjar

clj-kondo:
	@clj-kondo --config .clj-kondo/config.edn --lint  src/
	@clj-kondo --config .clj-kondo/config.edn --lint test/
cloc:
	@cloc . --exclude-list-file=cloc.excluded
