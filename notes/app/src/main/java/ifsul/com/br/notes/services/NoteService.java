package ifsul.com.br.notes.services;

import java.util.List;

import ifsul.com.br.notes.domain.AuthRequest;
import ifsul.com.br.notes.domain.AuthResponse;
import ifsul.com.br.notes.domain.NoteDTO;
import ifsul.com.br.notes.domain.UserDTO;
import ifsul.com.br.notes.domain.UserRegisterRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NoteService {

    @GET("note")
    Call<List<NoteDTO>> findAll(@Header("Authorization") String token);

}
