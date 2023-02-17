package com.example.graph_editor.jni;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;

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
        boolean success = false;
        try {
            final File libfile = File.createTempFile(name, ".lib");
            libfile.deleteOnExit();
            if (url == null) {
                return false;
            }
            final InputStream in = url.openStream();
            final OutputStream out = new BufferedOutputStream(Files.newOutputStream(libfile.toPath()));
            int len;
            byte[] buffer = new byte[8192];
            while ((len = in.read(buffer)) > -1)
                out.write(buffer, 0, len);
            out.close();
            in.close();
            System.load(libfile.getAbsolutePath());
            success = true;
        } catch (Exception ignored) {}
        return success;
    }
}