package ifsul.com.br.notes.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class UserUtils {

    private static final String SHARED_FILE = "mynotes";
    private static final String TOKEN = "token";
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    public static String getToken(final Context context) {
        return context.getSharedPreferences(SHARED_FILE, MODE_PRIVATE).getString(TOKEN, null);
    }

    public static String getEmail(final Context context) {
        return context.getSharedPreferences(SHARED_FILE, MODE_PRIVATE).getString(EMAIL, null);
    }

    public static String getName(final Context context) {
        return context.getSharedPreferences(SHARED_FILE, MODE_PRIVATE).getString(NAME, null);
    }

    public static void logout(final Context context) {

        final SharedPreferences.Editor edit = context.getSharedPreferences(SHARED_FILE, MODE_PRIVATE).edit();
        edit.remove(TOKEN);
        edit.remove(NAME);
        edit.remove(EMAIL);

        edit.commit();
    }

}
