/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;

import java.util.List;

public abstract class IncCommandOption<V> {

  protected final String name;
  protected final String title;

  public IncCommandOption(String name) {
    this.name = name;
    this.title = null;
  }

  public IncCommandOption(String name, String title) {
    this.name = name;
    this.title = title;
  }

  public String getName() {
    return name;
  }

  public String getTitle() {
    return title;
  }

  public abstract V parseValue(String key);

  public static class Str extends IncCommandOption<String> {

    public Str(String name) {
      super(name);
    }

    public Str(String name, String title) {
      super(name, title);
    }

    @Override
    public String parseValue(String key) {
      return key;
    }
  }

  public static class Int extends IncCommandOption<Integer> {

    public Int(String name) {
      super(name);
    }

    public Int(String name, String title) {
      super(name, title);
    }

    @Override
    public Integer parseValue(String key) {
      return Integer.parseInt(key);
    }
  }

  public static class Bool extends IncCommandOption<Boolean> {

    public Bool(String name) {
      super(name);
    }

    public Bool(String name, String title) {
      super(name, title);
    }

    @Override
    public Boolean parseValue(String key) {
      return key.equalsIgnoreCase("true") || key.equalsIgnoreCase("yes");
    }
  }

  public abstract static class IncList<T> extends IncCommandOption<List<T>> {


    public IncList(String name) {
      super(name);
    }

    public IncList(String name, String title) {
      super(name, title);
    }
  }

  public static class StringIncList extends IncList<String> {

    public StringIncList(String name) {
      super(name);
    }

    public StringIncList(String name, String title) {
      super(name, title);
    }

    @Override
    public List<String> parseValue(String key) {
      return List.of(key);
    }
  }

  public static class IntIncList extends IncList<Integer> {

    public IntIncList(String name) {
      super(name);
    }

    public IntIncList(String name, String title) {
      super(name, title);
    }

    @Override
    public List<Integer> parseValue(String key) {
      return List.of(Integer.parseInt(key));
    }
  }
}
