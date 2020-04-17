package id.ac.polinema.myretrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    //disini terdapat @GET("siswa") artinya kita mengambil data siswa
    @GET("siswa")
    Call<List<Post>> getPost();

    //terdapat tambahan untuk mengambil data siswa berdasarkan id nya
    @GET("siswa")
    Call<List<Post>> getPostById(
            @Query("nis") String nis
    );

    //lalu disini terdapat @POST("siswa") artinya kita menambahkan data siswa
    @FormUrlEncoded
    @POST("siswa")
    Call<ResponseBody> setPost(
            //terdapat 4 field yang perlu diisi, id_siswa tidak perlu ditambahkan karena sudah auto increment
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("no_telp") String no_telp
    );
}
