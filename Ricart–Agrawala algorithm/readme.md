#### How many messages are sent before all nodes have entered the critical section?
#### <105, p1>, <100, p2>, <100, p3>, p4
```
P1 -> request p2, p3, p4      3 messages  
P2 -> request p1, p3, p4      3 messages  
P3 -> request p1, p2, p4      3 messages  

P1 -> okay p2, p3             2 messages  
P3 -> okay p2                 1 message  
P4 -> okay p1, p2, p3         3 messages  

P2 -> okay p1, p3             2 messages  

P3 -> okay p1                 1 message  
In total:  18 messages
```
#### Consider a group of distributed systems P1, P2, P3 and P4 that share an object. They use the Ricart-Agrawala algorithm for management of mutual exclusion. P1 is currently in the critical section and there is no other node in the "wanted" state. Now consider requests from P4, P2 and P3 (in that order) to enter the same CS.
#### * Show the state (as required by the algorithm, i.e. "held", "wanted", etc.) and queue entries at each processor.
```
P1: state: held; Q = {P4, P2, P3}
P2: state: wanted; Q = {P3}
P3: state: wanted; Q = {}
P4: state: wanted; Q = {P2, P3}
```
#### * Now, P1 exits the CS and informs all relevant nodes that CS is released. Show the state and queue entries at each processor, at this stage.
```
P1: state: released; Q = {}
P2: state: wanted; Q = {P3}
P3: state: wanted; Q = {}
P4: state: held; Q = {P2, P3}
```
#### Ricart-Agrawala's distributed mutual exclusion algorithm uses logical clocks.
```
True. It uses lamport clock timestamps to decide whether or not a node should respond to a request.
```
