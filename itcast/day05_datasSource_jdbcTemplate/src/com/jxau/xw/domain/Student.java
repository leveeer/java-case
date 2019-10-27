package com.jxau.xw.domain;

    /**
     * 封装student表数据的JavaBean
     */
    public class Student {
        private Integer id;
        private String name;
        private Integer age;
        private String sex;
        private String address;
        private Integer math;
        private Integer english;

        public Student() {
        }

        public Student(Integer id, String name, Integer age, String sex, String address, Integer math, Integer english) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.address = address;
            this.math = math;
            this.english = english;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getMath() {
            return math;
        }

        public void setMath(Integer math) {
            this.math = math;
        }

        public Integer getEnglish() {
            return english;
        }

        public void setEnglish(Integer english) {
            this.english = english;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", sex='" + sex + '\'' +
                    ", address='" + address + '\'' +
                    ", math=" + math +
                    ", english=" + english +
                    '}';
        }
    }


