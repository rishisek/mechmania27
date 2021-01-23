package mech.mania.engine.networking;

public class Logger {
    // TODO: refactor into static functions?

    public Logger() { }

    public void info(String log) {
        System.err.println("info: " + log);
    }

    public void debug(String log) {
        System.err.println("debug: " + log);
    }
}
