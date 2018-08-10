package com.classloader;

public class MyClassLoader extends ClassLoader{

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
	}

	private byte[] loadClassData(String name) {
		return null;
	}

}
