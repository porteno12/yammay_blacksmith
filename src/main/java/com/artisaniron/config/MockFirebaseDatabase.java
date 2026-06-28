package com.artisaniron.config;

import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MockFirebaseDatabase {
    public static FirebaseDatabase create() {
        return (FirebaseDatabase) Proxy.newProxyInstance(
                FirebaseDatabase.class.getClassLoader(),
                new Class[]{FirebaseDatabase.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String methodName = method.getName();
                        if (methodName.equals("getReference")) {
                            return MockDatabaseReference.create();
                        }
                        if (methodName.equals("getInstance")) {
                            return proxy;
                        }
                        return null;
                    }
                }
        );
    }
}

