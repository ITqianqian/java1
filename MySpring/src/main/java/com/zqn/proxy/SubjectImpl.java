package com.zqn.proxy;

/**
 * Created by dell on 2017/1/11.
 */
public class SubjectImpl implements Subject {
    public void save() {
        System.out.println("subjectImpl.save...");

    }

    public void update() {
        System.out.println("subjectImpl.update...");
    }
}
