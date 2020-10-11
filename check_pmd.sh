#!/bin/bash
set -ev
file="server/target/site/pmd.html"
if [ -f "$file" ]
then
	echo "$file found."
    exit 1
else
	echo "$file not found."
	  exit 0
fi