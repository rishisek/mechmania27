package mech.mania.engine.networking;

import mech.mania.engine.api.Config;

import java.io.IOException;
import java.io.InputStreamReader;

public final class EngineCommunicator {
    /**
     *
     */
    public static Config gameConfig;

    public static String readLine() throws IOException {
        SafeBufferedReader reader = new SafeBufferedReader(new InputStreamReader(System.in), gameConfig.PLAYER_TIMEOUT);
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
