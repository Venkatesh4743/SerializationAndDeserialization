package SerializationAndDeserialization;

import java.io.*;

// A simple class that implements Serializable
class Student implements Serializable {
    private static final long serialVersionUID = 1L; // Unique identifier for version control
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}

public class SerializationDemo {
    public static void main(String[] args) {
        Student student = new Student("Alice", 20);

        // Create a FileOutputStream to write data to a file named "student.ser"
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("student.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Wrap the FileOutputStream in an ObjectOutputStream to handle object serialization
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Serialize the 'student' object and write it to the file using the ObjectOutputStream
        try {
            objectOutputStream.writeObject(student);
            System.out.println("Student object serialized and saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the ObjectOutputStream (automatically closes the underlying FileOutputStream)
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the FileOutputStream
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialization process (not shown in the previous code segment)
        try {
            // Create a FileInputStream to read data from the file "student.ser"
            FileInputStream fileInputStream = new FileInputStream("student.ser");

            // Wrap the FileInputStream in an ObjectInputStream to handle object deserialization
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // Deserialize the object from the file using the ObjectInputStream
            Student deserializedStudent = (Student) objectInputStream.readObject();
            System.out.println("Student object deserialized from file.");
            System.out.println("Deserialized Student: " + deserializedStudent);

            // Close the ObjectInputStream (automatically closes the underlying FileInputStream)
            objectInputStream.close();

            // Close the FileInputStream
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
