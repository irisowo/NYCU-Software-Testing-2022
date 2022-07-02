## Environment
### Mac
```bash
$MYPATH = bypath
export CLASSPATH=$CLASSPATH:$MYPATH/mujava.jar:$MYPATH/openjava.java:$MYPATH/hamcrest-core-1.3.jar:$MYPATH/junit-4.12.jar
source ~/.bash_profile
```

## Run
```bash
cd testset
javac CalTest.java ../src/Cal.java
cd ..
java mujava.gui.GenMutantsMain

// Before executing the next line, please copy the Cal.class from src to classes, and then 
// remove the Cal.class in src 
java mujava.gui.RunTestMain

```

## Run test again 
```bash
rm -f testset/CalTest.class
rm -f src/Cal.class
javac testset/CalTest.java src/Cal.java
java mujava.gui.RunTestMain
```