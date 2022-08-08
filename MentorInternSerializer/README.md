Task 1
Giving the following classes

```
public class Mentor {
private final String name;
private final String password;
private List<Intern> interns;
/// ...
}
```

```
public class Intern {
private final String name;
private final String password;
private final Mentor mentor;
// ...
}
```

Create Mentor object with 3 Intern objects
Make sure to set list of interns to Mentor and mentor to each Intern
Don’t override hashCode for these objects
Use Java Serialization API to write Mentor object to file and read it back
Compare contents of the original object and the object which was read from the file. Compare field values and hash codes of objects before and after serialization.
In the object read from file, compare hashCode of mentor in every Intern object. What does it mean?
Modify a code to skip serialization of password field for both classes due to security reasons

Note that Serialization was able to resolve cyclic references in a correct way.


CONCLUSIONS

Mentor name equality: true  ``` serialized and deserialized values are the same just as expected ```

Mentor interns equality: false ``` we did not override equals methods, so deafult equals implementation is used that's why interns are not equal (new instances are created when deserializing) ```

Mentor hashcodes equality: false ``` we are depending on default hascode implementation, which means if objects are equal hashcode must be the same```

Intern mentor's hashcodes:

1528902577
1528902577
1528902577

``` hashcode is the same for all interns which means all interns have the same mentor ```