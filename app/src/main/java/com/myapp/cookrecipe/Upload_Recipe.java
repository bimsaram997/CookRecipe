package com.myapp.cookrecipe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class Upload_Recipe extends AppCompatActivity {
    Uri uri;
    ImageView recipeImage;
    EditText txt_name, txt_description,txt_price, txt_link;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__recipe);
        recipeImage = (ImageView)findViewById(R.id.iv_foodImage);

        txt_name = (EditText)findViewById(R.id.txt_recipe_name);
        txt_description = (EditText)findViewById(R.id.text_description);
        txt_price = (EditText)findViewById(R.id.text_price);
        txt_link = (EditText)findViewById(R.id.text_link);

    }

    public void btnSelectImage(View view) {

        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker, 1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){

            uri = data.getData();
            recipeImage.setImageURI(uri);

        }else Toast.makeText(this, "You haven't pick image", Toast.LENGTH_SHORT).show();
    }

    public void uploadImage(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("gs://cookrecipe-8ed0c.appspot.com/").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl=  urlImage.toString();
                uploadRecipe();
                alert();

                Toast.makeText(Upload_Recipe.this, "Recipe Uploaded", Toast.LENGTH_SHORT);

            }
        });
    }

    public void btnUploadRecipe(View view) {

        uploadImage();
    }
    public void alert(){
  
    }

    public void uploadRecipe(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe uploaded...");

        FoodData foodData = new FoodData(
                txt_name.getText().toString(),
                txt_description.getText().toString(),
                txt_price.getText().toString(),
                txt_link.getText().toString(),
                imageUrl
        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("cookrecipe-8ed0c-default-rtdb").child(myCurrentDateTime).setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if (task.isSuccessful()){
                    Toast.makeText(Upload_Recipe.this, "Recipe Uploaded", Toast.LENGTH_SHORT);
                    progressDialog.dismiss();
                    finish();

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_Recipe.this, e.getMessage().toString(), Toast.LENGTH_SHORT);
                progressDialog.dismiss();
            }
        });
    }
}