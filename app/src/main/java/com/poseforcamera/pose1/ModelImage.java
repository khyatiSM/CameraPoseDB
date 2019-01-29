
package com.poseforcamera.pose1;
public class ModelImage {

    private int pose_id;
    private String name;
    private Boolean front_camera;
    private String category;
    private String full_image;
    private String colour_image;
    private String skeleton;
    private int freqCount;
    private Boolean favourites;

    public ModelImage(int pose_id, String name, Boolean front_camera, String category, String full_image, String colour_image, String skeleton, int freqCount, Boolean favourites) {
        this.pose_id = pose_id;
        this.name = name;
        this.front_camera = front_camera;
        this.category = category;
        this.full_image = full_image;
        this.colour_image = colour_image;
        this.skeleton = skeleton;
        this.freqCount = freqCount;
        this.favourites = favourites;
    }


    public int getPose_id() {
        return pose_id;
    }

    public void setPose_id(int pose_id) {
        this.pose_id = pose_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFront_camera() {
        return front_camera;
    }

    public void setFront_camera(Boolean front_camera) {
        this.front_camera = front_camera;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFull_image() {
        return full_image;
    }

    public void setFull_image(String full_image) {
        this.full_image = full_image;
    }

    public String getColour_image() {
        return colour_image;
    }

    public void setColour_image(String colour_image) {
        this.colour_image = colour_image;
    }

    public String getSkeleton() {
        return skeleton;
    }

    public void setSkeleton(String skeleton) {
        this.skeleton = skeleton;
    }

    public int getFreqCount() {
        return freqCount;
    }

    public void setFreqCount(int freqCount) {
        this.freqCount = freqCount;
    }

    public Boolean getFavourites() {
        return favourites;
    }

    public void setFavourites(Boolean favourites) {
        this.favourites = favourites;
    }






}