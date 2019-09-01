
if [ -z "$1" ] || [ -z "$2" ]; then
	echo "Usage: $0 <input file> <output file prefix>"
	exit 1
fi

INPUT=$1
OUTPUT=$2

javac -d . *.java
jar cvfe HW1.jar HW1 *.class

java -jar HW1.jar $1 $2
