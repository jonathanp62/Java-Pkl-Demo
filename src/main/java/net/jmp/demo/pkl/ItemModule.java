package net.jmp.demo.pkl;

import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.List;
import java.util.Objects;
import org.pkl.config.java.mapper.Named;
import org.pkl.config.java.mapper.NonNull;

public final class ItemModule {
  public final @NonNull List<@NonNull Item> items;

  public ItemModule(@Named("items") @NonNull List<@NonNull Item> items) {
    this.items = items;
  }

  public ItemModule withItems(@NonNull List<@NonNull Item> items) {
    return new ItemModule(items);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (this.getClass() != obj.getClass()) return false;
    ItemModule other = (ItemModule) obj;
    if (!Objects.equals(this.items, other.items)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + Objects.hashCode(this.items);
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(100);
    builder.append(ItemModule.class.getSimpleName()).append(" {");
    appendProperty(builder, "items", this.items);
    builder.append("\n}");
    return builder.toString();
  }

  private static void appendProperty(StringBuilder builder, String name, Object value) {
    builder.append("\n  ").append(name).append(" = ");
    String[] lines = Objects.toString(value).split("\n");
    builder.append(lines[0]);
    for (int i = 1; i < lines.length; i++) {
      builder.append("\n  ").append(lines[i]);
    }
  }

  public static final class Item {
    public final @NonNull String name;

    public final long itemNumber;

    public Item(@Named("name") @NonNull String name, @Named("itemNumber") long itemNumber) {
      this.name = name;
      this.itemNumber = itemNumber;
    }

    public Item withName(@NonNull String name) {
      return new Item(name, itemNumber);
    }

    public Item withItemNumber(long itemNumber) {
      return new Item(name, itemNumber);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (this.getClass() != obj.getClass()) return false;
      Item other = (Item) obj;
      if (!Objects.equals(this.name, other.name)) return false;
      if (!Objects.equals(this.itemNumber, other.itemNumber)) return false;
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + Objects.hashCode(this.name);
      result = 31 * result + Objects.hashCode(this.itemNumber);
      return result;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder(150);
      builder.append(Item.class.getSimpleName()).append(" {");
      appendProperty(builder, "name", this.name);
      appendProperty(builder, "itemNumber", this.itemNumber);
      builder.append("\n}");
      return builder.toString();
    }
  }
}
