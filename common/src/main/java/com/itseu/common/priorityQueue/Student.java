package com.itseu.common.priorityQueue;

public class Student implements Priority{
    private String name;
    /**
     * 返回对象的优先级，数字越大优先级越高
     *  以学生名的开头首字母作为优先级数字
     * @return 优先级
     */
    @Override
    public int priority() {
        return name.charAt(0)-'a';
    }
    public Student(String name){
        this.name = name;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     * @apiNote In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * The string output is not necessarily stable over time or across
     * JVM invocations.
     * @implSpec The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     */
    @Override
    public String toString() {
        return "Student = "+ name;
    }
}
