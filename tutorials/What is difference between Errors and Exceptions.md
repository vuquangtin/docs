# What is difference between Errors and Exceptions

>An Error "indicates serious problems that a reasonable application should not try to catch."

while

>An Exception "indicates conditions that a reasonable application might want to catch."

Error along with RuntimeException & their subclasses are unchecked exceptions. All other Exception classes are checked exceptions.

<b>Checked</b> exceptions are generally those from which a program can recover & it might be a good idea to recover from such exceptions programmatically. Examples include FileNotFoundException, ParseException, etc. A programmer is expected to check for these exceptions by using the try-catch block or throw it back to the caller

On the other hand we have <b>unchecked</b> exceptions. These are those exceptions that might not happen if everything is in order, but they do occur. Examples include ArrayIndexOutOfBoundException, ClassCastException, etc. Many applications will use try-catch or throws clause for RuntimeExceptions & their subclasses but from the language perspective it is not required to do so. Do note that recovery from a RuntimeException is generally possible but the guys who designed the class/exception deemed it unnecessary for the end programmer to check for such exceptions.

<b>Errors</b> are also unchecked exception & the programmer is not required to do anything with these. In fact it is a bad idea to use a try-catch clause for Errors. Most often, recovery from an Error is not possible & the program should be allowed to terminate. Examples include OutOfMemoryError, StackOverflowError, etc.

Do note that although Errors are unchecked exceptions, we shouldn't try to deal with them, but it is ok to deal with RuntimeExceptions(also unchecked exceptions) in code. Checked exceptions should be handled by the code.




Errors should not be caught or handled (except in the rarest of cases). Exceptions are the bread and butter of exception handling. The Javadoc explains it well:

>An Error is a subclass of Throwable that indicates serious problems that a reasonable application should not try to catch. Most such errors are abnormal conditions.

Look at a few of the subclasses of Error, taking some of their JavaDoc comments:

 * AnnotationFormatError - Thrown when the annotation parser attempts to read an annotation from a class file and determines that the annotation is malformed.
 * AssertionError - Thrown to indicate that an assertion has failed.
 * LinkageError - Subclasses of LinkageError indicate that a class has some dependency on another class; however, the latter class has incompatibly changed after the compilation of the former class.
 * VirtualMachineError - Thrown to indicate that the Java Virtual Machine is broken or has run out of resources necessary for it to continue operating.

There are really three important subcategories of Throwable:

 * Error - Something severe enough has gone wrong the most applications should crash rather than try to handle the problem,
 * Unchecked Exception (aka RuntimeException) - Very often a programming error such as a NullPointerException or an illegal argument. Applications can sometimes handle or recover from this Throwable category -- or at least catch it at the Thread's run() method, log the complaint, and continue running.
 * Checked Exception (aka Everything else) - Applications are expected to be able to catch and meaningfully do something with the rest, such as FileNotFoundException and TimeoutException...
 
 
@see <a href='https://stackoverflow.com/questions/912334/differences-between-exception-and-error'>https://stackoverflow.com/questions/912334/differences-between-exception-and-error</a>

