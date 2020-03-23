package com.company;

import java.util.Comparator;

public class Student {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     *
     * @return a comparator with compares students by their name in natural order.
     */
    public static Comparator<Student> nameComparator() {
        return new NameComparator();
    }
    /**
     *
     * @return a comparator with compares students by their age in natural order.
     */
    public static Comparator<Student> ageComparator() {
        return new AgeComparator();
    }
    /**
     *
     * @return a comparator with compares students by their name in natural order and when names
     * and when the names are the same, then their age .
     */
    public static Comparator<Student> nameAndAgeComparator() {
        return new NameAndAgeComparator();
    }

    /**
     * The comparator with compares students by their name in natural order
     */
    private static class NameComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    /**
     * The comparator with compares students by their age in natural order
     */
    private static class AgeComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getAge() - o2.getAge();
        }
    }
    /**
     * The comparator with compares students by their name in natural order and when names
     * and when the names are the same, then their age .
     */
    private static class NameAndAgeComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            if (o1.getName().compareTo(o2.getName()) == 0) {
                return o1.getAge() - o2.getAge();
            }
            return o1.getName().compareTo(o2.getName());
        }
    }
}
