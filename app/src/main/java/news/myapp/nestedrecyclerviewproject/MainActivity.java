package news.myapp.nestedrecyclerviewproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import news.myapp.nestedrecyclerviewproject.adapter.VerticalRecyclerAdapter;
import news.myapp.nestedrecyclerviewproject.model.MultiViewImageCategory;
import news.myapp.nestedrecyclerviewproject.model.SingleImageObject;

public class MainActivity extends AppCompatActivity implements VerticalRecyclerAdapter.CallbackInterface {

    FloatingActionButton floatingActionButton;
    Dialog dialog;
    RecyclerView mainRecyclerView;
    VerticalRecyclerAdapter mainRecyclerAdapter;
    TextView editTextTitle;
    List<MultiViewImageCategory> multiViewImageCategoryList = new ArrayList<>();;
    private int PICK_IMAGE_REQUEST = 22;
    Intent intent;
    private int CODE = 1;
    List<Bitmap> imageBitmaps;
    RecyclerView itemRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_layout);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button okay =dialog.findViewById(R.id.okButton);
        Button cancel = dialog.findViewById(R.id.cancelButton);
        editTextTitle = dialog.findViewById(R.id.editTextTitle);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Cancel --> "+editTextTitle.getText().toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "okay --> "+editTextTitle.getText().toString() , Toast.LENGTH_SHORT).show();
                dialog.dismiss();

                setMainCategoryRecycler(setOnDynamic2(editTextTitle.getText().toString()));
            }
        });

    }

    private void setMainCategoryRecycler(List<MultiViewImageCategory> multiViewImageCategoryList)
    {
        mainRecyclerView = findViewById(R.id.mainRecyclerView);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new VerticalRecyclerAdapter(this, multiViewImageCategoryList);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);
    }

    private void setMainCategoryRecycler2(List<MultiViewImageCategory> multiViewImageCategoryList)
    {
        mainRecyclerView = findViewById(R.id.mainRecyclerView);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new VerticalRecyclerAdapter(this, multiViewImageCategoryList);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);
    }


    private List<MultiViewImageCategory> setOnDynamic2(String text)
    {
        multiViewImageCategoryList.add(new MultiViewImageCategory(text));

        return multiViewImageCategoryList;
    }


    private List<MultiViewImageCategory> setOnDynamic(String text,List<Bitmap> imageBitmaps)
    {
        List<SingleImageObject> singleImageObjectList = new ArrayList<>();

        for(Bitmap bitmap : imageBitmaps)
            singleImageObjectList.add(new SingleImageObject(1,bitmap));

        multiViewImageCategoryList.add(new MultiViewImageCategory(text, singleImageObjectList));

        return multiViewImageCategoryList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        imageBitmaps = new ArrayList<>();

        if(resultCode != RESULT_CANCELED && data.getClipData() != null)
        {
            ClipData clipData = data.getClipData();

            //mutiple images selected
            if(clipData != null)
            {
                for(int i = 0 ; i < clipData.getItemCount() ; i++)
                {
                    Uri imageUri = clipData.getItemAt(i).getUri();

                    try {
                        InputStream inputStream = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageBitmaps.add(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            setMainCategoryRecycler2(setOnDynamic(editTextTitle.getText().toString(),imageBitmaps));
        }
        else//Single image selected
        {
            if(resultCode != RESULT_CANCELED && data.getData() != null)
            {
                Uri imageUri = data.getData();
                Log.i("ImageUri  ",imageUri.toString());
                if(imageUri != null)
                {
                    InputStream inputStream = null;
                    try {
                        inputStream = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        Log.i("Bitmap--> ",bitmap.toString());
                        imageBitmaps.add(bitmap);
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                setMainCategoryRecycler2(setOnDynamic(editTextTitle.getText().toString(),imageBitmaps));
            }
        }
    }


    @Override
    public void forImageSelected(Intent intent) {
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }
}