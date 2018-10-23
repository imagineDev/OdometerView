# OdometerView

Old school Odometer view, can also be called as Analog Flip View or Flip Calendar View.
Easy to implement and cumtomize.
<br/><br/>

## Installation
Import the Library as a module in your Application, as show in the sample.

You can use the widget in your layout file:
```java 
 <com.imaginedev.odometerview.OdometerView
        android:id="@+id/odometer"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:reading="12345"
        />
``` 

<br/>
Update the reading dynamically:

```java
double value = 1234;
boolean withAnimate = true;
odometer.setValue(value, withAnimate);
```


<br/><br/>

## Screenshot
<img src="https://github.com/imagineDev/OdometerView/blob/master/screenshots/Screen1.png" width="300"/>
<img src="https://github.com/imagineDev/OdometerView/blob/master/screenshots/Screen%20GIF.gif" width="300"/>
