#! /usr/bin/env bash

if ! command -v java &>/dev/null; then
	echo "Java not installed... Please ensure that Java Runtime 8+ is installed and on PATH"
	exit 1
fi

JAR_DIR="/usr/lib/ascii-generator"
java -jar $JAR_DIR/ascii-generator.jar "$@"
