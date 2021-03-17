package news.myapp.nestedrecyclerviewproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import news.myapp.nestedrecyclerviewproject.R;
import news.myapp.nestedrecyclerviewproject.model.MultiViewImageCategory;
import news.myapp.nestedrecyclerviewproject.model.SingleImageObject;

public class VerticalRecyclerAdapter extends RecyclerView.Adapter<VerticalRecyclerAdapter.VerticalRecyclerViewHolder> {

    private Context context;
    private List<MultiViewImageCategory> multiViewImageCategoryList;
    private final int PICK_IMAGE_REQUEST = 22;
    CallbackInterface callbackInterface;

    public interface CallbackInterface{
        public void forImageSelected(Intent intent);
    }

    public VerticalRecyclerAdapter(Context context, List<MultiViewImageCategory> multiViewImageCategoryList) {
        this.context = context;
        this.multiViewImageCategoryList = multiViewImageCategoryList;
    }

    @NonNull
    @Override
    public VerticalRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VerticalRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.horizontalrecycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalRecyclerViewHolder holder, int position) {
        holder.categoryTitle.setText(multiViewImageCategoryList.get(position).getTitle());
        setRecycler(holder.itemRecycler, multiViewImageCategoryList.get(position).getSingleImageObjectList());

        holder.galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);

                ((Activity) context).startActivityForResult(
                        Intent.createChooser(
                                intent,
                                "Select Image from here..."),
                        PICK_IMAGE_REQUEST);

                if(callbackInterface != null)
                    callbackInterface.forImageSelected(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return multiViewImageCategoryList.size();
    }


    public class VerticalRecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView categoryTitle;
        RecyclerView itemRecycler;
        ImageButton galleryButton;
        public VerticalRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            galleryButton = itemView.findViewById(R.id.galleryButton);
            categoryTitle = itemView.findViewById(R.id.cat_title);
            itemRecycler = itemView.findViewById(R.id.item_recycler);
        }
    }


    private void setRecycler(RecyclerView recyclerView, List<SingleImageObject> singleImageObjects)
    {
        HorizontalRecyclerAdapter horizontalRecyclerAdapter = new HorizontalRecyclerAdapter(context, singleImageObjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(horizontalRecyclerAdapter);
    }

}

