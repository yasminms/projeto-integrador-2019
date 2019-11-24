package ifsul.com.br.notes.services;

import ifsul.com.br.notes.domain.AuthRequest;
import ifsul.com.br.notes.domain.AuthResponse;
import ifsul.com.br.notes.domain.UserDTO;
import ifsul.com.br.notes.domain.UserRegisterRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("public/register")
    Call<UserDTO> register(@Body UserRegisterRequest request);

    @POST("public/auth")
    Call<AuthResponse> auth(@Body AuthRequest request);
}
