package com.example.listviewdemo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextAge;
    Button button, btnImageSelect;
    ListView listView;
    MyAdapter adapter;

    String imagePath;
    Lang lang;
    List<Lang> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        button = findViewById(R.id.button);
        btnImageSelect = findViewById(R.id.btnImageSelect);
        listView = findViewById(R.id.list);

        System.out.println("тестовое сообщение");
        int a = 7684;
        Log.w("777", "любой нужный текст" + a);

        list = new ArrayList<>();
        lang = new Lang();
//        lang.name = "Java";
//        lang.age = 30;
//        lang.image = R.drawable.java;
//        list.add(lang);
//        lang = new Lang();
//        lang.name = "Python";
//        lang.age = 29;
//        lang.image = R.drawable.python;
//        list.add(lang);


        adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);

        btnImageSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Выберите картинку"), 1);

            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lang lang = new Lang();
                String strName = editTextName.getText().toString();
                if (!strName.isEmpty()){
                    lang.name = strName;
                }
                String strAge = editTextName.getText().toString();
                if (!strAge.isEmpty()){
                    lang.age = Integer.parseInt(editTextAge.getText().toString());
                }

                adapter.notifyDataSetChanged();


            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                adapter.notifyDataSetChanged();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 1){
                Uri selectedImageUri = data.getData();
                lang.uri = selectedImageUri;
                list.add(lang);
             //   imagePath = getPath(selectedImageUri);
            }
        }
    }

//    public String getPath(Uri uri) {
//        // just some safety built in
//        if( uri == null ) {
//            // TODO perform some logging or show user feedback
//            return null;
//        }
//        // try to retrieve the image from the media store first
//        // this will only work for images selected from gallery
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//        if( cursor != null ){
//            int column_index = cursor
//                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            String path = cursor.getString(column_index);
//            cursor.close();
//            return path;
//        }
//        // this is our fallback here
//
//        return uri.getPath();
//    }
}