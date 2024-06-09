package com.bookelse.model.product;

public class RentalBook extends Book {
  private final Depreciation depreciation;

  protected RentalBook(Builder<?> builder) {
    super(builder);
    this.depreciation = builder.depreciation;
  }

  public static Builder<?> builder() {
    return new RentalBookBuilder();
  }

  public Depreciation getDepreciation() {
    return depreciation;
  }

  public abstract static class Builder<B extends Builder<B>> extends Book.Builder<B> {

    private Depreciation depreciation;

    public B addDepreciation(Depreciation depreciation) {
      this.depreciation = depreciation;
      return self();
    }

    public RentalBook build() {
      return new RentalBook(this);
    }
  }

  private static class RentalBookBuilder extends Builder<RentalBookBuilder> {
    @Override
    protected RentalBookBuilder self() {
      return this;
    }
  }
}
