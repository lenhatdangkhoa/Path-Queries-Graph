cp:
	javac -d bin p4/src/Graph.java
	javac -cp bin -d bin p4/src/Driver.java
run : cp
	java -cp bin p4.src.Driver input.txt
clean:
	rm -rf bin/p4
