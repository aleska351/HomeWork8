package com.company;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Comparator<Student> pcomp = Student.nameComparator();
        Student s1 = new Student("Alex", 29);
        Student s2 = new Student("Alexa", 25);
        Student s3 = new Student("Alex", 27);
        Student s4 = new Student("Oleg", 25);
        Student s5 = new Student("Inna", 14);
        ArrayList<Student> list = new ArrayList();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        System.out.println("Сортировка по имени");

        Collections.sort(list, Student.nameComparator());

        for (Student s : list) {
            System.out.println(s.getName() + " " + s.getAge());
        }
        System.out.println("________________________________________________________________________");
        System.out.println("Сортировка по возрасту");

        Collections.sort(list, Student.ageComparator());
        for (Student s : list) { System.out.println(s.getName() + " " + s.getAge()); }

        System.out.println("________________________________________________________________________");
        System.out.println("Сортировка по имени и если имя одинаковое - то по возрасту");

        Collections.sort(list, Student.nameAndAgeComparator());
        for (Student s : list) { System.out.println(s.getName() + " " + s.getAge()); }
        System.out.println("________________________________________________________________________");

        System.out.println("Сортировка по имени в анонимном классе");
        Comparator<Student> comparatorName = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
        };
        Collections.sort(list, comparatorName);
        for (Student s : list) {
            System.out.println(s.getName() + " " + s.getAge());
        }
        System.out.println("________________________________________________________________________");

        System.out.println("Сортировка по возрасту");
        Comparator<Student> comparatorAge = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getAge() - s2.getAge();
            }
        };
        Collections.sort(list, comparatorAge);
        for (Student s : list) {
            System.out.println(s.getName() + " " + s.getAge());
        }
        System.out.println("________________________________________________________________________");
        System.out.println("Сортировка по имени и если имя одинаковое - то по возрасту");
        Comparator<Student> comparatorNameAndAge = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1.getName().compareTo(s2.getName()) == 0) {
                    return s1.getAge() - s2.getAge();
                }
                return s1.getName().compareTo(s2.getName());
            }
        };

        Collections.sort(list, comparatorNameAndAge);
        for (Student s : list) {
            System.out.println(s.getName() + " " + s.getAge());
        }
        System.out.println("________________________________________________________________________");
        IStudentStringConverter converterJson = new IStudentStringConverter() {
            @Override
            public String convert(ArrayList<Student> student) {
                StringBuilder builder = new StringBuilder();
                for (Student s : student) {
                    builder.append("{").append("\n");
                    builder.append("\t");
                    builder.append("\"name\"").append(":").append("\"").append(s.name).append("\"").append(",").append("\n");
                    builder.append("\t");
                    builder.append("\"age\"").append(":").append(s.age).append(",").append("\n");
                    builder.append("}").append("\n").append("\n");
                }
                return builder.toString();
            }
        };
        IStudentStringConverter converterXml = new IStudentStringConverter() {
            @Override
            public String convert(ArrayList<Student> student) {
                StringBuilder builder = new StringBuilder();
                for (Student s : student) {
                    builder.append("<student>").append("\n");
                    builder.append("\t");
                    builder.append("<name>").append(s.name).append("</name>").append("\n");
                    builder.append("\t");
                    builder.append("<age>").append(s.age).append("</age>").append("\n");
                    builder.append("</student>").append("\n").append("\n");
                }
                return builder.toString();
            }
        };
        IStudentStringConverter converterIni = new IStudentStringConverter() {
            @Override
            public String convert(ArrayList<Student> student) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Student s : student) {
                    stringBuilder.append("name").append("=").append(s.name).append("\n");
                    stringBuilder.append("age").append("=").append(s.age).append("\n").append("\n");
                }
                return stringBuilder.toString();
            }
        };

        IStudentPrinter printerJsonConsole = new IStudentPrinter() {
            @Override
            public void print(ArrayList<Student> students) {
                String s = converterJson.convert(students);
                System.out.println(s);
            }
        };
        IStudentPrinter printerIniConsole = new IStudentPrinter() {
            @Override
            public void print(ArrayList<Student> students) {
                String s = converterIni.convert(students);
                System.out.println(s);
            }
        };
        IStudentPrinter printerXmlConsole = new IStudentPrinter() {
            @Override
            public void print(ArrayList<Student> students) {
                String s = converterXml.convert(students);
                System.out.println(s);
            }
            };

        printerJsonConsole.print(list);
        printerIniConsole.print(list);
        printerXmlConsole.print(list);
    }
}

