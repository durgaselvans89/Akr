-injars dist\ADMIN.jar
-outjars ADMIN_Out.jar

-libraryjars lib\org.eclipse.swt.win32.win32.x86_3.3.3.v3349.jar
-libraryjars 'C:\Program Files\Java\jdk1.6.0_07\jre\lib\rt.jar'
-libraryjars lib\org.eclipse.ui.forms_3.3.0.v20070511.jar
-libraryjars lib\poi-2.5.1.jar

-useuniqueclassmembernames
-dontusemixedcaseclassnames
-adaptresourcefilenames **.properties,**.jpg,**.png
-adaptresourcefilecontents **.properties,META-INF/MANIFEST.MF


-keep,allowshrinking class hm.akr.dto.* {
    public private <fields>;
    public private static final <methods>;
}

-keep,allowshrinking class hm.akr.exceptions.* {
    public private <fields>;
    public private static final <methods>;
}

# Keep - Applications. Keep all application classes, along with their 'main'
# methods.
-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}

# Also keep - Serialization code. Keep all fields and methods that are used for
# serialization.
-keepclassmembers class * extends java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Also keep - Bean classes. Keep all specified classes, along with their getters
# and setters.
-keep class hm.akr.dto.* {
    void set*(***);
    void set*(int,***);
    boolean is*();
    boolean is*(int);
    *** get*();
    *** get*(int);
}

# Keep names - Native method names. Keep all native class/method names.
-keepclasseswithmembers,allowshrinking class * {
    native <methods>;
}

# Keep names - _class method names. Keep all .class method names. This may be
# useful for libraries that will be obfuscated again with different obfuscators.
-keepclassmembers,allowshrinking class * {
    java.lang.Class class$(java.lang.String);
    java.lang.Class class$(java.lang.String,boolean);
}
