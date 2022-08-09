Task 2
Giving the following class
```
public class Client {
private final String name;
private final String password;
private final Integer balance;
// ...
}
```

Create serialVersionUUID static field
Create an object and serialize it to the file
Now add a field private final Integer age
Remove the part of the code which writes to the file, and try to read the file, which was written before the new field was added. What is the value of the new field? Does it correspond to the value which was set in the constructor?
Try to rename field name to username and read the same file from step (2). What is the value of the field?
Try to remove field password from the class and read the same file from step (2)
Try to rename change the type of field balance to Double and read the same file from step (2)
In which cases you should have changed serialVersionUUID?

Note: as the task assumes continuous modification of the same class, please capture results of each step as a comment in the code or in a separate markdown file

CONCLUSIONS:

EOFException is thrown when trying to read empty file.
If we add field and try to read older version of object, null or default values will be assigned to it.
Changing field name and deserializing old version of object is going to result in null or default value in renamed field.
Which means we should serialVersionUUID when we add or rename fields.
When we change type of the field, and try to deserialize ClassCastException is going to be thrown.