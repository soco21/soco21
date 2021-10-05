#!/usr/bin/bash
set -e
set -x

SCRIPT_DIR=$(dirname $(realpath $0))

#use redirection for the loop to prevent to prevent subshell from pipe
#https://stackoverflow.com/questions/9612090/how-to-loop-through-file-names-returned-by-find
modules=""
while read -d $'\0' file
do
  file=$(echo $file | sed "s|$SCRIPT_DIR||")
  modules="$modules<module>$file</module>\n"
done < <(find $SCRIPT_DIR -type f -name pom.xml -print0)

sed "s|<!-- placeholder -->|$modules|" $SCRIPT_DIR/reactor.pom.template > $SCRIPT_DIR/reactor.pom
