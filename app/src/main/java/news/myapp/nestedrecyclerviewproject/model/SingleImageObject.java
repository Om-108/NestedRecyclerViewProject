package news.myapp.nestedrecyclerviewproject.model;

import android.graphics.Bitmap;

public class SingleImageObject {

    int titleId;
    Bitmap imageUrl;


    public SingleImageObject(int titleId, Bitmap imageUrl) {
        this.titleId = titleId;
        this.imageUrl = imageUrl;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public Bitmap getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Bitmap imageUrl) {
        this.imageUrl = imageUrl;
    }
}
