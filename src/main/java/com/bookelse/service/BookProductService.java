package com.bookelse.service;

import com.bookelse.dao.BookProductDAO;
import com.bookelse.model.id.BookId;
import com.bookelse.model.id.ProductId;
import com.bookelse.model.product.Book;
import com.bookelse.model.values.BigDecimalValue;
import com.bookelse.payload.product.NewBookProductAddRequestPayload;
import com.bookelse.util.datetime.DateTimeUtility;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("BookProductService")
public class BookProductService extends ProductService
    implements ProductServiceIN<NewBookProductAddRequestPayload, Book> {

  private final BookProductDAO bookProductDAO;

  public BookProductService(
      @Qualifier("BookProductDAO") BookProductDAO productDAO,
      @Qualifier("BookProductDAO") BookProductDAO bookProductDAO) {
    super(productDAO);
    this.bookProductDAO = bookProductDAO;
  }

  @Override
  public Book addProduct(NewBookProductAddRequestPayload productRequestPayload) {
    Book book = buildProduct(productRequestPayload);
    return bookProductDAO.addProduct(book);
  }

  private Book buildProduct(NewBookProductAddRequestPayload productRequestPayload) {
    Book.Builder<?> bookBuilder = Book.builder();
    if (StringUtils.isNotBlank(productRequestPayload.getProductName())) {
      bookBuilder.addProductName(productRequestPayload.getProductName());
    }
    if (StringUtils.isNotBlank(productRequestPayload.getProductMRP())) {
      bookBuilder.addProductMRP(BigDecimalValue.valueOf(productRequestPayload.getProductMRP()));
    }
    if (StringUtils.isNotBlank(productRequestPayload.getProductDiscount())) {
      bookBuilder.addProductDiscount(
          BigDecimalValue.valueOf(productRequestPayload.getProductDiscount()));
    }
    if (productRequestPayload.getProductFeatures() != null
        && !productRequestPayload.getProductFeatures().isEmpty()) {
      for (Map.Entry<String, String> entry :
          productRequestPayload.getProductFeatures().entrySet()) {
        String productFeatureKey = entry.getKey();
        String productFeature = entry.getValue();
        if (StringUtils.isNotBlank(productFeatureKey) && StringUtils.isNotBlank(productFeature)) {
          bookBuilder.addProductFeature(productFeatureKey, productFeature);
        }
      }
    }
    if (StringUtils.isNotBlank(productRequestPayload.getBookName())) {
      bookBuilder.addBookName(productRequestPayload.getBookName());
    }
    if (StringUtils.isNotBlank(productRequestPayload.getBookAuthor())) {
      bookBuilder.addBookAuthor(productRequestPayload.getBookAuthor());
    }
    if (StringUtils.isNotBlank(productRequestPayload.getPublisher())) {
      bookBuilder.addPublisher(productRequestPayload.getPublisher());
    }
    if (productRequestPayload.getPublishedOn() != null) {
      bookBuilder.addPublishedOn(DateTimeUtility.getCurrentUTCDateTime());
    }
    bookBuilder
        .addCreatedOnDate(DateTimeUtility.getCurrentUTCDateTime())
        .addUpdatedOn(DateTimeUtility.getCurrentUTCDateTime())
        .addProductId(new ProductId())
        .addBookId(new BookId());
    return bookBuilder.build();
  }
}
