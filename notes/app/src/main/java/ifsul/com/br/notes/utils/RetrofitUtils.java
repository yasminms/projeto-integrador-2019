package ifsul.com.br.notes.utils;


import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitUtils {

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8090/api/integrador/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

}
