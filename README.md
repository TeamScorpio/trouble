# Trouble
Trouble is a java library that can use enumeration to manage business exceptions.

## Specification By Examples

### Define a trouble enum
```java
public enum MyTroubleEnum implements TroubleEnum {

    @Trouble(code = "1", message = "trouble 1")
    TROUBLE_ONE,

    @Trouble(code = "2", message = "trouble 2")
    TROUBLE_TWO;

}
```

### throw trouble
```java
MyTroubleEnum.TROUBLE_ONE.thrown()
``` 
Or we can do like this
```java
throw MyTroubleEnum.TROUBLE_ONE.get()
```

Trouble is extended `RuntimeException`, so we don't need to mark throws on the method. 

### Print trouble stacktrace
```java
MyTroubleEnum.TROUBLE_ONE.printStackTrace()
```
It will print 
```

cn.teamscorpio.trouble.Trouble: trouble 1
	at cn.teamscorpio.trouble.TroubleHolder.get(TroubleHolder.java:34)
	at cn.teamscorpio.trouble.TroubleEnum.get(TroubleEnum.java:26)
	at cn.teamscorpio.trouble.TroubleEnum.printStackTrace(TroubleEnum.java:42)
	at cn.teamscorpio.trouble.TestTrouble.testPrintStackTrace(TestTrouble.java:42)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        ...
```

### Init cause
```java
MyTroubleEnum.TROUBLE_ONE.causeBy(new IOException("IO Failed")).printStackTrace()
```
It will print

```
cn.teamscorpio.trouble.Trouble: trouble 1
	at cn.teamscorpio.trouble.TroubleHolder.get(TroubleHolder.java:34)
	at cn.teamscorpio.trouble.TroubleEnum.causeBy(TroubleEnum.java:16)
	at cn.teamscorpio.trouble.TestTrouble.testPrintStackTrace(TestTrouble.java:42)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:686)
	at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
        ...
Caused by: java.io.IOException: IO Failed
	... 89 more
```

### Get Code and Message
```java
String code = MyTroubleEnum.TROUBLE_ONE.getCode();
String message = MyTroubleEnum.TROUBLE_ONE.getMesage();
```

### Code prefix
Sometimes we want to add a global code prefix, we can use `TroubleCodePrefix` annotation.
```java

@TroubleCodePrefix("10000")
public enum MyPrefixedTroubleEnum implements TroubleEnum {

    @Trouble(code = "1", message = "prefixed trouble 1")
    PREFIXED_TROUBLE_ONE,

}
```
The `getCode` method will return `100001`.

### Default trouble 
If we don't mark the `@trouble` on enumeration like this,
```java
public enum NoCodeTroubleEnum implements TroubleEnum {
    
    NO_CODE_TROUBLE;
    
}
```
the code will be assigned the field's ordinal and the message will be assigned the field's name. 

### HTTP support
We can get HTTP support through annotation `@HttpTrouble`.

```java
@HttpTrouble(status = HttpStatus.BAD_REQUEST)
public enum HttpTroubleEnum implements TroubleEnum {

    @Trouble(code = "0", message = "Bad request trouble")
    BAD_REQUEST_TROUBLE,

    @HttpTrouble(status = HttpStatus.BAD_GATEWAY)
    @Trouble(code = "1", message = "Bad gateway trouble")
    BAD_GATEWAY_TROUBLE;

}
```
And we can get http status like this:
```java
HttpStatus httpStatus = BAD_REQUEST_TROUBLE.getHttpStatus() 
```

When both class and field are annotated by `HttpTrouble`, the status is determined by the annotation of the field. 

