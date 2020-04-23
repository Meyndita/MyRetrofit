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

    // @GET kita menggunakan QueryMap, sedangkan @POST menggunakan FieldMap untuk tempat parameters yang akan ditembakkan ke endpoint.
    // @FormUrlEncoded ditambahkan informasi mime type application/x-www-form-urlencoded pada header ketika kita melakukan HTTP POST request.
    // lalu disini terdapat @POST("siswa") artinya kita menambahkan data siswa
    @FormUrlEncoded
    @POST("siswa")
    Call<ResponseBody> setPost(
            // @Field digunakan untuk pengisian data
            // id_siswa tidak perlu ditambahkan (auto increment)
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("no_telp") String no_telp
    );
}
