package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    private final List<String> startTime = new ArrayList<>();
    private final List<String> stopTime = new ArrayList<>();

    public void unavailable(String source, String target) {
        readLog(source);
        writeSelectedLog(target);
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.log");
    }

    private void readLog(String source) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String line;
            boolean isOffline = false;
            while ((line = in.readLine()) != null) {
                LogEntry logEntry = getLogEntry(line);
                if (logEntry == null) continue;
                if (logEntry.getStatus() >= 400 && !isOffline) {
                    isOffline = true;
                    startTime.add(logEntry.getTime());
                }
                if (logEntry.getStatus() < 400 && isOffline) {
                    isOffline = false;
                    stopTime.add(logEntry.getTime());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LogEntry getLogEntry(String line) {
        if (line.isBlank()) return null;
        LogEntry rsl = null;
        try {
            String[] record = line.split("\\s++");
            int status = Integer.parseInt(record[0]);
            rsl = new LogEntry(status, record[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void writeSelectedLog(String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (int i = 0; i < startTime.size(); i++) {
                out.println(startTime.get(i) + ";" + stopTime.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class LogEntry {
        private final int status;
        private final String time;

        public LogEntry(int status, String time) {
            this.status = status;
            this.time = time;
        }

        public int getStatus() {
            return status;
        }

        public String getTime() {
            return time;
        }
    }
}
