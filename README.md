# FinishEditText
Android library with custom Edit-text view purpose is to provide functionality to listen when user finished typing. Implement an interface to listen when user stop typing in edit text or finish typing in edit text.

## Attribute
### edit_interval (in Milliseconds)
You can set custom time interval if user start typing and then he do not enter any character after this interval finish callback will be trigger.

**Default interval =700 ms**

you can set in XML like this
```
app:edit_interval="1000"
```
in Java you can set and get this attribute by calling these function respectively
```
finishEditText.setEditIntervel(300);

finishEditText.getEditIntervel();
```


<br>

<img src="https://raw.githubusercontent.com/themhsami/FinishEditText/master/screenshot/Screenshot.png" align="center" height="500"  alt="FinishEditText screenshot" >

## How to integrate it
### 1. Gradle :
**Step 1**
Add it in your project root build.gradle
```
allprojects {
  repositories {
		...
    maven { url 'https://jitpack.io' }
  }
}
```

**Step 2**
Add the dependency in app level build.gradle
```
dependencies {
  compile 'com.github.themhsami:FinishEditText:1'
}
```
<br>

### 2. Maven
```
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```
  **Add the dependency**
```
<dependency>
   <groupId>com.github.themhsami</groupId>
	 <artifactId>FinishEditText</artifactId>
   <version>1</version>
</dependency>
```
  <br>
  
  ## Usage examples
  **XML**
```
<com.github.themhsami.libfinishedittext.FinishEditText
    android:id="@+id/finishEditText1"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Start typing for on finish typing listen"
    android:textColor="@android:color/black"
    android:textColorHint="@color/text_hint"
    app:edit_interval="1000" />
  ```
  
  <br>
  
  **JAVA**
```
finishEditText1.setEditIntervel(600);

finishEditText1.addFinishEditingListener(new FinishEditingListener() {
    @Override
    public void onEditingFinished() {
        Log.d("interval", finishEditText1.getEditIntervel() + "");
    }
});
```
