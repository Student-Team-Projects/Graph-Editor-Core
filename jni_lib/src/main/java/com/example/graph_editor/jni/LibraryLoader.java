package com.example.graph_editor.jni;

import java.io.*;
import java.net.URL;

public class LibraryLoader {
    public static String getExt() {
        String osName = System.getProperty("os.name");
        if (osName.equals("Linux"))
            return "so";
        else if (osName.equals("Mac OS X"))
            return "dylib";
        else
            return "dll";
    }

    public static Boolean load(Class<?> cls, String name) {
        System.err.println(1);
        String path = "/lib" + name + "." + getExt();
        URL url = cls.getResource(path);
        System.err.println(2);
        Boolean success = false;
        try {
            final File libfile = File.createTempFile(name, ".lib");
            libfile.deleteOnExit();
            System.err.println(3);
            final InputStream in = url.openStream();
            final OutputStream out = new BufferedOutputStream(new FileOutputStream(libfile));
            System.err.println(4);
            int len = 0;
            byte[] buffer = new byte[8192];
            System.err.println(5);
            while ((len = in.read(buffer)) > -1)
                out.write(buffer, 0, len);
            System.err.println(6);
            out.close();
            in.close();
            System.err.println(7);
            System.load(libfile.getAbsolutePath());
            success = true;
            System.err.println(8);
        } catch (Exception e) {
            System.err.println(9);
        }
        System.err.println(10);
        return success;
    }
}