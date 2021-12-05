#!/bin/sh
cd ~/se/node_modules

for d in */; do
	cd $d
	X=~/se/cr/$d
	Y=${X%?}
	cr -o $Y.json -f json .
	cd ..
done