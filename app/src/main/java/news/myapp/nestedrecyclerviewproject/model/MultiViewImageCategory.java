package news.myapp.nestedrecyclerviewproject.model;

import java.util.List;

public class MultiViewImageCategory {

    String title;
    List<SingleImageObject> singleImageObjectList;


    public MultiViewImageCategory(String title, List<SingleImageObject> singleImageObjectList) {
        this.title = title;
        this.singleImageObjectList = singleImageObjectList;
    }

    public MultiViewImageCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SingleImageObject> getSingleImageObjectList() {
        return singleImageObjectList;
    }

    public void setSingleImageObjectList(List<SingleImageObject> singleImageObjectList) {
        this.singleImageObjectList = singleImageObjectList;
    }
}
