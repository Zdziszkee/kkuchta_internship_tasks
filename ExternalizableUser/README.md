Task 3
Giving the following class

```
public class User {
// bad code is for the sake of the task, do not do it in real life
private final boolean isActive;
private final boolean isAdmin;
private final boolean isModerator;
private final boolean isVIP;
private final boolean isMuted;
private final boolean isBanned;
// ...
}
```

Using Java Serialization API to serialize the object to a byte array. Check the size of the byte array
Let’s try to save some bytes during serialization:
Implement Externalizable instead of Serializable
Implement the serialization for boolean flags as a single byte
Serialize the object again and check the size, compare it to the standard serialization
Add another class to reproduce the cyclic reference example from the Serialization task 1

CONCLUSIONS:
Byte array size using Serializable: 132.
Byte array size using Externalizable: 75.

Byte array using Serializable with cyclic reference: 1338
Byte array using Externalizable with cyclic reference: 1285