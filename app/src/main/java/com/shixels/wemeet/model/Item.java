package com.shixels.wemeet.model;

public class Item {
    private String timeString, descriptionString, countString,
            likesCountString,  sharesCountString,  commentsCountString,
            shareTypeString, companyNameString;
    private int imageId;

    public int getImageId() {
        return imageId;
    }

    public Item(String timeString, String descriptionString, String countString, String likesCountString,
                String sharesCountString, String commentsCountString, String shareTypeString, String companyNameString, int imageId) {
        this.timeString = timeString;
        this.descriptionString = descriptionString;
        this.countString = countString;
        this.likesCountString = likesCountString;
        this.sharesCountString = sharesCountString;
        this.commentsCountString = commentsCountString;
        this.shareTypeString = shareTypeString;
        this.companyNameString = companyNameString;
        this.imageId = imageId;
    }

    public String getTimeString() {
        return timeString;
    }

    public String getDescriptionString() {
        return descriptionString;
    }

    public String getCountString() {
        return countString;
    }

    public String getLikesCountString() {
        return likesCountString;
    }

    public String getSharesCountString() {
        return sharesCountString;
    }

    public String getCommentsCountString() {
        return commentsCountString;
    }

    public String getShareTypeString() {
        return shareTypeString;
    }

    public String getCompanyNameString() {
        return companyNameString;
    }
}
