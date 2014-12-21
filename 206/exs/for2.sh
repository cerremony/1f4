#!/usr/local/bin/bash
i=$1
weekdays="Mon Tue Wed Thu Fri"
for day in "$weekdays"
do
	i++
done
	echo "Weekday $i : $day"
