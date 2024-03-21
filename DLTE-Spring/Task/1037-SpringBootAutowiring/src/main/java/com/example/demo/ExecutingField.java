package com.example.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExecutingField {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.scan("com.example.demo");
        applicationContext.refresh();
        Bank myBank=applicationContext.getBean(Bank.class);
        System.out.println(myBank.callFindAll());
    }

}
