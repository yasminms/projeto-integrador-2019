package ifsul.com.br.notes.services;

import ifsul.com.br.notes.domain.AuthRequest;
import ifsul.com.br.notes.domain.AuthResponse;
import ifsul.com.br.notes.domain.UserDTO;
import ifsul.com.br.notes.domain.UserRegisterRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserService {

    @POST("public/register")
    Call<UserDTO> register(@Body UserRegisterRequest request);

    @POST("public/auth")
    Call<AuthResponse> auth(@Body AuthRequest request);

    @DELETE("user")
    Call<Void> delete(@Header("Authorization") String token);

    @PUT("user")
    Call<Void> update(@Header("Authorization") String token, @Query("name") String name);
}
