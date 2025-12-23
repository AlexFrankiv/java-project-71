.PHONY: run-dist help test lint build

run-dist:
	./build/install/app/bin/app
help:
	@./build/install/app/bin/app -h
test:
	./gradlew test
build:
	./gradlew clean build
lint:
	./gradlew checkstyleMain