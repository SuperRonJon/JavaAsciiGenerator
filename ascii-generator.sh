#! /usr/bin/env bash

if ! command -v java &>/dev/null; then
	echo "Java not installed... Please ensure that Java Runtime 8+ is installed and on PATH"
	exit 1
fi

SCRIPT_DIR=$(cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
java -jar $SCRIPT_DIR/ascii-generator.jar "$@"
