package com.bookelse.model.product;

import com.bookelse.model.id.ProductId;
import com.bookelse.model.values.BigDecimalValue;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Product {
  protected final ProductId productId;
  protected final String productName;
  protected final BigDecimalValue productMRP;
  protected final BigDecimalValue productDiscount;
  protected final BigDecimalValue productNetPrice;
  protected final List<ProductImage> productImages;
  protected final ProductFeatures productFeatures;
  protected final Integer quantity;
  protected final ZonedDateTime createdOn;
  protected final ZonedDateTime updatedOn;

  protected Product(Builder<?> builder) {
    this.productId = builder.productId;
    this.productFeatures = builder.productFeatures;
    this.productNetPrice = builder.productNetPrice;
    this.productName = builder.productName;
    this.productMRP = builder.productMRP;
    this.productImages = builder.productImages;
    this.productDiscount = builder.productDiscount;
    this.quantity = builder.quantity;
    this.createdOn = builder.createdOn;
    this.updatedOn = builder.updatedOn;
  }

  public String getProductName() {
    return productName;
  }

  public BigDecimalValue getProductMRP() {
    return productMRP;
  }

  public BigDecimalValue getProductDiscount() {
    return productDiscount;
  }

  public BigDecimalValue getProductNetPrice() {
    return productNetPrice;
  }

  public List<ProductImage> getProductImages() {
    return productImages;
  }

  public ProductFeatures getProductFeatures() {
    return productFeatures;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public ZonedDateTime getCreatedOn() {
    return createdOn;
  }

  public ZonedDateTime getUpdatedOn() {
    return updatedOn;
  }

  public ProductId getProductId() {
    return productId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(getProductId(), product.getProductId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getProductId());
  }

  public abstract static class Builder<B extends Builder<B>> {
    protected Integer quantity;
    protected ZonedDateTime createdOn;
    protected ZonedDateTime updatedOn;
    private String productName;
    private BigDecimalValue productMRP;
    private BigDecimalValue productDiscount;
    private BigDecimalValue productNetPrice;
    private List<ProductImage> productImages;
    private ProductFeatures productFeatures;
    private ProductId productId;

    public B addProductId(ProductId productId) {
      this.productId = productId;
      return self();
    }

    protected abstract B self();

    public B addProductName(String productName) {
      this.productName = productName;
      return self();
    }

    public B addProductMRP(BigDecimalValue productMRP) {
      this.productMRP = productMRP;
      calculateProductNetPrice();
      return self();
    }

    private void calculateProductNetPrice() {
      if (productDiscount != null && productMRP != null) {
        productNetPrice = productMRP.subtractValue(productMRP.percentage(productDiscount));
      }
    }

    public B addProductDiscount(BigDecimalValue productDiscount) {
      this.productDiscount = productDiscount;
      calculateProductNetPrice();
      return self();
    }

    public B addCreatedOnDate(ZonedDateTime createdOn) {
      this.createdOn = createdOn;
      return self();
    }

    public B addUpdatedOn(ZonedDateTime updatedOn) {
      this.updatedOn = updatedOn;
      return self();
    }

    public B addProductNetPrice(BigDecimalValue productNetPrice) {
      this.productNetPrice = productNetPrice;
      return self();
    }

    public B setProductImages(List<ProductImage> productImages) {
      this.productImages = productImages;
      return self();
    }

    public B setProductFeatures(ProductFeatures productFeatures) {
      this.productFeatures = productFeatures;
      return self();
    }

    public B addProductFeature(String featureName, String featureDescription) {
      if (productFeatures == null) productFeatures = new ProductFeatures();
      productFeatures.addFeature(featureName, featureDescription);
      return self();
    }

    public B setQuantity(Integer quantity) {
      this.quantity = quantity;
      return self();
    }

    public B addProductImage(ProductImage productImage) {
      if (productImages == null) productImages = new ArrayList<>();
      productImages.add(productImage);
      return self();
    }
  }
}
