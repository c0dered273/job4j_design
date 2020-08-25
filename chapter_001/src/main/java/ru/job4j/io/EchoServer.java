package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        boolean isListen = true;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (isListen) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        String msg = getMessage(str);
                        if (msg.equals("Bye")) {
                            isListen = false;
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }

    public static String getMessage(String header) {
        String rsl = "";
        if (header.startsWith("GET")) {
            String[] h = header.split("\\s++");
            int paramPos = h[1].indexOf("msg=");
            if (paramPos != -1) {
                rsl = h[1].substring(paramPos + 4);
            }
        }
        return rsl;
    }
}