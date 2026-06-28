package com.artisaniron.config;

import com.google.firebase.database.DatabaseReference;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MockDatabaseReference {
    public static DatabaseReference create() {
        return (DatabaseReference) Proxy.newProxyInstance(
                DatabaseReference.class.getClassLoader(),
                new Class[]{DatabaseReference.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String methodName = method.getName();
                        if (methodName.equals("child") || methodName.equals("getReference")) {
                            return MockDatabaseReference.create();
                        }
                        if (methodName.equals("push")) {
                            return MockDatabaseReference.create();
                        }
                        if (methodName.equals("setValue") || methodName.equals("removeValue") ||
                            methodName.equals("orderByChild") || methodName.equals("equalTo") ||
                            methodName.equals("addListenerForSingleValueEvent")) {
                            return proxy;
                        }
                        if (methodName.equals("getKey")) {
                            return "mock-key";
                        }
                        return null;
                    }
                }
        );
    }
}
