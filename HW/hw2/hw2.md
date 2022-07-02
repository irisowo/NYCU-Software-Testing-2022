# Hw2
## [a]
* List all of the input variables including the state variables.
    * 1. capacity
        * the input of constructor, which signifies the queue's capacity
    * 2. X
        * the input of enQueue(), which is if the element type of the queue(that is, object o).
    * 3. state
        * state can be considered as the state of the queue's iterator
    * Summary 
| |variables|
| -------- |------- |
| input varables : | capacity, X | 
| state varables : | state | 

## [b]
* Define the characteristics of the input variables. 
    * should cover all input variables.

|Method      |Params        |Returns|Values|Exception               |ChID |Characteristic| Covered by|
|------------|--------------|-------|------|------------------------|-----|--------------|----|
|BoundedQueue|int capacity  |       |      |                        | C1  | Constructor  |    |
|-           |              |       |      |                        | C2  | If argument is less than 0 ||
|-           |              |       |      |IllegalArgumentException|     | capacity < 0 | C2 |
|enQueue     |Object o      |       |      |                        | C3  |Make o the newest element of the queue||
|-           |              |       |      |NullPointerException    | C4  |If argument is null||
|-           |              |       |      |IllegalStateException   |     |queue is full | C7 |
|deQueue     |state         |element generic|Object o|              | C5  |remove and return oldest element of Queue||
|-           |              |       |      |IllegalStateException   |     |queue is empty| C6 |
|isEmpty     |state         |boolean| T, F  |                       | C6  |queue is empty|    |
|isFull      |state         |boolean| T, F  |                       | C7  |queue is full |    |



## [c]
* Partition the characteristics into blocks. 
    * Designate one block in each partition as the "Base" block. 

|ID|Characteristic                         |BoundedQueue(int capacity)|enQueue(Object o)|deQueue()|isEmpty()|isFull()|
|-|-|-|-|-|-|-| 
|C1|Constructor                            |     V      |       |       |       |      |
|C2|If argument is less than 0             |     V      |   V   |   V   |   V   |  V   |
|C3|Make o the newest element of the queue |            |   V   |       |       |      |
|C4|If argument is null                    |            |   V   |       |       |      |
|C5|Remove and return oldest element of queue|          |       |   V   |       |      |
|C6|If queue is empty                      |            |       |   V   |   V   |      |
|C7|If queue is full                       |            |   V   |       |       |  V   |
 
## [d]
* Define values for each block.

| ID  |   | Values |
|---|---|--|
|C1 |Constructor|T|
|C2 |If argument is less than 0|T, F|
|C3 |Make o the newest element of the queue|T, F|
|C4 |If argument is null|T, F|
|C5 |Remove and return oldest element of queue|T, F|
|C6 |If queue is empty|T, F|
|C7 |If queue is full|T, F|

## [e]
*  Define a test set that satisfies Base Choice Coverage (BCC). Write your tests with the values from the previous step. Be sure to include the test oracles. 

| Method  | Characteristic | Base  | Test Requirements | Infeasible TRs |Revised TRs| # of TRs |
|---------|----------------|-------|-------------------|----------------|-----------|----------|
|BoundedQueue|  C1 C2      | {TF}  | {TF, TT}          |                |           |2|
|  enQueue   | C2 C3 C4 C7 | {FTFF} | {FTFF, TTFF, FFFF, FTTF, FTFT} |{TTFF, FFFF, FTTF, FTFT} |FFFF -> FTFF(重複), FTTF -> FFTF, FTFT -> FFFT|3|
|  deQueue   |  C2 C5 C6   | {FTF} | {FTF, TTF, FFF, FTT} |{TTF, FFF, FTT}|FFF -> FTF(重複), FTT -> FFT|2|
|  isEmpty   |  C2 C6      | {FF}  | {FF, TF, FT}      |{TF}            |           |2|
|  isFull    |  C2 C7      | {FF}  | {FF, TF, FT}      |{TF}            |           |2|

* Summary

| Method  | Characteristic | Base  | Test Requirements | # of TRs |
|---------|-----------------|------|-------------------|----------|
|BoundedQueue|  C1 C2       | {TF} | {TF, TT}          |2|
|  enQueue   |  C2 C3 C4 C7 | {FTFF} | {FTFF, FFTF, FFFT} |3|
|  deQueue   |  C2 C5 C6    | {FTF} | {FTF, FFT}       |2|
|  isEmpty   |  C2 C6       | {FF}  | {FF, FT}         |2|
|  isFull    |  C2 C7       | {FF}  | {FF, FT}         |2|
