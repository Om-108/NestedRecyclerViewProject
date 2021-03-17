package news.myapp.nestedrecyclerviewproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import news.myapp.nestedrecyclerviewproject.R;
import news.myapp.nestedrecyclerviewproject.model.SingleImageObject;

public class HorizontalRecyclerAdapter extends RecyclerView.Adapter<HorizontalRecyclerAdapter.HoriRecyclerViewHolder> {

    private Context context;
    private List<SingleImageObject> singleImageObjectList;

    public HorizontalRecyclerAdapter(Context context, List<SingleImageObject> singleImageObjectList) {
        this.context = context;
        this.singleImageObjectList = singleImageObjectList;
    }

    @NonNull
    @Override
    public HoriRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HoriRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.singleimage, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HoriRecyclerViewHolder holder, int position) {

        holder.imageView.setImageBitmap(singleImageObjectList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        if(singleImageObjectList == null)
            return 0;
        return singleImageObjectList.size();
    }

    public static final class HoriRecyclerViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageView;
        public HoriRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.single_image);
        }
    }
}
