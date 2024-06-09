package com.bookelse.model.id;

import java.util.Objects;
import java.util.StringJoiner;

public abstract class Id {

  protected final String id;

  protected Id() {
    this.id = generateId();
  }

  protected Id(String id) {
    this.id = id;
  }

  protected abstract String generateId();

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Id.class.getSimpleName() + "[", "]")
        .add("id='" + id + "'")
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Id id1 = (Id) o;
    return Objects.equals(getId(), id1.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }
}
