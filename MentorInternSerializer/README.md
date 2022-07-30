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

