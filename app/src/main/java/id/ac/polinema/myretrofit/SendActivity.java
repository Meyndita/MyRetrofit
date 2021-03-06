package id.ac.polinema.myretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendActivity extends AppCompatActivity {
    private EditText inputNama, inputAlamat, inputTelp;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // tambahkan pengaturan button
        Button btn = findViewById(R.id.btn_tambah);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNama = findViewById(R.id.edt_nama);
                inputAlamat = findViewById(R.id.edt_alamat);
                inputTelp = findViewById(R.id.edt_telp);

                //variabel untuk menyimpan data yang didapat dari form tambah data
                String nama = inputNama.getText().toString();
                String alamat = inputAlamat.getText().toString();
                radioGroup = findViewById(R.id.group_jk);
                RadioButton selected = findViewById(radioGroup.getCheckedRadioButtonId());
                String jenis_kelamin = selected.getText().toString();
                String no_telp = inputTelp.getText().toString();

                //ini kode jika data yang diisi kosong
                if (TextUtils.isEmpty(nama) && TextUtils.isEmpty(alamat) && TextUtils.isEmpty(jenis_kelamin) && TextUtils.isEmpty(no_telp)) {
                    Toast.makeText(getApplicationContext(), "Fill the field", Toast.LENGTH_SHORT).show();
                } else {
                    //jika tidak kosong, maka retrofit akan diinstansiasi ke base url
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://meyndita.000webhostapp.com/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

                    //kemudian mengisi parameternya ke body sesuai data yang dibutuhkan
                    Call<ResponseBody> call = jsonPlaceHolderApi.setPost(
                            nama,
                            alamat,
                            jenis_kelamin,
                            no_telp
                    );

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                // jika request succes maka akan menuju MainActivity untuk ditampilkan datanya menggunakan intent
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
