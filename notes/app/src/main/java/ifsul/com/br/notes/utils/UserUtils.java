package ifsul.com.br.notes.utils;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

public class UserUtils {

    private static final String SHARED_FILE = "mynotes";
    private static final String TOKEN = "token";

    public static boolean isAuthenticated(final Context context) {
        return context.getSharedPreferences(SHARED_FILE, MODE_PRIVATE).contains(TOKEN);
    }

}
