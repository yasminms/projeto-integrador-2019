package ifsul.com.br.notes.utils;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

public class UserUtils {

    private static final String SHARED_FILE = "mynotes";
    private static final String TOKEN = "token";

    public static String getToken(final Context context) {
        return context.getSharedPreferences(SHARED_FILE, MODE_PRIVATE).getString(TOKEN, null);
    }

}
