package com.bookelse.model.product;

import com.bookelse.model.id.Id;

public class ProductImage {
  private final Id imageId;
  private final String imageLabel;
  private final String alternateText;
  private final String imageLocation;

  private ProductImage(Id imageId, String imageLabel, String alternateText, String imageLocation) {
    this.imageId = imageId;
    this.imageLabel = imageLabel;
    this.alternateText = alternateText;
    this.imageLocation = imageLocation;
  }

  public static ProductImage createProductImageObject(
      Id imageId, String imageLabel, String alternateText, String imageLocation) {
    return new ProductImage(imageId, imageLabel, alternateText, imageLocation);
  }

  public Id getImageId() {
    return imageId;
  }

  public String getImageLocation() {
    return imageLocation;
  }

  public String getImageLabel() {
    return imageLabel;
  }

  public String getAlternateText() {
    return alternateText;
  }
}
