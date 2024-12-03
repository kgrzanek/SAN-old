package edu.san;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class MyEntity extends PanacheEntityBase {

  @Id
  @GeneratedValue
  private Long id;

  @Version
  private short version;

  private String field;

  public final long getId() {
    return id;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  @Override
  public final int hashCode() {
    return Long.hashCode(getId());
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof MyEntity))
      return false;
    MyEntity other = (MyEntity) obj;
    return getId() == other.getId();
  }

}
