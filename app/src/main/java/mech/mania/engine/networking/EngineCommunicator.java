package mech.mania.engine.networking;

public final class EngineCommunicator {
    /**
     *
     */
    public static String readLine() {
        SafeBufferedReader reader = new SafeBufferedReader(System.in);
        String line = reader.readLine();
        return line;
    }

    /**
     *
     */
    public static void sendString(String string) {
        System.out.println(string);
    }
}
