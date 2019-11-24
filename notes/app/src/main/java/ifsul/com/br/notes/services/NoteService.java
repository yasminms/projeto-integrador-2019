package ifsul.com.br.notes.services;

import java.util.List;

import ifsul.com.br.notes.domain.NoteDTO;
import ifsul.com.br.notes.domain.NoteRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NoteService {

    @GET("note")
    Call<List<NoteDTO>> findAll(@Header("Authorization") String token);

    @POST("note")
    Call<Void> save(@Header("Authorization") String token, @Body NoteRequest request);

    @PUT("note/{id}")
    Call<Void> update(@Header("Authorization") String token, @Path("id") Integer id, @Body NoteRequest request);

    @DELETE("note/{id}")
    Call<Void> delete(@Header("Authorization") String token, @Path("id") Integer id);

}